package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjTables;

public class SprServer extends Sprite {

    private float fX, fY, fW, fH;//Server X, Y, W, H
    private float fTX, fTY, fTW, fTH; // tables X, Y, W and H
    private StretchViewport viewport;
    private Vector3 vTouch;
    private boolean bBeyondBounds = false;
    private int arnDx[] = {0, 2, 0, -2, 0}; //Array of speed
    private int arnDy[] = {0, 0, -2, 0, 2};


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

        // Setting Table X and Y.. Gets Coordinates at an even number to match server speed
        if (Math.round(objTables.getX()) % 2 == 0) {
            fTX = Math.round(objTables.getX());
        } else {
            fTX = Math.round(objTables.getX()) + 1;
        }
        if (Math.round(objTables.getY()) % 2 == 0) {
            fTY = Math.round(objTables.getX());
        } else {
            fTY = Math.round(objTables.getY()) + 1;
        }

        //Setting Table Width the Height
        fTW = objTables.getWidth();
        fTH = objTables.getHeight();

        //Setting variables for Width and Height of Server
        fW = getWidth();
        fH = getHeight();

        //Hit detection between table and server
        if (getBoundingRectangle().overlaps(objTables.getBoundingRectangle())) {
            System.out.println("SERVER HIT TABLE");
            fX += arnDx[0];
            fY += arnDy[0];
            setX(fX);
            setY(fY);
        }

        //Makes server move right according to coordinate location
        if (fX + fW != fTX && fX < fTX + fTW / 2) {
            System.out.println("RIGHT 1");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }

        //Makes server move up according to coordinate location
        if (fX + fW == fTX && fY + fH != fTY) {
            System.out.println("UP 2");
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);
        }

        //makes server move right according to coordinate location
        if (fX + fW >= fTX && fY + fH == fTY) {
            System.out.println("RIGHT 3");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }

        //makes server move up according to coordinate location
        if (fX > fTX + fTW && fY < fTY + fTH) {
            System.out.println("UP 4");
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);
        }

        //makes server move left according to coordinate location
        if (fY >= fTY + fTH && fX >= (fTX + fW / 2)) {
            System.out.println("LEFT 5");
            fX += arnDx[3];
            fY += arnDy[3];
            setX(fX);
            setY(fY);
        }

        //makes server move right according to coordinate location
        if (fX < (fTX + fW / 2) && fY >= fTY + fTH) {
            System.out.println("RIGHT 7");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }
    }

    //NOT USED YET FOR MULTI TABLES
    public boolean isAtTable(float fX, float fY, float fTX, float fTY) {

//        if(fX<= fTX+187 && fX >= fTX){
//            if(fY > fTY){
//                System.out.println(" AT LOWER TABLE ");
//                if(fY >= fTY +110 && fY < fTY+111){
//                    return true;
//                }
//            }
//            if(fY < fTY){
//                System.out.println(" AT HIGHER TABLE ");
//                if(fY >= fTY+110){
//                   return true;
//                }
//            }
//        }
        return false;
    }

}







