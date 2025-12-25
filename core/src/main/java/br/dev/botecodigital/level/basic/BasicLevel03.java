package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.TileManager.*;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.Tile;
import br.dev.botecodigital.robo.Robo;
import br.dev.botecodigital.robo.Robo.Direction;

public class BasicLevel03 extends Level {

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
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, FAC_W_12_, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, FAC_W_12_, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__}           
        };
    }

    @Override
    public Vector2 getRoboInitialPosition() {
        return new Vector2(2, 7);
    }

    @Override
    public Direction getRoboDirection(){
        return Direction.RIGHT;
    }

    @Override
    public String getName() {
        return "Básico 03";
    }

    @Override
    public String getDescription() {
        return "Chegue no ponto X";
    }

    @Override
    public boolean isFinished(Robo robo) {
        return robo.getX() == 5 && robo.getY() == 4;
    }


}
