package hellofx.Enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
// import com.almasb.fxgl.animation.Animation;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class EnemyVertical extends Enemy {

    protected final AnimationChannel animation;

    private final AnimationChannel animDead;

    public EnemyVertical() {
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
        animation = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE, ENEMY_SIZE,
                Duration.seconds(0.7), 6, 8);
        animDead = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 5, ENEMY_SIZE, ENEMY_SIZE,
                Duration.seconds(0.4), 0, 4);
        texture = new AnimatedTexture(animation);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    public void dead() {
        up_ = false;
        down_ = false;
        texture.loopAnimationChannel(animDead);
    }

    public void move() {
        texture.loopAnimationChannel(animation);
        if (up_) {
            turnUp();
        } else if (down_) {
            turnDown();
        }
        getGameTimer().runOnceAfter(() -> {
            move();
        }, Duration.seconds(0.2));
    }

    @Override
    public void turnBack() {
        if (up_) {
            up_ = false;
            down_ = true;
        } else if (down_) {
            up_ = true;
            down_ = false;
        }
    }

}
