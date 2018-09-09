package bobabox.main.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SprGuest extends Sprite {

    float fX, fY, fStopY;
    public static boolean ifEnd = false;

    public SprGuest(float _fX, float _fY, String sFile) {
        super(new Texture(Gdx.files.internal(sFile)));
        fX = _fX;
        fY = _fY;
        setPosition(fX, fY);
        setSize(100,120);
    }
    public void goDown() {
        fY -= fYSpeed;
        fYSpeed -= fGravity;
        setY(fY);
    }
}
