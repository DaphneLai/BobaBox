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
    private StretchViewport viewport;
    private boolean bJustClicked;
    private Texture txtButton, txtButtonHover;
    private int nTxt;

    public ObjButton(float _fX, float _fY, float _fW, float _fH, String _sButton, String _sButtonHover, StretchViewport _viewport) {
        super(new Texture(Gdx.files.internal(_sButton)));

        //Importing Info (Thanks Joel https://github.com/brauj1894/Yoilith/blob/master/core/src/com/icsgame/objects/Button.java)
        txtButton = new Texture(_sButton);
        txtButtonHover = new Texture(_sButtonHover);
        viewport = _viewport;
        fW = _fW;
        fH = _fH;
        fX = _fX - fW / 2;
        fY = _fY - fH / 2;

        //Setting Size
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

        //Setting Texture Info
        setTexture(txtButton);
        nTxt = 0;
    }

    public boolean isMousedOver() { // Checks if the mouse is over the button, not whether the mouse was clicked
        vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(vTouch);
        if (vTouch.x > fX && vTouch.x < fX + fW) {
            if (vTouch.y > fY && vTouch.y < fY + fH) {
                return true;
            }
        }
        return false;
    }

    public boolean bJustClicked() { // Checks if the button was just clicked
        if (Gdx.input.justTouched()) {
            if (isMousedOver()) {
                return true;
            }
        }
        return false;
    }

    public void changeTexture(int _nTxt) { // Changes the Texture of the button
        if (nTxt != _nTxt) {
            switch (_nTxt) {
                case 0:
                    // Regular Texture
                    setTexture(txtButton);
                    nTxt = 0;
                    break;
                case 1:
                    // Pressed Button
                    setTexture(txtButtonHover);
                    nTxt = 1;
                    break;
                default:
                    break;
            }
        }
    }
}