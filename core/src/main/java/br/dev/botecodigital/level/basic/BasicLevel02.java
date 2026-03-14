package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.tiles.tilegroup.FabricaTiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.tiles.Tile;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.robot.Robot.MapDirection;

public class BasicLevel02 extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK060, BACK010},
            {BACK120, BACK010, BACK010, BACK010, BACK010, BACK010, BACK120, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK120, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK120, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK120, BACK010, BACK120, BACK010, BACK010, BACK010, BACK010, BACK060, BACK010},
            {BACK010, BACK010, BACK010, BACK120, BACK010, BACK010, BACK010, BACK010, BACK010, BACK060}            
        };
    }

    @Override
    public Tile[][] getObstacle() {
        return new Tile[][]{
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, OBST110, OBST110, BLANK__, BLANK__},           
            {OBST110, OBST110, OBST110, OBST110, OBST110, OBST110, BLANK__, OBST110, BLANK__, BLANK__},           
            {OBST110, BLANK__, BLANK__, BLANK__, BLANK__, OBST110, BLANK__, OBST110, BLANK__, BLANK__},           
            {OBST110, OBST110, OBST110, OBST110, BLANK__, BLANK__, BLANK__, OBST110, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, OBST110, OBST110, OBST110, OBST110, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__}           
        };
    }
    
    public Map<Vector2, Tile> getMarkers(){
        HashMap<Vector2, Tile> makers = new HashMap<Vector2, Tile>();
        makers.put(new Vector2(6, 7), MARK030);
        return makers;
    }

    @Override
    public Vector2 getRobotInitialPosition() {
        return new Vector2(1, 6);
    }

    @Override
    public MapDirection getRobotDirection(){
        return MapDirection.RIGHT;
    }

    @Override
    public String getName() {
        return "B02 - Fazendo as curvas...";
    }

    @Override
    public String getDescription() {
        return "Vire o Robô para fazer curvas até o ponto de chegada ";
    }

    @Override
    public boolean isFinished(Robot robot) {
        return robot.getX() == 6 && robot.getY() == 6;
    }

    @Override
    public void dispose() {
        
    }
}
