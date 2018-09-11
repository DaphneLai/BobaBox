package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import bobabox.main.Objects.Button;


public class ScrTut implements Screen {
    GamMenu gammenu;
    Texture txtBg, txtPlay, txtBack;
    SpriteBatch batch;
    Sprite sPlay, sBack;
    private OrthographicCamera camera;
    private Vector3 touchPoint;
    Button button;

    public ScrTut(GamMenu _gammenu) {
        gammenu = _gammenu;
        txtBg = new Texture("Test_img.jpg");
        batch = new SpriteBatch();
        txtPlay = new Texture("Play_btn.png");
        txtBack = new Texture("Back_btn.png");
        sPlay = new Sprite(txtPlay);
        sBack = new Sprite(txtBack);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touchPoint=new Vector3();
       // button = new Button();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        //TEXTURES
        batch.draw(txtBg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(txtPlay, 550, 25, 50, 50);
        batch.draw(txtBack, 50, 25, 50, 50);
        batch.end();
        sPlay.setBounds(550, 25, 50, 50);
        sBack.setBounds(50, 25, 50, 50);
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
