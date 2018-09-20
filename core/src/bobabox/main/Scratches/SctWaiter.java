package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
<<<<<<< HEAD

=======
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
>>>>>>> 06fd4763d99414ddd40ed164303cf6fe40306040

import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjTables;

//Sarah
//Help from Grondin & Daph ;)
public class SctWaiter implements Screen,  ContactListener {

<<<<<<< HEAD
    private OrthographicCamera oc;
    private SpriteBatch batch;
    private Texture txWaiter, txBG;
    private Sprite sprWaiter;
    private ObjTables objTable;
    private float fWX, fWY, fDx = 0, fDy = 0, fTabX, fTabY, fWaitX, fWaitY;
    private boolean isAtTable;
    private int nTableTapped = 0;

    public SctWaiter(GamMenu _gammenu) {

        fWX = 1000;
        fWY = 500;
=======
    OrthographicCamera oc;
    SpriteBatch batch;
    Texture txWaiter, txBG;
    Sprite sprWaiter;
    Tables tbl1, tbl2;
    float fWX, fWY, fDx = 0, fDy = 0, fTabX, fTabY, fWaitX, fWaitY;
    boolean isAtTable;
    int nDirectionCheck = 0;
   // private World world;

    public SctWaiter(GamMenu _gammenu) {
        //game height and width
        fWX = GamMenu.WORLD_WIDTH;
        fWY = GamMenu.WORLD_HEIGHT;
>>>>>>> 06fd4763d99414ddd40ed164303cf6fe40306040

        //camera
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        //textures
        txWaiter = new Texture("Guest_spr.png");
        txBG = new Texture("Test_img.jpg");

        //sprites
        batch = new SpriteBatch();
        sprWaiter = new Sprite(txWaiter);
        sprWaiter.setSize(100, 100);
        fWaitX = fWX / 2 * 0;
        fWaitY = fWY / 2 * 0;
        sprWaiter.setPosition(fWaitX, fWaitY);
<<<<<<< HEAD
        // sprWaiter.setFlip(false, true);
=======

        //Table
        tbl1 = new Tables(fWX / 2, fWY / 2, "Table2_obj.png");
      //  tbl2 = new Tables(fWX / 2-200, fWY / 2-200, "Table2_obj.png");

        //world = new World(new Vector2(0,0), false);
        //world.setContactListener(this);
>>>>>>> 06fd4763d99414ddd40ed164303cf6fe40306040

        objTable = new ObjTables(fWX / 2, fWY / 2, "Table2_obj.png");


    }

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        //drawing
        batch.setProjectionMatrix(oc.combined);
        batch.begin();
        batch.draw(txBG, 0, 0);
        sprWaiter.draw(batch);
<<<<<<< HEAD
        objTable.draw(batch);
        if (objTable.isMouseOver() == true && Gdx.input.justTouched()) {
            nTableTapped = 1;
        }
        if (nTableTapped == 1) {
            isAtTable = CheckPos(sprWaiter.getX(), sprWaiter.getY(), objTable.getX(), objTable.getY());
            fDx = 1;
            fTabX = objTable.getX();
            fTabY = objTable.getY();
=======
        tbl1.draw(batch);
       // tbl2.draw(batch);

        //Actual Code to make the waiter move
        if (tbl1.isMouseOver() == true && Gdx.input.justTouched()) {
            nDirectionCheck = 1;
        }
        if (nDirectionCheck == 1) {
            System.out.println("HORIZONTAL");
            isAtTable = CheckPosX(sprWaiter.getX(), tbl1.getX()); //check if X of table and Waiter are aligned
            fDx = 1; // speed of waiter
            fTabX = tbl1.getX();
            fTabY = tbl1.getY();
>>>>>>> 06fd4763d99414ddd40ed164303cf6fe40306040
            if (isAtTable == false) {
                System.out.println(" NOT EQUAL");
                // System.out.println(fTabX+ " TABLE");
                //  System.out.println(fWaitX + " WAITER");
                fWaitX += fDx;
                sprWaiter.setX(fWaitX);
            } else {
                System.out.println(" EQUAL");
                fDx = 0;
<<<<<<< HEAD
                nTableTapped = 0;

=======
                nDirectionCheck = 2;
>>>>>>> 06fd4763d99414ddd40ed164303cf6fe40306040
            }
        }
        if (nDirectionCheck == 2) {
            System.out.println("VERTICAL");
            isAtTable = CheckPosY(sprWaiter.getY(), tbl1.getY()); //check if Y coordinate of table and waiter are the same
            fDy = 1;
            fTabX = tbl1.getX();
            fTabY = tbl1.getY();
            if (isAtTable == false) {
                System.out.println(" NOT EQUAL");
                //  System.out.println(fTabY+ " TABLE");
                //  System.out.println(fWaitY + " WAITER");
                fWaitY += fDy;
                sprWaiter.setY(fWaitY);
            } else {
                System.out.println(" EQUAL");
                fDy = 0;
                nDirectionCheck = 0;

            }
        }
        batch.end();
    }


    @Override
    public void resize(int width, int height) {

    }

<<<<<<< HEAD
    public static boolean CheckPos(float fXWait, float fYWait, float fXTab, float fYTab) {
        if (fXWait != fXTab) {
            return false;
        } else {
=======
    public static boolean CheckPosX(float fXWait, float fXTab) {
        if (Math.round(fXWait) != Math.round(fXTab)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean CheckPosY(float fYWait, float fYTab) {
        if (Math.round(fYWait) != Math.round(fYTab)) {
            return false;
        } else {
>>>>>>> 06fd4763d99414ddd40ed164303cf6fe40306040
            return true;
        }
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
    txWaiter.dispose();
    txBG.dispose();
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
