package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;

import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjButton;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprCustomer;

//NOT IN USE
public class SctMultiGuests implements Screen, InputProcessor {
    private GamMenu gamMenu;

    //Logic
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private StretchViewport viewport;
    private Vector2 vTouch;
    //Assets
    private Texture txtBG;
    private ObjButton btnHome;
    private ObjTables arTables[] = new ObjTables[3], objTable;
    private SprCustomer sprCustomer, sprCustSat;
    //Values
    private float fWORLD_WIDTH, fWORLD_HEIGHT;
    private boolean isSitting, isOpen = true, isReleased, isChecked;
    private int nTimer = 0, nGst = 0, nAdd, nTarget, nTable, nGoal;
    private List<SprCustomer> arliGuests;
    private List<SprCustomer> arliGuestsSat;
    private SprCustomer sprCst;

    public SctMultiGuests(GamMenu _gammenu) {
        gamMenu = _gammenu;


        //game height and width
        fWORLD_WIDTH = 1000;
        fWORLD_HEIGHT = 500;

        //Logic
        camera = new OrthographicCamera();
        vTouch = new Vector2();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        batch = new SpriteBatch();

        //table
        arTables[0] = new ObjTables(280, 80, "data/TABLE1_obj.png", "data/TABLE12_obj.png", viewport);
        arTables[1] = new ObjTables(fWORLD_WIDTH / 2, 50, "data/TABLE2_obj.png", "data/TABLE22_obj.png", viewport);
        arTables[2] = new ObjTables(fWORLD_WIDTH - 280, 80, "data/TABLE3_obj.png", "data/TABLE32_obj.png", viewport);

        txtBG = new Texture(Gdx.files.internal("data/Test_img.jpg"));
        btnHome = new ObjButton(900, 30, 260 / 2, 70 / 2, "data/HOME1_btn.png", "data/HOME2_btn.png", viewport);

        //guests
        arliGuests = new ArrayList<SprCustomer>();
        arliGuestsSat = new ArrayList<SprCustomer>();

        for (int i = 1; i <= 5; i++) {
            arliGuests.add(new SprCustomer("data/GUEST1_spr.png"));
        }

        //    System.out.println("LIST:" + arliGuests);
        //  System.out.println("SIZE:" + arliGuests.size());

        nGoal=5;
    }

    @Override
    public void render(float delta) {

        //set up
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(this);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nTimer++;
//        System.out.println("TIME:" + nTimer);

        //Drawing
        batch.draw(txtBG, 0, 0);
        btnHome.update(batch);
        updateTable();
        updateGuest(nGst, batch);
        batch.end();

        //Buttons
        if (btnHome.justClicked()) {
            gamMenu.updateScreen(2);
        }
        if (nTimer % 300 == 0) {
            if (nGst < nGoal) {
                nGst++;
            } else {
                nGst = 4;
            }
            nTimer = 0;
        }
      /**/  if (arliGuests.get(nTarget).isleaving()) {
            System.out.println("leaving");
            isSitting = false;
            arliGuests.get(nTarget).sittingDown(isSitting);
            arTables[nTable].sittingDown(isSitting);
        }

    }

    //Method runs through the array of tables
    private void updateTable() {
        for (int i = 0; i < 1; i++) {
            objTable = arTables[i];
            arTables[i].draw(batch);
        }
    }

    //Runs all of the SprCustomers' functions
    private void updateGuest(int nGst, SpriteBatch batch) {
        for (int n = 0; n < nGst; n++) {
            sprCustomer = arliGuests.get(n); //temporary Guest
            sprCustomer.draw(batch);
            sprCustomer.updateStatus(nGst);
            sprCustomer.entering(nGst);
            sprCustomer.hearts(batch, objTable);
        }
    }

    public void queue() {
        if (isSitting) {
            System.out.println(isSitting + " isSitting");
            arliGuestsSat.add(arliGuests.get(nTarget));
            sprCustSat = arliGuests.get(nTarget);
            System.out.println(arliGuestsSat.get(nTarget).sittingDown(isSitting));
            if (sprCustSat.sittingDown(isSitting)) {
                arliGuests.remove(nTarget);
                nGoal = arliGuests.size();
                //isSitting = false;
                for (int n = 0; n < nGoal; n++) {
                    //  System.out.println(arliGuests.indexOf(n));
                    System.out.println(n);
                    arliGuests.get(n).updateQueue(nGst);
                    isSitting=false;
                    // sprCustomer.updateQueue(nGst);
                }
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
        viewport.unproject(vTouch.set(Gdx.input.getX(), Gdx.input.getY()));
        for (int n = 0; n < arliGuests.size(); n++) {
            sprCst = arliGuests.get(n); //temporary Guest
            if (sprCst.getBoundingRectangle().contains(vTouch)) {
                nTarget = n;
            }
        }

        return true;

    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (!isSitting) {
            if (arTables[nTable].isAvb()) {
                if (arTables[nTable].getBoundingRectangle().overlaps(arliGuests.get(nTarget).getBoundingRectangle())) {
                    isSitting = true;
                }
                arliGuests.get(nTarget).sittingDown(isSitting);
                arTables[nTable].sittingDown(isSitting);
            }
        }


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (arliGuests.get(nTarget).getBoundingRectangle().contains(vTouch)) {
            arliGuests.get(nTarget).drag(vTouch, viewport);
        }
        for (int i = 0; i < 1; i++) {
            if (arTables[i].getBoundingRectangle().overlaps(arliGuests.get(nTarget).getBoundingRectangle())) {
                nTable = i;
            }
        }


        return true;
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
