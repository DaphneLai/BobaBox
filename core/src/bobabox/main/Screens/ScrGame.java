package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import bobabox.main.Objects.Button;

import bobabox.main.GamMenu;

public class ScrGame implements Screen {

    GamMenu gammenu;
    Texture txtBg, txtPause;
    SpriteBatch batch;
    int nW, nH;
    Sprite sPause;
    Button button;
    private OrthographicCamera camera;
    private Vector3 touchPoint;


    public ScrGame(GamMenu _gammenu) {

        gammenu = _gammenu;
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
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        batch.begin();
//TEXTURES
        batch.draw(txtBg, 0, 0, nW, nH);
        batch.draw(txtPause, 200, 25, 50, 50);
        batch.end();
        sPause.setBounds(200, 25, 50, 50);
        button.ClickButton(sPause, 2, camera, touchPoint, gammenu); //pause button leads to menu right now
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
