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

//https://github.com/TimCatana/gamegravity/blob/master/core/src/com/catani/gamegravity/SprChar.java

public class SprServer extends Sprite {

    private float fX, fY, fW, fH;//Server X, Y, W, H
    private int nDir = 4, nTimer = 0;
    private Texture txtSheet, txtServer, txtDrink;
    private TextureRegion[] traniFrames;
    private TextureRegion[][] trTmpFrames;
    private Animation[] araniServer;
    private float fElapsedTime, fMove = 1.5f;
    private boolean bHasDrink = false, HasOrder;
    // nDir: 0 = North, 1 = East, 2 = South, 3 = West, 4 = stop

    public SprServer(float _fX, float _fY) {
        txtSheet = new Texture("data/SERVER1_sprsheet.png");
        txtServer = new Texture("data/SERVER1_spr.png");
        fX = _fX;
        fY = _fY;
        fW = 80;
        fH = 100;
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


    private void directions(SpriteBatch batch) {
        if (nDir < 4) {
            batch.draw((TextureRegion) araniServer[nDir].getKeyFrame(fElapsedTime, true), fX, fY, fW - 10, fH);
        }
        if (nDir == 0) {
//            System.out.println("NORTH");
            fY += fMove;
            setY(fY);

        } else if (nDir == 1) {
//            System.out.println("EAST");
            fX += fMove;
            setX(fX);

        } else if (nDir == 2) {
//            System.out.println("SOUTH");
            fY -= fMove;
            setY(fY);

        } else if (nDir == 3) {
//            System.out.println("WEST");
            fX -= fMove;
            setX(fX);

        } else if (nDir == 4) {
//            System.out.println("STOP");
            batch.draw(txtServer, fX, fY, fW, fH);
            setX(fX);
            setY(fY);
        }
    }


    // Makes server move to table coordinates
    public void update(float fXG, float fYG, SpriteBatch batch) {
//        System.out.println("ndir" + nDir);
        fElapsedTime += Gdx.graphics.getDeltaTime();//make sure to stop this timer when the game pauses
        directions(batch);
        //North
        if (fY < fYG - 1) {
            nDir = 0;
        } //East
        else if (fX < fXG - 1) {
            nDir = 1;
        } //South
        else if (fY > fYG + 1) {
            nDir = 2;
        } //West
        else if (fX > fXG + 1) {
            nDir = 3;
        } else {
            nDir = 4;
        }
    }

    public void carryDrink(SpriteBatch batch, boolean hasOrder, ObjBar objBar) {
        HasOrder = hasOrder;
//        System.out.println("NDIRytrset7yfghcf" + " "+ nDir);
//        System.out.println("FX " + fX + " FY "+ fY);
        System.out.println("nTimer " + nTimer);
        if (fX == objBar.rBar().x + 1 && fY == objBar.rBar().y - 20) {
            if (HasOrder && nDir == 4) {
                nTimer++;
            }
        }
        if(bHasDrink){
            HasOrder = false;
            nTimer = 0;
            batch.draw(txtDrink, fX, fY +10,  50, 50);
        }
        if (nTimer >= 60 && HasOrder) {
            batch.draw(txtDrink, objBar.rBar().x + fW, objBar.rBar().y + 50 , 50, 50);
            if (objBar.isTapped()) {
                bHasDrink = true;
            }
        }
    }
}



