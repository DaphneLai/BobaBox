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
    private ObjTables objTable, objTable2;
    private boolean isSitting, isOpen = true;
    Vector3 vTouch;
    private int nTimer = 0, nGst = 0;
    private List<SprGuest> arliGuests;

    public SctMultiGuests(Game _gammenu) {

        //Logic
        camera = new OrthographicCamera();
        vTouch = new Vector3();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        batch = new SpriteBatch();

        //Assets
        objTable = new ObjTables(500, 250, "data/TABLE3_obj.png", "data/TABLE32_obj.png", viewport);
        // objTable2 = new ObjTables(800, 150, "data/TABLE2_obj.png", "data/TABLE22_obj.png", viewport);
        txtBG = new Texture(Gdx.files.internal("data/Test_img.jpg"));
        arliGuests = new ArrayList<SprGuest>();

        for (int i = 1; i <= 10; i++) {
            arliGuests.add(new SprGuest("data/GUEST1_spr.png", viewport));
        }

        System.out.println("LIST:" + arliGuests);
        System.out.println("SIZE:" + arliGuests.size());
    }

    @Override
    public void render(float delta) {

        //Logic
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nTimer++;
        System.out.println("TIME:" + nTimer);

        //Setting input
        if (Gdx.input.isTouched()) {
            viewport.unproject(vTouch.set(Gdx.input.getX(), (Gdx.input.getY() * (-1) + 500), 0));
        }

        //Drawing
        batch.draw(txtBG, 0, 0);
        objTable.draw(batch);
        // objTable2.draw(batch);
        updateGuest(nGst, batch);
        if (isOpen) {
            if (nTimer % 300 == 0) {
                if (nGst < 9) {
                    nGst++;
                } else {
                    nGst = 0;
                }
                nTimer = 0;
            }
        }
        System.out.println("nGST:" + nGst);
        batch.end();

//        if (objTable.isOpen(arliGuests.get(nGst)) == false) {
//            isSitting = true;
//            arliGuests.get(nGst).sittingDown(isSitting);
//        }
    }

    //Runs all of the SprGuests' functions
    private void updateGuest(int nGst, SpriteBatch batch) {
        for (int n = 0; n < nGst; n++) {
            arliGuests.get(n).draw(batch);
            arliGuests.get(n).walkDown();
            arliGuests.get(n).drag();
            arliGuests.get(n).heartTracker(batch);
            if (isOpen) {
                if (!objTable.isOpen(arliGuests.get(n))) {
                    isSitting = true;
                    isOpen = false;
//                    arliGuests.get(n).sittingDown(isSitting);
//                    objTable.sittingDown(isSitting)
                } else {
                    isSitting = false;
                }
                arliGuests.get(n).sittingDown(isSitting);
               objTable.sittingDown(isSitting);
            }
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
