package hellofx.Enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import static hellofx.Constant.GameConstant.TITLE_SIZE;
import static hellofx.Map.MyMap.canGoThisWay;

public class EnemyDoria extends EnemyDahl {
    public EnemyDoria() {
        animLeft = new AnimationChannel(FXGL.image("enemy/enemyDoria40.png"), 3, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4), 3, 5);
        animRight = new AnimationChannel(FXGL.image("enemy/enemyDoria40.png"), 3, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4), 6, 8);
        animDead = new AnimationChannel(FXGL.image("enemy/enemyDoria40.png"), 6, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.7), 0, 5);
        texture = new AnimatedTexture(animRight);
        setRightAnimationOnce();
    }

    @Override
    public void setMap() {
        index_x_ = (int) entity.getX() / TITLE_SIZE;
        index_y_ = (int) entity.getY() / TITLE_SIZE;

        // set ben trai
        turn_left_ = canGoThisWay(index_x_ - 1, index_y_, "1");
        //set ben phai
        turn_right_ = canGoThisWay(index_x_ + 1, index_y_, "1");
        //set ben tren
        turn_up_ = canGoThisWay(index_x_, index_y_ - 1, "1");
        //set ben duoi
        turn_down_ = canGoThisWay(index_x_, index_y_ + 1, "1");
    }

    @Override
    public void onUpdate(double tpf) {
        if(!isDead) {
            move();
            if (right_) {
                setRightAnimationOnce();
                entity.translateX(1);
            } else if (left_) {
                setLeftAnimationOnce();
                entity.translateX(-1);
            } else if (up_) {
                entity.translateY(-1);
            } else if (down_) {
                entity.translateY(1);
            }
        } else {
            setDeadAnimationOnce();
        }
    }
}
