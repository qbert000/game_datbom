package hellofx.Enemy;

// import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
// import com.almasb.fxgl.texture.AnimationChannel;
// // import com.almasb.fxgl.animation.Animation;
// import javafx.util.Duration;
// import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
// import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class BalloonVertical extends Enemy {

    public BalloonVertical() {
        super();
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
        texture = new AnimatedTexture(animRight);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void turnBack() {
        if (down_ ) {
            if (!turn_down_) {
                turnUp();
            } else {
                turnDown();
            }
        } else if (up_) {
            if (!turn_up_) {
                turnDown();
            } else {
                turnUp();
            }
        }
    }

}
