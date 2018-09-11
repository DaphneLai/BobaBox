package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import bobabox.main.Objects.Button;

import bobabox.main.GamMenu;


public class ScrEnd implements Screen{
    GamMenu gammenu;
    Texture txtBg, txtMenu;
    SpriteBatch batch;
    Sprite sMenu;
    private OrthographicCamera camera;
    private Vector3 touchPoint;
    Button button;

    public ScrEnd(GamMenu _gammenu) {
        gammenu = _gammenu;
        txtBg = new Texture("Test_img.jpg");
        batch = new SpriteBatch();
        txtMenu = new Texture("Menu_btn.png");
        sMenu = new Sprite(txtMenu);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touchPoint=new Vector3();
        button = new Button();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        //TEXTURES
        batch.draw(txtBg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(txtMenu, 550, 25, 50, 50);
        batch.end();
        sMenu.setBounds(550, 25, 50, 50);
        button.ClickButton(sMenu, 2, camera, touchPoint, gammenu);
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
