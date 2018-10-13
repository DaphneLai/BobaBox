package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Sprites.SprGuest;


public class ObjTables extends Sprite {

    private float fX, fY, fW, fH; //table
    private float fGY, fGX; //guest
    private Vector3 vTouch;
    private StretchViewport viewport;
    private Texture nTxt1, nTxt2;
    private boolean isSitting = false;

    public ObjTables(float _fX, float _fY, String _sOpenT, String _sSittingT, StretchViewport _viewport) {
        super(new Texture(Gdx.files.internal(_sOpenT)));

        //Importing Info
        nTxt1 = new Texture(_sOpenT);
        nTxt2 = new Texture(_sSittingT);
        viewport = _viewport;
        fW = 187;
        fH = 110;
        fX = _fX - fW / 2;
        fY = _fY - fH / 2;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

        setTexture(nTxt1);
    }

    public void sittingDown(boolean isSitting_) {
        isSitting = isSitting_;
        if (isSitting == true) {
            setTexture(nTxt2);
        } else if (isSitting == false){
            setTexture(nTxt1);
        }
    }

    public boolean isOpen(SprGuest sprGuest) { // Checks if the spr is over the table

        fGY = sprGuest.getY();
        fGX = sprGuest.getX();

        if (fGX > fX && fGX < fX + fW) {
            if (fGY > fY && fGY < fY + fH) {
                super.setBounds(fX, fY, fW, fH);
                if (Gdx.input.isTouched() && isSitting == false) {
                    super.setBounds(fX - 10, fY - 10, fW + 20, fH + 20);
                    return true;

                }
                return false;
            }
        }
        super.setBounds(fX, fY, fW, fH);
        return true;
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


