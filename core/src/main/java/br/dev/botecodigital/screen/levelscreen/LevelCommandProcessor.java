package br.dev.botecodigital.screen.levelscreen;

import java.util.HashMap;
import java.util.Map;

import br.dev.botecodigital.socket.SocketCommandRequest.Command;

public class LevelCommandProcessor {

    private Map<Command, CommandProcessor> robotCommandProcessor = new HashMap<>();
    private Map<Command, CommandProcessor> systemCommandProcessor = new HashMap<>();
    private LevelScreen levelScreen;

    public LevelCommandProcessor( LevelScreen levelScreen) {
        this.levelScreen = levelScreen;
    }

    public void addRobotCommandProcessor(CommandProcessor commandProcessor){
        commandProcessor.setLevelScreen(this.levelScreen);
        this.robotCommandProcessor.put(commandProcessor.getCommand(), commandProcessor);
    }
    
    public void addSystemCommandProcessor(CommandProcessor commandProcessor){
        commandProcessor.setLevelScreen(this.levelScreen);
        this.systemCommandProcessor.put(commandProcessor.getCommand(), commandProcessor);
    }

    public CommandProcessor getRobotCommandProcessor(Command command){
        return this.robotCommandProcessor.get(command);
    } 

    public CommandProcessor getSystemCommandProcessor(Command command){
        return this.systemCommandProcessor.get(command);
    } 

}
