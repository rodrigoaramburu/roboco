package br.dev.botecodigital.socket;

public class SocketCommandResponse {

    public enum Status {
        SUCCESS,
        ERROR
    }

    private Status status;
    private String message;

    private SocketCommandResponse(){

    }

    public static SocketCommandResponse success(String message){
        SocketCommandResponse response = new SocketCommandResponse();
        response.status = Status.SUCCESS;
        response.message = message;
        return response;
    }

    public static SocketCommandResponse error(String message){
        SocketCommandResponse response = new SocketCommandResponse();
        response.status = Status.ERROR;
        response.message = message;
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    

}
