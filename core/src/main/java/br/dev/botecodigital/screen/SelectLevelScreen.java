package br.dev.botecodigital.screen;

import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import br.dev.botecodigital.AssetManager;
import br.dev.botecodigital.level.Level;
import br.dev.botecodigital.level.basic.BasicLevel01;
import br.dev.botecodigital.level.basic.BasicLevel02;
import br.dev.botecodigital.level.basic.BasicLevel03;
import br.dev.botecodigital.level.basic.BasicLevel04;
import br.dev.botecodigital.level.basic.BasicLevel05;
import br.dev.botecodigital.screen.uicomponents.Button;
import br.dev.botecodigital.screen.uicomponents.ListItem;

public class SelectLevelScreen implements Screen {

    private SpriteBatch batch;
    private Game game;
    private FitViewport viewport;
    private Stage stage;
    private List<Level> levels;

    private Level selectedLevel = null;

    public SelectLevelScreen(Game game){
        this.game = game;

        this.levels = List.of(
            new BasicLevel01(),
            new BasicLevel02(),
            new BasicLevel03(),
            new BasicLevel04(),
            new BasicLevel05()
        );
    }

    @Override
    public void show() {
        this.viewport = new FitViewport(800,640);
        this.stage = new Stage(this.viewport);
        Gdx.input.setInputProcessor(this.stage);

        this.batch = new SpriteBatch();

        Table root  = new Table();
        root.setFillParent(true);
        root.top().left();
        stage.addActor(root);
        
        Table listContainer = new Table();

        this.levels.forEach( (level) -> {
            ListItem item = new ListItem(level);
            item.addListener( new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (Actor actor : listContainer.getChildren()) {
                        if (actor instanceof ListItem) {
                            ((ListItem) actor).setSelected(false);
                        }
                    }
                    selectedLevel = level;
                    item.setSelected(true);
                }
            });
            
            listContainer.add(item).padBottom(0);
            listContainer.row();
        });

        Label labelLevel = new Label("Selecione um nível", new LabelStyle(AssetManager.font1, Color.WHITE));
        labelLevel.setFontScale(0.7f);
        labelLevel.setAlignment(Align.center);
        root.add(labelLevel)
            .expandX()
            .size(400, 50)
            .padTop(52);
         root.row();

        ScrollPane scrollPane = new ScrollPane(listContainer);
        scrollPane.setFadeScrollBars(true);
        
        root.add(scrollPane)
            .width(580)
            .height(390)
            .expandX()
            .padLeft(100)
            .padTop(15);
        root.row();

        Button btIniciar = new Button("Iniciar");
        btIniciar.addListener( new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(selectedLevel != null){
                        game.setScreen( new LevelScreen(game, selectedLevel));
                    }
                }
        });
        root.add(btIniciar).padTop(70);

    }



    @Override
    public void render(float delta) {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        this.viewport.apply();

        this.batch.begin();
        this.batch.draw(AssetManager.uiBackgroubd, 0, 0 ,800,640);
        this.batch.end();

        this.stage.act(delta);
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }

}
