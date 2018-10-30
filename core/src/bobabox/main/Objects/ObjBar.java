package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class ObjBar extends Sprite {
    private float fX, fY, fW, fH; //bar
    private float fSY, fSX; //server
    private Vector3 vTouch;
    private StretchViewport viewport;

    public ObjBar(float _fX, float _fY, String _sImg, StretchViewport _viewport) {
        super(new Texture(Gdx.files.internal(_sImg)));

        //Importing Info
        viewport = _viewport;
        fW = 187;
        fH = 110;
        fX = _fX - fW / 2;
        fY = _fY - fH / 2;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);
    }

    public boolean isMousedOver() {
        vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(vTouch);
        if (vTouch.x > fX && vTouch.x < fX + fW) {
            if (vTouch.y > fY && vTouch.y < fY + fH) {
                return true;
            }
        }
        return false;
    }
}


