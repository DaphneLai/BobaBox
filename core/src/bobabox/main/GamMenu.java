package bobabox.main;


import com.badlogic.gdx.Game;

import bobabox.main.Scratches.SctGuests;
import bobabox.main.Scratches.SctTap;
import bobabox.main.Screens.ScrGame;
import bobabox.main.Screens.ScrEnd;
import bobabox.main.Screens.ScrMenu;
import bobabox.main.Screens.ScrTut;
import bobabox.main.Scratches.SctWaiter;


public class GamMenu extends Game{

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
        nScreen = 2;
        scrGame = new ScrGame(this);
        scrEnd = new ScrEnd(this);
        scrMenu = new ScrMenu(this);
        scrTut = new ScrTut(this);
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
