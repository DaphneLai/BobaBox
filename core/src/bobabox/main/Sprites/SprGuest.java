package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import bobabox.main.Objects.ObjTables;

public class SprGuest extends Sprite {

    private SpriteBatch batch;
    private StretchViewport viewport;
    private Vector3 vTouch;
    private float fX, fY, fH, fW, fMove; //Guest
    private float fHx, fHy, fHw, fHh; //Hearts
    private boolean isDown = false, isLeft = false, isUp = false, bCanDrag = false, isReady = false, bSitting = false, isGone = false;
    private int nTimer = 0, nHearts;
    private ObjTables objTable;
    private Texture arHearts[] = new Texture[4];




    public SprGuest(String sFile, StretchViewport _viewport) {
        super(new Texture(Gdx.files.internal(sFile)));

        //Importing
        viewport = _viewport;
        //Guests
        fX = 80;
        fY = 330;
        fW = 80;
        fH = 100;
        fMove = 6.0f;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

        //Hearts
        fHw = 100;
        fHh = 30;
        nHearts = 0;
        arHearts[0] = new Texture("data/Hearts-01.png");
        arHearts[1] = new Texture("data/Hearts-02.png");
        arHearts[2] = new Texture("data/Hearts-03.png");
        arHearts[3] = new Texture("data/Hearts-04.png");

    }

    //Beginning where the guest enters the store
    public void walkDown() {
        if (!isDown) {
            fY -= fMove;
            // fHy = fY + 120;
            setY(fY);
            if (fY <= 30 && fHy <= 150) {
                isDown = true;
                fMove = 0;
                bSitting = false;
                bCanDrag = true;
            }
        }
    }

    //Checks if guest is Sitting (I switched to bSitting to associate it to the guest)
    public void sittingDown(boolean _isSitting) {
        bSitting = _isSitting;
        if (bSitting) {
            setSize(0, 0);
            bCanDrag = false;
          //  System.out.println("bSitting:" + bSitting);
        }
    }

    //Active when the guest is dragged
    public void drag() {
        walkDown();
        if (isMousedOver()) {
            if (!bSitting) {
                if (bCanDrag) {
                    if (Gdx.input.isTouched()) {
                        vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                        viewport.unproject(vTouch);
                        fX = vTouch.x - 50;
                        fY = vTouch.y - 60;
                        setX(fX);
                        setY(fY);
                        nHearts = 0;
                    }
                }
            }
        }
    }

    //Takes into account all events that affects hearts
    public void hearts(SpriteBatch _batch, ObjTables _objTable) {
        //Importing
        objTable = _objTable;
        batch = _batch;
        objTable.sittingDown(bSitting);
        fHx = fX - 10;
        fHy = fY + 120;
        if (isDown) {
            nTimer++;
        }
        //Ordering
        if (bSitting) {
            fHx = objTable.getX() + objTable.getWidth() / 2 - 60;
            fHy = objTable.getY() + objTable.getHeight();
            if (isReady) {
                // System.out.println("Ready to order");
            }
        }

        //Level of hearts
        if (!isGone) {
            batch.draw(arHearts[nHearts], fHx, fHy, fHw, fHh);
        }
            if ((nTimer % 300 == 0) && isDown) {
                if (nHearts < arHearts.length) {
                    nHearts++;
                }
            }
            if (nHearts == 3) {
                leave();
                if (isGone) {
                    setSize(0, 0);
                }
            }
    }

    //When guest is too impatient, they leave
    private void leave() {
        setSize(fW, fH);
        bCanDrag = false;
        bSitting = false;
        if (!isLeft) {
            fMove = 8;
            fX -= fMove;
            setX(fX);
            if (fX <= 70) {
                isLeft = true;
            }
        }
        if (!isUp && isLeft) {
            fY += fMove;
            setY(fY);
            if (fY >= 330) {
                isUp = true;
                isGone = true;
                fHh = 0;
                fHw = 0;
            }
        }
    }

    public boolean isMousedOver() {
        vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(vTouch);
        if (vTouch.x > (fX - 20) && vTouch.x < fX + (fW + 20)) {
            if (vTouch.y > (fY - 20) && vTouch.y < fY + (fH + 20)) {
                return true;
            }
        }
        return false;
    }
}
