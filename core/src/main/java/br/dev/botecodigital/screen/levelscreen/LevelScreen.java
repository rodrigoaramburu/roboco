package br.dev.botecodigital.screen.levelscreen;

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
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.screen.SelectLevelScreen;
import br.dev.botecodigital.screen.levelscreen.commands.RobotMoveCommandProcessor;
import br.dev.botecodigital.screen.levelscreen.commands.RobotScanCommandProcessor;
import br.dev.botecodigital.screen.levelscreen.commands.RobotTurnLeftCommandProcessor;
import br.dev.botecodigital.screen.levelscreen.commands.RobotTurnRightCommandProcessor;
import br.dev.botecodigital.screen.levelscreen.commands.SystemDisconnectCommandProcessor;
import br.dev.botecodigital.screen.levelscreen.commands.SystemIsFinishCommandProcessor;
import br.dev.botecodigital.screen.uicomponents.ButtonSmall;
import br.dev.botecodigital.socket.NetworkUtils;
import br.dev.botecodigital.socket.SocketCommandRequest;
import br.dev.botecodigital.socket.SocketCommandResponse;
import br.dev.botecodigital.socket.SocketController;

public class LevelScreen implements Screen {

    private Game game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    
    private Robot robot;
    private Level level;

    private FitViewport viewport;
    private FitViewport viewportStage;
    
    private String currentUsername = "";

    private LevelStatus status;

    private Stage stage;

    private LevelCommandProcessor commandProcessor;

    private DialogBox dialogBox;

    public enum LevelStatus {
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
        
        this.viewportStage = new FitViewport(600,600);

        this.status = LevelStatus.WAITING;
        
        this.batch = new SpriteBatch();

        this.initStage();
        
        this.robot = new Robot(
            this.level.getRobotInitialPosition().x, 
            this.level.getRobotInitialPosition().y,
            this.level.getRobotDirection()
        );


        this.commandProcessor = new LevelCommandProcessor(this);
        this.commandProcessor.addRobotCommandProcessor(new RobotMoveCommandProcessor());
        this.commandProcessor.addRobotCommandProcessor(new RobotTurnLeftCommandProcessor());
        this.commandProcessor.addRobotCommandProcessor(new RobotTurnRightCommandProcessor());
        this.commandProcessor.addRobotCommandProcessor(new RobotScanCommandProcessor());

        this.commandProcessor.addSystemCommandProcessor( new SystemIsFinishCommandProcessor());
        this.commandProcessor.addSystemCommandProcessor( new SystemDisconnectCommandProcessor());
        

    }

    private void initStage() {
        this.stage = new Stage(this.viewportStage);
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
            .size(100, 50)
            .top().left().fillX();
        
        
        this.dialogBox = new DialogBox();
        stage.addActor(this.dialogBox);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        this.viewport.apply();
        this.camera.update();
        this.batch.setProjectionMatrix(camera.combined);
            
        this.processInput();

        this.robot.update();

        if(!this.isLevelFinished() && this.level.isFinished(this.robot)){
            status = LevelStatus.WIN;
            this.getDialogBox().addMessage("Nível finalizado.");
        }

        this.batch.begin();

        this.level.render(batch);
        this.drawSide();
        this.robot.render(batch);

        this.batch.end();

        this.stage.act(delta);
        this.stage.draw();
    }

    private void drawSide() {
        this.batch.draw(AssetManager.side, 0, 2, 2, 9);
    }

    private void initSocketController() {
        SocketController.getInstance().setOnStartListenning( () ->{
            int port = SocketController.getInstance().getPort();
            this.dialogBox.addMessage("Aguardando conexão em "+NetworkUtils.getLocalIPAddress()+":"+port+"...");
        });

        SocketController.getInstance().setOnConnectedAction( () -> {
            currentUsername = SocketController.getInstance().getUsername();
            Gdx.graphics.setTitle("Usuário conectado: " + currentUsername);
            this.dialogBox.addMessage("Usuário conectado: " + currentUsername);
            status = LevelStatus.RUNNING;
        });

        SocketController.getInstance().setOnDisconnectedAction( () -> {
            currentUsername = SocketController.getInstance().getUsername();
            Gdx.graphics.setTitle("Roboco");
            this.dialogBox.addMessage("Usuário " + currentUsername+" desconetou.");
            status = LevelStatus.RUNNING;
        });

        SocketController.getInstance().startServer();
    }


    private void processInput() {
        SocketCommandRequest commandRequest = SocketController.getInstance().readCommand();
        if(commandRequest != null){  
            
            CommandProcessor systemCommandProcessor = this.commandProcessor.getSystemCommandProcessor(commandRequest.getCommand());
            if(systemCommandProcessor != null){
                systemCommandProcessor.handle(commandRequest, robot, level);
                return;
            }
                        
            // Após nivel vizalizado robo não pode receber mais comandos
            if(isLevelFinished()){
                SocketController.getInstance().send(
                    SocketCommandResponse.error("LEVEL_FINISHED", "O level já foi finalizaddo com o status: "+this.status)
                );
                return;
            }

            CommandProcessor robotCommandProcessor = this.commandProcessor.getRobotCommandProcessor(commandRequest.getCommand());
            if(robotCommandProcessor != null){
                robotCommandProcessor.handle(commandRequest, robot, level);
            }

        }
    }

    private boolean isLevelFinished() {
        return this.status == LevelStatus.WIN || this.status == LevelStatus.LOSE;
    }

    public DialogBox getDialogBox(){
        return this.dialogBox;
    }

    public void setLevelStatus(LevelStatus levelStatus) {
        this.status = levelStatus;
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
        this.robot.dispose();
        SocketController.getInstance().disconnect();
        SocketController.getInstance().dispose();
        TileManager.dispose();
    }

    

}
