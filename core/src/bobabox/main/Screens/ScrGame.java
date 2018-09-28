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
import com.badlogic.gdx.graphics.g2d.Sprite;

import bobabox.main.Objects.ObjButton;
import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjHearts;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.ObjTables;


public class ScrGame implements Screen, InputProcessor {

    GamMenu gamMenu;
    //Values
    int nW = 1000, nH = 500;
    private float fWX, fWY, fDx = 0, fDy = 0, fTabX, fTabY, fWaitX, fWaitY;
    private boolean isAtTable;
    private int nDirectionCheck = 0; //0 = not moving 1 = moving left/right, 2 = moving up/down OF WAITER
    public boolean isSitting = false;
    Vector3 vTouch;
    //Logic
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    //Assets
    Texture txtBg, txWaiter;
    private SprGuest sprGuest;
    private Sprite sprWaiter;
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

        txtBg = new Texture("GameBG_img.png");
        txWaiter = new Texture("Waiter_spr.png");
        sprWaiter = new Sprite(txWaiter);
        sprWaiter.setSize(100, 120);
        fWaitX = fWX / 2 + 850;
        fWaitY = fWY / 2 + 175;
        sprWaiter.setPosition(fWaitX, fWaitY);
        sprGuest = new SprGuest("Guest_spr.png", viewport);
        objTable = new ObjTables(nW / 2 + 40, nH / 3, "Table1_obj.png");
        btnPause = new ObjButton(950, 40, 70, 70, "Pause_btn.png", viewport);
       // objHearts = new ObjHearts();

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
        sprWaiter.draw(batch);
        objTable.draw(batch);
        sprGuest.walkDown();
        sprGuest.draw(batch);
        sprGuest.drag();
        sprGuest.heartTracker(batch);
        btnPause.draw(batch);
        batch.end();

        //ObjButton
        if (btnPause.isMousedOver() && Gdx.input.justTouched()) {
            System.out.println("Pause");
            gamMenu.updateScreen(1);
        }
        //Mouse is over table && Clicked
        if (objTable.isMouseOver() == true && Gdx.input.justTouched()) {
            nDirectionCheck = 1;
        }
        //Table
        if (objTable.isOpen(sprGuest) == false) {
            isSitting = true;
            System.out.println("is sitting =" + isSitting);
        }

        //Waiter constantly travels LEFT/RIGHT until aligned w/ table
        if (nDirectionCheck == 1) {
            isAtTable = CheckPosX(sprWaiter.getX(), objTable.getX());
            fDx = 1; // speed of waiter
            fTabX = objTable.getX();
            fTabY = objTable.getY();

            if (isAtTable == false) {
                fTabX += 60;
                if (fWaitX < fTabX) {
                    fWaitX += fDx;
                    sprWaiter.setX(fWaitX);
                } else if (fWaitX > fTabX) {
                    fWaitX -= fDx;
                    sprWaiter.setX(fWaitX);
                }
            } else {
                fDx = 0;
                nDirectionCheck = 2;

            }
        }
        //Waiter constantly travels UP/DOWN until aligned w/ table
        if (nDirectionCheck == 2) {
            isAtTable = CheckPosY(sprWaiter.getY(), objTable.getY());
            fDy = 1;
            fTabX = objTable.getX();
            fTabY = objTable.getY();
            if (isAtTable == false) {
                fTabY += 75;
                if (fTabY < fWaitY) {
                    fWaitY -= fDy;
                    sprWaiter.setY(fWaitY);
                } else {
                    fWaitY += fDy;
                    sprWaiter.setY(fWaitY);
                }
            } else {
                fDy = 0;
                nDirectionCheck = 0;

            }
        }

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(nW / 2, nH / 2, 0);
    }

    //Checks if Waiter X and Table X is equal
    public static boolean CheckPosX(float fXWait, float fXTab) {
        if (Math.round(fXWait) != Math.round(fXTab) + 60) {
            return false;
        } else {
            return true;
        }
    }

    //Checks if Waiter Y and Table Y is equal
    public static boolean CheckPosY(float fYWait, float fYTab) {
        if (Math.round(fYWait) != Math.round(fYTab) + 75) {
            return false;
        } else {

            return true;
        }
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
