package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.TileManager.*;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.Tile;
import br.dev.botecodigital.robot.Robot;
import br.dev.botecodigital.robot.Robot.MapDirection;

public class BasicLevel04 extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_},
            {FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01X, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_}            
        };
    }

    @Override
    public Tile[][] getWalls() {
        return new Tile[][]{
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__}           
        };
    }

    @Override
    public Vector2 getRobotInitialPosition() {
        return new Vector2(5, 8);
    }

    @Override
    public MapDirection getRobotDirection(){
        return MapDirection.DOWN;
    }

    @Override
    public String getName() {
        return "Básico 04";
    }

    @Override
    public String getDescription() {
        return "Chegue no ponto X";
    }

    @Override
    public boolean isFinished(Robot robot) {
        return robot.getX() == 5 && robot.getY() == 4;
    }

}
