package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import bobabox.main.Sprites.SprGuest;



public class Tables extends Sprite {

    float fX, fY, fW, fH; //table
    float fGY, fGX, fGW, fGH; //guest
    public boolean isTouch = true;

    public Tables(float _nX, float _nY, String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        fW = 187;
        fH = 110;
        fX = _nX - fW/2;
        fY = _nY - fH/2;
        setPosition(fX, fY);
        setSize(fW, fH);
        setFlip(false, false);

    }


    public boolean isOpen(SprGuest sprGuest) { // Checks if the spr is over the table

        fGH = sprGuest.getHeight();
        fGW = sprGuest.getWidth();
        fGY = sprGuest.getY();
        fGX = sprGuest.getX();

        if (fGX > fX && fGX < fX + fW) {
            if (fGY > fY && fGY < fY +fH) {
                super.setBounds(fX, fY, fW, fH);
                if (Gdx.input.isTouched()) {
                    super.setBounds(fX - 10, fY - 10, fW + 20, fH + 20);
                    isTouch = true;
                    return true;
                }
                isTouch = false;
                return false;
            }
        }
        super.setBounds(fX, fY, fW, fH);
        return true;
    }
    public boolean isMouseOver() {
        if(Gdx.input.getX() > fX && Gdx.input.getX() < fX + fW){
        if(Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() > fY && Gdx.input.getY()*(-1)+Gdx.graphics.getHeight() < fY + fH){
            return true;
        }

    }

        return false;

}

}


