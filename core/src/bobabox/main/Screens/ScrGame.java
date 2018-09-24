package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjButton;
import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjHearts;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.ObjTables;


public class ScrGame implements Screen, InputProcessor {

    GamMenu gamMenu;
    //Values
    int nW = 1000, nH = 500;
    public boolean isSitting = false;
    Vector3 vTouch;
    //Logic
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    //Assets
    Texture txtBg;
    private SprGuest sprGuest;
    ObjTables objTable;
    ObjButton btnPause;
    ObjHearts objHearts;

    public ScrGame(GamMenu _gamMenu, StretchViewport _viewport, OrthographicCamera _camera) {
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

        txtBg = new Texture("GameBG_img.png");
        sprGuest = new SprGuest("Guest_spr.png");
        objTable = new ObjTables(nW / 2 + 40, nH / 3, "Table1_obj.png");
        btnPause = new ObjButton(950, 40, 70, 70, "Pause_btn.png");
        objHearts = new ObjHearts();

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
        batch.draw(txtBg, 0, 0, nW, nH);
        objTable.draw(batch);
        sprGuest.Drag(isSitting);
        sprGuest.walkDown();
        sprGuest.draw(batch);
        btnPause.draw(batch);
        objHearts.walkDown();
        objHearts.Patience(batch, isSitting);

        batch.end();

        //ObjButton
        if (btnPause.isMousedOver() && Gdx.input.justTouched()) {
            System.out.println("Pause");
            gamMenu.updateScreen(1);
        }
        //Table
        if (objTable.isOpen(sprGuest) == false) {
            isSitting = true;
            System.out.println("is sitting =" + isSitting);
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(nW / 2, nH / 2, 0);
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
        txtBg.dispose();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(vTouch.set(screenX, (screenY * (-1) + nH), 0));
        System.out.println("x: " + screenX);
        System.out.println("y: " + (screenY * -1 + 500));

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
