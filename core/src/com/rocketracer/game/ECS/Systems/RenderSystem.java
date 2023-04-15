package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.SharedData.GameConfig;
import com.rocketracer.game.SharedData.YComparator;

import java.util.Comparator;

import javax.swing.text.Position;

public class RenderSystem extends SortedIteratingSystem {



    // static method to get screen width in metres
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();
    public static Vector2 getScreenSizeInMeters(){
        meterDimensions.set(Gdx.graphics.getWidth()*GameConfig.PIXELS_TO_METRES,
                            Gdx.graphics.getHeight()*GameConfig.PIXELS_TO_METRES);
        return meterDimensions;
    }

    // static method to get screen size in pixels
    public static Vector2 getScreenSizeInPixesl(){
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    // convenience method to convert pixels to meters
    public static float PixelsToMeters(float pixelValue){
        return pixelValue * GameConfig.PIXELS_TO_METRES;
    }

    private SpriteBatch batch; // a reference to our spritebatch
    private Array<Entity> renderQueue; // an array used to allow sorting of images allowing us to draw images on top of each other
    private Comparator<Entity> comparator; // a comparator to sort images based on the z position of the transfromComponent
    private OrthographicCamera cam; // a reference to our camera

    // component mappers to get components from entities
    private ComponentMapper<PositionComponent> pMapper;
    private ComponentMapper<SpriteComponent> sMapper;
    private static final Family FAMILY = Family.all(
            SpriteComponent.class,
            PositionComponent.class).get();

    /**
     * Constructor
     * @param batch the spritebatch to use for rendering
     */
	public RenderSystem(SpriteBatch batch) {
        // gets all entities with a sprite and position component
        super(Family.all(SpriteComponent.class, PositionComponent.class).get(), new YComparator());

        //creates out componentMappers
        pMapper = ComponentMapper.getFor(PositionComponent.class);
        sMapper = ComponentMapper.getFor(SpriteComponent.class);
        // create the array for sorting entities
        renderQueue = new Array<Entity>();
     
        this.batch = batch;  // set our batch to the one supplied in constructor

        // set up the camera to match our screen size
        cam = new OrthographicCamera(GameConfig.FRUSTUM_WIDTH, GameConfig.FRUSTUM_HEIGHT);
        cam.position.set(GameConfig.FRUSTUM_WIDTH / 2f, GameConfig.FRUSTUM_HEIGHT / 2f, 0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        //System.out.println("Render system running, delta: " + deltaTime);

        // sort the renderQueue based on y index
        //renderQueue.sort(comparator);
        
        // update camera and sprite batch
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        // loop through each entity in our render queue
        //System.out.println("Render queue size: " + renderQueue.size);
        for (Entity entity : renderQueue) {
            PositionComponent positionComponent = pMapper.get(entity);
            SpriteComponent spriteComponent = sMapper.get(entity);


            float width = spriteComponent.sprite.getWidth();
            float height = spriteComponent.sprite.getHeight();

            batch.draw(spriteComponent.sprite,
                    positionComponent.x, positionComponent.y,
                    0, 0,
                    width, height,
                    PixelsToMeters(spriteComponent.sprite.getScaleX()), PixelsToMeters(spriteComponent.sprite.getScaleY()),
                    spriteComponent.sprite.getRotation());
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    // convenience method to get camera
    public OrthographicCamera getCamera() {
        return cam;
    }
}