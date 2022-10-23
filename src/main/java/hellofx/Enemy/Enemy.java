package hellofx.Enemy;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;
import com.almasb.fxgl.entity.Entity;

import static hellofx.Constant.GameConstant.ENEMY_SIZE;

public class Enemy extends Component {

    protected double speed = 0;

    public boolean right_;
    public boolean left_;
    public boolean up_;
    public boolean down_;
    public boolean isDead = false;
    public boolean canLoop = true;
    public boolean canLoopWalkRight = true;
    public boolean canLoopWalkLeft = true;

    protected double currentPosX;
    protected double currentPosY;

    public int speedX;
    public int speedY;
    public AnimatedTexture texture;
    public final AnimationChannel animDead = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 5, ENEMY_SIZE,
            ENEMY_SIZE, Duration.seconds(0.7), 0, 4);
    // anim right
    public final AnimationChannel animation = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE,
            ENEMY_SIZE, Duration.seconds(0.5), 6, 8);
    // anim left
    public final AnimationChannel animationLeft = new AnimationChannel(FXGL.image("enemy/balloon36.png"), 3, ENEMY_SIZE,
            ENEMY_SIZE, Duration.seconds(0.5), 3, 5);

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
        if(!isDead) {
            // Luu vi tri cu cua Enemy
            //entity.translateX(speedX * tpf);
            if (right_) {
                if(canLoopWalkRight) {
                    texture.loopAnimationChannel(animation);
                    canLoopWalkRight = false;
                    canLoopWalkLeft = true;
                }
                entity.translateX(1);
            } else if (left_) {
                if(canLoopWalkLeft) {
                    texture.loopAnimationChannel(animationLeft);
                    canLoopWalkLeft = false;
                    canLoopWalkRight = true;
                }
                entity.translateX(-1);
            }
            currentPosX = entity.getPosition().getX() - speedX * tpf;
            //entity.translateY(speedY * tpf);
            if (up_) {
                if(canLoopWalkRight) {
                    texture.loopAnimationChannel(animation);
                    canLoopWalkRight = false;
                    canLoopWalkLeft = true;
                }
                entity.translateY(-1);
            } else if (down_) {
                if(canLoopWalkLeft) {
                    texture.loopAnimationChannel(animationLeft);
                    canLoopWalkLeft = false;
                    canLoopWalkRight = true;
                }
                entity.translateY(1);
            }
            currentPosY = entity.getPosition().getY() - speedY * tpf;
        }
        else {
            if(canLoop) {
                texture.playAnimationChannel(animDead);
                canLoop = false;
            }
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

    public void turnBack() {};

    public double getCurrentPosX() {
        return currentPosX;
    }

    public double getCurrentPosY() {
        return currentPosY;
    }
}
