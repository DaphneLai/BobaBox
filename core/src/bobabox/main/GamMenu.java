package bobabox.main;


import com.badlogic.gdx.Game;
import bobabox.main.Screens.scrGame;
import bobabox.main.Screens.scrEnd;
import  bobabox.main.Screens.scrMenu;



public class GamMenu extends Game {
    int nScreen;
    scrGame scrGame;
    scrEnd scrEnd;
    scrMenu scrMenu;
	


    public void updateScreen (int nScreen){
	    nScreen = nScreen;
	    if(nScreen ==0){
	        setScreen(scrGame);
        }
        else if(nScreen==1){
	        setScreen(scrEnd);
        }
        else if(nScreen==2){
	        setScreen(scrMenu);
        }

    }
	public void create () {
    nScreen = 0;
    scrGame = new scrGame(this);
    scrEnd = new scrEnd(this);
    scrMenu = new scrMenu(this);
    updateScreen(0);

	}

	@Override
	public void render () {
    super.render();
	}

    @Override
    public void resume () {

    }
    @Override
    public void resize (int width, int height) {

    }	@Override
    public void dispose () {
    super.dispose();
    }
}
