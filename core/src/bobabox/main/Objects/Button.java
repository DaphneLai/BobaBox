package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

import bobabox.main.GamMenu;

//Brain's (Joel and Alex) code (modified)

public class Button extends Sprite {

    int nX, nY;
    float fW, fH;

    public Button(int _nX, int _nY, String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        nX = _nX - 130;
        nY = _nY - 35;
        fW = 260;
        fH = 70;
        setPosition(nX, nY);
        setSize(fW, fH);
        setFlip(false, false);

    }



    public boolean isMousedOver() { // Checks if the mouse is over the button, not whether the mouse was clicked

        if(Gdx.input.getX() > nX && Gdx.input.getX() < nX + fW){
            if(Gdx.input.getY()*(-1)+ GamMenu.WORLD_HEIGHT > nY && Gdx.input.getY()*(-1)+ GamMenu.WORLD_HEIGHT < nY + fH){
                return true;
            }
        }
        return false;
    }

}