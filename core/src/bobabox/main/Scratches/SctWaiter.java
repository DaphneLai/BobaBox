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

//Sarah
//Help from Grondin & Daph
//Screen is not used in game currently
public class SctWaiter implements Screen, InputProcessor{


    private OrthographicCamera oc;
    private SpriteBatch batch;
    private Texture txWaiter, txBG;
    private Sprite sprWaiter;
    private ObjTables objTable;
    private float fWX, fWY, fDx = 0, fDy = 0, fTabX, fTabY, fWaitX, fWaitY;
    private boolean isAtTable;
    private int nDirectionCheck = 0;

    public SctWaiter(GamMenu _gammenu) {

        //game height and width
        fWX = 1000;
        fWY = 500;

        //camera
        oc = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        oc.update();

        //textures
        txWaiter = new Texture("Waiter_spr.png");
        txBG = new Texture("Test_img.jpg");

        //sprites
        batch = new SpriteBatch();
        sprWaiter = new Sprite(txWaiter);
        sprWaiter.setSize(150, 160);
        fWaitX = fWX /-400;
        fWaitY = fWY / 2-400 ;
        sprWaiter.setPosition(fWaitX, fWaitY);


        objTable = new ObjTables(fWX / 2+100, fWY / 2-100, "Table2_obj.png");


    }


    @Override
    public void show() {

    }

    public void render(float delta) {
        //drawing
        batch.setProjectionMatrix(oc.combined);
        batch.begin();
        batch.draw(txBG, 0, 0);
        sprWaiter.draw(batch);
        objTable.draw(batch);
        batch.end();
        if (objTable.isMouseOver() == true && Gdx.input.justTouched()) {
            nDirectionCheck = 1;
        }

            //Actual Code to make the waiter move
            if (objTable.isMouseOver() == true && Gdx.input.justTouched()) {
                nDirectionCheck = 1;
            }
            if (nDirectionCheck == 1) {
                System.out.println("HORIZONTAL");
                isAtTable = CheckPosX(sprWaiter.getX(), objTable.getX()); //check if X of table and Waiter are aligned
                fDx = 1; // speed of waiter
                fTabX = objTable.getX();
                fTabY = objTable.getY();

                if (isAtTable == false) {
                    System.out.println(" NOT EQUAL");
                     fTabX+=35;
                     System.out.println(fTabX+ " TABLE");
                     System.out.println(fWaitX + " WAITER");
                    if(fWaitX<fTabX){
                       fWaitX+=fDx;
                        sprWaiter.setX(fWaitX);
                    }else if (fWaitX>fTabX){
                        fWaitX -= fDx;
                        sprWaiter.setX(fWaitX);
                    }
                } else {
                    System.out.println(" EQUAL");
                    fDx=0;
                    nDirectionCheck = 2;

                }
            }
            if (nDirectionCheck == 2) {
                System.out.println("VERTICAL");
                isAtTable = CheckPosY(sprWaiter.getY(), objTable.getY()); //check if Y coordinate of table and waiter are the same
                fDy = 1;
                fTabX = objTable.getX();
                fTabY = objTable.getY();
                if (isAtTable == false) {
                    System.out.println(" NOT EQUAL");
                    fTabY+=75;
                      System.out.println(fTabY+ " TABLE");
                      System.out.println(fWaitY + " WAITER");
                    if(fTabY < fWaitY){
                        fWaitY-=fDy;
                        sprWaiter.setY(fWaitY);
                    }else {
                        fWaitY+=fDy;
                        sprWaiter.setY(fWaitY);
                    }
                } else {
                    System.out.println(" EQUAL");
                    fDy = 0;
                    nDirectionCheck = 0;

                }
            }
        }

    @Override
    public void resize(int width, int height) {

    }


    public static boolean CheckPosX ( float fXWait, float fXTab){
            if (Math.round(fXWait) != Math.round(fXTab)+35) {
                return false;
            } else {
                return true;
            }
        }

        public static boolean CheckPosY ( float fYWait, float fYTab){
            if (Math.round(fYWait) != Math.round(fYTab)+75) {
                return false;
            } else {

                return true;
            }
        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {
            txWaiter.dispose();
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