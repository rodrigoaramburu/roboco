package br.dev.botecodigital.socket;

public class SocketCommandRequest {

    public Command command;
    public String value;

    public enum Command{
        SYSTEM_SETUSERNAME, 
        SYSTEM_DISCONNECT, 
        SYSTEM_IS_FINISH,

        ROBOT_MOVE,
        ROBOT_TURN_LEFT,
        ROBOT_TURN_RIGHT, 
        ROBOT_SCAN

    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
