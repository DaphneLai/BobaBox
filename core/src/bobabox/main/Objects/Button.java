package bobabox.main.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import bobabox.main.GamMenu;



public class Button {
    public void ClickButton(Sprite _sButton, int _nScreen, OrthographicCamera _camera, Vector3 _touchpoint, GamMenu _gammenu) {
        Sprite sButton = _sButton;
        int nScreen = _nScreen;
        OrthographicCamera camera = _camera;
        Vector3 touchpoint = _touchpoint;
        GamMenu gamMenu = _gammenu;
        if (Gdx.input.isTouched()) {
            camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (sButton.getBoundingRectangle().contains(touchpoint.x, touchpoint.y)) {
                gamMenu.updateScreen(nScreen);
            }

        }
    }
}
