package br.dev.botecodigital.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.robo.Robo.Direction;

public abstract class Level {

    /**
     * Retorna uma matriz onde o primeiro indice representa a linha no caso o eixo Y do mundo e
     * o segundo indice representa a coluna no caso o eixo X do mundo.
     * @return Tile[][]
     */
    public abstract Tile[][] getFloor(); 
    public abstract Tile[][] getWalls(); 


    public void render(SpriteBatch batch){
        Tile[][] floor = this.getFloor();
        for(int row = 0; row < 10; row++ ){
            for(int col = 0; col < 10; col++ ){
                if(floor[row][col] == null) continue;
                floor[row][col].draw(batch, col+2, 9 - row + 2);
            }
        }
        
        Tile[][] walls = this.getWalls();
        for(int row = 0; row < 10; row++ ){
            for(int col = 0; col < 10; col++ ){
                if(walls[row][col] == null) continue;
                walls[row][col].draw(batch, col+2, 9 - row + 2);
            }
        }
    }

    public boolean canMove(float x, float y, Direction diretion){
        
        if(diretion == Direction.LEFT) x--;
        if(diretion == Direction.RIGHT) x++;
        if(diretion == Direction.UP) y++;
        if(diretion == Direction.DOWN) y--;
        
        if(x < 0 || x >= 10 || y < 0 || y > 10){
            return false;
        }
        
        Tile[][] wallTile = this.getWalls();
        return wallTile[ 9 - (int)  y][(int) x] == null;

    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract Vector2 getRoboInitialPosition();

    public abstract Direction getRoboDirection();

}
