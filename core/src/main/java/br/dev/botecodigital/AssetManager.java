package br.dev.botecodigital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class AssetManager {

    public static BitmapFont font1;

    public static Texture dialogBox;
    public static Texture button;
    public static Texture side;

    public static void load(){

        dialogBox = new Texture("dialog-box.png");
        button = new Texture("button.png");
        side = new Texture("side.png");

        FreeTypeFontGenerator fontGeneratorRobotoRegular = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontParameter params = new FreeTypeFontParameter();
        params.size = 36;
        params.color = Color.WHITE;
        params.borderWidth = 2;
        params.borderColor = Color.BLACK;
        font1 = fontGeneratorRobotoRegular.generateFont(params);
        font1.setUseIntegerPositions(false);
        font1.getData().setScale(0.01f);

    }

    public static void dispose(){
        dialogBox.dispose();
        button.dispose();
        side.dispose();

        font1.dispose();
    }
}
