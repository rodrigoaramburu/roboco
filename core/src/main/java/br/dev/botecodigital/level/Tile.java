package br.dev.botecodigital.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {

    private Sprite sprite;

    public Tile(Texture texture){
        this.sprite = new Sprite(texture);
        this.sprite.setSize(1, 1);
    }

    public Tile(TextureRegion textureRegion) {
        this.sprite = new Sprite(textureRegion);
        this.sprite.setSize(1, 1);
    }

    public void draw(SpriteBatch batch, float x, float y){
        this.sprite.setPosition(x, y);
        this.sprite.draw(batch);
    }

}
