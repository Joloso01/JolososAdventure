package com.mygdx.game.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.JadventureMain;
import com.mygdx.game.MyWidgets.MyScreen;
import com.mygdx.game.NakamaController.NakamaSessionManager;

public class LoadScreen extends MyScreen {

    SpriteBatch spriteBatch;
    Texture background;
    Stage stage;
    NakamaSessionManager nakamaSessionManager = new NakamaSessionManager();

    public LoadScreen(JadventureMain game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        spriteBatch = new SpriteBatch();
        background = new Texture("background.png");
    }

    @Override
    public void render(float delta) {
        if(!assets.update()){
            return;
        }
        setScreen(new LoginScreen(game,nakamaSessionManager));
//        setScreen(new MenuScreen(game));
//        spriteBatch.begin();
//
//        spriteBatch.draw(background, 0, 0, 640, 480);
//        spriteBatch.end();
    }
}
