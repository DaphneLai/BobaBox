package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bobabox.main.Objects.Button;


public class ScrMenu implements Screen {

    GamMenu gamMenu;
    //Values
    int nW, nH;
    //Logic
    private OrthographicCamera camera;
    SpriteBatch batch;
    //Assets
    Texture txtBack;
    Button btnStart, btnTut;

    public ScrMenu(GamMenu _gamMenu) {
        gamMenu = _gamMenu;

        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        txtBack = new Texture("Main_bg.png");
        btnStart = new Button(nW / 2 - 319 / 2, nH / 2 - 110, "Start_btn.png");
        btnTut = new Button(nW / 2 - 319 / 2, nH / 2 - 200, "Tutorial_btn.png");
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing
        batch.draw(txtBack, 0, 0, nW, nH);
        btnStart.draw(batch);
        btnTut.draw(batch);

        batch.end();

        //Button
        if (btnStart.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(0);
        }
        if (btnTut.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(3);
        }
    }


    @Override
    public void resize(int width, int height) {
        return;
    }

    @Override
    public void pause() {
        return;
    }

    @Override
    public void resume() {
        return;
    }

    @Override
    public void hide() {
        return;
    }

    @Override
    public void dispose() {
        batch.dispose();
        txtBack.dispose();
    }

}
