package br.dev.botecodigital.robo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Robo {

    private static final double DELAY_BETWEEN_ACTION = -0.5;
    
    private Sprite sprite;
    private Texture texture;

    private float velocity = 2.0f;

    private RoboAction currentAction = null;

    private Direction direction;

    private float actionProgress = 0f;
    
    private TextureRegion[][] frames;

    private FinishRoboAction finishRoboAction;

    public enum Direction{
        UP, RIGHT, DOWN, LEFT; // posições em sentido horário
    }

    private enum RoboAction{
        MOVING,
        TURNING,
        COLLIDING
    } 
   

    public Robo(float x, float y, Direction direction){
        this.texture = new Texture("robo/robo-sprite.png");
        frames = TextureRegion.split(texture, 50, 50);

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
    }

    public void move(FinishRoboAction finishRoboAction){
        if(this.isExecutingAction()) return;
        
        this.currentAction = RoboAction.MOVING;
        this.actionProgress = 1f;
        this.finishRoboAction = finishRoboAction;
    }
    
    public void turnLeft(FinishRoboAction finishRoboAction){
        if(this.isExecutingAction()) return;
        
        int i = this.direction.ordinal() - 1;
        i = i < 0 ? 3 : i;
        this.direction = Direction.values()[i];

        this.currentAction = RoboAction.TURNING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 1f;

    }


    public void turnRight(FinishRoboAction finishRoboAction){
        if(this.isExecutingAction()) return;

        int i = (this.direction.ordinal() + 1) % 4; 
        this.direction = Direction.values()[i];

        this.currentAction = RoboAction.TURNING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 1f;
    }

    public void colide(FinishRoboAction finishRoboAction) {
        if(this.isExecutingAction()) return;
        this.currentAction = RoboAction.COLLIDING;
        this.finishRoboAction = finishRoboAction;
        this.actionProgress = 1f;

    }

    public void update() {
        
        if(this.currentAction != null){
            float actionPart = this.velocity * Gdx.graphics.getDeltaTime();

            if(this.currentAction == RoboAction.MOVING && this.actionProgress > 0){
                handleRobotMoveAction(actionPart);
            }

            if(this.currentAction == RoboAction.COLLIDING && this.actionProgress > 0){
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
        this.finishRoboAction.action();
        this.finishRoboAction = null;
    }

    private void handleRobotMoveAction(float actionPart) {
       
        if(this.direction == Direction.UP){
            this.sprite.translateY(actionPart);
        }else if(this.direction == Direction.DOWN){
            this.sprite.translateY(-actionPart);                
        }else if(this.direction == Direction.LEFT){
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
        
        if(this.direction == Direction.UP){
            this.sprite.translateY(actionPart * factor);
        }else if(this.direction == Direction.DOWN){
            this.sprite.translateY(-actionPart * factor);                
        }else if(this.direction == Direction.LEFT){
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

    public Direction getDiretion() {
        return this.direction;
    }

    public void dispose(){
        this.texture.dispose();
    }
}
