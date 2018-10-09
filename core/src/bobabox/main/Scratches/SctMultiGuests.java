package bobabox.main.Scratches;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;

import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprGuest;



public class SctMultiGuests implements Screen {

    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    private Texture txtBG;
    private SprGuest sprGuest;
    private ObjTables objTable;
    boolean isSitting, isOpen =true;
    Vector3 vTouch;
    private List<SprGuest> sprGuests;
    private int nTimer = 0, nGst = 0, nSec = 300;

    public SctMultiGuests(Game _gammenu) {

        camera = new OrthographicCamera();
        vTouch = new Vector3();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        batch = new SpriteBatch();
        objTable = new ObjTables(200, 100, "data/TABLE3_obj.png", "data/TABLE32_obj.png");
        txtBG = new Texture(Gdx.files.internal("data/Test_img.jpg"));
        sprGuests = new ArrayList<SprGuest>();
        while (isOpen) {
            sprGuests.add(new SprGuest("data/GUEST1_spr.png", viewport));
            if (sprGuests.size() == 10) {
                isOpen = false;
            }
        }
    }

    @Override
    public void render(float delta) {
        //Logic
        nTimer++;
        System.out.println(nTimer);
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Drawing
        batch.draw(txtBG, 0, 0);
        objTable.draw(batch);
        addguest(0, batch);
        if (nTimer >= nSec) {
                nSec = nSec + 300;
                nGst++;
                addguest(nGst, batch);
        }
        batch.end();

        if (objTable.isOpen(sprGuests.get(nGst)) == false) {
            isSitting = true;
            sprGuests.get(nGst).sittingDown(isSitting);
        }
        if (Gdx.input.isTouched()) {
            viewport.unproject(vTouch.set(Gdx.input.getX(), (Gdx.input.getY() * (-1) + 500), 0));
        }


    }

    @Override
    public void dispose() {
        txtBG.dispose();
        batch.dispose();

    }
    public void addguest(int nGst, SpriteBatch batch) {
        sprGuests.get(nGst).walkDown();
        sprGuests.get(nGst).draw(batch);
        sprGuests.get(nGst).drag();
        sprGuests.get(nGst).heartTracker(batch);
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(1000 / 2, 500 / 2, 0);
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
