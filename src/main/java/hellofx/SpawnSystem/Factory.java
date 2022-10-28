package hellofx.SpawnSystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import hellofx.Enemy.*;
import javafx.scene.control.Label;
import hellofx.GameEntity.DynamicEntity.Player;
import hellofx.GameUI.GameUIComponent;
import javafx.geometry.Point2D;
import static hellofx.GameUI.GameUIComponent.*;

import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Constant.GameConstant.*;
import hellofx.Bomb_Flame.*;
import hellofx.Animation.*;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(PLAYER)
                .with(new Player())
                .zIndex(1000)
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(6, 1), BoundingShape.box(26, 36)))
                .build();
    }

    @Spawns("background") 
    public Entity newBackGround(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view("background/score.png")
                .build();
    }

    @Spawns("balloonHorizontal")
    public Entity newEnemyHorizon(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(BALLOONHORIZONTAL)
                .zIndex(900)
                .with(new CollidableComponent(true))
                .with(new BalloonHorizontal())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(38, 38)))
                .build();
    }

    @Spawns("enemyPontan")
    public Entity newEnemyRandom(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(ENEMYPONTAN)
                .zIndex(1000)
                .with(new EnemyDahl())
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(38, 38)))
                .build();
    }

    @Spawns("enemyDoria")
    public Entity newEnemyDoria(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(ENEMYDORIA)
                .zIndex(800)
                .with(new EnemyDoria())
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(38, 38)))
                .build();
    }

    @Spawns("enemyDahl")
    public Entity newEnemy8(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(ENEMYDAHL)
                .zIndex(900)
                .with(new EnemyPontan())
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(38, 38)))
                .build();
    }

    @Spawns("enemyPass")
    public Entity newEnemy1(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(ENEMYPASS)
                .zIndex(900)
                .with(new CollidableComponent(true))
                .with(new EnemyPass())
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(40, 40)))
                .build();
    }

    @Spawns("balloonVertical")
    public Entity newEnemyVertical(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(BALLOONVERTICAL)
                .zIndex(900)
                .with(new CollidableComponent(true))
                .with(new BalloonVertical())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(38, 38)))
                .build();
    }

    @Spawns("wall")
    public Entity newCoin(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(WALL)
                .viewWithBBox("mapTexture/wall2.png")
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("brick")
    public Entity newBrick(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(WALL)
                .bbox(new HitBox(new Point2D(0, 0), BoundingShape.box(TITLE_SIZE, TITLE_SIZE)))
                .with(new CollidableComponent(true))
                .with(new BrickBreakAnimation())
                .build();
    }

    @Spawns("grass")
    public Entity newGrass(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(GRASS)
                .viewWithBBox("mapTexture/grass.png")
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
                .viewWithBBox("mapTexture/portal.png")
                .collidable()
                .build();
    }

    @Spawns("speedItem")
    public Entity newSpeedIem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(SPEED_ITEM)
                .viewWithBBox("gameItem/powerup_speed.png")
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(10, 10), BoundingShape.box(TITLE_SIZE - 20, TITLE_SIZE - 20)))
                .build();
    }

    @Spawns("flameItem")
    public Entity newFlameItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAME_ITEM)
                .viewWithBBox("gameItem/powerup_flames.png")
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(10, 10), BoundingShape.box(TITLE_SIZE - 20, TITLE_SIZE - 20)))
                .build();
    }

    @Spawns("bombItem")
    public Entity newBombItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(BOMB_ITEM)
                .viewWithBBox("gameItem/powerup_bombs.png")
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(10, 10), BoundingShape.box(TITLE_SIZE - 20, TITLE_SIZE - 20)))
                .build();
    }

    @Spawns("flamePowerItem")
    public Entity newFlamePowerItem(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAME_POWER_ITEM)
                .viewWithBBox("gameItem/powerup_flamepass.png")
                .with(new CollidableComponent(true))
                .bbox(new HitBox(new Point2D(10, 10), BoundingShape.box(TITLE_SIZE - 20, TITLE_SIZE - 20)))
                .build();
    }

    @Spawns("flame")
    public Entity newFlameCenter(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAME)
                .collidable()
                .with(new FlameAnimation())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(TITLE_SIZE, TITLE_SIZE)))
                .build();
    }

    @Spawns("flameRight")
    public Entity newFlameRight(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMERIGHT)
                .collidable()
                .with(new FlameAnimation())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }

    @Spawns("flameLeft")
    public Entity newFlameLeft(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMELEFT)
                .collidable()
                .with(new FlameAnimation())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }

    @Spawns("flameUp")
    public Entity newFlameUp(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMEUP)
                .collidable()
                .with(new FlameAnimation())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }

    @Spawns("flameDown")
    public Entity newFlameDown(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(FLAMEDOWN)
                .collidable()
                .with(new FlameAnimation())
                .bbox(new HitBox(new Point2D(1, 1), BoundingShape.box(flame4dirSize, flame4dirSize)))
                .build();
    }
    @Spawns("scoreBalloon")
    public Entity newBScore(SpawnData data) {
        return FXGL.entityBuilder(data)
                .collidable()
                .view(GameUIComponent.scoreOnScreen(BALLOON_SCORE))
                .zIndex(60000)
                .build();
    }

    @Spawns("scoreDahl")
    public Entity newDScore(SpawnData data) {
        return FXGL.entityBuilder(data)
                .collidable()
                .view(GameUIComponent.scoreOnScreen(DAHL_SCORE))
                .zIndex(100000)
                .build();
    }

    @Spawns("scoreDoria")
    public Entity newDRScore(SpawnData data) {
        return FXGL.entityBuilder(data)
                .collidable()
                .view(GameUIComponent.scoreOnScreen(DORIA_SCORE))
                .zIndex(90000)
                .build();
    }

    @Spawns("scorePass")
    public Entity newPassScore(SpawnData data) {
        return FXGL.entityBuilder(data)
                .collidable()
                .view(GameUIComponent.scoreOnScreen(PASS_SCORE))
                .zIndex(80000)
                .build();
    }

    @Spawns("scorePontan")
    public Entity newPontanScore(SpawnData data) {
        return FXGL.entityBuilder(data)
                .collidable()
                .view(GameUIComponent.scoreOnScreen(PONTAN_SCORE))
                .zIndex(70000)
                .build();
    }

}
