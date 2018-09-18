package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

import bobabox.main.GamMenu;

//Brain's (Joel and Alex) code (modified)

public class Button extends Sprite {

    float fX, fY, fW, fH;
    public Button(float _fX, float _fY, float _fW, float _fH,String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        fW = _fW;
        fH = _fH;
        fX = _fX - fW/2;
        fY = _fY - fH/2;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

    }



    public boolean isMousedOver() { // Checks if the mouse is over the button, not whether the mouse was clicked

        if(Gdx.input.getX() > fX && Gdx.input.getX() < fX + fW){
            if(Gdx.input.getY()*(-1)+ GamMenu.WORLD_HEIGHT > fY && Gdx.input.getY()*(-1)+ GamMenu.WORLD_HEIGHT < fY + fH){
                return true;
            }
        }
        return false;
    }

}