package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
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
    private int arnDx[] = {0, 2, 0, -2, 0};
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
        fWX = 1000;
        fWY = 500;



    }

    public void walk(ObjTables objTables) { // Makes server move to table coordinates
        fTX = Math.round(objTables.getX())+1;
        fTY = Math.round(objTables.getY())+1;

        if (getBoundingRectangle().overlaps(objTables.getBoundingRectangle())) {
            System.out.println("SERVER HIT TABLE");
            fX += arnDx[0];
            fY += arnDy[0];
            setX(fX);
            setY(fY);
        }

        if (fX + getWidth() != fTX && fX < fTX+94) {
            System.out.println("RIGHT 1");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }

        if (fX + getWidth() == fTX && fY + getHeight() != fTY) {
            System.out.println("UP 2");
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);
        }

        if (fX + 80 >= fTX && fY + 100 == fTY) {
            System.out.println("RIGHT 3");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }

        if (fX > fTX + 187 && fY < fTY + 110) {
            System.out.println("UP 4");
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);
        }

        if (fY >= fTY + 110 && fX > (fTX + 94) - 40) {
            System.out.println("LEFT 5");
            fX += arnDx[3];
            fY += arnDy[3];
            setX(fX);
            setY(fY);
        }

        if (fX < (fTX + 94) && fY >= fTY + 110) {
            System.out.println("RIGHT 6");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }
    }
}









