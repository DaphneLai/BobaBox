package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import bobabox.main.Objects.Button;
import bobabox.main.GamMenu;
import bobabox.main.Objects.Hearts;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.Tables;


public class ScrGame implements Screen, InputProcessor {

    GamMenu gamMenu;
    //Values
    int nW = GamMenu.WORLD_WIDTH, nH = GamMenu.WORLD_HEIGHT;
    boolean isSitting = false;
    Vector3 vTouch;
    //Logic
    private OrthographicCamera camera;
    private Viewport viewport;
    SpriteBatch batch;
    //Assets
    Texture txtBg;
    private SprGuest sprGuest;
    Tables table;
    Button btnPause;
    Hearts hearts3, hearts2, hearts1, hearts0;

    public ScrGame(GamMenu _gamMenu, Viewport _viewport, OrthographicCamera _camera) {
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
        table = new Tables(nW / 2 + 40, nH / 3, "Table1_obj.png");
        btnPause = new Button(50, 25, 260, 70, "Pause_btn.png");
        hearts3 = new Hearts("Hearts-01.png");
        hearts2 = new Hearts("Hearts-02.png");
        hearts1 = new Hearts("Hearts-03.png");
        hearts0 = new Hearts("Hearts-04.png");
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
        table.draw(batch);
        sprGuest.Drag();
        sprGuest.walkDown();
        sprGuest.draw(batch);
        btnPause.draw(batch);

        //Hearts
        hearts3.walkDown();
        hearts3.Patience();
        if (hearts3.nHearts == 3) {
            hearts3.draw(batch);
        }
        if (hearts3.nHearts == 2) {
            hearts2.draw(batch);
            hearts2.setPosition(hearts3.getX(), hearts3.getY());
        }
        if (hearts3.nHearts == 1) {
            hearts1.draw(batch);
            hearts1.setPosition(hearts3.getX(), hearts3.getY());
        }
        if (hearts3.nHearts == 0) {
            hearts0.draw(batch);
            hearts0.setPosition(hearts3.getX(), hearts3.getY());
        }
        batch.end();

        //Button
        if (btnPause.isMousedOver() && Gdx.input.justTouched()) {
            System.out.println("Pause");
            gamMenu.updateScreen(1);
        }
        //Table
        table.isOpen(sprGuest);
        if (table.isOpen(sprGuest) == false && table.isTouch == false) {
            isSitting = true;
            System.out.println("is sitting =" + isSitting);
        }
        if (isSitting == true) {
            if (hearts3.isReady == true) {
                System.out.println("Ready to order");
            }
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
        System.out.println("y: " + (screenY * -1 + GamMenu.WORLD_HEIGHT));

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
