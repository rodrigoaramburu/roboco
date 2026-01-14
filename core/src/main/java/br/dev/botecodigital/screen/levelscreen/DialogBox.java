package br.dev.botecodigital.screen.levelscreen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import br.dev.botecodigital.AssetManager;

public class DialogBox extends Table{

    private Table messageContainer;
    private ScrollPane scrollPane;

    public DialogBox(){
        this.setBackground(new TextureRegionDrawable(AssetManager.dialogBox));
        this.setSize(600, 100);
        this.setFillParent(false);
        this.bottom();
        this.left();
       

        this.messageContainer = new Table();
                
        this.scrollPane = new ScrollPane(this.messageContainer);
        this.scrollPane.setFadeScrollBars(true);
        this.scrollPane.setForceScroll(false, true);
                        
        this.add(this.scrollPane)
            .top()
            .expandX()
            .left()
            .width(740)
            .height(75)
            .padBottom(10.0f)
            .padTop(10.0f)
            .padLeft(40.0f)
            .padRight(40.0f);

    }

    public void addMessage(String message){
        Gdx.app.postRunnable(() -> {
            LabelStyle style = new LabelStyle(AssetManager.font20, Color.WHITE);
            Label messageLabel = new Label(message, style);
            
            messageLabel.setWrap(true);
            
            messageContainer.add(messageLabel)
                .left()
                .expandX()
                .fillX();            
            messageContainer.row();

            scrollPane.validate();
            scrollPane.setScrollPercentY(1f);
        });
        
    }

}
