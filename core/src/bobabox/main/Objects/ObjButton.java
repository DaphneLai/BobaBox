package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import org.omg.CORBA.VersionSpecHelper;

import bobabox.main.GamMenu;

//Brain's (Joel and Alex) code (modified)

public class ObjButton extends Sprite {

    private float fX, fY, fW, fH;
    private Vector3 vTouch;
    private  StretchViewport viewport;

    public ObjButton(float _fX, float _fY, float _fW, float _fH, String sFile, StretchViewport _viewport) {

        super(new Texture(Gdx.files.internal(sFile)));
        viewport = _viewport;
        fW = _fW;
        fH = _fH;
        fX = _fX - fW/2;
        fY = _fY - fH/2;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

    }



    public boolean isMousedOver() { // Checks if the mouse is over the button, not whether the mouse was clicked
        if (Gdx.input.isTouched()) {
            vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(vTouch);
            if (vTouch.x > fX && vTouch.x < fX + fW) {
                if (vTouch.y  > fY && vTouch.y < fY + fH) {
                    return true;
                }
            }
        }
        return false;
    }

}