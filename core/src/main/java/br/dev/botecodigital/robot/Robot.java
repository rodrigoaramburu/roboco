package br.dev.botecodigital.robot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.AssetManager;
import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.Tile;

public class Robot {

    private static final double DELAY_BETWEEN_ACTION = -0.5;
    
    private Sprite sprite;
    private Texture texture;

    private Sprite spriteScanner;

    private float velocity = 2.0f;

    private RobotAction currentAction = null;

    private MapDirection direction;

    private float actionProgress = 0f;
    
    private TextureRegion[][] frames;

    private FinishRobotAction finishRoboAction;
    private String finishRoboActionCode = null;

    public enum MapDirection{
        UP, RIGHT, DOWN, LEFT; // posições em sentido horário
    }

    public enum RobotRelativeDirection{
        FRONT("FRENTE"), LEFT("ESQUERDA"), RIGHT("DIREITA"), BACK("ATRÁS");

        public final String label;

        private RobotRelativeDirection(String label){
            this.label = label;
        }
    }

    private enum RobotAction{
        MOVING,
        TURNING,
        COLLIDING, 
        SCANNING
    } 
   

    public Robot(float x, float y, MapDirection direction){
        this.texture = new Texture("robo/robo-sprite.png");
        frames = TextureRegion.split(texture, 50, 50);

        this.spriteScanner = new Sprite(AssetManager.scan);
        this.spriteScanner.setSize(1, 1);
        this.spriteScanner.setOriginCenter();

        this.direction = direction;
        this.sprite = new Sprite(this.frames[0][0]);
        this.sprite.setPosition(x, y);
        this.sprite.setSize(1, 1);
    }

    public void render(SpriteBatch batch){
        this.sprite.setRegion( this.frames[0][this.direction.ordinal()]);
        this.sprite.setSize(1, 1);
        this.sprite.translate(2, 2);
        this.sprite.draw(batch);
        this.sprite.translate(-2, -2);

        if(this.currentAction == RobotAction.SCANNING){
            this.spriteScanner.translate(2, 2);
            this.spriteScanner.draw(batch);
            this.spriteScanner.translate(-2, -2);
        }
    }

    public void move(FinishRobotAction finishRoboAction){
        if(this.isExecutingAction()) return;
        
        this.currentAction = RobotAction.MOVING;
        this.actionProgress = 1f;
        this.finishRoboAction = finishRoboAction;
    }
    
    public void turnLeft(FinishRobotAction finishRoboAction){
        if(this.isExecutingAction()) return;
        
        int i = this.direction.ordinal() - 1;
        i = i < 0 ? 3 : i;
        this.direction = MapDirection.values()[i];

        this.currentAction = RobotAction.TURNING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 1f;

    }


    public void turnRight(FinishRobotAction finishRoboAction){
        if(this.isExecutingAction()) return;

        int i = (this.direction.ordinal() + 1) % 4; 
        this.direction = MapDirection.values()[i];

        this.currentAction = RobotAction.TURNING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 1f;
    }

    public void colide(FinishRobotAction finishRoboAction) {
        if(this.isExecutingAction()) return;
        this.currentAction = RobotAction.COLLIDING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 1f;

    }

    public void scan(Level level, RobotRelativeDirection relativeDirection, FinishRobotAction finishRoboAction) {
        if(this.isExecutingAction()) return;
        this.currentAction = RobotAction.SCANNING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 3f;

        Vector2 scanPosition = this.getRelativePosition(relativeDirection);

        Tile tile = level.getTile(scanPosition.x, scanPosition.y);
        if(tile == null){
            this.finishRoboActionCode = "EMPTY";
        }else{
            this.finishRoboActionCode = "WALL";
        }

        this.spriteScanner.setPosition(scanPosition.x, scanPosition.y);

        if(scanPosition.y > this.getY()){
            this.spriteScanner.setRotation(0);
        }
        if(scanPosition.y < this.getY()){
            this.spriteScanner.setRotation(180);
        }
        if(scanPosition.x > this.getX()){
            this.spriteScanner.setRotation(-90);
        }
        if(scanPosition.x < this.getX()){
            this.spriteScanner.setRotation(90);
        }

    }

    private Vector2 getRelativePosition(RobotRelativeDirection direction) {
        float x = this.getX();
        float y = this.getY();

        if(RobotRelativeDirection.FRONT  == direction ){
            if(this.direction == MapDirection.LEFT) x--;
            if(this.direction == MapDirection.RIGHT) x++;
            if(this.direction == MapDirection.UP) y++;
            if(this.direction == MapDirection.DOWN) y--;
            return new Vector2(x, y);
        }
        if(RobotRelativeDirection.LEFT  == direction){
            if(this.direction == MapDirection.LEFT) y--;
            if(this.direction == MapDirection.RIGHT) y++;
            if(this.direction == MapDirection.UP) x--;
            if(this.direction == MapDirection.DOWN) x++;
            return new Vector2(x, y);
        }

        if(RobotRelativeDirection.RIGHT  == direction){
            if(this.direction == MapDirection.LEFT) y++;
            if(this.direction == MapDirection.RIGHT) y--;
            if(this.direction == MapDirection.UP) x++;
            if(this.direction == MapDirection.DOWN) x--;
            return new Vector2(x, y);
        }

        if(RobotRelativeDirection.BACK  == direction){
            if(this.direction == MapDirection.LEFT) x++;
            if(this.direction == MapDirection.RIGHT) x--;
            if(this.direction == MapDirection.UP) y--;
            if(this.direction == MapDirection.DOWN) y++;
            return new Vector2(x, y);
        }

        return null;
    }

    public void update() {
        
        if(this.currentAction != null){
            float actionPart = this.velocity * Gdx.graphics.getDeltaTime();

            if(this.currentAction == RobotAction.MOVING && this.actionProgress > 0){
                handleRobotMoveAction(actionPart);
            }

            if(this.currentAction == RobotAction.COLLIDING && this.actionProgress > 0){
                handleRobotCollidingAction(actionPart);
            }

            actionProgress -= actionPart;
                
            if(this.actionProgress < DELAY_BETWEEN_ACTION){
                finalizeAction();
            }
        }
        
    }

    private void finalizeAction() {
        this.currentAction = null;
        this.actionProgress = 0f;
        this.sprite.setPosition(Math.round(this.sprite.getX()), Math.round(this.sprite.getY()));
        this.finishRoboAction.action(this.finishRoboActionCode);
        this.finishRoboActionCode = null;
        this.finishRoboAction = null;
    }

    private void handleRobotMoveAction(float actionPart) {
       
        if(this.direction == MapDirection.UP){
            this.sprite.translateY(actionPart);
        }else if(this.direction == MapDirection.DOWN){
            this.sprite.translateY(-actionPart);                
        }else if(this.direction == MapDirection.LEFT){
            this.sprite.translateX(-actionPart);                
        }else{
            this.sprite.translateX(actionPart);                
        }
        
    }

    private void handleRobotCollidingAction(float actionPart) {

        int factor = 1;
        if(this.actionProgress < 0.2f) factor = 1; else
        if(this.actionProgress < 0.4f) factor = -1; else
        if(this.actionProgress < 0.6f) factor = 1; else
        if(this.actionProgress < 0.8f) factor = -1; 
        
        if(this.direction == MapDirection.UP){
            this.sprite.translateY(actionPart * factor);
        }else if(this.direction == MapDirection.DOWN){
            this.sprite.translateY(-actionPart * factor);                
        }else if(this.direction == MapDirection.LEFT){
            this.sprite.translateX(-actionPart * factor);  
        }else{
            this.sprite.translateX(actionPart * factor);                
        }
        
    }

    public boolean isExecutingAction(){
        return this.currentAction != null;
    }

    public float getX() {
        return this.sprite.getX();
    }
    public float getY() {
        return this.sprite.getY();
    }

    public MapDirection getDiretion() {
        return this.direction;
    }

    public void dispose(){
        this.texture.dispose();
    }


}
