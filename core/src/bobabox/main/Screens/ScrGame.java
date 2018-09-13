package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    SpriteBatch batch;
    //Assets
    Texture txtBg;
    private SprGuest sprGuest;
    Tables table;
    Button btnPause;

    public ScrGame(GamMenu _gamMenu) {
        gamMenu = _gamMenu;

        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        txtBg = new Texture("Test_img.jpg");
        sprGuest = new SprGuest("Guest_spr.png");
        table = new Tables(nW/2, nH/2, "Table1_obj.png");
        btnPause = new Button(50, 25, "Pause_btn.png");

        fTW = table.getWidth();
        fTH = table.getHeight();
    }


    @Override
    public void render(float delta) {

        batch.begin();
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
        //Tables
        if (table.isOpen(sprGuest) == false) {
            table.setBounds(nW/2, nH/2, fTW, fTH);
            if (Gdx.input.isTouched()) {
                table.setBounds(nW/2 - 35, nH/2 - 20, fTW + 50, fTH + 50);
            }
        }
        if (table.isOpen(sprGuest) == true) {
            table.setBounds(nW/2, nH/2, fTW, fTH);
        }
    }

    @Override
    public void show() {
        return;
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
        txtBg.dispose();
    }
}
