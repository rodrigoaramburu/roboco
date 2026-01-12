package br.dev.botecodigital.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import br.dev.botecodigital.AssetManager;
import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.TileManager;
import br.dev.botecodigital.robo.Robo;
import br.dev.botecodigital.robo.Robo.RoboRelativeDirection;
import br.dev.botecodigital.screen.uicomponents.ButtonSmall;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;
import br.dev.botecodigital.socket.SocketCommandRequest.Command;

public class LevelScreen implements Screen {

    private Game game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    
    private Robo robo;
    private FitViewport viewport;

    private Level level;
    
    private String dialogBoxText = "";
    private String currentUsername = "";

    private LevelStatus status;

    private Stage stage;

    private enum LevelStatus {
        WAITING,
        RUNNING,
        WIN,
        LOSE
    }

    public LevelScreen(Game game, Level level){
        this.game = game;
        this.level = level;
        TileManager.load();

        initSocketController();
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(12,12, camera);
        this.camera.position.set(6, 6, 0); 

        this.status = LevelStatus.WAITING;
        
        this.batch = new SpriteBatch();

        this.initStage();
        
        this.robo = new Robo(
            this.level.getRoboInitialPosition().x, 
            this.level.getRoboInitialPosition().y,
            this.level.getRoboDirection()
        );
        

    }

    private void initStage() {
        this.stage = new Stage(this.viewport);
        Gdx.input.setInputProcessor(this.stage);

        Table root  = new Table();
        root.setFillParent(true);
        root.top().left();
        stage.addActor(root);

        ButtonSmall btVoltar = new ButtonSmall("Voltar");
        btVoltar.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new SelectLevelScreen(game));
            }
        });
        root.add(btVoltar)
            .size(2, 1)
            .top().left();
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        this.viewport.apply();
        this.camera.update();
        this.batch.setProjectionMatrix(camera.combined);
            
        this.processInput();

        this.robo.update();

        if(this.level.isFinished(this.robo)){
            status = LevelStatus.WIN;
        }

        this.batch.begin();

        this.level.render(batch);
        this.drawBottonDialog();
        this.drawMenuButton();
        this.drawSide();
        this.robo.render(batch);

        this.batch.end();

        this.stage.act(delta);
        this.stage.draw();
    }


    private void drawBottonDialog() {
        this.batch.draw(AssetManager.dialogBox, 0, 0, 12, 2);

        if(this.status == LevelStatus.WAITING){
            AssetManager.font1.draw(this.batch, "Aguardando conexão...", 0.5f, 1.5f);
        }else if(this.status == LevelStatus.RUNNING){
            AssetManager.font1.draw(this.batch, "Username: " +this.currentUsername , 0.5f, 1.5f);
            AssetManager.font1.draw(this.batch, this.dialogBoxText , 0.5f, 0.7f);
        }else if(this.status == LevelStatus.WIN){
            AssetManager.font1.draw(this.batch, "Username: " +this.currentUsername , 0.5f, 1.5f);
            AssetManager.font1.draw(this.batch, "Level Completado com sucesso!" , 0.5f, 0.7f);
        }else if(this.status == LevelStatus.LOSE){
            AssetManager.font1.draw(this.batch, "Username: " +this.currentUsername , 0.5f, 1.5f);
            AssetManager.font1.draw(this.batch, "Não foi desta vez!" , 0.5f, 0.7f);
        }
    }

    private void drawMenuButton() {
        this.batch.draw(AssetManager.button100x50, 0, 11, 2, 1);
    }
    
    private void drawSide() {
        this.batch.draw(AssetManager.side, 0, 2, 2, 9);
    }

    private void initSocketController() {
        
        SocketController.getInstance().setOnConnectedAction( () -> {
            currentUsername = SocketController.getInstance().getUsername();
            status = LevelStatus.RUNNING;
        });

        SocketController.getInstance().startServer();
    }


    private void processInput() {
        SocketCommandRequest commandRequest = SocketController.getInstance().readCommand();
        if(commandRequest != null){        
            
            if(commandRequest.getCommand() == Command.SYSTEM_IS_FINISH){
                processIfIsFinished(commandRequest);
                return;
            }

            if(commandRequest.getCommand() == Command.SYSTEM_DISCONNECT){
                processIfDisconnect(commandRequest);
                return;
            }
            
            if(this.status == LevelStatus.WIN || this.status == LevelStatus.LOSE){
                SocketController.getInstance().send(SocketCommandResponse.error("LEVEL_FINISHED", "O level já foi finalizaddo com o status: "+this.status));
                return;
            }

            processIfRoboMoveAction(commandRequest);

            processIfRoboTurnLeftAction(commandRequest);

            processIfRoboTurnRightAction(commandRequest);
        
            processIfRoboScanAction(commandRequest);

        }
    }

    private void processIfRoboTurnRightAction(SocketCommandRequest request) {
        if(request.getCommand() == SocketCommandRequest.Command.ROBOT_TURN_RIGHT){
            this.robo.turnRight((result) -> { 
                SocketController.getInstance().send(SocketCommandResponse.success("ROBOT_ACTION_OK","Robo virou a direita."));
                this.dialogBoxText = "";
            });
            this.dialogBoxText = "Virando Direita...";
        }
    }

    private void processIfRoboTurnLeftAction(SocketCommandRequest request) {
        if(request.getCommand() == SocketCommandRequest.Command.ROBOT_TURN_LEFT){
            this.robo.turnLeft((result) -> { 
                SocketController.getInstance().send(SocketCommandResponse.success("ROBOT_ACTION_OK", "Robo virou a esquerda."));
                this.dialogBoxText = "";
            });
            this.dialogBoxText = "Virando Esquerda...";
        }
    }

    private void processIfRoboMoveAction(SocketCommandRequest request) {
        if(request.getCommand() == SocketCommandRequest.Command.ROBOT_MOVE && this.canMove() ){
            this.robo.move( (result) -> {  
                SocketController.getInstance().send(SocketCommandResponse.success("ROBOT_ACTION_OK", "Robo moveu para frente."));
                this.dialogBoxText = "";
            });
            this.dialogBoxText = "Avançando...";
        }
    }

    private void processIfIsFinished(SocketCommandRequest request) {
        if(request.command == SocketCommandRequest.Command.SYSTEM_IS_FINISH){
            if(this.level.isFinished(this.robo)){
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

    private void processIfDisconnect(SocketCommandRequest request) {
        if(request.command == SocketCommandRequest.Command.SYSTEM_DISCONNECT){
            SocketController.getInstance().send(
                SocketCommandResponse.success("SYSTEM_DISCONNECTED","Desconectado!")
            );
            SocketController.getInstance().disconnect();
        }
    }

    private void processIfRoboScanAction(SocketCommandRequest command) {
        if(command.command == SocketCommandRequest.Command.ROBOT_SCAN){

            try{
                RoboRelativeDirection direction = RoboRelativeDirection.valueOf(command.getValue());

                this.robo.scan( this.level, direction, (result) ->{
                    SocketController.getInstance().send(
                        SocketCommandResponse.success("ROBOT_SCAN_RESULT", result)
                    );
                });
                this.dialogBoxText = "Verificando "+direction.name()+"...";
            }catch(IllegalArgumentException e){
                SocketController.getInstance().send(
                    SocketCommandResponse.error("ROBOT_INVALID_RELATIVE_DIRECTION", "Direção de scan inválida")
                );
            }

            
        }
    }

    public boolean canMove(){

        if(!this.level.canMove(this.robo.getX(), this.robo.getY(), this.robo.getDiretion())){
            this.robo.colide( (result) ->{
                status = LevelStatus.LOSE;
                SocketController.getInstance().send(
                    SocketCommandResponse.error("ROBOT_COLLISION","O robo colidiu!")
                );
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
        SocketController.getInstance().shutdownServer();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.robo.dispose();
        SocketController.getInstance().disconnect();
        SocketController.getInstance().dispose();
        TileManager.dispose();
    }

}
