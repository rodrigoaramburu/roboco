package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.tiles.tilegroup.FabricaTiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.tiles.Tile;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.robot.Robot.MapDirection;

public class BasicLevel05 extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK060, BACK010},
            {BACK060, BACK010, BACK010, BACK010, BACK010, BACK010, BACK060, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK060, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK060, BACK010, BACK060, BACK010, BACK010, BACK010, BACK010, BACK060, BACK010},
            {BACK010, BACK010, BACK010, BACK060, BACK010, BACK010, BACK010, BACK010, BACK010, BACK060}            
        };
    }

    @Override
    public Tile[][] getObstacle() {
        return new Tile[][]{
            {OBST120, OBST120, OBST120, OBST120, OBST120, OBST120, OBST120, OBST120, BLANK__, BLANK__},           
            {OBST120, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, OBST120, BLANK__, BLANK__},           
            {OBST120, OBST120, OBST120, OBST120, OBST120, OBST120, BLANK__, OBST120, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, OBST120, BLANK__, OBST120, BLANK__, BLANK__},           
            {BLANK__, OBST120, OBST120, OBST120, OBST120, OBST120, BLANK__, OBST120, BLANK__, BLANK__},           
            {BLANK__, OBST120, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, OBST120, BLANK__, BLANK__},           
            {BLANK__, OBST120, OBST120, OBST120, OBST120, OBST120, OBST120, OBST120, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__}           
        };
    }

    public Map<Vector2, Tile> getMarkers(){
        HashMap<Vector2, Tile> makers = new HashMap<Vector2, Tile>();
        
        return makers;
    }

    @Override
    public Vector2 getRobotInitialPosition() {
        return new Vector2(1, 8);
    }

    @Override
    public MapDirection getRobotDirection(){
        return MapDirection.RIGHT;
    }

    @Override
    public String getName() {
        return "Básico 05";
    }

    @Override
    public String getDescription() {
        return "Chegue no ponto X";
    }

    @Override
    public boolean isFinished(Robot robot) {
        return robot.getX() == 2 && robot.getY() == 4;
    }

    @Override
    public void dispose() {
        
    }

}
