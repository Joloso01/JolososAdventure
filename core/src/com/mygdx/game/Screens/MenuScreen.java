package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.MyWidgets.MyLabel;
import com.mygdx.game.MyWidgets.MyScreen;
import com.mygdx.game.JadventureMain;
import com.mygdx.game.MyWidgets.MyTextButton;
import com.mygdx.game.NakamaController.NakamaSessionManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class MenuScreen extends MyScreen {

    Table table;
    boolean matchCreated;
    boolean joinGame;

    public MenuScreen(JadventureMain game) {
        super(game);
    }

    @Override
    public void show(){
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        MyTextButton create = new MyTextButton("CREATE GAME");
        MyTextButton join = new MyTextButton("JOIN GAME");
        MyLabel error = new MyLabel("", Color.RED);

        table.add(create);
        table.row();
        table.add(join);
        table.row();
        table.add(error);
        create.onClick(() -> {
//            NakamaSessionManager.unirMatch(new )
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (matchCreated){
//            setScreen(new GameScreen());
        }
    }
    //    Stage stage;

//    @Override
//    public void show() {
//        super.show();
//        ImageButton.ImageButtonStyle buttonStartStyle = new ImageButton.ImageButtonStyle();
//        buttonStartStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_start.png")));
//        ImageButton buttonStart = new ImageButton(buttonStartStyle);
//
//        buttonStart.setPosition(200, 200);
//        buttonStart.setSize(64*3, 64*3);
//        buttonStart.addListener(new InputListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
////                setScreen(new GameScreen(game));
//                return true;
//            }
//        });
//
//        Gdx.input.setInputProcessor(stage = new Stage());
//        stage.addActor(buttonStart);
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        stage.act();
//        stage.draw();
//    }
}
