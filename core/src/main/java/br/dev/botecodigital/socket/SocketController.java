package br.dev.botecodigital.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import br.dev.botecodigital.socket.exception.InvalidScoketCommandException;
import br.dev.botecodigital.socket.exception.SocketException;

public class SocketController {

    private static SocketController instance;
    private int port = 9999;

    private ServerSocket socketServer;
    private BufferedReader input;
    private PrintWriter out;
    private Json json;

    private final ConcurrentLinkedQueue<SocketCommandRequest> queue = new ConcurrentLinkedQueue<>();

    private Status status;

    private String clientIP = "";
    private String clientUsername = "";
    private Socket client;
    private Thread threadListening;

    private SocketListener onConnectedAction;
    private SocketListener onDisconnectedAction;
    private SocketListener onStartListenningAction;

    private Thread serverThread;



    public enum Status{
        WAITING,
        CONNECTECTED,
        DISCONNECTED
    }

    private SocketController(){
        this.json = new Json();
        this.json.setOutputType(OutputType.json);
    }

    public static SocketController getInstance(){
        if(instance == null){
            instance = new SocketController();
        }
        return instance;
    }


    public void startServer() {
        this.serverThread = new Thread( ()  -> {
            ServerSocketHints serverHints = new ServerSocketHints();
            serverHints.acceptTimeout = 0; // infinite
            socketServer = Gdx.net.newServerSocket(Protocol.TCP, "0.0.0.0", this.port, serverHints);
            
            this.startListening();

        });
        this.serverThread.start();
    }

    public void shutdownServer() { 
        this.queue.clear();
        this.status = Status.DISCONNECTED;
        this.clientIP = "";
        this.clientUsername = "";
        
        if(this.client != null) {
            this.client.dispose();
            this.client = null;
        }
        this.threadListening.interrupt();
        this.serverThread.interrupt();
        this.socketServer.dispose();
        
    }

    private void startListening(){
        threadListening = new Thread( ()  -> {
            this.status = Status.WAITING;
            Gdx.graphics.setTitle("Aguardando conexão...");

            if(this.onStartListenningAction != null) this.onStartListenningAction.handle();
            
            SocketHints socketHints = new SocketHints();
            try{
                this.client = socketServer.accept(socketHints);
            }catch(GdxRuntimeException e){
                Gdx.app.log("SOCKET_CONTROLLER", "erro no accept");
            }
            input = new BufferedReader( new InputStreamReader(client.getInputStream() ) );
            out = new PrintWriter(client.getOutputStream(), true);

            try{
                
                processSetUsernameCommand();
                
                this.status = Status.CONNECTECTED;
                if(this.onConnectedAction != null) this.onConnectedAction.handle();
                
                String commandString;
                SocketCommandRequest command;
                while ( (commandString = input.readLine()) != null) {

                    try{
                        command = this.parseCommand(commandString);
                    }catch(InvalidScoketCommandException e){
                        send(SocketCommandResponse.error("SOCKET_INVALID_COMMAND", e.getMessage()));
                        continue;
                    }

                    queue.add( command );
                }
                this.disconnect();
            }catch(java.net.SocketException e){
                Gdx.app.log("SOCKET_CONTROLLER", "O cliente desconectou abruptamente");
                this.disconnect();
            }catch(IOException e){
                e.printStackTrace();
                this.disconnect();
            }catch(SocketException e){
                e.printStackTrace();
                this.send(SocketCommandResponse.error("SYSTEM_USERNAME_NOT_SET","Username not set"));
                this.disconnect();
            }
        });
        threadListening.start();
    }

    public SocketCommandRequest readCommand(){
        if(this.hasCommand()){
            return  queue.remove();
        }
        return null;
    }

    public boolean hasCommand(){
        return queue.size() != 0;
    }

    public void disconnect(){
        if(this.onDisconnectedAction != null) this.onDisconnectedAction.handle();
        this.queue.clear();
        this.status = Status.DISCONNECTED;
        this.clientIP = "";
        this.clientUsername = "";
        if(this.client != null )this.client.dispose();
        this.client = null;
        this.threadListening.interrupt();
        Gdx.graphics.setTitle("RoboCo");
    }

    private SocketCommandRequest parseCommand(String jsoString) throws InvalidScoketCommandException{
        Gdx.app.log("SOCKET_CONTROLLER", "Comando recebido: "+jsoString);
        try{
            return json.fromJson(SocketCommandRequest.class, jsoString);
        }catch(SerializationException e){
            throw new InvalidScoketCommandException("invalid command");
        }
    }

    private void processSetUsernameCommand() throws IOException, SocketException {
        String commandString = input.readLine();

        try{
            SocketCommandRequest command = this.parseCommand(commandString);
            if(command.command == SocketCommandRequest.Command.SYSTEM_SETUSERNAME){
                this.clientIP = this.client.getRemoteAddress();
                this.clientUsername = command.getValue();
                this.send(
                    SocketCommandResponse.success("SYSTEM_USERNAME_SETTED","Username \""+this.clientUsername+"\" foi configurado.")
                );
            }else{
                throw new SocketException("Username not set");
            }
        }catch(InvalidScoketCommandException e){
            send(SocketCommandResponse.error("SOCKET_INVALID_COMMAND", e.getMessage()));
        }
    }

    public void send(SocketCommandResponse message){
        Gdx.app.log("SOCKET_CONTROLLER", this.json.toJson(message));
        out.println(this.json.toJson(message));
        out.flush();
    }

    public Status getStatus(){
        return this.status;
    }
    public String getClientIP(){
        return this.clientIP;
    }
    public String getUsername(){
        return clientUsername;
    }

    public boolean isConnected(){
        return this.client != null;
    }

    public void setOnConnectedAction(SocketListener socketListener){
        this.onConnectedAction = socketListener;
    }
    public void setOnDisconnectedAction(SocketListener socketListener){
        this.onDisconnectedAction = socketListener;
    }
    public void setOnStartListenning(SocketListener socketListener) {
        this.onStartListenningAction = socketListener;
    }
    

    public void dispose() {
        this.client.dispose();
        this.threadListening.interrupt();
        this.socketServer.dispose();
    }

    public int getPort() {
        return this.port;
    }



    

}
