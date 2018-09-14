package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import bobabox.main.Objects.Button;
import bobabox.main.GamMenu;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.Tables;


public class ScrGame implements Screen {

    GamMenu gamMenu;
    //Values
    int nW, nH;
    float fTW, fTH;
    //Logic
    private OrthographicCamera camera;
    private Viewport viewport;
    SpriteBatch batch;
    //Assets
    Texture txtBg;
    private SprGuest sprGuest;
    Tables table;
    Button btnPause;

    public ScrGame(GamMenu _gamMenu, Viewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        viewport = _viewport;
        camera = _camera;
        resize(GamMenu.WIDTH,GamMenu.HEIGHT);
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        txtBg = new Texture("GameBG_img.png");
        sprGuest = new SprGuest("Guest_spr.png");
        table = new Tables(630, 178, "Table1_obj.png");
        btnPause = new Button(50, 25, "Pause_btn.png");
        fTW = table.getWidth();
        fTH = table.getHeight();
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
        sprGuest.Patience();
        sprGuest.walkDown();
        sprGuest.draw(batch);
        btnPause.draw(batch);

        batch.end();

        //Button
        if (btnPause.isMousedOver() && Gdx.input.justTouched()) {
            System.out.println("Pause");
        }
        table.isOpen(sprGuest);
    }

    @Override
    public void show() {
        return;
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
        txtBg.dispose();
    }
}
