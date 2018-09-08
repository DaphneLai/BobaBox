package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bobabox.main.GamMenu;

public class ScrGame implements Screen {

    GamMenu gammenu;
    Texture txtTest;
    SpriteBatch batch;
    int nW, nH;

    public ScrGame(GamMenu _gammenu) {

        gammenu = _gammenu;
        txtTest = new Texture("Test_img.jpg");
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
    }


    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        batch.begin();
//TEXTURES
        batch.draw(txtTest, 0, 0, nW, nH);
        batch.end();
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
