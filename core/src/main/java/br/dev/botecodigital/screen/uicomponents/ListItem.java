package br.dev.botecodigital.screen.uicomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import br.dev.botecodigital.AssetManager;
import br.dev.botecodigital.level.Level;

public class ListItem extends Table{

    private Level level;
    private boolean selected;

    public ListItem(Level level){
        this.level = level;

        this.setBackground(new TextureRegionDrawable(AssetManager.uiPanel));

        setSize(580, 95);
        setTouchable(Touchable.enabled);

        LabelStyle style = new LabelStyle(AssetManager.font36, Color.WHITE);
        Label titleLabel = new Label(this.level.getName(), style);
        titleLabel.setFontScale(0.7f);
        

        Label descLabel = new Label(this.level.getDescription(), style);
        descLabel.setFontScale(0.5f);

        this.top().left();
        this.pad(20);
        this.padLeft(50);

        this.add(titleLabel).left().expandX();
        this.row();
        this.add(descLabel).left().expandX().padTop(5);
    }

    public void setSelected(boolean selected){
        this.selected = selected;
        if(this.selected){
            this.setBackground(new TextureRegionDrawable(AssetManager.uiPanelSelected));
        }else{
            this.setBackground(new TextureRegionDrawable(AssetManager.uiPanel));
        }
    }


    
}
