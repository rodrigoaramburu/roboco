package br.dev.botecodigital;

import com.badlogic.gdx.Game;

import br.dev.botecodigital.screen.SelectLevelScreen;

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
    }
}
