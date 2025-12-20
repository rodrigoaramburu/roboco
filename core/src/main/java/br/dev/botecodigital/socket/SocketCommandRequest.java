package br.dev.botecodigital.socket;

public class SocketCommandRequest {

    public Target target;
    public String command;
    public String value;

    public enum Target{
        ROBO,
        SYSTEM
    }

    public enum SystemCommand{
        SETUSERNAME, 
        DISCONNECT
    }
    public enum RoboCommand{
        MOVE,
        TURN_LEFT,
        TURN_RIGHT
    }

    public boolean is(Target expectedTarget, Enum<?> expectedCommand) {
        if (target != expectedTarget) return false;
        return expectedCommand.name().equals(command);
    }

    public Target getTarget() {
        return target;
    }
    public void setTarget(Target target) {
        this.target = target;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid() {
        if(target == null){
            return false;
        }
        if(target == Target.SYSTEM){
            try{
                SystemCommand.valueOf(this.command);
                return true;
            }catch(Exception e){
                return false;
            }
        }

        if(target == Target.ROBO){
            try{
                RoboCommand.valueOf(this.command);
                return true;
            }catch(Exception e){
                return false;
            }
        }

        return false;
    }


    
    

}
