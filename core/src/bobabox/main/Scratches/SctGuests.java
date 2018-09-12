package bobabox.main.Scratches;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import bobabox.main.Objects.Tables;
import bobabox.main.Sprites.SprGuest;

public class SctGuests implements Screen {

    private OrthographicCamera camera;
    private Texture txtBG, txtTable;
    private SpriteBatch batch;
    private SprGuest sprGuest;
    Tables table;

    public SctGuests(Game game) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        txtBG = new Texture(Gdx.files.internal("Test_img.jpg"));
        txtTable = new Texture(Gdx.files.internal("rectangle-stroked.png"));
        batch = new SpriteBatch();
        sprGuest = new SprGuest("Guest_spr.png");
        table = new Tables(200, 100, "rectangle-stroked.png");
    }

    @Override
    public void render(float delta) {

        //Logic
        camera.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //Drawing
        batch.draw(txtBG, 0,0 );
        sprGuest.Patience();
        sprGuest.walkDown();
        sprGuest.draw(batch);
        table.draw(batch);
        batch.end();

        if(table.isOpen(sprGuest)==false){
            System.out.println("hehehrrrrer");
        }
    }

    @Override
    public void dispose() {
        txtBG.dispose();
        batch.dispose();

    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
