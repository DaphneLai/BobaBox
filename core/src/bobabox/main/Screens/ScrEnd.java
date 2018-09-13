package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.Objects.Button;

import bobabox.main.GamMenu;
import sun.security.jgss.GSSCaller;



public class ScrEnd implements Screen {

    GamMenu gamMenu;
    //Values
    int nW, nH;
    //Logic
    private OrthographicCamera camera;
    SpriteBatch batch;
    //Assets
    Texture txtBg;
    Button btnMenu;

    public ScrEnd(GamMenu _gamMenu) {
        gamMenu = _gamMenu;

        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, nW, nH);
        batch = new SpriteBatch();

        txtBg = new Texture("Test_img.jpg");
        btnMenu = new Button(nW/2 - 319, nH /2 - 638, "Home_btn.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing
        batch.draw(txtBg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnMenu.draw(batch);

        batch.end();

        //Button
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
    batch.dispose();
    txtBg.dispose();
    }
}
