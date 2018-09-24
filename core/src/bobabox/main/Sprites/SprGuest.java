package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprGuest extends Sprite {

   private float fX, fY, fMove;
   private boolean bCanDrag = false, isDown = false, isLeft = false, isUp = false, isSitting;
   private int nTimer = 0;

    public SprGuest(String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        fX = 10;
        fY = Gdx.graphics.getHeight() - 130;
        fMove = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 120);

    }

    public void Drag(boolean _isSitting) {
        isSitting = _isSitting;

        if (isDown == true) {
            bCanDrag = true;
            nTimer++;
        }

        if (bCanDrag == true) {
            if (Gdx.input.isTouched()) {
                setX(Gdx.input.getX() - 50);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() - 60);
                nTimer = 0;
            }
        }
        if (nTimer > 900) {
            System.out.println("This is horrible service!");
            if (isSitting == true) {
                Leave(getX(), getY());
            }
        }
    }
    public void walkDown() {
        if (isDown == false) {
            fY -= fMove + 5;
            setY(fY);
            if (fY <= 10) {
                isDown = true;
            }
        }

    }
    public void Leave(float _fX, float _fY) {
        fX = _fX;
        fY = _fY;
        if (isLeft == false) {
            fX -= fMove + 4;
            setX(fX);
            if (fX <= 70) {
                isLeft = true;
            }
        }
        if (isUp == false && isLeft == true) {
            fY += fMove + 4;
            setY(fY);
            if (fY >= 330) {
                isUp = true;
            }
        }
    }

}