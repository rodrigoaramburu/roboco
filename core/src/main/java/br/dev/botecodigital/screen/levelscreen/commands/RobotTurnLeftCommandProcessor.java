package br.dev.botecodigital.screen.levelscreen.commands;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.screen.levelscreen.CommandProcessor;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class RobotTurnLeftCommandProcessor extends CommandProcessor{

    @Override
    public Command getCommand() {
        return Command.ROBOT_TURN_LEFT;
    }

    @Override
    public void handle(SocketCommandRequest request, Robot robot, Level level) {
        robot.turnLeft((result) -> { 
            SocketController.getInstance().send(
                SocketCommandResponse.success("ROBOT_ACTION_OK", "Robo virou a esquerda.")
            );
        });
        this.getDialogBox().addMessage("Virando Esquerda...");
    
    }

}
