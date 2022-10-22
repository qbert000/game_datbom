package hellofx.Enemy;

// import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class EnemyHorizontal extends Enemy {
    public EnemyHorizontal() {
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
        texture = new AnimatedTexture(animation);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
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
