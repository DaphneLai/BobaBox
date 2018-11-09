package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Objects.ObjBar;
import bobabox.main.Objects.ObjTables;

public class SprServer extends Sprite {

    private float fX, fY;//Server X, Y, W, H
    private StretchViewport viewport;
    private Vector3 vTouch;
    private int nDir;
    // nDir: 0 = North, 1 = East, 2 = South, 3 = West, 4 = stop x, 5 = stop y

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

    public void directions() {
        if (nDir == 0) {
            System.out.println("NORTH");
            fY += 2.0f;
            setY(fY);

        } else if (nDir == 1) {
            System.out.println("EAST");
            fX += 2.0f;
            setX(fX);

        } else if (nDir == 2) {
            System.out.println("SOUTH");
            fY -= 2.0f;
            setY(fY);

        } else if (nDir == 3) {
            System.out.println("WEST");
            fX -= 2.0f;
            setX(fX);

        } else if (nDir == 4) {
            System.out.println("STOP X AXIS");
            setX(fX);
            System.out.println("STOP Y AXIS");
            setY(fY);
        }
    }

    // Makes server move to table coordinates
    public void update(float fXG, float fYG) {
        directions();
        //North
        if (fY < fYG - 1) {
            nDir = 0;
        } //East
        else if (fX < fXG - 1) {
            nDir = 1;
        } //South
        else if (fY > fYG + 1) {
            nDir = 2;
        } //West
        else if (fX > fXG + 1) {
            nDir = 3;
        } else {
            nDir = 4;
        }


        //Makes server move right according to coordinate location
        System.out.println(fYG + " TABLE X");
        System.out.println(fY + " SERVER X");

    }

}







