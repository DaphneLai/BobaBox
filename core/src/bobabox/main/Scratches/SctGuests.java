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

import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjButton;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprGuest;


public class SctGuests implements Screen {
    GamMenu gamMenu;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    private Texture txtBG;
    private SprGuest sprGuest;
    private ObjTables objTable;
    private boolean isSitting;
    private Vector3 vTouch;
    private ObjButton btnHome;
    private boolean isDown=false;

    public SctGuests(GamMenu _gammenu) {
        gamMenu = _gammenu;

        camera = new OrthographicCamera();
        vTouch = new Vector3();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        batch = new SpriteBatch();
        txtBG = new Texture(Gdx.files.internal("data/Test_img.jpg"));
        sprGuest = new SprGuest("data/GUEST1_spr.png", viewport);
        objTable = new ObjTables(500, 250, "data/TABLE3_obj.png", "data/TABLE32_obj.png",viewport);
        btnHome = new ObjButton(900, 30, 260 / 2, 70 / 2, "data/HOME1_btn.png", "data/HOME2_btn.png", viewport);
    }

    @Override
    public void render(float delta) {
        //Logic
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Drawing
        batch.draw(txtBG, 0, 0);
        btnHome.update(batch);
        objTable.draw(batch);
        //sprGuest.walkDown(isDown);
        sprGuest.draw(batch);
        sprGuest.drag();
        sprGuest.hearts(batch, objTable);
        sprGuest.sittingDown(isSitting);

        batch.end();

        if (!objTable.isAvb(sprGuest)) {
           // System.out.println("HERE!");
            isSitting = true;
           // sprGuest.sittingDown(isSitting);
        } else if (objTable.isAvb(sprGuest)){
           // System.out.println("LEAVING");
            isSitting = false;
           // sprGuest.sittingDown(isSitting);
        }

        if (Gdx.input.isTouched()) {
            viewport.unproject(vTouch.set(Gdx.input.getX(), (Gdx.input.getY() * (-1) + 500), 0));
        }

        //Buttons
        if(btnHome.justClicked()){
            gamMenu.updateScreen(2);
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
