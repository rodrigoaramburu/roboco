package br.dev.botecodigital;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import br.dev.botecodigital.robo.Robo;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class RoboCo extends Game {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    
    private Robo robo;
    private FitViewport viewport;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        
        viewport = new FitViewport(10,10, camera);
        camera.position.set(5, 5, 0); 
        viewport.apply();
        
        batch = new SpriteBatch();
        
        robo = new Robo();
    }

    @Override
    public void render() {
        
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !robo.isExecutingAction()){
            robo.move( () -> { Gdx.app.log("MOVE", "Mover Finalizado");} );
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !robo.isExecutingAction()){
            robo.turnLeft(() -> { Gdx.app.log("MOVE", "Virar Esquerda Finalizado");});
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !robo.isExecutingAction()){
            robo.turnRight( () -> { Gdx.app.log("MOVE", "Virar Direita Finalizado"); } );
        }

        robo.update();

        batch.begin();
        robo.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        robo.dispose();
        
    }
}
