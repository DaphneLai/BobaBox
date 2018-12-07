package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class ObjBar {
    private Rectangle rect;
    private Vector3 vTouch;
    private StretchViewport viewport;
    private float fX, fY;
    private Texture txtBubbleTea;

    public ObjBar(StretchViewport _viewport, Rectangle rect) {
        //Importing Info
        viewport = _viewport;
        this.rect = rect;
        txtBubbleTea = new Texture("data/BubbleTea_img.png");
    }

    public boolean isTapped() {
        vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(vTouch);
        if (rect.contains(vTouch.x, vTouch.y) && Gdx.input.justTouched()) {
            return true;
        }
        return false;
    }


    public Rectangle rBar() {
        return rect;
    }
}


