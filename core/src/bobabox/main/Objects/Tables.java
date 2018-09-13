package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import bobabox.main.Sprites.SprGuest;



public class Tables extends Sprite {

    int nX, nY;
    float fW, fH, fGY, fGX, fGW, fGH;

    public Tables(int _nX, int _nY, String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        nX = _nX;
        nY = _nY;
        fW = 150;
        fH = 80;
        setPosition(nX, nY);
        setSize(fW, fH);
        setFlip(false, false);

    }


    public boolean isOpen(SprGuest sprGuest) { // Checks if the spr is over the table

        fGH = sprGuest.getHeight();
        fGW = sprGuest.getWidth();
        fGY = sprGuest.getY();
        fGX = sprGuest.getX();

        if (fGX > nX && fGX < nX + fW) {
            if (fGY > nY && fGY < nY + fW) {
                super.setBounds(nX, nY, fW, fH);
                if (Gdx.input.isTouched()) {
                    super.setBounds(nX - 35, nY - 20, fW + 50, fH + 50);
                }
                return false;
            }
        }
        super.setBounds(nX, nY, fW, fH);
        return true;
    }
    public boolean isMouseOver() {
        if(Gdx.input.getX() > nX && Gdx.input.getX() < nX + fW){
        if(Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() > nY && Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() < nY + fH){
            return true;
        }

    }

        return false;

}

}


