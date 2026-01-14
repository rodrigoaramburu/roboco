package br.dev.botecodigital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class AssetManager {

    public static BitmapFont font36;
    public static BitmapFont font20;
    public static BitmapFont font14;

    public static Texture dialogBox;
    public static Texture button100x50;
    public static Texture side;

    public static Texture uiBackgroubd;
    public static Texture uiPanel;
    public static Texture uiPanelSelected;
    public static Texture uiButtonBg;

    public static Texture scan;

    public static void load(){

        dialogBox = new Texture("ui/dialog-box.png");
        side = new Texture("ui/side.png");
        button100x50 = new Texture("ui/button-100x50.png");

        uiBackgroubd = new Texture("ui/background.png");
        uiPanel = new Texture("ui/panel.png");
        uiPanelSelected = new Texture("ui/panel-selected.png");
        uiButtonBg = new Texture("ui/button-bg.png");
        
        scan = new Texture("robo/scan.png");


        FreeTypeFontGenerator fontGeneratorRobotoRegular = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontParameter params = new FreeTypeFontParameter();
        params.size = 36;
        params.color = Color.WHITE;
        params.borderWidth = 2;
        params.borderColor = Color.BLACK;
        font36 = fontGeneratorRobotoRegular.generateFont(params);
        font36.setUseIntegerPositions(false);
        
        FreeTypeFontParameter params2 = new FreeTypeFontParameter();
        params2.size = 20;
        params2.color = Color.WHITE;
        params2.borderWidth = 1;
        params2.borderColor = Color.BLACK;
        font20 = fontGeneratorRobotoRegular.generateFont(params2);
        font20.setUseIntegerPositions(false);

        FreeTypeFontParameter params3 = new FreeTypeFontParameter();
        params3.size = 14;
        params3.color = Color.WHITE;
        params3.borderWidth = 1;
        params3.borderColor = Color.BLACK;
        font14 = fontGeneratorRobotoRegular.generateFont(params2);
        font14.setUseIntegerPositions(false);
        

    }

    public static void dispose(){
        dialogBox.dispose();
        button100x50.dispose();
        side.dispose();

        uiBackgroubd.dispose();
        uiPanel.dispose();
        uiPanelSelected.dispose();

        font36.dispose();
        font20.dispose();

        scan.dispose();
    }
}
