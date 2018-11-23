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

//https://github.com/TimCatana/gamegravity/blob/master/core/src/com/catani/gamegravity/SprChar.java

public class SprServer extends Sprite {

    private float fX, fY, fW, fH;//Server X, Y, W, H
    private int nDir = 4;

    private Texture SpriteSheet, txtServer;
    private TextureRegion[][] tmp;
    private TextureRegion[] txtWest, txtEast, txtNorth, txtSouth;
    private Animation[] AnmCreateAnimation;
    private float ElapsedTime;
    private int index = 0;

    // nDir: 0 = North, 1 = East, 2 = South, 3 = West, 4 = stop

    public SprServer(float _fX, float _fY) {
        SpriteSheet = new Texture("data/SERVER1_sprsheet.png");
        txtServer = new Texture("data/SERVER1_spr.png");
        PrepareAnimations(4, 5);
        fX = _fX;
        fY = _fY;
        fW = 80;
        fH = 100;
        setPosition(fX, fY);
        setFlip(false, false);
    }


    private void directions(SpriteBatch batch) {
        if (nDir == 0) {
            System.out.println("NORTH");
            fY += 0.5f;
            setY(fY);
            batch.draw((TextureRegion) AnmCreateAnimation[0].getKeyFrame(ElapsedTime, true), fX, fY, fW, fH + 50);

        } else if (nDir == 1) {
            System.out.println("EAST");
            fX += 1.5f;
            setX(fX);
            batch.draw((TextureRegion) AnmCreateAnimation[1].getKeyFrame(ElapsedTime, true), fX, fY, fW, fH + 50);

        } else if (nDir == 2) {
            System.out.println("SOUTH");
            fY -= 0.5f;
            setY(fY);
            batch.draw((TextureRegion) AnmCreateAnimation[2].getKeyFrame(ElapsedTime, true), fX, fY, fW, fH + 50);

        } else if (nDir == 3) {
            System.out.println("WEST");
            fX -= 1.5f;
            setX(fX);
            batch.draw((TextureRegion) AnmCreateAnimation[3].getKeyFrame(ElapsedTime, true), fX, fY, fW, fH + 50);

        } else if (nDir == 4) {
            System.out.println("STOP");
            batch.draw(txtServer, fX, fY, fW, fH); //normal
            setX(fX);
            setY(fY);
        }
    }

    private void PrepareAnimations(int rows, int columns) {
        tmp = TextureRegion.split(SpriteSheet, SpriteSheet.getWidth() / columns, SpriteSheet.getHeight() / rows);

        txtWest = new TextureRegion[5];
        txtEast = new TextureRegion[5];
        txtNorth = new TextureRegion[5];
        txtSouth = new TextureRegion[5];

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < columns; j++) {
                txtWest[index++] = tmp[i][j];
            }
        }

        index = 0;

        for (int i = 3; i < 4; i++) {
            for (int j = 0; j < columns; j++) {
                txtEast[index++] = tmp[i][j];
            }
        }

        index = 0;

        for (int i = 2; i < 3; i++) {
            for (int j = 0; j < columns; j++) {
                txtNorth[index++] = tmp[i][j];
            }
        }

        index = 0;

        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < columns; j++) {
                txtSouth[index++] = tmp[i][j];
            }
        }

        AnmCreateAnimation = new Animation[4];

        AnmCreateAnimation[0] = new Animation<TextureRegion>(0.5f, txtNorth); // north
        AnmCreateAnimation[1] = new Animation<TextureRegion>(0.5f, txtEast); // east
        AnmCreateAnimation[2] = new Animation<TextureRegion>(0.5f, txtSouth); // south
        AnmCreateAnimation[3] = new Animation<TextureRegion>(0.5f, txtWest); // west

    }

    // Makes server move to table coordinates
    public void update(float fXG, float fYG, SpriteBatch batch) {
        ElapsedTime += Gdx.graphics.getDeltaTime();
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


        //Makes server move right according to coordinate location
//        System.out.println(fYG + " TABLE X");
//        System.out.println(fY + " SERVER X");

    }

}







