package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprGuest extends Sprite {

<<<<<<< HEAD
    float fX, fXSpeed, fY, fYSpeed, fMove;
    boolean canDrag = false, isDown = false, isLeft = false, isUp = false;
=======
    private float fX, fY, fDown;
    private boolean isWait = false, canDrag = false;
>>>>>>> 07119ad973577b79092a79a5bf3b01aa5216c3c1

    public SprGuest(String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        fX = 10;
        fY = Gdx.graphics.getHeight() - 130;
<<<<<<< HEAD
        fXSpeed = 0;
        fYSpeed = 0;
        fMove = 1.0f;
=======
        fDown = 1.0f;
>>>>>>> 07119ad973577b79092a79a5bf3b01aa5216c3c1
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 120);

    }

    public void Drag() {


        if (canDrag == true) {
            if (Gdx.input.isTouched()) {
                setX(Gdx.input.getX() - 50);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() - 60);
            }
        }
    }
    public void walkDown() {
        if (isDown == false) {
            fY -= fMove + 10;
            setY(fY);
            if (fY <= 10) {
                isDown = true;
                canDrag = true;
            }
        }

    }
/*    public void Leave() {
        if (isLeft == false) {
            fX-= fMove + 2;
            setX(fX);
            if (fX == 115) {
                isLeft = true;
               /* if (isUp == false) {
                    fY += fMove + 2;
                    setY(fY);
                    if (fY == 170) {
                        isUp = true;
                    }

                }
            }//x = 115, y = 170
        }
    }*/

}