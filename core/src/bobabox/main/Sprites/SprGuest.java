package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprGuest extends Sprite {

   private float fX, fY, fMove;
   private boolean bCanDrag = false, isDown = false;

    public SprGuest(String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        fX = 10;
        fY = Gdx.graphics.getHeight() - 130;
        fMove = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 120);

    }

    public void Drag() {

        if (bCanDrag == true) {
            if (Gdx.input.isTouched()) {
                setX(Gdx.input.getX() - 50);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() - 60);
            }
        }
    }
    public void walkDown() {
        if (isDown == false) {
            fY -= fMove + 5;
            setY(fY);
            if (fY <= 10) {
                isDown = true;
                bCanDrag = true;
            }
        }

    }

}