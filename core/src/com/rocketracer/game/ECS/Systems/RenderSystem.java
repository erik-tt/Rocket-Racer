package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.SharedData.GameConfig;

import java.util.Comparator;

/**
 * Deals with rendering the entities in the game.
 */
public class RenderSystem extends IteratingSystem {

    // static method to get screen width in metres
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

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

    //Constructor
	public RenderSystem(SpriteBatch batch) {
        // gets all entities with a sprite and position component
        super(Family.all(SpriteComponent.class, PositionComponent.class).get());

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
        
        // update camera and sprite batch
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        // loop through each entity in our render queue
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

    //Methods
    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return cam;
    }
}