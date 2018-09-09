package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import bobabox.main.GamMenu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;


public class ScrTut implements Screen {
    GamMenu gammenu;
    Texture txtBg, txtPlay, txtBack;
    SpriteBatch batch;
    Sprite sPlay, sBack;
    private OrthographicCamera camera;
    private Vector3 touchPoint;

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
        Button(sPlay, sBack);
    }
    private void Button(Sprite sButton, Sprite sButton2) {
        if (Gdx.input.isTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (sButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
               System.out.println("Play");
                gammenu.updateScreen(0);
            } if (sButton2.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                   System.out.println("Menu");
                gammenu.updateScreen(2);
            }
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
