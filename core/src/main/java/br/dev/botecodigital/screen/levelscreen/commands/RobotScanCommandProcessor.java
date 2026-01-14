package br.dev.botecodigital.screen.levelscreen.commands;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.robot.Robot.RobotRelativeDirection;
import br.dev.botecodigital.screen.levelscreen.CommandProcessor;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class RobotScanCommandProcessor extends CommandProcessor{

    @Override
    public Command getCommand() {
        return Command.ROBOT_SCAN;
    }
    

    @Override
    public void handle(SocketCommandRequest request, Robot robot, Level level) {
        try{
            RobotRelativeDirection direction = RobotRelativeDirection.valueOf(request.getValue());

            robot.scan( level, direction, (result) ->{
                SocketController.getInstance().send(
                    SocketCommandResponse.success("ROBOT_SCAN_RESULT", result)
                );
            });
            this.getDialogBox().addMessage("Verificando "+direction.label + "...");
        }catch(IllegalArgumentException e){
            SocketController.getInstance().send(
                SocketCommandResponse.error("ROBOT_INVALID_RELATIVE_DIRECTION", "Direção de scan inválida")
            );
        }
         
    }
}
