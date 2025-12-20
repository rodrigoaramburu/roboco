package br.dev.botecodigital;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import br.dev.botecodigital.robo.Robo;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class RoboCo extends Game {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    
    private Robo robo;
    private FitViewport viewport;
    private SocketController socketController;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        
        viewport = new FitViewport(10,10, camera);
        camera.position.set(5, 5, 0); 
        viewport.apply();
        
        batch = new SpriteBatch();
        
        robo = new Robo();

        socketController = new SocketController();
        socketController.startServer();
    }

    @Override
    public void render() {
        
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
            
        SocketCommandRequest command = socketController.readCommand();
        
        if(command != null){
        
            if(command.is(SocketCommandRequest.Target.ROBO, SocketCommandRequest.RoboCommand.MOVE)){
                robo.move( () -> {  socketController.send(SocketCommandResponse.success("movido!"));} );
            }
            if(command.is(SocketCommandRequest.Target.ROBO, SocketCommandRequest.RoboCommand.TURN_LEFT)){
                robo.turnLeft(() -> { socketController.send(SocketCommandResponse.success("viara esquerda!"));} );
            }
            if(command.is(SocketCommandRequest.Target.ROBO, SocketCommandRequest.RoboCommand.TURN_RIGHT)){
                robo.turnRight(() -> { socketController.send(SocketCommandResponse.success("virar direita!"));} );
            }
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
