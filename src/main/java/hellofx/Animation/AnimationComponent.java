package hellofx.Animation;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import hellofx.Enemy.Enemy1;
// import javafx.geometry.Point2D;
import javafx.util.Duration;
import static hellofx.Constant.GameConstant.*;
import static hellofx.Map.Mymap.g_map;
import static hellofx.Map.Mymap.enemy;
import static hellofx.Constant.GameConstant.ENEMY_NUMBER;
import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import hellofx.Bomb_Flame.*;
import hellofx.Enemy.PathFinding;

public class AnimationComponent extends Component {
    public double speed = 0;

    private static int amountBoom = 1;
    public static boolean k = true;

    public static double currentXpos;
    public static double currentYpos;

    public AnimatedTexture texture;
    private final AnimationChannel animIdle, animWalk;
    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isLeft = false;
    public boolean isRight = true;
    public boolean isStay = true;
    public double getMyX;
    public double getMyY;
    public double spDistance = 0;
    public boolean onCollision = false;
    public boolean isWin = false;
    public boolean isDead = false;

    // Nhan vat die
    AnimationChannel animDie = new AnimationChannel(FXGL.image("character/gold_player_dead.png"), 6, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.9),
            0, 5);
    // Nhan vat Win --> next level
    AnimationChannel animWin = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 3, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            0, 2);
    // Di chuyen len tren
    AnimationChannel animUp = new AnimationChannel(FXGL.image("character/gold_player_up.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            0, 2);
    // Di chuyen xuong duoi
    AnimationChannel animDown = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            0, 2);
    // Dung yen ben tren
    AnimationChannel IdleUp = new AnimationChannel(FXGL.image("character/gold_player_up.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            2, 2);
    // Dung yen ben duoi
    AnimationChannel IdleDown = new AnimationChannel(FXGL.image("character/gold_player_down.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            2, 2);
    // Di chuyen sang trai
    AnimationChannel AnimLeft = new AnimationChannel(FXGL.image("character/gold_player_left_temp.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            0, 2);
    // Dung yen ben trai
    AnimationChannel IdleLeft = new AnimationChannel(FXGL.image("character/gold_player_left_temp.png"), 4, TITLE_SIZE,
            TITLE_SIZE,
            Duration.seconds(0.4),
            2, 2);

    /*
     * Khoi tao Animation.
     */
    public AnimationComponent() {
        // Dung yen ben phai
        animIdle = new AnimationChannel(FXGL.image("character/gold_player_right_temp.png"), 4, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4),
                2, 2);
        // Di chuyen ben phai
        animWalk = new AnimationChannel(FXGL.image("character/gold_player_right_temp.png"), 4, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4),
                0, 2);
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        // entity la 1 component chua class hien tai
        entity.getViewComponent().addChild(texture);
    }

    public static int getAmountBoom() {
        return amountBoom;
    }

    public String getStatus() {
        if (isLeft)
            return "LEFT";
        if (isRight)
            return "RIGHT";
        if (isUp)
            return "UP";
        if (isDown)
            return "DOWN";
        else
            return "BUG!!!!";
    }

    @Override
    public void onUpdate(double tpf) {
        if (!isWin && !isDead) {
            // ham check di chuyen ben duoi
            if (isDown && !isUp && !isLeft && !isRight) {
                makeSupportMoveVertical();
                if (speed == 0 && isStay) {
                    setIdleAnim();
                }
                entity.translateY(speed);
                getMyX = getXPos();
                getMyY = getYPos() - speed;
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

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(IdleDown);
                    }
                }
            }
            // ham check di chuyen ben trai
            if (isLeft && !isDown && !isUp && !isRight) {
                makeSupportMoveHorizontal();
                if (speed == 0) {
                    setIdleAnim();
                }
                entity.translateX(speed);
                getMyX = getXPos() - speed;
                getMyY = getYPos();
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

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(IdleLeft);
                    }
                }
            }
            // ham check di chuyen ben phai
            if (isRight && !isUp && !isDown && !isLeft) {
                makeSupportMoveHorizontal();
                if (speed == 0) {
                    setIdleAnim();
                }
                entity.translateX(speed);
                getMyX = getXPos() - speed;
                getMyY = getYPos();
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

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(animIdle);
                    }
                }
            }
            // ham check di chuyen ben tren
            if (isUp && !isDown && !isLeft && !isRight) {
                makeSupportMoveVertical();
                if (speed == 0) {
                    setIdleAnim();
                }
                entity.translateY(speed);
                getMyX = getXPos();
                getMyY = getYPos() - speed;
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

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(IdleUp);
                    }
                }
            }
            double tempX = Math.round(getXPos() / 40);
            double tempY = Math.round(getYPos() / 40);
            if (Math.abs(tempX - getXPos() / 40) < 0.4 && Math.abs(tempY - getYPos() / 40) < 0.4) {
                if (!g_map.updatePlayerPosition((int) tempX, (int) tempY)) {
                    // g_map.getPlayerTile();
                    // PathFinding.resetFinding = true;
                    for (int i = 0; i < ENEMY_NUMBER; i++) {
                        enemy[i].getComponent(Enemy1.class).findPlayer.resetFinding = true;
                    }
                }
            }
        } else {

        }
    }

    public double getXPos() {
        return entity.getPosition().getX();
    }

    public double getYPos() {
        return entity.getPosition().getY();
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

    public void supportMoveVertical(Entity player, Entity wall, boolean hasRightTile, boolean hasLeftTile) {
        double diff = player.getPosition().getX() - wall.getPosition().getX();
        if (FXGLMath.abs(diff) >= 0 && FXGLMath.abs((int) diff) <= 2) {
            // System.out.println("MIDDLE");
        } else if (diff > 0) {
            if (!hasRightTile) {
                double translate = 35 - diff;
                player.getComponent(AnimationComponent.class).spDistance = translate;
            }
        } else {
            if (!hasLeftTile) {
                double translate = -(35 + diff);
                player.getComponent(AnimationComponent.class).spDistance = translate;
            }
        }
    }

    public void supportMoveHorizontal(Entity player, Entity wall, boolean hasUpTile, boolean hasDownTile) {
        double diff = player.getPosition().getY() - wall.getPosition().getY();
        if (FXGLMath.abs(diff) >= 0 && FXGLMath.abs((int) diff) <= 10) {
            // System.out.println("MIDDLE");
        } else if (diff > 0) {
            if (!hasDownTile) {
                double translate = 41.5 - diff;
                player.getComponent(AnimationComponent.class).spDistance = translate;
            }
        } else {
            if (!hasUpTile) {
                double translate = -(41.5 + diff);
                ;
                player.getComponent(AnimationComponent.class).spDistance = translate;
            }
        }
    }

    public void makeSupportMoveVertical() {
        if (spDistance < 0) {
            entity.translateX(-3);
            spDistance += 3;
        }
        if (spDistance > 0) {
            entity.translateX(3);
            spDistance -= 3;
        }
    }

    public void makeSupportMoveHorizontal() {
        if (spDistance < 0) {
            entity.translateY(-3);
            spDistance += 3;
        }
        if (spDistance > 0) {
            entity.translateY(3);
            spDistance -= 3;
        }
    }

    public void setIdleAnim() {
        if (isUp) {
            texture.loopAnimationChannel(IdleUp);
            return;
        }
        if (isDown) {
            texture.loopAnimationChannel(IdleDown);
        }
        if (isLeft) {
            texture.loopAnimationChannel(IdleLeft);
        }
        if (isRight) {
            texture.loopAnimationChannel(animIdle);
        }
    }

    public void loadDeadAnim() {
        isDead = true;
        texture.loopAnimationChannel(animDie);
    }

    public void loadWinAnim() {
        isWin = true;
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
    public static void amountBoomDown() {
        amountBoom = 1;
    }

    public void increaseSpeed() {
        CONST_SPEED += 0.5;
        if (CONST_SPEED > 3.0) {
            CONST_SPEED = 3.0;
        }
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
        isStay = true;
        speed = 1;
    }

    public static void increaseBoomAmount() {
        amountBoom++;
    }

    public void setLeft() {
        isLeft = true;
        isUp = false;
        isDown = false;
        isRight = false;
    }

    public void setUp() {
        isUp = true;
        isLeft = false;
        isDown = false;
        isRight = false;
    }

    public void setDown() {
        isDown = true;
        isUp = false;
        isLeft = false;
        isRight = false;
    }

    public void setRight() {
        isRight = true;
        isUp = false;
        isDown = false;
        isLeft = false;
    }
}