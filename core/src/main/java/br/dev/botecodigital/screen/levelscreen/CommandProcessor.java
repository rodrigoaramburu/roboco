package br.dev.botecodigital.screen.levelscreen;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.screen.levelscreen.LevelScreen.LevelStatus;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;

public abstract class CommandProcessor {

    private LevelScreen levelScreen;

    public abstract Command getCommand();

    public abstract void handle(SocketCommandRequest request, Robot robot, Level level);

    public void setLevelScreen(LevelScreen levelScreen) {
        this.levelScreen = levelScreen;
    }

    public void setLevelStatus(LevelStatus levelStatus){
        this.levelScreen.setLevelStatus(levelStatus);
    }

    public DialogBox getDialogBox() {
        return this.levelScreen.getDialogBox();
    }

}
