package bobabox.main.Screens;

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


public class ScrMenu implements Screen, InputProcessor {
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
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        resize(nW, nH);
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        txtBack = new Texture("data/Main_bg.png");
        btnStart = new ObjButton(nW / 2, nH / 3 + 50, 260, 70, "data/START1_btn.png", "data/START2_btn.png", viewport);
        btnTut = new ObjButton(nW / 2, nH / 4 + 10, 260, 70, "data/TUTORIAL1_btn.png", "data/TUTORIAL2_btn.png", viewport);
        btnScratch = new ObjButton(nW / 2, 50, 110, 70, "data/SCRATCH1_btn.png", "data/SCRATCH2_btn.png", viewport);
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
        //Set-up
        Gdx.input.setInputProcessor(this);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();


        //Drawing
        batch.draw(txtBack, 0, 0, nW, nH);
        btnStart.draw(batch);
        btnTut.draw(batch);
        btnScratch.draw(batch);

        batch.end();

        //Buttons
        checkButtons();
    }

    private void checkButtons(){ // Checks if Buttons are pressed
        checkButtonTextures();
        if (btnStart.bJustClicked()) {
            gamMenu.updateScreen(0);
        }
        if (btnTut.bJustClicked()) {
            gamMenu.updateScreen(3);
        }
        if (btnScratch.bJustClicked()){
            gamMenu.updateScreen(20);
        }
    }

    private void checkButtonTextures(){
        if(btnStart.isMousedOver()){
            btnStart.changeTexture(1);
        } else {
            btnStart.changeTexture(0);
        }
        if(btnTut.isMousedOver()){
            btnTut.changeTexture(1);
        } else {
            btnTut.changeTexture(0);
        }
        if(btnScratch.isMousedOver()){
            btnScratch.changeTexture(1);
        } else {
            btnScratch.changeTexture(0);
        }
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
        vTouch = new Vector3(screenX, screenY, 0);
        //Readjusts input coordinates (vTouch.x and vTouch.y are our new input coordinates)
        //Gdx.input.getX/Y >> vTouch.x/y
        viewport.unproject(vTouch);
        System.out.println("vTouchX: " + vTouch.x);
        System.out.println("vTouchY: " + vTouch.y);

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
