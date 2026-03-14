package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.tiles.tilegroup.FabricaTiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.tiles.Tile;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.robot.Robot.MapDirection;

public class BasicLevel01 extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK100},
            {BACK010, BACK010, BACK010, BACK010, BACK110, BACK110, BACK110, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK120, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010}            
        };
    }

    @Override
    public Tile[][] getObstacle() {
        return new Tile[][]{
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, OBST210, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__}           
        };
    }

    public Map<Vector2, Tile> getMarkers(){
        HashMap<Vector2, Tile> makers = new HashMap<Vector2, Tile>();
        makers.put(new Vector2(6, 5), MARK030);
        return makers;
    }

    @Override
    public Vector2 getRobotInitialPosition() {
        return new Vector2(4, 5);
    }

    @Override
    public MapDirection getRobotDirection(){
        return MapDirection.RIGHT;
    }

    @Override
    public String getName() {
        return "B01 - Avançando...";
    }

    @Override
    public String getDescription() {
        return "Avance com o robô até o ponto de chegada";
    }

    @Override
    public boolean isFinished(Robot robot) {
        return robot.getX() == 6 && robot.getY() == 5;
    }

    @Override
    public void dispose() {
        
    }


}
