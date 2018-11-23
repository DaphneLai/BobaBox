package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.sql.SQLOutput;

import bobabox.main.Objects.ObjTables;

//MADE BY DAPHNE
//NOT IN USE
public class SprCustomer extends Sprite {
    private SpriteBatch batch;
    private float fX, fY, fH, fW, fMove; //Guest
    private float fHx, fHy, fHw, fHh; //Hearts
    private boolean bCanDrag = false, bSitting = false, isGone = false, isEntering;
    private StretchViewport viewport;
    private Texture arHearts[] = new Texture[4];
    private ObjTables objTable;
    private int nTimer = 0, nHearts = 0, nStatus, nDir, nPointer = -1;

    public SprCustomer(String sFile) {
        super(new Texture(Gdx.files.internal(sFile)));

        //Customer specs
        fX = 80;
        fY = 330;
        fW = 80;
        fH = 100;
        fMove = 3.0f;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

        //Heart specs
        fHw = 100;
        fHh = 30;
        arHearts[0] = new Texture("data/Hearts-01.png");
        arHearts[1] = new Texture("data/Hearts-02.png");
        arHearts[2] = new Texture("data/Hearts-03.png");
        arHearts[3] = new Texture("data/Hearts-04.png");


        //nStatus
        nStatus = 0;
//        for (int n = 0; n < 8; n++) {
//            nStatus[n] = n;
//        }

        /* EXPLANATION OF nSTATUS:
        0 = Is entering the restaurant
        1 = Is waiting for a table
        2 = is at a table, deciding what to order
        3 = is ready to order
        4 = order is taken, is waiting for their beverage
        5 = gets their order, is eating
        6 = done eating, wants to pay
        7 = pays and leaves
        8 = leaves due to lack of service
         */

    }

    public void updateStatus() {
        directions();
        if (nStatus == 0) {
            nDir = 2;
            setY(fY);
            if (nDir == 2 && fY <= 20) { //Walking down
                nDir = 4;
                setY(fY);
                nStatus = 1;
            }

        } else if (nStatus == 1) {
          //  System.out.println("STATUS: Waiting for a table");
            nTimer++;

        } else if (nStatus == 2) {
         //   System.out.println("STATUS: Deciding order");
            bSitting = true;
            if (nTimer == 240) {
                nStatus = 3;
            }

        } else if (nStatus == 3) {
          //  System.out.println("STATUS: Ready to order");

        } else if (nStatus == 4) {
           // System.out.println("STATUS: Waiting for food");

        } else if (nStatus == 5) {
            //System.out.println("STATUS: Eating, nom nom nom");

        } else if (nStatus == 6) {
            //System.out.println("STATUS: Wants to pay");

        } else if (nStatus == 7) {
            //System.out.println("STATUS: Paid, and leaving");
            leave();

        } else if (nStatus == 8) {
            //System.out.println("STATUS: This is horrible service!");
            leave();
        }

    }

    //Active when the guest is dragged
    public void drag(Vector2 vTouch, StretchViewport viewport) {
        nHearts = 0;
        viewport.unproject(vTouch.set(Gdx.input.getX(), Gdx.input.getY()));
     if (nStatus == 1) {
     //   if (bCanDrag) {
            fX = vTouch.x - 50;
            fY = vTouch.y - 60;
            setX(fX);
            setY(fY);
        }
    }

    public void hearts(SpriteBatch _batch, ObjTables _objTable) {
        //Importing
        objTable = _objTable;
        batch = _batch;
        //Sitting
        objTable.sittingDown(bSitting);

        if (bSitting) {
            fHx = objTable.getX() + objTable.getWidth() / 2 - 60;
            fHy = objTable.getY() + objTable.getHeight();
        }
        //Level of hearts
        if (!isGone) {
            batch.draw(arHearts[nHearts], fX - 10 , fY + 110 , fHw, fHh);
        }
        if ((nTimer % 300 == 0) && nStatus >= 1) {
            if (nHearts < 3) {
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

    private void directions() {
        if (nDir == 0) { //North
            fY += fMove;
            setY(fY);
        }
        if (nDir == 1){ //East
            fX += fMove;
            setX(fX);
        }
        if (nDir == 2) { //south
            fY -= fMove;
        } else if (nDir == 3) { //West
            fX -= fMove;
            setX(fX);
        } else if (nDir == 4) { // stopped
            setX(fX);
            setY(fY);
        }
    }

    private void leave() {
        directions();
        bSitting = false;
        nDir = 3;
        if (nDir == 3 && getX() == 80) {
            System.out.println("Stop");
        }
    }

}
