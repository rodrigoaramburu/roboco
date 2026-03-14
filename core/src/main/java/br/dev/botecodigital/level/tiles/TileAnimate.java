package br.dev.botecodigital.level.tiles;

import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileAnimate  implements Tile{

    private Animation<TextureRegion> animation;
    private Texture texture;
    
    public TileAnimate(ClassLoader classLoader, String assetFile, float frameDuration){
        
        try(InputStream stream = classLoader.getResourceAsStream(assetFile)) {
            byte[] bytes = stream.readAllBytes();
            this.texture = new Texture(new Pixmap(bytes, 0, bytes.length));
            TextureRegion[][] textureRegion = TextureRegion.split(
                this.texture,
                    50,
                    50
            );
            int index = 0;
            TextureRegion[] frames = new TextureRegion[textureRegion.length * textureRegion[0].length];
            for (TextureRegion[] row : textureRegion) {
                System.arraycopy(row, 0, frames, index, row.length);
                index += row.length;
            }

            this.animation = new Animation<TextureRegion>(frameDuration , frames);

        } catch (IOException e) {
            Gdx.app.log("TILE", assetFile+"not found");
        }
    }

    public TileAnimate(TextureRegion[] frames, float frameDuration){
        this.animation = new Animation<TextureRegion>(frameDuration , frames);
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y, float stateTime) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y, 1, 1);
    }

    @Override
    public void dispose() {
        if( this.texture != null){
            this.dispose();
        }
    }

}
