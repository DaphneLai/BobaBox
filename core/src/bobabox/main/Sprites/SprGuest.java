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
    private float fX, fY, fMove, fHx, fHy, fH, fW, fHw, fHh;
    private boolean isDown = false, isLeft = false, isUp = false, bCanDrag = false, isReady = false, isSitting, isGone = false;
    private int nTimer = 0;
    private Texture txt3, txt2, txt1, txt0;


    public SprGuest(String sFile, StretchViewport _viewport) {

        super(new Texture(Gdx.files.internal(sFile)));
        viewport = _viewport;
        //Guests
        fX = 80;
        fY = 330;
        fW = 80;
        fH = 100;
        fMove = 1.0f;
        setPosition(fX, fY);
        setFlip(false, false);
        setSize(fW, fH);

        //Hearts
        fHw = 100;
        fHh = 30;
        txt3 = new Texture("data/Hearts-01.png");
        txt2 = new Texture("data/Hearts-02.png");
        txt1 = new Texture("data/Hearts-03.png");
        txt0 = new Texture("data/Hearts-04.png");

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

                isSitting = false;
                bCanDrag = true;
            }
        }
    }
    //Checks if guest is Sitting
    public void sittingDown(boolean isSitting_) {
        isSitting = isSitting_;
        if (isSitting == true) {
            setSize(0, 0);

            //  System.out.println("is sitting =" + isSitting);
        }
    }

    //Active when the guest is dragged
    public void drag() {
        if (isDown == true) {
            nTimer++;
        }
        if (isMousedOver()) {
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
        }

        if (nTimer > 900) {
            if (isSitting == true) {
                leave(getX(), getY());
            }
        }
    }


    //Takes into account all events that affects hearts
    public void heartTracker(SpriteBatch _batch) {
        batch = _batch;
        fHx = fX - 10;
        fHy = fY + 120;
        if (bCanDrag == true) {
            if (Gdx.input.isTouched()) {
                nTimer = 0;
            }
        }

        //Ordering
        if (isSitting == true) {
            if (isReady == true) {
               // System.out.println("Ready to order");
            }
        }

        //Level of patience
        if (nTimer >= 0 && nTimer < 300) {
            batch.draw(txt3, fHx, fHy, fHw, fHh);

        } else if (nTimer > 300 && nTimer < 600) {
            isReady = true;
            batch.draw(txt2, fHx, fHy, fHw, fHh);

        } else if (nTimer > 600 && nTimer < 900) {
            batch.draw(txt1, fHx, fHy, fHw, fHh);

        } else if (nTimer > 900) {
            //System.out.println("This is horrible service!");
            batch.draw(txt0, fHx, fHy, fHw, fHh);
            leave(fHx, fHy);
            if (isGone == true) {
                setSize(0, 0);
            }
        }
    }


    //When guest is too impatient, they leave
    private void leave(float _fX, float _fY) {
        setSize(fW, fH);
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
                isGone = true;
                fHh = 0;
                fHw = 0;
            }
        }
    }

    public boolean isMousedOver() {
        vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(vTouch);
        if (vTouch.x > (fX-100) && vTouch.x < fX + (fW+100)) {
            if (vTouch.y > (fY - 50) && vTouch.y < fY + (fH + 50) ) {
                return true;
            }
        }
        return false;
    }
}
