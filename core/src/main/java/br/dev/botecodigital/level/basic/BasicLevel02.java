package br.dev.botecodigital.level.basic;

import static br.dev.botecodigital.level.TileManager.*;

import com.badlogic.gdx.math.Vector2;

import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.Tile;
import br.dev.botecodigital.robo.Robo.Direction;

public class BasicLevel02 extends Level {

    @Override
    public Tile[][] getFloor() {
        return new Tile[][]{
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_},
            {FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_06_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_06X, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
            {FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_, FAC_F_01_},
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
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, __BLANK__, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, FAC_W_12_, FAC_W_12_, FAC_W_12_, __BLANK__, __BLANK__},           
            {__BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__, __BLANK__}           
        };
    }

    @Override
    public Vector2 getRoboInitialPosition() {
        return new Vector2(6, 2);
    }

    @Override
    public Direction getRoboDirection(){
        return Direction.UP;
    }

    @Override
    public String getName() {
        return "Básico 02";
    }

    @Override
    public String getDescription() {
        return "Chegue no ponto X";
    }


}
