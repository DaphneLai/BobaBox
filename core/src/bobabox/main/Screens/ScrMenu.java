package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import bobabox.main.GamMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.Objects.Button;

public class ScrMenu implements Screen, InputProcessor {
    GamMenu gammenu;
    Texture txtBack;
    SpriteBatch batch;
    int nW, nH;
    private OrthographicCamera camera;
    Button btnPlay, btnTut;


    public ScrMenu(GamMenu _gammenu) {

        gammenu = _gammenu;
        txtBack = new Texture("Test_img.jpg");
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        btnPlay = new Button(50, 25, "Play_btn.png");
        btnTut= new Button(550, 25, "Tutorial_btn.png");
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
        btnPlay.draw(batch);
        btnTut.draw(batch);
        batch.end();
        if(btnPlay.isMousedOver() && Gdx.input.justTouched()) {
            gammenu.updateScreen(0);
        }if(btnTut.isMousedOver() && Gdx.input.justTouched()) {
            gammenu.updateScreen(3);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
