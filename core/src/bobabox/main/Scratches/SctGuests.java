package bobabox.main.Scratches;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bobabox.main.Objects.ObjHearts;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprGuest;


public class SctGuests implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture txtBG;
    private SprGuest sprGuest;
    private  boolean isSitting = false;
    private ObjTables objTable;
    private ObjHearts objHearts;

    public SctGuests(Game _gammenu) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        txtBG = new Texture(Gdx.files.internal("Test_img.jpg"));
        sprGuest = new SprGuest("Guest_spr.png");
        objTable = new ObjTables(200, 100, "Table3_obj.png");
        objHearts = new ObjHearts();
    }

    @Override
    public void render(float delta) {
        //Logic
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //Drawing
        batch.draw(txtBG, 0, 0);
        objTable.draw(batch);
        sprGuest.Drag(isSitting);
        sprGuest.walkDown();
        sprGuest.draw(batch);
        objHearts.walkDown();
        objHearts.Patience(batch, isSitting);

        batch.end();


        if (objTable.isOpen(sprGuest) == false) {
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
