package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Hearts extends Sprite{
    public float fX, fXSpeed, fY, fYSpeed, fDown, fYup, fXup;
    int nTimer = 0;
    public int  nHearts = 3;
    public boolean isWait = false, canDrag = false, isReady = false;

    public Hearts(String sFile) {
        super(new Texture(Gdx.files.internal(sFile)));
        fX = 10;
        fY = Gdx.graphics.getHeight() - 10;
        fXSpeed = 0;
        fYSpeed = 0;
        fDown = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 30);

    }
    public void Patience() {

        //Entered
        if (isWait == true) {
            nTimer++;
        }
        if (canDrag == true) {
            if (Gdx.input.isTouched()) {
                setX(Gdx.input.getX() - 50);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() + 60);
                nTimer = 0;
            }
        }
        if (nTimer > 0 && nTimer < 300) {
            nHearts = 3;
        } else if (nTimer > 300 && nTimer < 600) {
            nHearts = 2;
            isReady = true;
        } else if (nTimer > 600 && nTimer < 900) {
            nHearts = 1;
        } else if (nTimer > 900) {
            System.out.println("This is horrible service!");
            nHearts = 0;
        }
    }
    public void walkDown() {
        if (isWait == false) {
            fY -= fDown + 10;
            setY(fY);
            if (fY <= 130) {
                fDown = 0;
                isWait = true;
                canDrag = true;
            }
        }
    }
}
