package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import bobabox.main.GamMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ScrMenu implements Screen, InputProcessor {
    GamMenu gammenu;
    Texture txtBack, txtPlay, txtTut;
    SpriteBatch batch;
    int nW, nH;
    private OrthographicCamera camera;
    private Vector3 touchPoint;
    Sprite sPlay, sTut;
    ScrGame scrGame;


    public ScrMenu(GamMenu _gammenu) {

        gammenu = _gammenu;
        txtBack = new Texture("Test_img.jpg");
        txtPlay = new Texture("Play_btn.png");
        txtTut = new Texture("Tutorial_btn.png");
        sPlay = new Sprite(txtPlay);
        sTut = new Sprite(txtTut);
        nW = Gdx.graphics.getWidth();
        nH = Gdx.graphics.getHeight();
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        touchPoint=new Vector3();
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
        batch.draw(txtPlay, 50, 25, 50, 50);
        batch.draw(txtTut, 550, 25, 50, 50);
        batch.end();
        sPlay.setBounds(50, 25, 50, 50);
        sTut.setBounds(550, 25, 50, 50);
        Button(sPlay, sTut);
    }

    public void Button(Sprite sButton, Sprite sButton2) {
        if (Gdx.input.isTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (sButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                System.out.println("Play");
                gammenu.updateScreen(0);
            }if (sButton2.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
               System.out.println("Tutorial");
                gammenu.updateScreen(3);
            }
            if (sButton2.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                System.out.println("Tutorial");
            }
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
