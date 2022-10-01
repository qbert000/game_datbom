package hellofx;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class AnimationComponent extends Component {

    private int speed = 0;

    public AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;
    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isLeft = false;
    public boolean isRight = true;
    public double getMyX;
    public double getMyY;

    // Di chuyen len tren
    AnimationChannel animUp = new AnimationChannel(FXGL.image("character/gold_player_up.png"), 4, 40, 40,
            Duration.seconds(0.5),
            0, 2);
    // Di chuyen xuong duoi
    AnimationChannel animDown = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 4, 40, 40,
            Duration.seconds(0.3),
            0, 2);
    // Dung yen ben tren
    AnimationChannel IdleUp = new AnimationChannel(FXGL.image("character/gold_player_up.png"), 4, 40, 40,
            Duration.seconds(0.3),
            2, 2);
    // Dung yen ben duoi
    AnimationChannel IdleDown = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 4, 40, 40,
            Duration.seconds(0.3),
            2, 2);
    // Di chuyen sang trai
    AnimationChannel AnimLeft = new AnimationChannel(FXGL.image("character/gold_player_left.png"), 4, 40, 40,
            Duration.seconds(0.45),
            0, 2);
    // Dung yen ben trai
    AnimationChannel IdleLeft = new AnimationChannel(FXGL.image("character/gold_player_left.png"), 4, 40, 40,
            Duration.seconds(0.45),
            2, 2);

    public AnimationComponent() {
        // Dung yen ben phai
        animIdle = new AnimationChannel(FXGL.image("character/gold_player_right.png"), 4, 40, 40,
                Duration.seconds(0.45),
                2, 2);
        // Di chuyen ben phai
        animWalk = new AnimationChannel(FXGL.image("character/gold_player_right.png"), 4, 40, 40,
                Duration.seconds(0.45),
                0, 2);
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        // entity.getTransformComponent().setScaleOrigin(new Point2D(32, 32));
        // entity la 1 component chua class hien tai
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        // ham check di chuyen ben trai
        if (isLeft && !isDown && !isUp && !isRight) {
            entity.translateX(speed * tpf);
            getMyX = entity.getPosition().getX() - speed * tpf;
            getMyY = entity.getPosition().getY();
            if (speed != 0) {
                if (texture.getAnimationChannel() == animIdle
                        || texture.getAnimationChannel() == IdleDown
                        || texture.getAnimationChannel() == IdleUp
                        || texture.getAnimationChannel() == animUp
                        || texture.getAnimationChannel() == animDown
                        || texture.getAnimationChannel() == IdleLeft
                        || texture.getAnimationChannel() == animWalk) {
                    texture.loopAnimationChannel(AnimLeft);
                }

                speed = (int) (speed * 0.9);

                if (FXGLMath.abs(speed) < 1) {
                    speed = 0;
                    texture.loopAnimationChannel(IdleLeft);
                }
            }
        }
        // ham check di chuyen ben phai
        if (isRight && !isUp && !isDown && !isLeft) {
            entity.translateX(speed * tpf);
            getMyX = entity.getPosition().getX() - speed * tpf;
            getMyY = entity.getPosition().getY();
            if (speed != 0) {
                if (texture.getAnimationChannel() == animIdle
                        || texture.getAnimationChannel() == IdleDown
                        || texture.getAnimationChannel() == IdleUp
                        || texture.getAnimationChannel() == animUp
                        || texture.getAnimationChannel() == animDown
                        || texture.getAnimationChannel() == AnimLeft
                        || texture.getAnimationChannel() == IdleLeft) {
                    texture.loopAnimationChannel(animWalk);
                }

                speed = (int) (speed * 0.9);

                if (FXGLMath.abs(speed) < 1) {
                    speed = 0;
                    texture.loopAnimationChannel(animIdle);
                }
            }
        }
        // ham check di chuyen ben tren
        if (isUp && !isDown && !isLeft && !isRight) {
            entity.translateY(speed * tpf);
            getMyX = entity.getPosition().getX();
            getMyY = entity.getPosition().getY() - speed * tpf;
            if (speed != 0) {
                if (texture.getAnimationChannel() == animIdle
                        || texture.getAnimationChannel() == IdleDown
                        || texture.getAnimationChannel() == IdleUp
                        || texture.getAnimationChannel() == animWalk
                        || texture.getAnimationChannel() == animDown
                        || texture.getAnimationChannel() == AnimLeft
                        || texture.getAnimationChannel() == IdleLeft) {
                    texture.loopAnimationChannel(animUp);
                }

                speed = (int) (speed * 0.9);

                if (FXGLMath.abs(speed) < 1) {
                    speed = 0;
                    texture.loopAnimationChannel(IdleUp);
                }
            }
        }
        // ham check di chuyen ben duoi
        if (isDown && !isUp && !isLeft && !isRight) {
            entity.translateY(speed * tpf);
            getMyX = entity.getPosition().getX();
            getMyY = entity.getPosition().getY() - speed * tpf;
            if (speed != 0) {

                if (texture.getAnimationChannel() == animIdle
                        || texture.getAnimationChannel() == IdleDown
                        || texture.getAnimationChannel() == IdleUp
                        || texture.getAnimationChannel() == animUp
                        || texture.getAnimationChannel() == animWalk
                        || texture.getAnimationChannel() == AnimLeft
                        || texture.getAnimationChannel() == IdleLeft) {
                    texture.loopAnimationChannel(animDown);
                }

                speed = (int) (speed * 0.9);

                if (FXGLMath.abs(speed) < 1) {
                    speed = 0;
                    texture.loopAnimationChannel(IdleDown);
                }
            }
        }
    }

    public void moveRight() {
        speed = 90;
    }

    public void moveLeft() {
        speed = -90;
    }

    public void moveUp() {
        speed = -90;
    }

    public void moveDown() {
        speed = 90;
    }

    public void stay() {
        speed = 1;
    }
}