package bobabox.main.Scratches;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    //Values
    private float fWORLD_WIDTH, fWORLD_HEIGHT;
    private boolean isSitting;
    private int nTimer = 0, nGst = 0;
    private List<SprCustomer> arliGuests;
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
        for (int i = 1; i <= 5; i++) {
            arliGuests.add(new SprCustomer("data/GUEST1_spr.png"));
        }

        System.out.println("LIST:" + arliGuests);
        System.out.println("SIZE:" + arliGuests.size());
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
//        if (isTFree) {
//            if (nTimer % 300 == 0) {
//                if (nGst < 9) {
//                    nGst++;
//                } else {
//                    nGst = 0;
//                }
//                nTimer = 0;
//            }
//        }
//        System.out.println("nGST:" + nGst);
        batch.end();

        //Buttons
        if (btnHome.justClicked()) {
            gamMenu.updateScreen(2);
        }
        if (nTimer % 500 == 0) {
            if (nGst < 3) {
                nGst++;
            } else {
                nGst = 0;
            }
            nTimer = 0;
        }
    }

    //Method runs through the array of tables
    private void updateTable() {
        for (int i = 0; i < 3; i++) {
            objTable = arTables[i];
            arTables[i].draw(batch);
        }
    }

    //Runs all of the SprCustomers' functions
    private void updateGuest(int nGst, SpriteBatch batch) {
        for (int n = 0; n < nGst; n++) {
            sprCst = arliGuests.get(n); //temporary Guest
            // dragGuest(sprCst);
            sprCst.updateStatus();
            sprCst.draw(batch);
            sprCst.hearts(batch, objTable);
        }
    }

//    private void dragGuest(SprCustomer sprCst) {
//        if (!isSitting) {
//            if (Gdx.input.isTouched()) {
//                sprCst.setX(vTouch.x);
//                sprCst.setY(vTouch.y);
//            }
//        }
//    }

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
        System.out.println("TOUCHED DOWN");
//      int nTarget = -1;
//        for (int n = 0; n < 1; n++) {
//            sprCst = arliGuests.get(n); //temporary Guest
//            if (sprCst.getBoundingRectangle().contains(vTouch)) {
//                nTarget = n;
//            }
//        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        sprCst.drag(vTouch, viewport);
        System.out.println("DRAGGED");
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
