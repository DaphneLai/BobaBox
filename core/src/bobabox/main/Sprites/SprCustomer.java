package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjTables;

//MADE BY DAPHNE
//NOT IN USE
public class SprCustomer extends Sprite {
    private SpriteBatch batch;
    private float fX, fY, fH, fW, fMove; //Guest
    private float fHx, fHy, fHw, fHh; //Hearts
    private boolean bCanDrag = false, bSitting = false, isGone = false;
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
            nDir = 3;
            setY(fY);
            if (nDir == 3 && fY <= 20) { //Walking down
                nDir = 0;
                setY(fY);
                nStatus = 1;
            }

        } else if (nStatus == 1) {
            System.out.println("STATUS: Waiting for a table");
            nTimer++;

        } else if (nStatus == 2) {
            System.out.println("STATUS: Deciding order");
            bSitting = true;
            if (nTimer == 240) {
                nStatus = 3;
            }

        } else if (nStatus == 3) {
            System.out.println("STATUS: Ready to order");

        } else if (nStatus == 4) {
            System.out.println("STATUS: Waiting for food");

        } else if (nStatus == 5) {
            System.out.println("STATUS: Eating, nom nom nom");

        } else if (nStatus == 6) {
            System.out.println("STATUS: Wants to pay");

        } else if (nStatus == 7) {
            System.out.println("STATUS: Paid, and leaving");
            leave();

        } else if (nStatus == 8) {
            System.out.println("STATUS: This is horrible service!");
            leave();
        }

    }

    //Active when the guest is dragged
    public void drag(Vector2 vTouch, StretchViewport viewport) {
        viewport.unproject(vTouch.set(Gdx.input.getX(), Gdx.input.getY()));
        if (getBoundingRectangle().contains(vTouch.x, vTouch.y)) {
            bCanDrag = true;
        }
        if (bCanDrag) {
            fX = vTouch.x;
            fY = vTouch.y;
            setX(fX - 40);
            setY(fY - 50);
        }
    }

    public void hearts(SpriteBatch _batch, ObjTables _objTable) {
        //Importing
        objTable = _objTable;
        batch = _batch;
        objTable.sittingDown(bSitting);
        fHx = fX - 10;
        fHy = fY + 120;

        //Sitting
        if (bSitting) {
            fHx = objTable.getX() + objTable.getWidth() / 2 - 60;
            fHy = objTable.getY() + objTable.getHeight();
        }

        //Level of hearts
        if (!isGone) {
            batch.draw(arHearts[nHearts], fHx, fHy, fHw, fHh);
        }
        if ((nTimer % 300 == 0) && nStatus == 1) {
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

    private void directions() {
        if (nDir == 3) {
            fY -= fMove;
        }
        if (nDir == 1) {
            fX -= fMove;
            nDir = 2;
        } else if (nDir == 2) {
            fY += fMove;
        }

    }

    private void leave() {
        directions();
        bSitting = false;
        nDir = 1;
    }

}
