package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import bobabox.main.Objects.Button;

import bobabox.main.GamMenu;
import bobabox.main.Sprites.SprGuest;

public class ScrGame implements Screen {

    GamMenu gammenu;

    Texture txtBg, txtPause;
    int nW, nH;
    SpriteBatch batch;
    Sprite sPause;
    private SprGuest sprGuest;
    Button button;
    private OrthographicCamera camera;
    private Vector3 touchPoint;


    public ScrGame(GamMenu _gammenu) {
        gammenu = _gammenu;

        sprGuest = new SprGuest("Guest_spr.png");
        txtBg = new Texture("Test_img.jpg");
        txtPause = new Texture("pause_btn.png");
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        sPause = new Sprite(txtPause);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touchPoint=new Vector3();
        button = new Button();
    }


    @Override
    public void render(float delta) {
        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//TEXTURES
        batch.draw(txtBg, 0, 0, nW, nH);
        batch.draw(txtPause, 200, 25, 50, 50);
        sprGuest.Patience();
        sprGuest.walkDown();
        sprGuest.draw(batch);
        batch.end();
        sPause.setBounds(200, 25, 50, 50);
        button.ClickButton(sPause, 2, camera, touchPoint, gammenu); //pause button leads to menu right now
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
    }
}
