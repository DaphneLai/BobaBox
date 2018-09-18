package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import bobabox.main.Objects.Button;


public class ScrTut implements Screen {

    GamMenu gamMenu;
    //Values
    int nW = GamMenu.WORLD_WIDTH, nH = GamMenu.WORLD_HEIGHT;
    //Logic
    private OrthographicCamera camera;
    private Viewport viewport; //how it's seen
    SpriteBatch batch;
    //Assets
    Texture txtBg;
    Button btnStart, btnHome;

    public ScrTut(GamMenu _gamMenu, Viewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        viewport = _viewport;
        camera = _camera;
        resize(nW, nH);
        batch = new SpriteBatch();

        txtBg = new Texture("Test_img.jpg");
        btnStart = new Button(70, 70, 260/2, 70/2, "Start_btn.png");
        btnHome = new Button(70, 30, 260/2, 70/2,"Home_btn.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing
        batch.draw(txtBg, 0, 0, nW, nH);
        btnHome.draw(batch);
        btnStart.draw(batch);

        batch.end();

        //Button
        if (btnStart.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(0);
        }
        if (btnHome.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(2);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

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
