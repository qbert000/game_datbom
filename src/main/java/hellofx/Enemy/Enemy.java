package hellofx.Enemy;

// import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimationChannel;

import hellofx.GameEntity.DynamicEntity.DynamicEntity;

import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;
import com.almasb.fxgl.entity.Entity;

import static hellofx.Constant.GameConstant.ENEMY_SIZE;
import static hellofx.Constant.GameConstant.TITLE_SIZE;
import static hellofx.Map.MyMap.canGoThisWay;

public class Enemy extends DynamicEntity {
    public boolean canLoop = true;
    public boolean canLoopWalkRight = true;
    public boolean canLoopWalkLeft = true;

    public int index_x_;
    public int index_y_;

    public boolean turn_right_;
    public boolean turn_left_;
    public boolean turn_up_;
    public boolean turn_down_;


    public AnimatedTexture texture;

    public Enemy() {
        animDead = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 6, ENEMY_SIZE,
                ENEMY_SIZE, Duration.seconds(0.7), 0, 5);
        animRight = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE,
                ENEMY_SIZE, Duration.seconds(0.5), 6, 8);
        animLeft = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE,
                ENEMY_SIZE, Duration.seconds(0.5), 3, 5);
    }


    public void dead() {
        isDead = true;
        up_ = false;
        down_ = false;
        right_ = false;
        left_ = false;
    }

    @Override
    public void onUpdate(double tpf) {
        if (!isDead) {
            move();
            if (right_) {
                setRightAnimationOnce();
                entity.translateX(speed);
            } else if (left_) {
                setLeftAnimationOnce();
                entity.translateX(speed);
            }
            // entity.translateY(speedY * tpf);
            if (up_) {
                //setRightAnimationOnce();
                entity.translateY(speed);
            } else if (down_) {
                //setLeftAnimationOnce();
                entity.translateY(speed);
            }
        } else {
            setDeadAnimationOnce();
        }
    }


    public void move() {
        if ((int) (entity.getY() % TITLE_SIZE) != 0 || (int) (entity.getX() % TITLE_SIZE) != 0) {
            System.out.println((int) (entity.getY() % TITLE_SIZE) + " " + (int) (entity.getX() % TITLE_SIZE));
            return;
        }
        //speed = 1;
        setMap();
        turnBack();
    }

    public void turnRight() {
        speed = 1;
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
    }

    public void turnLeft() {
        speed = -1;
        left_ = true;
        right_ = false;
        up_ = false;
        down_ = false;
    }

    public void turnUp() {
        speed = -1;
        right_ = false;
        left_ = false;
        up_ = true;
        down_ = false;
    }

    public void turnDown() {
        speed = 1;
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
    }

    public void turnBack() {

    };
    public void setMap() {

        index_x_ = (int) entity.getX() / TITLE_SIZE;
        index_y_ = (int) entity.getY() / TITLE_SIZE;

        // set ben trai
        turn_left_ = canGoThisWay(index_x_ - 1, index_y_);
        //set ben phai
        turn_right_ = canGoThisWay(index_x_ + 1, index_y_);
        //set ben tren
        turn_up_ = canGoThisWay(index_x_, index_y_ - 1);
        //set ben duoi
        turn_down_ = canGoThisWay(index_x_, index_y_ + 1);

        //System.out.println(index_x_ + " " +index_y_ + " " + turn_right_ + " " + turn_left_ + " " + turn_down_ + " " + turn_up_ );

    }


    public void setRightAnimationOnce() {
        if(canLoopWalkRight) {
            texture.loopAnimationChannel(animRight);
            canLoopWalkRight = false;
            canLoopWalkLeft = true;
        }
    }

    public void setLeftAnimationOnce() {
        if(canLoopWalkLeft) {
            texture.loopAnimationChannel(animLeft);
            canLoopWalkLeft = false;
            canLoopWalkRight = true;
        }
    }

    public void setDeadAnimationOnce() {
        if(canLoop) {
            texture.playAnimationChannel(animDead);
            canLoop = false;
        }
    }
}
