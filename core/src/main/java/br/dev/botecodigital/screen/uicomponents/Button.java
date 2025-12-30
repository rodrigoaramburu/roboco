package br.dev.botecodigital.screen.uicomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import br.dev.botecodigital.AssetManager;

public class Button extends Table {

    public Button(String labelText){
        this.setBackground(new TextureRegionDrawable(AssetManager.uiButtonBg));
        setTouchable(Touchable.enabled);

        this.setSize(200, 53);

        LabelStyle style = new LabelStyle(AssetManager.font1, Color.WHITE);
        Label label = new Label(labelText, style);
        label.setFontScale(0.7f);

        this.center();

        this.add(label).center().expandX();
    }
}
