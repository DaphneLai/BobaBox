package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjButton;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprServer;


//Sarah
//Help from Grondin & Daph
//Release 2.9 Scratch
public class SctWaiter implements Screen, InputProcessor {

    //Logic
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private StretchViewport viewport;
    Vector3 vTouch;
    //Assets
    private Texture txBG;
    private SprServer sprServer;
    private ObjTables objTable;
    private ObjButton btnHome;
    private GamMenu gamMenu;
    private ShapeRenderer sh;
    //Values
    private ObjTables arTables[] = new ObjTables[4];
    private float fWX, fWY;
    private boolean isTableClicked = false;

    public SctWaiter(GamMenu _gammenu) {
        gamMenu = _gammenu;

        //game height and width
        fWX = 1000;
        fWY = 500;

        //camera
        camera = new OrthographicCamera();
        vTouch = new Vector3();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen

        //textures
        txBG = new Texture("data/Test_img.jpg");

        //sprites
        batch = new SpriteBatch();

        //table
        objTable = new ObjTables(fWX / 2+100, fWY / 2, "data/TABLE2_obj.png", "data/TABLE22_obj.png", viewport);

        //server
        sprServer = new SprServer("data/SERVER1_spr.png", fWX / 2 * 0, fWY / 2 * 0, viewport); //850, 175

        //Buttons
        btnHome = new ObjButton(900, 30, 260 / 2, 70 / 2, "data/HOME1_btn.png", "data/HOME2_btn.png", viewport);

        //Shape Renderer
        //https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/graphics/glutils/ShapeRenderer.html
        sh = new ShapeRenderer();
    }


    @Override
    public void show() {

    }


    public void render(float delta) {

        //drawing
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(txBG, 0, 0);
        sprServer.draw(batch);
        objTable.draw(batch);
        btnHome.draw(batch);
        batch.end();

        //Draws Rectangle
        sh.begin(ShapeRenderer.ShapeType.Line);
        sh.setColor(0, 0, 0, 1);

        //Rectangle for Server
        sh.rect(sprServer.getX(), sprServer.getY(), sprServer.getWidth(), sprServer.getHeight());

        //Rectangle for Table
        sh.rect(objTable.getX(), objTable.getY(), objTable.getWidth(), objTable.getHeight());
        sh.end();

        //if home button clicked. Goes back to home screen
        if (btnHome.justClicked()) {
            gamMenu.updateScreen(2);
        }

        //Checks if mouse is over table and clicked
        if (objTable.isMousedOver() && Gdx.input.justTouched()) {
            isTableClicked=true;
        }

        //Make server go to table clicked
        if (isTableClicked == true) {
            sprServer.walk(objTable);
        }

        //Matches touched coordinates and screen coordinates
        if (Gdx.input.isTouched()) {
            vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            //Readjusts input coordinates (vTouch.x and vTouch.y are our new input coordinates)
            //Gdx.input.getX/Y >> vTouch.x/y
            viewport.unproject(vTouch);
            System.out.println("vTouchX: " + vTouch.x);
            System.out.println("vTouchY: " + vTouch.y);
        }


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
    public void dispose() {
        txBG.dispose();
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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

