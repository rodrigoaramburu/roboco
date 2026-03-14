package br.dev.botecodigital.level.tiles;

import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileImage implements Tile{

    private Sprite sprite;
    private Texture texture;

    public enum TileDirection {
        UP, RIGHT, DOWN, LEFT, FLIPY, FLIPX
    }

    public TileImage(ClassLoader classLoader, String assetFile){
        try(InputStream stream = classLoader.getResourceAsStream(assetFile)) {
            byte[] bytes = stream.readAllBytes();
            this.texture = new Texture(new Pixmap(bytes, 0, bytes.length));
            this.sprite = new Sprite(this.texture);
            this.sprite.setSize(1, 1);
        } catch (IOException e) {
            Gdx.app.log("TILE", assetFile+"not found");
        }
    }

    public TileImage(Texture texture){
        this.sprite = new Sprite(texture);
        this.sprite.setSize(1, 1);
    }

    public TileImage(TextureRegion textureRegion) {
        this.sprite = new Sprite(textureRegion);
        this.sprite.setSize(1, 1);
    }

    public TileImage(Texture texture, TileDirection tileDiretion){
        this.sprite = new Sprite(texture);
        this.sprite.setSize(1, 1);
        this.sprite.setOriginCenter();
        
        rotateTile(tileDiretion);
    }

    public TileImage(TextureRegion textureRegion , TileDirection tileDiretion) {
        this.sprite = new Sprite(textureRegion);
        this.sprite.setSize(1, 1);
        this.sprite.setOriginCenter();
        
        this.rotateTile(tileDiretion);
    }

    private void rotateTile(TileDirection tileDiretion) {
        switch (tileDiretion) {
            case UP:
                this.sprite.setRotation(0);
                break;
            case RIGHT:
                this.sprite.setRotation(90);
                break;
            case DOWN:
                this.sprite.setRotation(180);
                break;
            case LEFT:
                this.sprite.setRotation(270);
                break;
            case FLIPY:
                this.sprite.flip(false, true);
                break;
            case FLIPX:
                this.sprite.flip(true, false);
                break;
        }
    }


    public void draw(SpriteBatch batch, float x, float y, float stateTime){
        this.sprite.setPosition(x, y);
        this.sprite.draw(batch);
    }

    @Override
    public void dispose() {
        if( this.texture != null ) this.texture.dispose();
    }

}
