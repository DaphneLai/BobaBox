package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.Objects.Button;

import bobabox.main.GamMenu;


public class ScrEnd implements Screen{
    GamMenu gammenu;
    Texture txtBg;
    SpriteBatch batch;
    private OrthographicCamera camera;
    Button btnMenu;

    public ScrEnd(GamMenu _gammenu) {
        gammenu = _gammenu;
        txtBg = new Texture("Test_img.jpg");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnMenu = new Button(550, 25, "Menu_btn.png");
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
            gammenu.updateScreen(2);
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
