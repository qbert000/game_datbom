package hellofx.Enemy;

// import com.almasb.fxgl.animation.Animation;
// import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
// import com.almasb.fxgl.texture.AnimationChannel;
// import javafx.util.Duration;

// import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
// import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class BalloonHorizontal extends Enemy {
    public BalloonHorizontal() {
        super();
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
        texture = new AnimatedTexture(animRight);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
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
