package hellofx.Enemy;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static hellofx.Constant.GameConstant.TITLE_SIZE;
public class EnemyDahl extends Enemy {

    private final List<Integer> turn_ = new ArrayList<Integer>();

    private int corner;

    public EnemyDahl() {
        animLeft = new AnimationChannel(FXGL.image("enemy/enemyDahl.png"), 3, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4), 3, 5);
        animRight = new AnimationChannel(FXGL.image("enemy/enemyDahl.png"), 3, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4), 6, 8);
        animDead = new AnimationChannel(FXGL.image("enemy/enemyDahl.png"), 6, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.7), 0, 5);
        texture = new AnimatedTexture(animRight);
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = false;
       setLeftAnimationOnce();
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void move() {
        if ((int) (entity.getY() % TITLE_SIZE) != 0 || (int) (entity.getX() % TITLE_SIZE) != 0) {
            return;
        }
        setMap();
        setCorner();
        if (corner == 1) {
            turnBack();
        }
        setTurnRandom();
    }



    public void setCorner() {
        corner = 0;
        if (turn_right_) {corner++;}
        if (turn_left_) {corner++;}
        if (turn_up_) {corner++;}
        if (turn_down_) {corner++;}
    }
    public void setTurnRandom() {
        if (turn_right_ && !left_) {turn_.add(0);}
        if (turn_left_ && !right_) {turn_.add(1);}
        if (turn_up_ && !down_) {turn_.add(2);}
        if (turn_down_ && !up_) {turn_.add(3);}

        int ranNum = ThreadLocalRandom.current().nextInt(0,turn_.size());
        switch (turn_.get(ranNum)) {
            case 0:
                turnRight();
                break;
            case 1:
                turnLeft();
                break;
            case 2:
                turnUp();
                break;
            case 3:
                turnDown();
                break;
            default:
        }
        turn_.clear();
    }


    @Override
    public void turnBack() {
        if (turn_right_) {
            turnRight();
        } else if (turn_left_) {
            turnLeft();
        } else if (turn_up_) {
            turnUp();
        } else if (turn_down_) {
            turnDown();
        }
    }
}
