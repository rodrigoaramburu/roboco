package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.TileManager.*;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.Tile;
import br.dev.botecodigital.robo.Robo;
import br.dev.botecodigital.robo.Robo.MapDirection;

public class BasicLevel05 extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_},
            {FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01X, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_}            
        };
    }

    @Override
    public Tile[][] getWalls() {
        return new Tile[][]{
            {FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__},           
            {FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__}           
        };
    }

    @Override
    public Vector2 getRoboInitialPosition() {
        return new Vector2(1, 8);
    }

    @Override
    public MapDirection getRoboDirection(){
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
    public boolean isFinished(Robo robo) {
        return robo.getX() == 2 && robo.getY() == 4;
    }

}
