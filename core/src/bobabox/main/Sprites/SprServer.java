package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjTables;

public class SprServer extends Sprite {

    private float fX, fY, fDx = 0, fDy;// Waiter
    private float fTX, fTY; // tables
    private StretchViewport viewport;
    private Vector3 vTouch;
    private int nYCheck = 0;
    private boolean isAtTable = false, isAtPointXRight = false, isAtPointYBelow = false, isAtPointYAbove = false, isLeft = false, isRight = false, isUp = false, isDown = false;


    public SprServer(String sFile, float _fX, float _fY, StretchViewport _viewport) {
        super(new Texture(Gdx.files.internal(sFile)));
        viewport = _viewport;
        fX = _fX;
        fY = _fY;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(80, 100);

        if (Gdx.input.isTouched()) {
            vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(vTouch);
        }

    }

    public void walk(ObjTables objTables) { // Makes server move to table coordinates

        //move server horizontally left/right
        if (nYCheck == 0) {
            fTX = objTables.getX();
            fTY = objTables.getY();
            isAtTable = CheckPosX(fX, fTX);
            fTX += 50;
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
                nYCheck = 1;
            }
        }

        //move Server vertically up/down
        if (nYCheck == 1) {
            fTX = objTables.getX();
            fTY = objTables.getY();
            isAtTable = CheckPosY(fY, fTY);
            fDy = 1;
            fTY += 80;
            isAtPointYBelow = bBelowTable(fY, fTY);
            if (isAtPointYBelow == false) {
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
            } else {
                collide(getX(), getY(), fTX, fTY);
            }
        }
    }

    public void collide(float fX, float fY, float fTX, float fTY) {
        if (isAtPointYBelow = true) { //checks if the server is at table y-coordinate subtract 150
            isRight = true;
        }
        System.out.println("UP 1 " + isUp);
        isAtPointXRight = bRightTable(getX(), fTX); // if it's go to the right until table x-coordinate + 30
         System.out.println("AT  RIGHT " + isAtPointXRight);
        if (isRight = true && isAtPointXRight == false) {
            fDx = 1;
            if (fX <= fTX) {
                fX -= fDx;
                setX(fX);
            } else if (fX > fTX) {
                fX += fDx;
                setX(fX);
            }
        }
        if (isAtPointXRight == true) { // if at point right go up
            fDx=0;
           // isUp = true;
            isRight = false;
        }

        isAtPointYAbove = bAboveTable(getY(), fTY); // if at y-coordinate of table + 30
       System.out.println("AT  ABOVE " + isAtPointYAbove);
        System.out.println("UP 2 " + isUp);
        if (isUp = true && isAtPointYAbove==false) {
           fDy = 1;
            System.out.println("TABLE " + fTY);
            System.out.println("SERVER " + fY);
           if (fY < fTY) {
                fY += fTY;
                setY(fY);
            } else if(fY >fTY) {
               fY -= fTY;
               setY(fY);
            }
        }
//        if(isAtPointYAbove==true){ //if at y-point then go to the left
//            fDy=0;
//            isUp=false;
//            isLeft=true;
//            System.out.println("LEFT HERE");
//
//        }

    }

    //Checks if Waiter X and Table X is equal
    public static boolean CheckPosX(float fXWait, float fXTab) {
        if (Math.round(fXWait) != Math.round(fXTab) + 50) {
            return false;
        } else {
            return true;
        }
    }

    //Checks if Waiter Y and Table Y is equal
    public static boolean CheckPosY(float fYWait, float fYTab) {
        if (Math.round(fYWait) != Math.round(fYTab) + 30) {
            return false;
        } else {
            return true;
        }
    }

    //Checks if Waiter is at Y-coordinate -150 below the table. This is a hit detection boolean
    public static boolean bBelowTable(float fYWait, float fYTab) {
        if (Math.round(fYWait) == Math.round(fYTab) - 150) {
            return true;
        } else {
            return false;
        }
    }

    //If the Waiter is at X-coordinate + 200 to the right of the table. This is a hit detection boolean
    public static boolean bRightTable(float fXWait, float fXTab) {
        if (Math.round(fXWait) == Math.round(fXTab) + 200) {
            return true;
        } else {
            return false;
        }
    }

    //if Waiter is at y-coordinate + 30 above table . This is a hit detection boolean
    public static boolean bAboveTable(float fYWait, float fYTab) {
        if (Math.round(fYWait) != Math.round(fYTab) + 30) {
            return false;
        } else {
            return true;
        }

    }

}






