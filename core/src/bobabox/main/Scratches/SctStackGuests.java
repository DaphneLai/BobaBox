package bobabox.main.Scratches;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import java.util.ArrayList;
import java.util.List;
import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjButton;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprGuest;

//NOT IN USE
//Help from Grondin & Daph
public class SctStackGuests implements Screen {
    GamMenu gamMenu;

    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    private Texture txtBG;
    private ObjTables objTable;
    private boolean isSitting, isTFree = true, isDown = false;
    Vector3 vTouch;
    private SprGuest sprGst;
    private int nTimer = 0, nGst = 0;
    private List<SprGuest> arliGuests;
    private List<SprGuest> arliGuestsSat;
    private ObjButton btnHome;
    private ShapeRenderer sh;
    private float fGoalY = 30, fY, fH;


    public SctStackGuests(GamMenu _gammenu) {
        gamMenu = _gammenu;

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
        arliGuestsSat = new ArrayList<SprGuest>();
        btnHome = new ObjButton(900, 30, 260 / 2, 70 / 2, "data/HOME1_btn.png", "data/HOME2_btn.png", viewport);

        for (int i = 1; i <= 10; i++) {
            arliGuests.add(new SprGuest("data/GUEST1_spr.png", viewport));
        }

        System.out.println("LIST:" + arliGuests);
        System.out.println("SIZE:" + arliGuests.size());

        sh = new ShapeRenderer();

    }

    @Override
    public void render(float delta) {

        //Logic
        camera.update();
        batch.begin();
        sh.begin(ShapeRenderer.ShapeType.Line);
        sh.setColor(0, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nTimer++;
        //System.out.println("TIME:" + nTimer);

        //Setting input
        if (Gdx.input.isTouched()) {
            viewport.unproject(vTouch.set(Gdx.input.getX(), (Gdx.input.getY() * (-1) + 500), 0));
        }

        //Drawing
        batch.draw(txtBG, 0, 0);
        objTable.draw(batch);
        btnHome.update(batch);
        updateGuest(nGst, batch);
        if (isTFree) {
            if (nTimer % 300 == 0) {
                if (nGst < 9) {
                    nGst++;
                } else {
                    nGst = 0;
                }
                nTimer = 0;
            }
        }
       // System.out.println("nGST:" + nGst);
        batch.end();
        sh.end();


        //Buttons
        if (btnHome.justClicked()) {
            gamMenu.updateScreen(2);
        }

    }


    //Runs all of the SprGuests' functions
    private void updateGuest(int nGst, SpriteBatch batch) {
        for (int n = 0; n < nGst; n++) {
            // System.out.println(n + " N");
            sprGst = arliGuests.get(n); //temporary Guest
            sprGst.draw(batch);
            sprGst.walkDown(fGoalY);
            sprGst.drag();
            sprGst.sittingDown(isSitting);
            sprGst.hearts(batch, objTable);

            if (objTable.isAvb2(sprGst)) {
                isSitting = false;
                isTFree = true;
            } else if (!objTable.isAvb2(sprGst)) {
                isSitting = true;
                isTFree = false;
                sprGst.sittingDown(isSitting);
            }

            //Goal is restarted and updated to multiply by the guest height.
            //30 is added since the very first guest is less than or equal to the coordinate 30
            if (n > 0) {
                fGoalY=0;
                fGoalY = 30+ (100 * nGst);
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

