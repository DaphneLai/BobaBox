package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import bobabox.main.Objects.ObjBar;

public class SprServer extends Sprite {

    private float fX, fY, fW, fH, fXG, fYG;//Server X, Y, W, H
    private int nDir = 4, nTimer = 0;
    private Texture txtSheet, txtServer, txtDrink;
    private TextureRegion[] traniFrames;
    private TextureRegion[][] trTmpFrames;
    private Animation[] araniServer;
    private float fElapsedTime, fDx[], fDy[];
    private boolean bHasDrink = false, bHasOrder = false;
    // nDir: 0 = North, 1 = East, 2 = South, 3 = West, 4 = stop

    public SprServer(float _fX, float _fY) {
        txtSheet = new Texture("data/SERVER1_sprsheet.png");
        txtServer = new Texture("data/SERVER1_spr.png");
        fX = _fX;
        fY = _fY;
        fW = 80;
        fH = 100;
        fDx = new float[]{0, 1, 0, -1, 0};
        fDy = new float[]{1, 0, -1, 0, 0};

        setFlip(false, false);

        txtDrink = new Texture("data/BubbleTea_img.png");
        araniServer = new Animation[4];

        trTmpFrames = TextureRegion.split(txtSheet, txtSheet.getWidth() / 5, txtSheet.getHeight() / 4);

        for (int i = 0; i < 4; i++) {
            traniFrames = new TextureRegion[5];
            for (int j = 0; j < 5; j++) {
                traniFrames[j] = trTmpFrames[i][j];
            }
            araniServer[i] = new Animation(1f / 6f, traniFrames);
        }
    }

    //under the condition that the
    private void directions(SpriteBatch batch, boolean isCstDragged) {

        if (!isCstDragged) {
            fX += fDx[nDir];
            fY += fDy[nDir];
            setX(fX);
            setY(fY);
        } else {
            nDir = 4;
        }
            if (nDir < 4) {
                batch.draw((TextureRegion) araniServer[nDir].getKeyFrame(fElapsedTime, true), fX, fY, fW - 10, fH);
            }


        if (nDir == 4) {
//            System.out.println("STOP");
            batch.draw(txtServer, fX, fY, fW, fH);
        }
    }


    // Makes server move to table coordinates
    public void update(float fXG, float fYG, SpriteBatch batch, boolean isCstDragged) {
        fElapsedTime += Gdx.graphics.getDeltaTime();//make sure to stop this timer when the game pauses
            directions(batch, isCstDragged);
            this.fXG = fXG;
            this.fYG = fYG;
//        System.out.println("FXG: " + fXG);
//        System.out.println("FYG: " + fYG);

        //North
        if (fY <= fYG - 1) {
            nDir = 0;
        } //East
        else if (fX <= fXG - 1) {
            nDir = 1;
        } //South
        else if (fY >= fYG + 1) {
            nDir = 2;
        } //West
        else if (fX >= fXG + 1) {
            nDir = 3;
        } else {
            nDir = 4;
        }
    }

    //server has arrived to table/bar
    public boolean arrived() {
        if (fY == fYG && fX == fXG && nDir == 4) {
//            System.out.println("HAS ARRIVED AT THE GOAL LOCATION MUAHAHHAHAHAHAH");
            return true;
        } else {
            return false;
        }
    }

    public void carryDrink(SpriteBatch batch, boolean bHasOrder, int nClickedBar) {
        this.bHasOrder = bHasOrder;

        if (bHasOrder && nClickedBar == 1) {
            if (arrived()) {
                nTimer++;
            }
        }

        if (nTimer >= 120) {
            batch.draw(txtDrink, 300 + fW, 350, 50, 50);
            if (nClickedBar >= 2) {
                bHasDrink = true;
            }
        }
        if (bHasDrink) {
            System.out.println("HAS DRINK");
            nTimer = 0;
            batch.draw(txtDrink, fX, fY + 10, 50, 50);

        }
    }
}



