package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.GamMenu;
import bobabox.main.Objects.Tables;

public class SctWaiter implements Screen, InputProcessor{

    SpriteBatch batch;
    Texture txWaiter, txBG;
    Sprite sprWaiter, sprBg;
    OrthographicCamera oc;
    Tables tbl1;
    GamMenu gammenu;

    public SctWaiter(GamMenu _gammenu) {
        gammenu = _gammenu;
    }

    @Override

    public void show() {
        batch = new SpriteBatch();
        txWaiter = new Texture("Guest_spr.png");
        txBG = new Texture("Test_img.jpg");


        sprWaiter = new Sprite(txWaiter);
        sprWaiter.setSize(100, 100);
        sprWaiter.setFlip(false, true);

        sprBg = new Sprite(txBG);
        sprBg.setFlip(false, true);

        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();

        tbl1 = new Tables(200, 100, "rectangle-stroked.png");

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(oc.combined);
        batch.begin();
        batch.draw(txBG, 0,0 );
        sprWaiter.draw(batch);
        tbl1.draw(batch);
        if(tbl1.isMouseOver()==true){
            System.out.println("TABLE");
            if(Gdx.input.isTouched()){
                sprWaiter.translate(Gdx.input.getX(), Gdx.input.getY());
            }
        }
        batch.end();
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
    public boolean touchUp(int screenX, int screenY, int pointer, int table) {
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
