package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprGuest extends Sprite {

    float fX, fXSpeed, fY, fYSpeed, fDown;
    int nTimer = 0;
    boolean isWait = false, canDrag = false;

    public SprGuest(String sFile) {

        super(new Texture(Gdx.files.internal(sFile)));
        fX = 10;
        fY = Gdx.graphics.getHeight() - 130;
        fXSpeed = 0;
        fYSpeed = 0;
        fDown = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 120);

    }

    public void Drag() {

        //Entered
        if (isWait == true) {
            nTimer++;
        }
        if (canDrag == true) {
            if (Gdx.input.isTouched()) {
                setX(Gdx.input.getX() - 50);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() - 60);
                nTimer = 0;
            }
        }
    }
    public void walkDown() {
        if (isWait == false) {
            fY -= fDown + 2;
            setY(fY);
            if (fY <= 10) {
                fDown = 0;
                isWait = true;
                canDrag = true;
            }
        }
    }

}