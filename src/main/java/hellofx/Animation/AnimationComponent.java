package hellofx.Animation;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
// import javafx.geometry.Point2D;
import javafx.util.Duration;
import static hellofx.Constant.GameConstant.*;

import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import hellofx.Bomb_Flame.*;

public class AnimationComponent extends Component {
    private int speed = 0;

    private int amountBoom = 1;
    public static boolean k = true;

    public AnimatedTexture texture;
    private final AnimationChannel animIdle, animWalk;
    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isLeft = false;
    public boolean isRight = true;
    public double getMyX;
    public double getMyY;

    // Nhan vat die
    AnimationChannel animDie = new AnimationChannel(FXGL.image("character/gold_player_dead.png"), 6, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.7),
            0, 5);
    // Nhan vat Win --> next level
    AnimationChannel animWin = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 3, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            0, 2);
    // Di chuyen len tren
    AnimationChannel animUp = new AnimationChannel(FXGL.image("character/gold_player_up.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.5),
            0, 2);
    // Di chuyen xuong duoi
    AnimationChannel animDown = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.3),
            0, 2);
    // Dung yen ben tren
    AnimationChannel IdleUp = new AnimationChannel(FXGL.image("character/gold_player_up.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.3),
            2, 2);
    // Dung yen ben duoi
    AnimationChannel IdleDown = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.3),
            2, 2);
    // Di chuyen sang trai
    AnimationChannel AnimLeft = new AnimationChannel(FXGL.image("character/gold_player_left_temp.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.5),
            0, 2);
    // Dung yen ben trai
    AnimationChannel IdleLeft = new AnimationChannel(FXGL.image("character/gold_player_left_temp.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.5),
            2, 2);

    /*
     * Khoi tao Animation.
     */
    public AnimationComponent() {
        // Dung yen ben phai
        animIdle = new AnimationChannel(FXGL.image("character/gold_player_right_temp.png"), 4, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.5),
                2, 2);
        // Di chuyen ben phai
        animWalk = new AnimationChannel(FXGL.image("character/gold_player_right_temp.png"), 4, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.5),
                0, 2);
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
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

    public void placeBoom() {
        if (amountBoom < 1 || !k) {
            return;
        }
        amountBoom--;
        int h = Boom.getSizeBoom();
        int l = Boom.getSizeBreak();
        k = false;
        Entity g_boom = spawn("boom", ((int) ((entity.getX() + 15) / TITLE_SIZE)) * TITLE_SIZE,
                ((int) ((entity.getY() + 15) / TITLE_SIZE)) * TITLE_SIZE);
        g_boom.getComponent(FlameAnimation.class).AnimationCenter();
        getGameTimer().runOnceAfter(() -> {
            Vector<Entity> tex = new Vector<>();
            g_boom.getComponent(Boom.class).explodeBoom(tex, h, l);
            amountBoom++;
        }, Duration.seconds(1.5));
    }

    public void loadDeadAnim() {
        texture.loopAnimationChannel(animDie);
    }

    public void loadWinAnim() {
        texture.loopAnimationChannel(animWin);
    }

    /**
     * Increase amount boom on map.
     */
    public void amountBoomUp() {
        amountBoom++;
    }

    /**
     * Reset amount boom on map.
     */
    public void amountBoomDown() {
        amountBoom = 1;
    }

    public void increaseSpeed() {
        CONST_SPEED += 20;
    }

    public void moveRight() {
        speed = CONST_SPEED;
    }

    public void moveLeft() {
        speed = -CONST_SPEED;
    }

    public void moveUp() {
        speed = -CONST_SPEED;
    }

    public void moveDown() {
        speed = CONST_SPEED;
    }

    public void stay() {
        speed = 1;
    }
}