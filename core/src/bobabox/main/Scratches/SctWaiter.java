package bobabox.main.Scratches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import bobabox.main.GamMenu;
import bobabox.main.Objects.ObjBar;
import bobabox.main.Objects.ObjButton;
import bobabox.main.Objects.ObjTables;
import bobabox.main.Sprites.SprServer;

//Sarah
//Help from Grondin & Daph
//Release 2.9 Scratch
public class SctWaiter implements Screen {

    //Logic
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private StretchViewport viewport;
    Vector3 vTouch;
    //Assets
    private Texture txBG;
    private SprServer sprServer;
    private ObjButton btnHome;
    private GamMenu gamMenu;
    private ShapeRenderer sh;
    private ObjBar objBar;
    private ObjTables arTables[] = new ObjTables[3];
    //Values
    private float fWORLD_WIDTH, fWORLD_HEIGHT, fXG, fYG;
    private boolean isTableClicked = false;

    public SctWaiter(GamMenu _gammenu) {
        gamMenu = _gammenu;

        //game height and width
        fWORLD_WIDTH = 1000;
        fWORLD_HEIGHT = 500;

        //camera
        camera = new OrthographicCamera();
        vTouch = new Vector3();
        viewport = new StretchViewport(1000, 500, camera);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();
        camera.setToOrtho(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0); //camera looks at the center of the screen

        //textures
        txBG = new Texture("data/GameBG_img.png");

        //sprites
        batch = new SpriteBatch();

        //bar
        objBar = new ObjBar(viewport, new Rectangle(300, 300, 450, 80));

        //table
        arTables[0] = new ObjTables(280, 80, "data/TABLE1_obj.png", "data/TABLE12_obj.png", viewport);
        arTables[1] = new ObjTables(fWORLD_WIDTH / 2, 50, "data/TABLE2_obj.png", "data/TABLE22_obj.png", viewport);
        arTables[2] = new ObjTables(fWORLD_WIDTH - 280, 80, "data/TABLE3_obj.png", "data/TABLE32_obj.png", viewport);

        //server
        sprServer = new SprServer("data/SERVER1_spr.png", fWORLD_WIDTH / 2, fWORLD_HEIGHT / 2, viewport); //850, 175

        //Buttons
        btnHome = new ObjButton(900, 30, 260 / 2, 70 / 2, "data/HOME1_btn.png", "data/HOME2_btn.png", viewport);

        //Shape Renderer
        //https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/graphics/glutils/ShapeRenderer.html
        sh = new ShapeRenderer();
    }

    @Override
    public void show() {
    }

    public void render(float delta) {

        //set up
        camera.update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(txBG, 0, 0, fWORLD_WIDTH, fWORLD_HEIGHT);
        sh.begin(ShapeRenderer.ShapeType.Line);
        sh.setColor(0, 0, 0, 1);

        //Matches touched coordinates and screen coordinates
        if (Gdx.input.isTouched()) {
            vTouch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            viewport.unproject(vTouch);
        }

        //drawing
        updateTable();
        btnHome.draw(batch);
        sprServer.draw(batch);
        batch.end();
        //Rectangle for Server
        sh.rect(sprServer.getX(), sprServer.getY(), sprServer.getWidth(), sprServer.getHeight());
        sh.end();

        //if home button clicked. Goes back to home screen
        if (btnHome.justClicked()) {
            gamMenu.updateScreen(2);
        }

        //Checks if bar is clicked
        if (objBar.isTapped()) {
            System.out.println("Bar is touched");
            fXG = objBar.rBar().x;
            fYG = objBar.rBar().y;
            sprServer.update(fXG,fYG);
        }
    }

    //Method runs through the array of tables
    private void updateTable() {
        for (int i = 0; i < 3; i++) {
            arTables[i].draw(batch);
            sh.rect(arTables[i].getX(), arTables[i].getY(), arTables[i].getWidth(), arTables[i].getHeight());

            // Checks if mouse is over table and clicked
            if (arTables[i].isTableClicked()) {
                fXG = arTables[i].getX() + (arTables[i].getWidth()/2 - 40);
                fYG = arTables[i].getY() + arTables[i].getHeight();
                isTableClicked = true;
            }

            if (isTableClicked) {
                sprServer.update(fXG, fYG);
            }


            //Debugging stuff
            if (arTables[0].isTableClicked()) {
                System.out.println("TABLE 11111111 WAS CLICKEDD");
            }
            if (arTables[1].isTableClicked()) {
                System.out.println("TABLE 22222222 WAS CLICKEDD");
            }
            if (arTables[2].isTableClicked()) {
                System.out.println("TABLE 33333333 WAS CLICKEDD");
            }

        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(1000 / 2, 500 / 2, 0);
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

    @Override
    public void dispose() {
        txBG.dispose();
    }

}