package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjHearts extends Sprite {
    private float fX, fY, fDown;
    private SpriteBatch batch;
    private int nTimer = 0;
    private Texture txt3, txt2, txt1, txt0;
    private boolean isWait = false, canDrag = false, isReady = false, isSitting;

    public ObjHearts() {
        fX = 10;
        fY = Gdx.graphics.getHeight() - 10;
        fDown = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(100, 30);

        txt3 = new Texture("Hearts-01.png");
        txt2 = new Texture("Hearts-02.png");
        txt1 = new Texture("Hearts-03.png");
        txt0 = new Texture("Hearts-04.png");
    }

    public void Patience(SpriteBatch _batch, boolean _isSitting) {
        batch = _batch;
        isSitting = _isSitting;
        //Entered
        if (isWait == true) {
            canDrag = true;
            nTimer++;
        }
        if (canDrag == true) {
            if (Gdx.input.isTouched()) {
                fX = Gdx.input.getX() - 50;
                fY = Gdx.graphics.getHeight() - Gdx.input.getY() + 60;
                nTimer = 0;
            }
        }
        //Ordering
        if (isSitting == true) {
            if (isReady == true) {
                System.out.println("Ready to order");
            }
        }
        //Level of patience
        if (nTimer >= 0 && nTimer < 300) {
            batch.draw(txt3, fX, fY, 100, 30);

        } else if (nTimer > 300 && nTimer < 600) {
            isReady = true;
            batch.draw(txt2, fX, fY, 100, 30);

        } else if (nTimer > 600 && nTimer < 900) {
            canDrag = true;
            batch.draw(txt1, fX, fY, 100, 30);

        } else if (nTimer > 900) {
            System.out.println("This is horrible service!");
            batch.draw(txt0, fX, fY, 100, 30);

        }
    }

    public void walkDown() {
        if (isWait == false) {
            fY -= fDown + 5;
            setY(fY);
            if (fY <= 130) {
                fDown = 0;
                isWait = true;
                canDrag = false;
            }
        }
    }
}
