package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bobabox.main.Objects.Button;


public class ScrTut implements Screen {

    GamMenu gamMenu;
    //Logic
    private OrthographicCamera camera;
    SpriteBatch batch;
    //Assets
    Texture txtBg;
    Button btnPlay, btnBack;

    public ScrTut(GamMenu _gamMenu) {
        gamMenu = _gamMenu;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        txtBg = new Texture("Test_img.jpg");
        btnPlay = new Button(550, 25, "Start_btn.png");
        btnBack = new Button(50, 25, "Back_btn.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing
        batch.draw(txtBg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnBack.draw(batch);
        btnPlay.draw(batch);

        batch.end();

        //Button
        if (btnPlay.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(0);
        }
        if (btnBack.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(2);
        }
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        txtBg.dispose();
    }
}
