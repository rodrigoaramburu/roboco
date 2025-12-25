package br.dev.botecodigital;

import com.badlogic.gdx.Game;

import br.dev.botecodigital.level.basic.BasicLevel01;
import br.dev.botecodigital.level.basic.BasicLevel02;
import br.dev.botecodigital.level.basic.BasicLevel03;
import br.dev.botecodigital.level.basic.BasicLevel04;
import br.dev.botecodigital.screen.LevelScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class RoboCo extends Game {

    @Override
    public void create() {
        AssetManager.load();
        setScreen( new LevelScreen(this, new BasicLevel03()));
    }

    @Override
    public void dispose(){
        AssetManager.dispose();
    }
}
