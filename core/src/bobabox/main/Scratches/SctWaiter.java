package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprServer;

//Sarah
//Help from Grondin & Daph
//Screen is not used in game currently
//Release 2.5 and 2.6 scratch
public class SctWaiter implements Screen, InputProcessor {


    private OrthographicCamera oc;
    private SpriteBatch batch;
    private Texture txBG;
    private SprServer sprServer;
    private ObjTables objTable;
    private float fWX, fWY;
    private boolean isTableClicked = false;

    public SctWaiter(GamMenu _gammenu) {

        //game height and width
        fWX = 1000;
        fWY = 500;

        //camera
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();

        //textures
        txBG = new Texture("Test_img.jpg");

        //sprites
        batch = new SpriteBatch();
        sprServer = new SprServer("Waiter_spr.png", fWX / 2-200, fWY / 2 + 200); //850, 175

        objTable = new ObjTables(fWX / 2 + 100, fWY / 2 - 100, "Table2_obj.png");


    }


    @Override
    public void show() {

    }

    public void render(float delta) {
        //drawing
        batch.setProjectionMatrix(oc.combined);
        batch.begin();
        batch.draw(txBG, 0, 0);
        sprServer.draw(batch);
        objTable.draw(batch);
        batch.end();
        if (objTable.isMouseOver() == true && Gdx.input.justTouched()) {
            isTableClicked = true;
        }
        //Make server go to table clicked
        if (isTableClicked == true) {
            sprServer.walk(objTable);
        }


    }

    @Override
    public void resize(int width, int height) {

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

//405.5, 195.0