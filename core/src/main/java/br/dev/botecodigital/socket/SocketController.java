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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import br.dev.botecodigital.socket.exception.SocketException;

public class SocketController {

    private ServerSocket socketServer;
    private BufferedReader input;
    private PrintWriter out;
    private Json json;

    private final ConcurrentLinkedQueue<SocketCommandRequest> queue = new ConcurrentLinkedQueue<>();

    private Status status;

    private String clientIP;
    private String clientUsername;
    private Socket client;
    private Thread threadListening;


    public enum Status{
        WAITING,
        CONNECTECTED,
        DISCONNECTED
    }

    public SocketController(){
        this.json = new Json();
        this.json.setOutputType(OutputType.json);
    }


    public void startServer() {
        new Thread( ()  -> {
            ServerSocketHints serverHints = new ServerSocketHints();
            serverHints.acceptTimeout = 0; // infinite
            socketServer = Gdx.net.newServerSocket(Protocol.TCP, "localhost", 9999, serverHints);

            this.startListening();

        }).start();
    }

    private void startListening(){
        threadListening = new Thread( ()  -> {
            this.status = Status.WAITING;
            SocketHints socketHints = new SocketHints();
            this.client = socketServer.accept(socketHints);
            input = new BufferedReader( new InputStreamReader(client.getInputStream() ) );
            out = new PrintWriter(client.getOutputStream(), true);

            try{

                processSetUsernameCommand();
                
                this.status = Status.CONNECTECTED;
                
                String commandString;
                SocketCommandRequest command;
                while ((commandString = input.readLine()) != null) {
                    command = this.parseCommand(commandString);

                    if(!command.isValid()){
                        send(SocketCommandResponse.error("Invalid command"));
                        continue;
                    }

                    if(isDisconnectCommand(command)){
                        break;
                    }

                    queue.add( command );
                }
            }catch(IOException e){
                e.printStackTrace();
                this.disconnect();
            }catch(SocketException e){
                this.send(SocketCommandResponse.error("Username not set"));
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
        this.queue.clear();
        this.status = Status.DISCONNECTED;
        this.clientIP = "";
        this.clientUsername = "";
        this.client.dispose();
        this.client = null;
        this.threadListening.interrupt();
        this.startListening();
    }

    private SocketCommandRequest parseCommand(String jsoString){
        Gdx.app.log("SOCKETCONTROLLER", jsoString);
        try{
            return json.fromJson(SocketCommandRequest.class, jsoString);
        }catch(SerializationException e){
            return new SocketCommandRequest();
        }
    }

    private void processSetUsernameCommand() throws IOException, SocketException {
        String commandString = input.readLine();
        SocketCommandRequest command = this.parseCommand(commandString);
        
        if(command.is(SocketCommandRequest.Target.SYSTEM, SocketCommandRequest.SystemCommand.SETUSERNAME)){
            this.clientIP = this.client.getRemoteAddress();
            this.clientUsername = command.getValue();
            this.send(SocketCommandResponse.success("Username \""+this.clientUsername+"\" was set successfully"));
        }else{
            throw new SocketException("Username not set");
        }
    }

    private boolean isDisconnectCommand(SocketCommandRequest command){
        if(command.is(SocketCommandRequest.Target.SYSTEM, SocketCommandRequest.SystemCommand.DISCONNECT)){
            send(SocketCommandResponse.success("Disconnected!"));
            disconnect();
            return true;
        }
        return false;
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

    public void send(SocketCommandResponse message){
        out.println(this.json.toJson(message));
        out.flush();
    }
}
