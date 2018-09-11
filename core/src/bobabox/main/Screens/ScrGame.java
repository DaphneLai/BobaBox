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

public class ScrGame implements Screen {

    GamMenu gammenu;

    Texture txtBg;
    int nW, nH;
    SpriteBatch batch;
    private SprGuest sprGuest;
    Button btnPause;
    private OrthographicCamera camera;

    public ScrGame(GamMenu _gammenu) {
        gammenu = _gammenu;

        sprGuest = new SprGuest("Guest_spr.png");
        txtBg = new Texture("Test_img.jpg");
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        btnPause = new Button(200, 25, "Pause_btn.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @Override
    public void render(float delta) {
        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//TEXTURES
        batch.draw(txtBg, 0, 0, nW, nH);
        btnPause.draw(batch);
        sprGuest.Patience();
        sprGuest.walkDown();
        sprGuest.draw(batch);
        batch.end();
        if(btnPause.isMousedOver() && Gdx.input.isTouched()) {
            System.out.println("Pause");
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
    }
}
