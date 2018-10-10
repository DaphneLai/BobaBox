package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import bobabox.main.Objects.ObjButton;
import bobabox.main.GamMenu;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprServer;


public class ScrGame implements Screen, InputProcessor {

    GamMenu gamMenu;
    //Values
    int nW = 1000, nH = 500;
    private float fWX, fWY;
    private boolean isTableClicked;
    public boolean isSitting = false;
    Vector3 vTouch;
    //Logic
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    //Assets
    Texture txtBg;
    private SprGuest sprGuest;
    private SprServer sprServer;
    private ObjTables objTable;
    private ObjButton btnPause;

    public ScrGame(GamMenu _gamMenu, StretchViewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        Gdx.input.setInputProcessor(this);
        vTouch = new Vector3();
        viewport = _viewport;
        viewport.apply();
        camera = _camera;
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        resize(nW, nH);
        batch = new SpriteBatch();
        txtBg = new Texture("data/GameBG_img.png");
        objTable = new ObjTables(nW / 2 + 40, nH / 3, "data/TABLE2_obj.png","data/TABLE22_obj.png");
        btnPause = new ObjButton(950, 40, 110, 70, "data/PAUSE1_btn.png", "data/PAUSE2_btn.png", viewport);

    }


    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing
        batch.draw(txtBg, 0, 0, nW, nH);
        btnPause.draw(batch);
        sprServer.draw(batch);
        objTable.draw(batch);
        sprGuest.walkDown();
        sprGuest.draw(batch);
        sprGuest.drag();
        sprGuest.heartTracker(batch);
        batch.end();

        //ObjButton
        if (btnPause.isMousedOver() && Gdx.input.isTouched()) {
            System.out.println("Pause");
            gamMenu.updateScreen(1);
        }
        //Mouse is over table && Clicked
        if (objTable.isMouseOver() == true && Gdx.input.isTouched()) {
            isTableClicked=true;
        }
        //Make server go to table clicked
        if(isTableClicked==true){
            sprServer.walk(objTable);
        }
        //Table
        if (objTable.isOpen(sprGuest) == false) {
            isSitting = true;
            sprGuest.sittingDown(isSitting);
            objTable.sittingDown(isSitting);
        }else if (objTable.isOpen(sprGuest) == true){
            isSitting = false;
            objTable.sittingDown(isSitting);
        }



    }
    public void reset() {
        sprGuest = new SprGuest("data/GUEST1_spr.png", viewport);
        sprServer = new SprServer("data/SERVER1_spr.png",fWX/2+850, fWY/2+175); //850, 175
        isTableClicked=false;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(nW / 2, nH / 2, 0);
    }

    @Override
    public void pause() {
        return;
    }

    @Override
    public void resume() {
        return;
    }

    @Override
    public void hide() {
        return;
    }

    @Override
    public void dispose() {
        batch.dispose();
        txtBg.dispose();
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
        viewport.unproject(vTouch.set(screenX, (screenY * (-1) + nH), 0));
        System.out.println("x: " + screenX);
        System.out.println("y: " + (screenY * -1 + 500));

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
