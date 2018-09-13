package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import bobabox.main.GamMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.Objects.Button;

public class ScrMenu implements Screen {
    GamMenu gamMenu;
    Texture txtBack;
    SpriteBatch batch;
    int nW, nH;
    private OrthographicCamera camera;
    Button btnStart, btnTut;


    public ScrMenu(GamMenu _gamMenu) {

        gamMenu = _gamMenu;
        txtBack = new Texture("Main_bg.png");
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnStart = new Button(nW/2 - 319/2, nH/2 - 110, "Start_btn.png");
        btnTut= new Button(nW/2 - 319/2, nH/2 - 200, "Tutorial_btn.png");
    }

    @Override
    public void show() {
    return;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        //TEXTURES
        batch.draw(txtBack, 0, 0, nW, nH);
        btnStart.draw(batch);
        btnTut.draw(batch);
        batch.end();
        if(btnStart.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(0);
        }if(btnTut.isMousedOver() && Gdx.input.justTouched()) {
            gamMenu.updateScreen(3);
        }

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
