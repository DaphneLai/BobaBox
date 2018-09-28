package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjHearts extends Sprite {
    private float fX, fY, fMove;
    private SpriteBatch batch;
    private int nTimer = 0;
    private Texture txt3, txt2, txt1, txt0;
    private boolean isWait = false, canDrag = false, isReady = false, isSitting, isLeft = false, isUp = false;

    public ObjHearts() {
        fX = 10;
        fY = Gdx.graphics.getHeight() - 10;
        fMove = 1.0f;
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
             // Ordering
           if (isSitting == true) {
             //  canDrag = false;
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
                  if (isSitting == true) {
                      Leave(fX, fY);
                  }

           }
       }

       public void walkDown() {
           if (isWait == false) {
               fY -= fMove + 5;
               setY(fY);
               if (fY <= 130) {
                   fMove = 0;
                   isWait = true;
                   canDrag = false;
               }
           }
       }
       public void Leave(float fX_, float fY_) {
           fX = fX_;
           fY = fY_;
           if (isLeft == false) {
               fX -= fMove + 5;
               setX(fX);
               if (fX <= 70) {
                   isLeft = true;
               }
           }
           if (isUp == false && isLeft == true) {
               fY += fMove + 5;
               setY(fY);
               if (fY >= 450) {
                   isUp = true;
               }
           }
       }
}
