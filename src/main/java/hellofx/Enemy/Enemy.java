package hellofx.Enemy;

// import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimationChannel;

import hellofx.GameEntity.DynamicEntity.DynamicEntity;

import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;
import com.almasb.fxgl.entity.Entity;

import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class Enemy extends DynamicEntity {
    public boolean canLoop = true;
    public boolean canLoopWalkRight = true;
    public boolean canLoopWalkLeft = true;

    protected double currentPosX;
    protected double currentPosY;

    public int speedX;
    public int speedY;
    public AnimatedTexture texture;

    public Enemy() {
        animDead = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 6, ENEMY_SIZE,
                ENEMY_SIZE, Duration.seconds(0.7), 0, 5);
        animRight = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE,
                ENEMY_SIZE, Duration.seconds(0.5), 6, 8);
        animLeft = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE,
                ENEMY_SIZE, Duration.seconds(0.5), 3, 5);
    }

    public boolean isRight_() {
        return right_;
    }

    public boolean isLeft_() {
        return left_;
    }

    public boolean isUp_() {
        return up_;
    }

    public boolean isDown_() {
        return down_;
    }

    public Entity getMyEntity() {
        return entity;
    }

    public void dead() {
        isDead = true;
        up_ = false;
        down_ = false;
        right_ = false;
        left_ = false;
    }

    public void setCurrentPosX(double currentPosX) {
        this.currentPosX = currentPosX;
    }

    public void setCurrentPosY(double currentPosY) {
        this.currentPosY = currentPosY;
    }

    @Override
    public void onUpdate(double tpf) {
        if (!isDead) {
            if (right_) {
                setRightAnimationOnce();
                entity.translateX(1);
            } else if (left_) {
                setLeftAnimationOnce();
                entity.translateX(-1);
            }
            currentPosX = entity.getPosition().getX() - speedX * tpf;
            // entity.translateY(speedY * tpf);
            if (up_) {
                setRightAnimationOnce();
                entity.translateY(-1);
            } else if (down_) {
                setLeftAnimationOnce();
                entity.translateY(1);
            }
            currentPosY = entity.getPosition().getY() - speedY * tpf;
        } else {
            setDeadAnimationOnce();
        }
    }

    public void turnRight() {
        speedX = 70;
        speedY = 0;
        right_ = true;
        left_ = false;
        up_ = false;
        down_ = false;
    }

    public void turnLeft() {
        speedX = -70;
        speedY = 0;
        left_ = true;
        right_ = false;
        up_ = false;
        down_ = false;
    }

    public void turnUp() {
        speedY = -70;
        speedX = 0;
        right_ = false;
        left_ = false;
        up_ = true;
        down_ = false;
    }

    public void turnDown() {
        speedY = 70;
        speedX = 0;
        right_ = false;
        left_ = false;
        up_ = false;
        down_ = true;
    }

    public void turnBack() {
    };

    public double getCurrentPosX() {
        return currentPosX;
    }

    public double getCurrentPosY() {
        return currentPosY;
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
