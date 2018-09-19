package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;



import bobabox.main.GamMenu;
import bobabox.main.Objects.Tables;


public class SctWaiter implements Screen {

    OrthographicCamera oc;
    SpriteBatch batch;
    Texture txWaiter, txBG;
    Sprite sprWaiter;
    Tables tbl1;
    float fWX, fWY, fDx =0, fDy =0, fTabX, fTabY, fWaitX, fWaitY;
    Vector2 vVelocity, vAcc;
    boolean  isAtTable;
    int nTableTapped=0;

    public SctWaiter(GamMenu _gammenu) {

        fWX = GamMenu.WORLD_WIDTH;
        fWY = GamMenu.WORLD_HEIGHT;

        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();
        batch = new SpriteBatch();

        txWaiter = new Texture("Guest_spr.png");
        txBG = new Texture("Test_img.jpg");

        sprWaiter = new Sprite(txWaiter);
        sprWaiter.setSize(100, 100);
        fWaitX = fWX/2*0;
        fWaitY = fWY/2*0;
        sprWaiter.setPosition(fWaitX, fWaitY);
       // sprWaiter.setFlip(false, true);

        tbl1 = new Tables(fWX/2, fWY/2, "Table2_obj.png");
        vVelocity = new Vector2();
        vAcc = new Vector2();




    }

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(oc.combined);
        batch.begin();
        batch.draw(txBG, 0, 0);


        sprWaiter.draw(batch);
        tbl1.draw(batch);
        if (tbl1.isMouseOver() == true && Gdx.input.justTouched()) {
            nTableTapped = 1;
        }
        if(nTableTapped ==1) {
            isAtTable = CheckPos(sprWaiter.getX(), sprWaiter.getY(), tbl1.getX(), tbl1.getY());
            fDx = 1;
            fTabX = tbl1.getX();
            fTabY = tbl1.getY();
            if (isAtTable == false) {
                System.out.println(" NOT EQUAL");
                fWaitX += fDx;
                fWaitY += fDy;
                sprWaiter.setX(fWaitX);
            } else {
                System.out.println(" EQUAL");
                fDx=0;
                nTableTapped =0;

            }
        }


        batch.end();
    }



    @Override
    public void resize(int width, int height) {

    }

    public static boolean CheckPos (float fXWait, float fYWait, float fXTab, float fYTab){
        if(fXWait != fXTab){
            return false;
        }else{
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

    }

}
