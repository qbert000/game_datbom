package hellofx.SpawnSystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Rectangle;
// import javax.imageio.plugins.tiff.TIFFDirectory;
// import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Constant.GameConstant.*;
import hellofx.Bomb_Flame.*;
import hellofx.Animation.*;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(PLAYER)
                // .at(200, 200)
                .with(new AnimationComponent())
                .collidable()
                // de va cham phai co ham nay
                .with(new CollidableComponent(true))
                // point2d la chinh vi tri box so vs vi tri ban dau
                // Point la vi tri cua hop so vs ban dau la (0,0)
                // chieu cao vs chieu rong cua hop
                .bbox(new HitBox(new Point2D(5, 7), BoundingShape.box(26, 26)))
                .build();
    }

    @Spawns("wall")
    public Entity newCoin(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(WALL)
                // .viewWithBBox(new Rectangle(TITLE_SIZE, TITLE_SIZE, Color.BLACK))
                .viewWithBBox("mapTexture/wall.png")
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("brick")
    public Entity newBrick(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(WALL)
                // .viewWithBBox(new Rectangle(TITLE_SIZE, TITLE_SIZE, Color.BLACK))
                // .viewWithBBox("")
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(TITLE_SIZE, TITLE_SIZE)))
                .with(new CollidableComponent(true))
                .with(new BrickBreakAnimation())
                .build();
    }

    @Spawns("grass")
    public Entity newGrass(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(GRASS)
                // .viewWithBBox(new Rectangle(TITLE_SIZE, TITLE_SIZE, Color.BLACK))
                .viewWithBBox("mapTexture/grass.png")
                // .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("boom")
    public Entity newBoom(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(BOOM)
                .with(new FlameAnimation())
                .with(new Boom())
                .collidable()
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(TITLE_SIZE, TITLE_SIZE)))
                .build();
    }

    @Spawns("portal")
    public Entity newPortal(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(PORTAL)
                // .viewWithBBox(new Rectangle(TITLE_SIZE, TITLE_SIZE, Color.BLACK))
                .viewWithBBox("mapTexture/portal.png")
                .collidable()
                .build();
    }

    @Spawns("speedItem")
    public Entity newSpeedIem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(SPEED_ITEM)
                // .viewWithBBox(new Rectangle(TITLE_SIZE, TITLE_SIZE, Color.BLACK))
                .viewWithBBox("gameItem/powerup_speed.png")
                .collidable()
                .build();
    }

    @Spawns("flameItem")
    public Entity newFlameItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAME_ITEM)
                // .viewWithBBox(new Rectangle(TITLE_SIZE, TITLE_SIZE, Color.BLACK))
                .viewWithBBox("gameItem/powerup_flames.png")
                .collidable()
                .build();
    }

    @Spawns("flame")
    public  Entity newFlameCenter(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAME)
                .collidable()
                .with(new FlameAnimation())
                //.viewWithBBox(new Rectangle(40, 40, Color.BLACK))
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(TITLE_SIZE, TITLE_SIZE)))
                .build();
    }

    @Spawns("flameRight")
    public  Entity newFlameRight(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMERIGHT)
                .collidable()
                .with(new FlameAnimation())
                //.viewWithBBox(new Rectangle(40, 40, Color.BLACK))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }

    @Spawns("flameLeft")
    public  Entity newFlameLeft(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMELEFT)
                .collidable()
                .with(new FlameAnimation())
                //.viewWithBBox(new Rectangle(40, 40, Color.BLACK))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }

    @Spawns("flameUp")
    public  Entity newFlameUp(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMEUP)
                .collidable()
                .with(new FlameAnimation())
                //.viewWithBBox(new Rectangle(40, 40, Color.BLACK))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }

    @Spawns("flameDown")
    public  Entity newFlameDown(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMEDOWN)
                .collidable()
                .with(new FlameAnimation())
                //.viewWithBBox(new Rectangle(40, 40, Color.BLACK))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }


}
