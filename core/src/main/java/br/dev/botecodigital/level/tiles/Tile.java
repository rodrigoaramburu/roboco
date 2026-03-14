package br.dev.botecodigital.level.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Tile {

    void draw(SpriteBatch batch, float x, float y, float stateTime);

    void dispose();
}
