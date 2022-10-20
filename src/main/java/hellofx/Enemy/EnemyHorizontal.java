package hellofx.Enemy;

// import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class EnemyHorizontal extends Enemy {
    protected final AnimationChannel animation;

    private final AnimationChannel animDead;

    public EnemyHorizontal() {
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
        animation = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE, ENEMY_SIZE,
                Duration.seconds(0.7), 6, 8);
        animDead = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 5, ENEMY_SIZE, ENEMY_SIZE,
                Duration.seconds(0.3), 0, 4);
        texture = new AnimatedTexture(animation);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    public void dead() {
        right_ = false;
        left_ = false;
        texture.loopAnimationChannel(animDead);
    }

    public void move() {
        texture.loopAnimationChannel(animation);
        if (right_) {
            turnRight();
        } else if (left_) {
            turnLeft();
        }
        getGameTimer().runOnceAfter(() -> {
            move();
        }, Duration.seconds(0.2));
    }

    @Override
    public void turnBack() {
        if (right_) {
            right_ = false;
            left_ = true;
        } else if (left_) {
            right_ = true;
            left_ = false;
        }
    }

}
