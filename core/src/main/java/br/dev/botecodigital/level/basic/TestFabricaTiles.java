package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.tiles.tilegroup.FabricaTiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.tiles.Tile;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.robot.Robot.MapDirection;

public class TestFabricaTiles extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {BACK010, BACK020, BACK030, BACK031, BACK040, BACK041, BACK042, BACK043, BACK050, BACK060},
            {BACK070, BACK080, BACK090, BACK091, BACK100, BACK101, BACK102, BACK103, BACK110, BACK120},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070},
            {BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070},
            {BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070},
            {BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK010, BACK010, BACK010},
            {BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070, BACK070},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010},
            {BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010, BACK010}            
        };
    }

    @Override
    public Tile[][] getObstacle() {
        return new Tile[][]{
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {OBST010, OBST020, OBST030, OBST040, OBST050, OBST060, OBST070, OBST080, OBST090, OBST100},           
            {OBST110, OBST120, OBST130, OBST140, OBST150, OBST160, OBST170, OBST180, OBST190, OBST200},           
            {OBST210, OBST220, OBST230, OBST240, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, MARK010, MARK020, MARK02A, MARK02B, MARK02C, MARK030, BLANK__, BLANK__, BLANK__},           
            {BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__},           
            {BLANK__, OBST25A, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__, BLANK__}           
        };
    }

    public Map<Vector2, Tile> getMarkers(){
        HashMap<Vector2, Tile> makers = new HashMap<Vector2, Tile>();
        
        makers.put( new Vector2(1, 1), MARK010 );
        makers.put( new Vector2(3, 1), MARK02A );
        makers.put( new Vector2(4, 1), MARK02B );
        makers.put( new Vector2(5, 1), MARK02C );
        makers.put( new Vector2(6, 1), MARK030 );

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
        return "Level Teste Canos e Esteiras";
    }

    @Override
    public String getDescription() {
        return "Level para testar os tiles da fabrica";
    }

    @Override
    public boolean isFinished(Robot robot) {
        return robot.getX() == 6 && robot.getY() == 5;
    }

    @Override
    public void dispose() {
        
    }
}
