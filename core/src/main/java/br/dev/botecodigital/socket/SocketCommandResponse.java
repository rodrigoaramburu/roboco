package br.dev.botecodigital.socket;

public class SocketCommandResponse {

    public enum Status {
        SUCCESS,
        ERROR
    }

    private Status status;
    private String code;
    private String message;

    private SocketCommandResponse(){

    }

    public static SocketCommandResponse success(String codigo, String message){
        SocketCommandResponse response = new SocketCommandResponse();
        response.status = Status.SUCCESS;
        response.message = message;
        response.code = codigo;
        return response;
    }
    
    public static SocketCommandResponse error(String codigo, String message){
        SocketCommandResponse response = new SocketCommandResponse();
        response.status = Status.ERROR;
        response.message = message;
        response.code = codigo;
        return response;
    }


    public Status getStatus() {
        return status;
    }
    
    public String getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }


}
