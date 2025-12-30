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
    public static Texture button100x50;
    public static Texture side;

    public static Texture uiBackgroubd;
    public static Texture uiPanel;
    public static Texture uiPanelSelected;
    public static Texture uiButtonBg;

    public static void load(){

        dialogBox = new Texture("ui/dialog-box.png");
        side = new Texture("ui/side.png");
        button100x50 = new Texture("ui/button-100x50.png");

        uiBackgroubd = new Texture("ui/background.png");
        uiPanel = new Texture("ui/panel.png");
        uiPanelSelected = new Texture("ui/panel-selected.png");
        uiButtonBg = new Texture("ui/button-bg.png");

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
        button100x50.dispose();
        side.dispose();

        uiBackgroubd.dispose();
        uiPanel.dispose();
        uiPanelSelected.dispose();

        font1.dispose();
    }
}
