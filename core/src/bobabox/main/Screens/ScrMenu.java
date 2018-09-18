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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import bobabox.main.Objects.Button;

import static com.badlogic.gdx.Gdx.graphics;


public class ScrMenu implements Screen, InputProcessor {

    GamMenu gamMenu;
    //Values
    int nW = GamMenu.WORLD_WIDTH, nH = GamMenu.WORLD_HEIGHT;
    Vector3 vTouch;
    //Logic
    private OrthographicCamera camera; // what's seen
    private Viewport viewport; //how it's seen
    SpriteBatch batch;
    //Assets
    Texture txtBack;
    Button btnStart, btnTut, btnScratch;

    public ScrMenu(GamMenu _gamMenu, Viewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        Gdx.input.setInputProcessor(this);
        vTouch = new Vector3();
        viewport = _viewport;
        viewport.apply();
        camera = _camera;
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        resize(nW, nH);
        batch = new SpriteBatch();

        txtBack = new Texture("Main_bg.png");
        btnStart = new Button(nW / 2, nH / 3 + 50, 260, 70, "Start_btn.png");
        btnTut = new Button(nW / 2, nH / 4 + 10, 260, 70, "Tutorial_btn.png");
        btnScratch = new Button(nW / 2, 50, 110, 70, "Scratch_btn.png");
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
        batch.draw(txtBack, 0, 0, nW, nH);
        btnStart.draw(batch);
        btnTut.draw(batch);
        btnScratch.draw(batch);

        batch.end();

        //Button
        if (btnStart.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(0);
        }
        if (btnTut.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(3);
        }
        if (btnScratch.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(20);
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(vTouch.set(screenX, (screenY * (-1) + nH), 0));
        System.out.println("x: " + screenX);
        System.out.println("y: " + (screenY * -1 + GamMenu.WORLD_HEIGHT));

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
