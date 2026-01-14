package br.dev.botecodigital;

import com.badlogic.gdx.Game;

import br.dev.botecodigital.screen.SelectLevelScreen;
import br.dev.botecodigital.socket.SocketController;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class RoboCo extends Game {

    @Override
    public void create() {
        AssetManager.load();
        setScreen( new SelectLevelScreen(this) );
    }

    @Override
    public void dispose(){
        AssetManager.dispose();
        SocketController.getInstance().shutdownServer();
    }
}
