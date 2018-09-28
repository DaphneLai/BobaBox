package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import bobabox.main.Objects.ObjTables;

public class SprServer extends Sprite {

    private float fX, fY, fDx = 0, fDy;// Waiter
    private float fTX, fTY; // tables
    private int nDirectionCheck = 0;
    private boolean isAtTable = false;


    public SprServer(String sFile, float _fX, float _fY) {
        super(new Texture(Gdx.files.internal(sFile)));
        fX = _fX;
        fY = _fY;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 120);

    }

    public void walk(ObjTables objTables) { // Makes server move to table coordinates

        //move server horizontally left/right
        fTX = objTables.getX();
        fTY = objTables.getY();
        isAtTable = CheckPosX(fX, fTX);
        fTX += 60;
        fDx = 1;
        if (isAtTable == false) {
            if (fX < fTX) {
                fX += fDx;
                setX(fX);
            } else if (fX > fTX) {
                fX -= fDx;
                setX(fX);
            }
        } else {
            fDx = 0;
            nDirectionCheck = 1;
        }

        //move Server vertically up/down
        if (nDirectionCheck == 1) {
            fTX = objTables.getX();
            fTY = objTables.getY();
            isAtTable = CheckPosY(fY, fTY);
            fDy = 1;
            fTY += 75;
            if (isAtTable == false) {
                if (fY < fTY) {
                    fY += fDy;
                    setY(fY);
                } else if (fY > fTY) {
                    fY -= fDy;
                    setY(fY);
                }
            } else {
                fDy = 0;
            }
        }

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
}






