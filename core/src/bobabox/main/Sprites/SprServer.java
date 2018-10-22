package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjTables;

public class SprServer extends Sprite {

    private float fX, fY, fWX, fWY;
    private int nPrevDir = 0; // 0= neutral 1= East 2=South 3=West 4=North
    private float fTX, fTY; // tables
    private StretchViewport viewport;
    private Vector3 vTouch;
    private int nYCheck = 0;
    //private boolean
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
        fTX = objTables.getX();
        fTY = objTables.getY();
        if (Math.round(fX + 1) != Math.round(fTX)) {
            System.out.println("right");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
            System.out.println(fX + " WAITER ");
            System.out.println(fTX + " TABLE ");
        }
        if (Math.round(fX + 1) == Math.round(fTX)) {
            System.out.println("up");
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);
        }

        if (fY + 100 >= fTY) {
            System.out.println("right");
            fX += arnDx[1];
            fY += arnDy[1];
            setX(fX);
            setY(fY);
        }
        // 0= neutral 1= East 2=South 3=West 4=North

        if (fY+80 >= fTY+187) {
            System.out.println("up");
            fX += arnDx[4];
            fY += arnDy[4];
            setX(fX);
            setY(fY);
        }
    }
}









