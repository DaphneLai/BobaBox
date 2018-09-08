package bobabox.main;


import com.badlogic.gdx.Game;

import bobabox.main.Scratches.SctTap;
import bobabox.main.Screens.ScrGame;
import bobabox.main.Screens.ScrEnd;
import bobabox.main.Screens.ScrMenu;


public class GamMenu extends Game{

    int nScreen;
    ScrGame scrGame;
    ScrEnd scrEnd;
    ScrMenu scrMenu;
    SctTap sctTap;

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
            case 10:
                setScreen(sctTap);
                break;
            default:
                break;
        }
    }

    public void create() {
        nScreen = 10;
        scrGame = new ScrGame(this);
        scrEnd = new ScrEnd(this);
        scrMenu = new ScrMenu(this);
        sctTap = new SctTap(this);

        updateScreen(10);

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
