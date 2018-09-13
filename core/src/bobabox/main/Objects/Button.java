package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

//Brain's (Joel and Alex) code (modified)

public class Button extends Sprite {

    int nX, nY;
    float fW, fH;

    public Button(int _nX, int _nY, String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        nX = _nX;
        nY = _nY;
        fW = 638/2;
        fH = 174/2;
        setPosition(nX, nY);
        setSize(fW, fH);
        setFlip(false, false);

    }



    public boolean isMousedOver() { // Checks if the mouse is over the button, not whether the mouse was clicked

        if(Gdx.input.getX() > nX && Gdx.input.getX() < nX + fW){
            if(Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() > nY && Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() < nY + fH){
                return true;
            }
        }
        return false;
    }

}