package br.dev.botecodigital.level.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.dev.botecodigital.AssetManager;

public class TileText extends TileImage{

    private String text;

    public TileText(ClassLoader classLoader,String assetFile, String text){
        super(classLoader, assetFile);
        this.text = text;
    }

    public TileText(Texture texture, String text){
        super(texture);
        this.text = text;
    }

    public TileText(TextureRegion textureRegion, String text) {
        super(textureRegion);
        this.text = text;
    }

    public TileText(Texture texture, TileDirection tileDiretion, String text){
        super(texture, tileDiretion);
        this.text = text;
    }

    public TileText(TextureRegion textureRegion , TileDirection tileDiretion, String text) {
        super(textureRegion, tileDiretion);
        this.text = text;
    }

    @Override
    public void draw(SpriteBatch batch, float x, float y, float stateTime){
        super.draw(batch, x, y, stateTime);

        GlyphLayout layout = new GlyphLayout(AssetManager.fontTile, text);
        float textX = x + 0.5f - layout.width / 2f;
        float textY = y + 0.5f + layout.height / 2f;
        
        AssetManager.fontTile.draw(batch, text, textX ,  textY);
    }
}
