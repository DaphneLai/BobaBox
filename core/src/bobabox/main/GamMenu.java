package bobabox.main;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import bobabox.main.Scratches.SctGuests;
import bobabox.main.Scratches.SctTap;
import bobabox.main.Screens.ScrGame;
import bobabox.main.Screens.ScrEnd;
import bobabox.main.Screens.ScrMenu;
import bobabox.main.Screens.ScrTut;
import bobabox.main.Scratches.SctWaiter;


public class GamMenu extends Game {

    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 500;
    private OrthographicCamera camera; // what's seen
    private Viewport viewport; //how it's seen

    int nScreen;
    ScrGame scrGame;
    ScrEnd scrEnd;
    ScrMenu scrMenu;
    SctTap sctTap;
    ScrTut scrTut;
    SctGuests sctGuests;
    SctWaiter sctWaiter;

    //Kieran's code (modified)
    public void updateScreen(int _nScreen) {
        nScreen = _nScreen;
        switch (nScreen) {
            case 0:
                setScreen(scrGame);
                break;
            case 1:
                setScreen(scrEnd);
                break;
            case 2:
                setScreen(scrMenu);
                break;
            case 3:
                setScreen(scrTut);
                break;
            case 10:
                setScreen(sctTap);
                break;
            case 20:
                setScreen(sctGuests);
                break;
            case 30:
                setScreen(sctWaiter);
                break;
            default:
                break;
        }
    }

    public void create() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT,camera);
        scrGame = new ScrGame(this, viewport, camera);
        scrEnd = new ScrEnd(this, viewport, camera);
        scrMenu = new ScrMenu(this, viewport, camera);
        scrTut = new ScrTut(this, viewport, camera);
        sctTap = new SctTap(this);
        sctGuests = new SctGuests(this);
        sctWaiter = new SctWaiter(this);

        updateScreen(2);

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
