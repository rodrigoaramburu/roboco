package br.dev.botecodigital.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.robo.Robo;
import br.dev.botecodigital.robo.Robo.MapDirection;

public abstract class Level {

    /**
     * Retorna uma matriz onde o primeiro indice representa a linha no caso o eixo Y do mundo e
     * o segundo indice representa a coluna no caso o eixo X do mundo.
     * @return Tile[][]
     */
    public abstract Tile[][] getFloor(); 

    /**
     * Retorna uma matriz onde o primeiro indice representa a linha no caso o eixo Y do mundo e
     * o segundo indice representa a coluna no caso o eixo X do mundo.
     * @return Tile[][]
     */
    public abstract Tile[][] getWalls(); 

    public abstract String getName();

    public abstract String getDescription();

    public abstract Vector2 getRoboInitialPosition();

    public abstract MapDirection getRoboDirection();

    public abstract boolean isFinished(Robo robo);


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

    public boolean canMove(float x, float y, MapDirection direction){
        
        if(direction == MapDirection.LEFT) x--;
        if(direction == MapDirection.RIGHT) x++;
        if(direction == MapDirection.UP) y++;
        if(direction == MapDirection.DOWN) y--;
        
        if(x < 0 || x >= 10 || y < 0 || y > 10){
            return false;
        }
        
        Tile[][] wallTile = this.getWalls();
        return wallTile[ 9 - (int)  y][(int) x] == null;

    }

    public Tile getTile(float x, float y){
        if(x < 0 || x >= 10 || y < 0 || y > 10){
            return null;
        }
        
        Tile[][] wallTile = this.getWalls();
        return wallTile[ 9 - (int)  y][(int) x];
    }



}
