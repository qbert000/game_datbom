package hellofx.Enemy;

// import com.almasb.fxgl.animation.Animation;
// import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static hellofx.Constant.GameConstant.TITLE_SIZE;
// import com.almasb.fxgl.texture.AnimationChannel;
// import javafx.util.Duration;

// import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
// import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class BalloonHorizontal extends Enemy {
    private final List<Integer> turn_ = new ArrayList<Integer>();
    private int corner;
    public BalloonHorizontal() {
        //super();
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

//    @Override
//    public void onUpdate(double tpf) {
//        if(!isDead) {
//            move();
//            System.out.println(speed);
//            if (right_) {
//                setRightAnimationOnce();
//                entity.translateX(speed);
//            } else if (left_) {
//                setLeftAnimationOnce();
//                entity.translateX(speed);
//            } else if (up_) {
//                entity.translateY(speed);
//            } else if (down_) {
//                entity.translateY(speed);
//            }
//        } else {
//            setDeadAnimationOnce();
//        }
//
//    }


    @Override
    public void turnBack() {
        if (right_ ) {
            if (!turn_right_) {
                turnLeft();
            } else {
                turnRight();
            }
        } else if (left_) {
            if (!turn_left_) {
                turnRight();
            } else {
                turnLeft();
            }
        }
    }

}
