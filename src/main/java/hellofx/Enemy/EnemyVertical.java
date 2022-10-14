package hellofx.Enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.getPhysicsWorld;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Constant.GameConstant.TITLE_SIZE;
import static hellofx.SpawnSystem.Enum.*;

public class EnemyVertical extends Enemy{

    protected final AnimationChannel animation;

    public EnemyVertical() {
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
        animation = new AnimationChannel(FXGL.image("character\\bomb.png")
                , 3, TITLE_SIZE, TITLE_SIZE
                , Duration.seconds(0.5), 0,2);
        texture = new AnimatedTexture(animation);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    public void move() {
        texture.loopAnimationChannel(animation);
        if (up_) {
            turnUp();
        } else if (down_) {
            turnDown();
        }
        turnBack();//va chạm
        getGameTimer().runOnceAfter(() -> {
            move();
        }, Duration.seconds(0.2));
    }

    @Override
    public void turnBack() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ENEMYVERTICAL, WALL) {
            @Override
            protected void onCollisionBegin(Entity enemyVertical, Entity wall) {
                entity.setPosition(new Point2D(entity.getPosition().getX(), currentPosY));
                if (up_) {
                    up_ = false;
                    down_ = true;
                } else if (down_) {
                    up_ = true;
                    down_ = false;
                }
            }

            @Override
            protected void onCollision(Entity enemyVertical, Entity wall) {
                entity.setPosition(new Point2D(entity.getPosition().getX(), currentPosY));
            }
        });
    }


}
