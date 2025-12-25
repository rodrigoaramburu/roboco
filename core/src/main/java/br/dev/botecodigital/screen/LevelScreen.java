package br.dev.botecodigital.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import br.dev.botecodigital.AssetManager;
import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.TileManager;
import br.dev.botecodigital.robo.Robo;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class LevelScreen implements Screen {

    private Game game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    
    private Robo robo;
    private FitViewport viewport;
    private SocketController socketController;

    private Level level;
    
    private String dialogBoxText = "";

    private LevelStatus status;

    private enum LevelStatus {
        WAITING,
        CONNECTED,
        DISCONNECTED,
        WIN,
        LOSE
    }

    public LevelScreen(Game game, Level level){
        this.game = game;
        this.level = level;
        TileManager.load();
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(12,12, camera);
        this.camera.position.set(6, 6, 0); 

        this.status = LevelStatus.WAITING;
        
        this.batch = new SpriteBatch();
        
        this.robo = new Robo(
            this.level.getRoboInitialPosition().x, 
            this.level.getRoboInitialPosition().y,
            this.level.getRoboDirection()
        );
        
        initSocketController();
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        this.viewport.apply();
        this.camera.update();
        this.batch.setProjectionMatrix(camera.combined);
            
        this.processInput();

        this.robo.update();

        this.batch.begin();
        this.level.render(batch);
        this.drawBottonDialog();
        this.drawMenuButton();
        this.drawSide();
        this.robo.render(batch);
        this.batch.end();
    }


    private void drawBottonDialog() {
        this.batch.draw(AssetManager.dialogBox, 0, 0, 12, 2);

        if(this.status == LevelStatus.WAITING){
            AssetManager.font1.draw(this.batch, "Aguardando conexão...", 0.5f, 1.5f);
        }else if(this.status == LevelStatus.CONNECTED){
            AssetManager.font1.draw(this.batch, "Username: " +this.socketController.getUsername() , 0.5f, 1.5f);
            AssetManager.font1.draw(this.batch, this.dialogBoxText , 0.5f, 0.7f);
        }else if(this.status == LevelStatus.DISCONNECTED){
            AssetManager.font1.draw(this.batch, "Desconectado!" , 0.5f, 1.5f);
        }else if(this.status == LevelStatus.WIN){
            AssetManager.font1.draw(this.batch, "Level Completado Com sucesso!" , 0.5f, 1.5f);
        }else if(this.status == LevelStatus.LOSE){
            AssetManager.font1.draw(this.batch, "Não foi desta vez!" , 0.5f, 1.5f);
        }
    }

    private void drawMenuButton() {
        this.batch.draw(AssetManager.button, 0, 11, 2, 1);
    }
    
    private void drawSide() {
        this.batch.draw(AssetManager.side, 0, 2, 2, 9);
    }

    private void initSocketController() {
        this.socketController = new SocketController();
        this.socketController.setOnConnectedAction( () -> {
            status = LevelStatus.CONNECTED;
        });

        this.socketController.setOnDisconnectedAction( () -> {
            status = LevelStatus.DISCONNECTED;
        });

        this.socketController.startServer();
    }


    private void processInput() {
        SocketCommandRequest command = socketController.readCommand();
        
        if(command != null){        
            processIfRoboMoveAction(command);

            processIfRoboTurnLeft(command);

            processIfRoboTurnRight(command);
        }
    }

    private void processIfRoboTurnRight(SocketCommandRequest command) {
        if(command.is(SocketCommandRequest.Target.ROBO, SocketCommandRequest.RoboCommand.TURN_RIGHT)){
            this.robo.turnRight(() -> { 
                socketController.send(SocketCommandResponse.success("ROBO.ACTION_OK","Robo virou a direita."));
                this.dialogBoxText = "";
            });
            this.dialogBoxText = "Virando Direita...";
        }
    }

    private void processIfRoboTurnLeft(SocketCommandRequest command) {
        if(command.is(SocketCommandRequest.Target.ROBO, SocketCommandRequest.RoboCommand.TURN_LEFT)){
            this.robo.turnLeft(() -> { 
                socketController.send(SocketCommandResponse.success("ROBO.ACTION_OK", "Robo virou a esquerda."));
                this.dialogBoxText = "";
            });
            this.dialogBoxText = "Virando Esquerda...";
        }
    }

    private void processIfRoboMoveAction(SocketCommandRequest command) {
        if(command.is(SocketCommandRequest.Target.ROBO, SocketCommandRequest.RoboCommand.MOVE) && this.canMove() ){
            this.robo.move( () -> {  
                socketController.send(SocketCommandResponse.success("ROBO.ACTION_OK","Robo moveu para frente."));
                this.dialogBoxText = "";
            });
            this.dialogBoxText = "Avançando...";
        }
    }

    public boolean canMove(){

        if(!this.level.canMove(this.robo.getX(), this.robo.getY(), this.robo.getDiretion())){
            this.robo.colide( () ->{
                status = LevelStatus.LOSE;
                socketController.send(SocketCommandResponse.error("ROBO.COLLID","Colidiu!"));
                socketController.disconnect();
            });
        }

        return true;
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.robo.dispose();
        this.socketController.disconnect();
        TileManager.dispose();
    }

}
