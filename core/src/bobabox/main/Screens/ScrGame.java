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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;

import bobabox.main.Objects.ObjBar;
import bobabox.main.Objects.ObjButton;
import bobabox.main.GamMenu;
import bobabox.main.Sprites.SprCustomer;
import bobabox.main.Sprites.SprGuest;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprServer;

public class ScrGame implements Screen, InputProcessor {
    private GamMenu gamMenu;

    //Values
    private Vector2 vTouch;
    private boolean bArrived = false, bHasOrder = false, bDragging; //boolean for server
    private boolean isTableClicked = false, isSitting, bCustSat = false, bGstDrag= false; //boolean for guests
    private int nW, nH, nGameTimer = 60, nTable; //int for game
    private int nFPS, nStatGst, nClickedBar = 0; //int for server
    private int nTimer = 0, nGst = 0, nTarget, nGoal; //int for guests
    private float fXG, fYG;
    //Logic
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;
    //Assets
    private Texture txtBg, txtStats;
    private List<SprCustomer> arliGuests, arliGuestsSat;
    private SprCustomer sprCustomer, sprCst, sprCustMove;
    private SprServer sprServer;
    private ObjTables arTables[] = new ObjTables[3], objTable;
    private ObjButton btnPause;
    private BitmapFont bfFont;
    private ObjBar objBar;

    public ScrGame(GamMenu _gamMenu, StretchViewport _viewport, OrthographicCamera _camera) {
        gamMenu = _gamMenu;

        //game world
        nW = gamMenu.WORLD_WIDTH;
        nH = gamMenu.WORLD_HEIGHT;
        vTouch = new Vector2();
        viewport = _viewport;
        viewport.apply();
        camera = _camera;
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen
        resize(nW, nH);
        batch = new SpriteBatch();

        //assets
        objBar = new ObjBar(viewport, new Rectangle(300, 300, 450, 80));
        txtBg = new Texture("data/GameBG_img.png");
        txtStats = new Texture("data/STATS_img.png");
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

        //guests
        nGoal = 5;
        arliGuests = new ArrayList<SprCustomer>();
        arliGuestsSat = new ArrayList<SprCustomer>();
        for (int i = 1; i <= 5; i++) {
            arliGuests.add(new SprCustomer("data/GUEST1_spr.png", batch));
        }

    }

    @Override
    public void render(float delta) {
        //SetUp
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(this);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        nFPS++;
        nTimer++;
        if (nFPS % 60 == 0) {
            nGameTimer--;
        }
        if (nGameTimer == 0) {
            gamMenu.updateScreen(1);
        }

        //Drawing
        batch.draw(txtBg, 0, 0, nW, nH);
        btnPause.update(batch);
        sprServer.update(fXG, fYG, batch,bGstDrag);
        bfFont.draw(batch, Integer.toString(nGameTimer), nW - 100, nH - 138);


        //Checks if bar is clicked
        if (objBar.isTapped()) {
            nClickedBar++;
            fXG = objBar.rBar().x + 1;
            fYG = objBar.rBar().y - 20;
            sprServer.update(fXG, fYG, batch, bGstDrag);
        }

        if (isTableClicked) {
            sprServer.update(fXG, fYG, batch, bGstDrag);
            this.bArrived = sprServer.arrived();
            //server can take order from customer
            if(nStatGst == 3){
                if(bArrived && nClickedBar == 0){
                    bHasOrder = true;
                }
            }
        }
        sprServer.carryDrink(batch, bHasOrder, nClickedBar);
//        System.out.println("1 bar was clicked: " + nClickedBar);
//        System.out.println("2 picked up order: " + bHasOrder);

        bfFont.draw(batch, Integer.toString(nGameTimer), nW - 100, nH - 135);
        batch.draw(txtStats,nW - 200, nH - 165, 200, 150);
        bfFont.draw(batch, Integer.toString(nGameTimer), nW - 100, nH - 135);
       // queue();
        updateTable();
        updateGuest(nGst, batch);
        if (bDragging) {
            sprCustMove.draw(batch);
            sprCustMove.hearts(objTable);
            sprCustMove.updateStatus(isSitting);
        }
        batch.end();

        //Timer for Guests to enter
        if (nTimer % 300 == 0) {
            if (nGst < nGoal) {
                nGst++;
            }
            nTimer = 0;
        }

        //ObjButton
        checkButtons();
        if (btnPause.isMousedOver() && Gdx.input.isTouched()) {
            System.out.println("Pause");
            gamMenu.updateScreen(1);
        }
       if (arliGuests.get(nTarget).isLeaving()) {
            isSitting = false;
           arliGuests.get(nTarget).sittingDown(isSitting);
           arTables[nTable].sittingDown(isSitting);
           arTables[nTable].leave(isSitting);
       }

    }

    //Method runs through the array of tables
    private void updateTable() {
        for (int i = 0; i < 3; i++) {
            objTable = arTables[i];
            arTables[i].draw(batch);
         //   arliGuestsSat.get(nTarget).hearts(arTables[i]);

            // Checks if mouse is over table and clicked
            if (arTables[i].isTableClicked()) {
                fXG = Math.round(arTables[i].getX() + (arTables[i].getWidth() / 2 - 40));
                fYG = Math.round(arTables[i].getY() + arTables[i].getHeight());

                isTableClicked = true;
            }
//            Table Debugging stuff
//            if (arTables[0].isTableClicked()) {
//                System.out.println("TABLE 11111111 WAS CLICKEDD");
//            }
//            if (arTables[1].isTableClicked()) {
//                System.out.println("TABLE 22222222 WAS CLICKEDD");
//            }
//            if (arTables[2].isTableClicked()) {
//                System.out.println("TABLE 33333333 WAS CLICKEDD");
//            }

            //Table and guest interaction

            if (!arTables[i].isAvb(sprCustMove)) {
                isSitting = true;
                arliGuestsSat.get(nTarget).sittingDown(isSitting);
                arTables[i].sittingDown(isSitting);
            } else if (arTables[i].isAvb(sprCustMove)) {
                isSitting = false;
                arTables[i].sittingDown(isSitting);
            }
        }
    }

    //Runs all of the SprCustomers' functions
    private void updateGuest(int nGst, SpriteBatch batch) {
        for (int n = 0; n < nGst; n++) {
            this.sprCustomer = arliGuests.get(n); //temporary Guest
            sprCustomer.draw(batch);
            sprCustomer.entering(nGst, n, bCustSat);
            sprCustomer.hearts(objTable);
        }
       /* if (nStatGst > 7) {
            System.out.println("Leaving");
           // arliGuestsSat.get(nTarget).draw(batch);
        }*/
    }


    public void reset() { //Resets the game's cached values
        sprServer = new SprServer(850, 175);
        isTableClicked = false;
        nFPS = 0;
        nGameTimer = 60;
    }

    private void checkButtons() { // Checks if buttons are pressed
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
    public void dispose() {
        txtBg.dispose();
        batch.dispose();
    }


    @Override //Input processor
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        viewport.unproject(vTouch.set(Gdx.input.getX(), Gdx.input.getY()));
        for (int n = 0; n < arliGuests.size(); n++) {
            sprCst = arliGuests.get(n); //temporary Guest
            if (sprCst.getBoundingRectangle().contains(vTouch)) {
                bGstDrag = true;
                nTarget = n;
                sprCustMove = arliGuests.get(nTarget);
                arliGuests.remove(sprCustMove);
                bDragging = true;
            }
        }

        return true;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!isSitting) {
            if (arTables[nTable].isAvb(sprCustMove)) {
                if (arTables[nTable].getBoundingRectangle().overlaps(sprCustMove.getBoundingRectangle())) {
                    isSitting = true;
                    arliGuestsSat.add(sprCustMove);
                }
                arliGuestsSat.get(nTarget).sittingDown(isSitting);
                arTables[nTable].sittingDown(isSitting);
            }
        }
        nStatGst = arliGuestsSat.get(nTarget).updateStatus(isSitting);
        arliGuestsSat.get(nTarget).updateStatus(isSitting);
        nGoal = arliGuests.size();
        for (int n = 0; n < nGoal; n++) {
            bCustSat = true;
        }
        bGstDrag = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (sprCustMove.getBoundingRectangle().contains(vTouch)) {
            sprCustMove.drag(vTouch, viewport);
        }
        for (int i = 0; i < 3; i++) {
            if (arTables[i].getBoundingRectangle().overlaps(sprCustMove.getBoundingRectangle())) {
                nTable = i;
            }
        }

        return false;
    }




//-------------------------------------------------------------------------------------------------------------
    //VOIDS NOT IN USE
    @Override
    public void show() {
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

    //BOOLEANS NOT IN USE
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
