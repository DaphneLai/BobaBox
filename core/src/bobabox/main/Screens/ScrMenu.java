package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xpath.internal.operations.Or;

import bobabox.main.Objects.Button;

import static com.badlogic.gdx.Gdx.graphics;


public class ScrMenu implements Screen {

    GamMenu gamMenu;
    //Values
    int nW, nH;
    //Logic
    private OrthographicCamera camera; // what's seen
    private Viewport viewport; //how it's seen
    SpriteBatch batch;
    //Assets
    Texture txtBack;
    Button btnStart, btnTut;

    public ScrMenu(GamMenu _gamMenu, Viewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        viewport = _viewport;
        camera = _camera;
        resize(GamMenu.WIDTH, GamMenu.HEIGHT);
        nW = graphics.getWidth();
        nH = graphics.getHeight();

        batch = new SpriteBatch();

        txtBack = new Texture("Main_bg.png");
        btnStart = new Button(0, 0, "Start_btn.png");
        btnTut = new Button(nW / 2, nH / 2, "Tutorial_btn.png");
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing
        batch.draw(txtBack, 0, 0, GamMenu.WIDTH, GamMenu.HEIGHT);
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
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

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
