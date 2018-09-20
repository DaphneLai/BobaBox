package bobabox.main.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjButton;


public class ScrMenu implements Screen, InputProcessor, ApplicationListener {
    GamMenu gamMenu;

    //Values
    int nW = 1000, nH = 500;
    Vector3 vTouch;
    //Logic
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    //Assets
    Texture txtBack;
    ObjButton btnStart, btnTut, btnScratch;

    public ScrMenu(GamMenu _gamMenu, StretchViewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        camera = _camera;
        camera.setToOrtho(false);
        viewport = _viewport;
        viewport.setCamera(camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        resize(nW, nH);
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);


        txtBack = new Texture("Main_bg.png");
        btnStart = new ObjButton(nW / 2, nH / 3 + 50, 260, 70, "Start_btn.png");
        btnTut = new ObjButton(nW / 2, nH / 4 + 10, 260, 70, "Tutorial_btn.png");
        btnScratch = new ObjButton(nW / 2, 50, 110, 70, "Scratch_btn.png");
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(width);
        System.out.println(height);
        viewport.update(width, height);
        camera.update();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();

        //Drawing
        batch.draw(txtBack, 0, 0, nW, nH);
        btnStart.draw(batch);
        btnTut.draw(batch);
        btnScratch.draw(batch);

        batch.end();

        //ObjButton
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
    public void create() {

    }

    @Override
    public void render() {

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
        System.out.println("y: " + (screenY * -1 + 500));

        return true;
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
