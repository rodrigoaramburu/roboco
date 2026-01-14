package br.dev.botecodigital.screen.levelscreen.commands;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.screen.levelscreen.CommandProcessor;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class RobotTurnRightCommandProcessor  extends CommandProcessor{

    @Override
    public Command getCommand() {
        return Command.ROBOT_TURN_RIGHT;
    }

    @Override
    public void handle(SocketCommandRequest request, Robot robot, Level level) {
        robot.turnRight((result) -> { 
            SocketController.getInstance().send(
                SocketCommandResponse.success("ROBOT_ACTION_OK","Robo virou a direita.")
            );
        });
        this.getDialogBox().addMessage("Virando Direita...");
    }
    
}
