package br.dev.botecodigital.screen.levelscreen.commands;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.screen.levelscreen.CommandProcessor;
import br.dev.botecodigital.screen.levelscreen.LevelScreen.LevelStatus;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class RobotMoveCommandProcessor extends CommandProcessor{

    @Override
    public Command getCommand() {
        return Command.ROBOT_MOVE;
    }

    @Override
    public void handle(SocketCommandRequest request, Robot robot, Level level) {
        if(!level.canMove(robot.getX(), robot.getY(), robot.getDiretion())){
            robot.colide( (result) ->{
                setLevelStatus(LevelStatus.LOSE);
                SocketController.getInstance().send(
                    SocketCommandResponse.error("ROBOT_COLLISION","O robo colidiu!")
                );
            });
            this.getDialogBox().addMessage("O robô colidiu!");
            return;
        }

        
        robot.move( (result) -> {  
            SocketController.getInstance().send(
                SocketCommandResponse.success("ROBOT_ACTION_OK", "Robo moveu para frente.")
            );
        });
        this.getDialogBox().addMessage("Avançando...");
         
    }

    
}
