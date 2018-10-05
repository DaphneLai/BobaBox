package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.Screens.ScrGame;

public class SprGuest extends Sprite {

    private SpriteBatch batch;
    private StretchViewport viewport;
    private Vector3 vTouch;
    private float fX, fY, fMove, fHx, fHy;
    private boolean isDown = false, isLeft = false, isUp = false, isWait = false, bCanDrag = false, isReady = false, isSitting;
    private int nTimer = 0;
    private Texture txt3, txt2, txt1, txt0;


    public SprGuest(String sFile, StretchViewport _viewport) {

        super(new Texture(Gdx.files.internal(sFile)));
        viewport = _viewport;
        //Guests
        fX = 10;
        fY = Gdx.graphics.getHeight() - 130;
        fMove = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(80, 100);

        //Hearts
        fHx = fX;
        fHy = Gdx.graphics.getHeight() - 10;
        txt3 = new Texture("Hearts-01.png");
        txt2 = new Texture("Hearts-02.png");
        txt1 = new Texture("Hearts-03.png");
        txt0 = new Texture("Hearts-04.png");

    }


    //Beginning where the guest enters the store
    public void walkDown() {
        if (isDown == false) {
            fY -= fMove + 5;
            fHy = fY + 120;
            setY(fY);
            if (fY <= 10) {
                isDown = true;
            }
            if (fHy <= 130) {
                fMove = 0;
                isWait = true;
                bCanDrag = false;
            }
        }
    }

    //Active when the guest is dragged
    public void drag() {
        if (isDown == true) {
            bCanDrag = true;
            nTimer++;
        }
        if (isSitting == false) {
            if (bCanDrag == true) {
                if (Gdx.input.isTouched()) {
                    vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                    viewport.unproject(vTouch);
                    fX = vTouch.x - 50;
                    fY = vTouch.y - 60;
                    setX(fX);
                    setY(fY);
                    nTimer = 0;
                }
            }
        }
        if (nTimer > 900) {
            System.out.println("This is horrible service!");
            if (isSitting == true) {
                leave(getX(), getY());
            }
        }
    }

    //Checks if guest is Sitting
    public void sittingDown(boolean isSitting_) {
        isSitting = isSitting_;
        if (isSitting == true) {
            setSize(0, 0);
            System.out.println("is sitting =" + isSitting);
        }
    }

    //Takes into account all events that affects hearts
    public void heartTracker(SpriteBatch _batch) {
        batch = _batch;
        fHx = fX;
        fHy = fY + 120;
        //Entered
        if (isWait == true) {
            bCanDrag = true;
            nTimer++;
        }
        if (bCanDrag == true) {
            if (Gdx.input.isTouched()) {
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
            batch.draw(txt3, fHx, fHy, 100, 30);

        } else if (nTimer > 300 && nTimer < 600) {
            isReady = true;
            batch.draw(txt2, fHx, fHy, 100, 30);

        } else if (nTimer > 600 && nTimer < 900) {
            bCanDrag = true;
            batch.draw(txt1, fHx, fHy, 100, 30);

        } else if (nTimer > 900) {
            System.out.println("This is horrible service!");
            batch.draw(txt0, fHx, fHy, 100, 30);
            leave(fHx, fHy);
        }
    }


    //When guest is too impatient, they leave
    private void leave(float _fX, float _fY) {
        setSize(100, 120);
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