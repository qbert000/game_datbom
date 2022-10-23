package hellofx.Enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
// import com.almasb.fxgl.animation.Animation;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class EnemyVertical extends Enemy {

    public EnemyVertical() {
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
        texture = new AnimatedTexture(animation);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
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
