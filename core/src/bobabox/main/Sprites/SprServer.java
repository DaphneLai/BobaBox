package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjTables;

public class SprServer extends Sprite {

    private float fX, fY, fWX, fWY;
    // 0= neutral 1= East 2=South 3=West 4=North
    private float fTX, fTY; // tables
    private StretchViewport viewport;
    private Vector3 vTouch;
    private int nYCheck = 0;
    private boolean bBeyondBounds = false;
    private int arnDx[] = {0, 10, 0, -10, 0};
    private int arnDy[] = {0, 0, -10, 0, 10};


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
        fWX = 1000;
        fWY = 500;
    }

    public void walk(ObjTables objTables) { // Makes server move to table coordinates
        fTX = objTables.getX()+1;
        fTY = objTables.getY()+1;

        //TEMPORARY CONTROL
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);

        }
        //SET BOUNDARIES
        System.out.println(fY+100 + " WAITER ");
        System.out.println(fTY + " TABLE ");
        if (fX+80 >= fTX) {
            fX += arnDx[0];
            fY += arnDy[0];
            setX(fX);
            setY(fY);
        }
        if(fX+80 <= getWidth()-fTX){
            fX += arnDx[0];
            fY += arnDy[0];
            setX(fX);
            setY(fY);
        }

        if(fY+100 >= fTY){
            System.out.println(" HIT BOTTOM ");
            fX += arnDx[0];
            fY += arnDy[0];
            setX(fX);
            setY(fY);
        }

        if(fY+100 <= getHeight()-fTY){
            System.out.println(" HIT TOP");
            fX += arnDx[0];
            fY += arnDy[0];
            setX(fX);
            setY(fY);

        }
    }

}

//    public boolean isInTable(float fTX, float fTY) {
//        if (fX > fTX && fX < getWidth() - fTX) {
//            if (fY > fTY && fY < getHeight() - fTY) {
//                return true;
//            }
//        }
//        return false;
//    }
//}









