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
    private int nDir;
    // nDir: 0 = North, 1 = East, 2 = South, 3 = West, 4 = stop

    public SprServer(String sFile, float _fX, float _fY) {
        super(new Texture(Gdx.files.internal(sFile)));
        fX = _fX;
        fY = _fY;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(80, 100);
    }

    private void directions() {
        if (nDir == 0) {
            System.out.println("NORTH");
            fY += 1.5f;
            setY(fY);

        } else if (nDir == 1) {
            System.out.println("EAST");
            fX += 1.5f;
            setX(fX);

        } else if (nDir == 2) {
            System.out.println("SOUTH");
            fY -= 1.5f;
            setY(fY);

        } else if (nDir == 3) {
            System.out.println("WEST");
            fX -= 1.5f;
            setX(fX);

        } else if (nDir == 4) {
            System.out.println("STOP");
            setX(fX);
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
//        System.out.println(fYG + " TABLE X");
//        System.out.println(fY + " SERVER X");

    }

}







