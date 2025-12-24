package br.dev.botecodigital.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.robo.Robo.Direction;

public abstract class Level {

    public abstract Tile[][] getFloor(); 
    public abstract Tile[][] getWalls(); 


    public void render(SpriteBatch batch){
        Tile[][] floor = this.getFloor();
        for(int row = 0; row < 10; row++ ){
            for(int col = 0; col < 10; col++ ){
                if(floor[row][col] == null) continue;
                floor[row][col].draw(batch, col+2, 9-row+2);
            }
        }
        
        Tile[][] walls = this.getWalls();
        for(int row = 0; row < 10; row++ ){
            for(int col = 0; col < 10; col++ ){
                if(walls[row][col] == null) continue;
                walls[row][col].draw(batch, col+2, 9-row+2);
            }
        }
    }

    public abstract String getName();
    public abstract String getDescription();

    public abstract Vector2 getRoboInitialPosition();

    public abstract Direction getRoboDirection();

}
