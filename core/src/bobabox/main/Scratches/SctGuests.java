package bobabox.main.Scratches;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import bobabox.main.Objects.Tables;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.Hearts;


public class SctGuests implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture txtBG;
    private SprGuest sprGuest;
    private  boolean isSitting = false;
    Tables tbl1;
    Hearts hearts;

    public SctGuests(Game _gammenu) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        txtBG = new Texture(Gdx.files.internal("Test_img.jpg"));
        sprGuest = new SprGuest("Guest_spr.png");
        tbl1 = new Tables(200, 100, "Table3_obj.png");
        hearts = new Hearts();
    }

    @Override
    public void render(float delta) {
        //Logic
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //Drawing
        batch.draw(txtBG, 0, 0);
        tbl1.draw(batch);
        sprGuest.Drag();
        sprGuest.walkDown();
        sprGuest.draw(batch);
        hearts.walkDown();
        hearts.Patience(batch, isSitting);

        batch.end();


        if (tbl1.isOpen(sprGuest) == false) {
            System.out.println("HERE!");
        }

    }

    @Override
    public void dispose() {
        txtBG.dispose();
        batch.dispose();

    }

    @Override
    public void show() {
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
}
