package br.dev.botecodigital.screen.levelscreen.commands;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.screen.levelscreen.CommandProcessor;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class SystemIsFinishCommandProcessor extends CommandProcessor{

    @Override
    public Command getCommand() {
        return Command.SYSTEM_IS_FINISH;
    }
    
    @Override
    public void handle(SocketCommandRequest request, Robot robot, Level level) {
        
        if(level.isFinished(robot)){
            SocketController.getInstance().send(
                SocketCommandResponse.success("LEVEL_FINISHED", "Level finalizado!")
            );
        }else{
            SocketController.getInstance().send(
                SocketCommandResponse.success("LEVEL_NOT_FINISHED", "Level ainda não finalizado.")
            );
        }
        
    }

}
