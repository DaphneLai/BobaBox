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

//https://stackoverflow.com/questions/43150890/array-list-for-sprites-in-android-game-app
public class SctMultiGuests implements Screen {

    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    private Texture txtBG;
    Vector3 vTouch;


    private SprGuest sprGuest;
    private ObjTables objTable;
    boolean isSitting, isOpen = true;
    private ArrayList<SprGuest> arliGuests;
    private int nTimer = 0, nGuest = 0, nSec = 300, nArraySize;

    public SctMultiGuests(Game _gammenu) {
        camera = new OrthographicCamera();
        vTouch = new Vector3();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        batch = new SpriteBatch();
        objTable = new ObjTables(500, 250, "TABLE3_obj.png", "TABLE32_obj.png");
        txtBG = new Texture(Gdx.files.internal("Test_img.jpg"));
        sprGuest = new SprGuest("GUEST1_spr.png", viewport);

        //Setting up ArrayList
        arliGuests = new ArrayList<SprGuest>(10); //10 is the initial size
        public void getArrayListSize() {
            if (arliGuests.isEmpty()) {
                // nothing
            } else {
                nArraySize = arliGuests.size();
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
        addGuest(batch);

        if (nTimer >= nSec) {
            System.out.println("nGuest = " + nGuest);
            nGuest++;
            addGuest(batch);
            //nSec = nSec + 300;
        }
        batch.end();

        if (objTable.isOpen(arliGuests.get(1)) == false) {
            isSitting = true;
            arliGuests.get(1).sittingDown(isSitting);
            objTable.sittingDown(isSitting);
        } else if (objTable.isOpen(arliGuests.get(1)) == true) {
            isSitting = false;
            arliGuests.get(1).sittingDown(isSitting);
            objTable.sittingDown(isSitting);
        }

        if (Gdx.input.isTouched()) {
            viewport.unproject(vTouch.set(Gdx.input.getX(), (Gdx.input.getY() * (-1) + 500), 0));
        }


    }


    public void addGuest(SpriteBatch batch) {
        sprGuest.walkDown();
        sprGuest.draw(batch);
        sprGuest.drag();
        sprGuest.heartTracker(batch);
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
