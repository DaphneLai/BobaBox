package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.Objects.Button;

import bobabox.main.GamMenu;
import sun.security.jgss.GSSCaller;


public class ScrEnd implements Screen{
    GamMenu gamMenu;
    Texture txtBg;
    SpriteBatch batch;
    private OrthographicCamera camera;
    Button btnMenu;
    int nW, nH;

    public ScrEnd(GamMenu _gamMenu) {
        gamMenu = _gamMenu;
        txtBg = new Texture("Test_img.jpg");
        batch = new SpriteBatch();
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, nW, nH);
        btnMenu = new Button(nW/2 - 319, nH /2 - 638, "Home_btn.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        //TEXTURES
        batch.draw(txtBg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnMenu.draw(batch);
        batch.end();
        if(btnMenu.isMousedOver() && Gdx.input.isTouched()) {
            gamMenu.updateScreen(2);
        }
    }

            @Override
    public void resize(int width, int height) {

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

    }
}
