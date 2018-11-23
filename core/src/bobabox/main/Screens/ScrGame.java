package bobabox.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjBar;
import bobabox.main.Objects.ObjButton;
import bobabox.main.GamMenu;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprServer;

public class ScrGame implements Screen, InputProcessor {

    GamMenu gamMenu;
    //Values
    int nW, nH;
    private boolean isTableClicked = false, isDown = false;
    private boolean isSitting = false;
    private Vector3 vTouch;
    private int nGameTimer = 60, nFPS;
    private float fXG, fYG;
    //Logic
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    //Assets
    Texture txtBg;
    private SprGuest sprGuest;
    private SprServer sprServer;
    private ObjTables arTables[] = new ObjTables[3];
    private ObjButton btnPause;
    private BitmapFont bfFont;
    private ObjBar objBar;

    public ScrGame(GamMenu _gamMenu, StretchViewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        nW = gamMenu.WORLD_WIDTH;
        nH = gamMenu.WORLD_HEIGHT;
        Gdx.input.setInputProcessor(this);
        vTouch = new Vector3();
        viewport = _viewport;
        viewport.apply();
        camera = _camera;
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        resize(nW, nH);
        batch = new SpriteBatch();
        objBar = new ObjBar(viewport, new Rectangle(300, 300, 450, 80));
        txtBg = new Texture("data/GameBG_img.png");
        btnPause = new ObjButton(940, 40, 90, 55, "data/PAUSE1_btn.png", "data/PAUSE2_btn.png", viewport);
        bfFont = new BitmapFont(Gdx.files.internal("data/font.fnt"));
        bfFont.setColor(Color.DARK_GRAY);
        bfFont.getData().setScale(1f, 1f);
        //table
        arTables[0] = new ObjTables(280, 80, "data/TABLE1_obj.png", "data/TABLE12_obj.png", viewport);
        arTables[1] = new ObjTables(nW / 2, 50, "data/TABLE2_obj.png", "data/TABLE22_obj.png", viewport);
        arTables[2] = new ObjTables(nW - 280, 80, "data/TABLE3_obj.png", "data/TABLE32_obj.png", viewport);
        fXG = 850;
        fYG = 175;
    }


    @Override
    public void show() {
        return;
    }

    @Override
    public void render(float delta) {
        //SetUp
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nFPS++;
        if (nFPS % 60 == 0) {
            nGameTimer--;
        }
        if (nGameTimer == 0) {
            gamMenu.updateScreen(1);
        }

        //Drawing
        batch.draw(txtBg, 0, 0, nW, nH);
        btnPause.update(batch);
        sprServer.update(fXG, fYG, batch);
        updateTable();
        //sprGuest.walkDown(isDown);
        sprGuest.draw(batch);
//        sprGuest.drag();
        bfFont.draw(batch, Integer.toString(nGameTimer), nW - 100, nH - 138);
        //Checks if bar is clicked
        if (objBar.isTapped()) {
            System.out.println("Bar is touched");
            fXG = objBar.rBar().x;
            fYG = objBar.rBar().y - 50;
            sprServer.update(fXG, fYG, batch);
        }
        batch.end();

        if (objBar.isTapped()) {
            System.out.println("Bar is touched");
            //sprServer.up](objTable);
        }
        //ObjButton
        checkButtons();
        if (btnPause.isMousedOver() && Gdx.input.isTouched()) {
            System.out.println("Pause");
            gamMenu.updateScreen(1);
        }

    }

    //Method runs through the array of tables
    private void updateTable() {
        for (int i = 0; i < 3; i++) {
            arTables[i].draw(batch);
            sprGuest.hearts(batch, arTables[i]);

            // Checks if mouse is over table and clicked
            if (arTables[i].isTableClicked()) {
                fXG = arTables[i].getX() + (arTables[i].getWidth() / 2 - 40);
                fYG = arTables[i].getY() + arTables[i].getHeight();
                isTableClicked = true;
            }

            if (isTableClicked) {
                sprServer.update(fXG, fYG, batch);
            }


            //Debugging stuff
            if (arTables[0].isTableClicked()) {
                System.out.println("TABLE 11111111 WAS CLICKEDD");
            }
            if (arTables[1].isTableClicked()) {
                System.out.println("TABLE 22222222 WAS CLICKEDD");
            }
            if (arTables[2].isTableClicked()) {
                System.out.println("TABLE 33333333 WAS CLICKEDD");
            }

            //Table and guest interaction
            if (arTables[i].isAvb2(sprGuest) == false) {
                isSitting = true;
                sprGuest.sittingDown(isSitting);
                arTables[i].sittingDown(isSitting);
            } else if (arTables[i].isAvb2(sprGuest) == true) {
                isSitting = false;
                arTables[i].sittingDown(isSitting);
            }
        }
    }

    public void reset() {
        sprGuest = new SprGuest("data/GUEST1_spr.png", viewport);
        sprServer = new SprServer(850, 175);
        isTableClicked = false;
        nFPS = 0;
        nGameTimer = 60;
    }

    private void checkButtons() { // Checks if Buttons are pressed
        if (btnPause.justClicked()) {
            gamMenu.updateScreen(2);
        }
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
