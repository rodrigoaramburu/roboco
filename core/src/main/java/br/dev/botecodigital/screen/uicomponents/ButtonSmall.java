package br.dev.botecodigital.screen.uicomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import br.dev.botecodigital.AssetManager;

public class ButtonSmall  extends Table{
    
    public ButtonSmall(String labelText){
        this.setBackground(new TextureRegionDrawable(AssetManager.button100x50));
        setTouchable(Touchable.enabled);

        this.setSize(100, 50);
        
        LabelStyle style = new LabelStyle(AssetManager.font1, Color.WHITE);
        Label label = new Label(labelText, style);
        label.setAlignment(Align.center);
        this.center();

        this.add(label).center().expandX();
    }
}
