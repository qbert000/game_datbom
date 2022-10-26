package hellofx.GameEntity.DynamicEntity;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
// import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import hellofx.Enemy.EnemyPass;
// import javafx.geometry.Point2D;
import javafx.util.Duration;
import static hellofx.Constant.GameConstant.*;
import static hellofx.Map.MyMap.g_map;
import static hellofx.Map.MyMap.enemy;
import static hellofx.Constant.GameConstant.ENEMY_NUMBER;
import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Map.MyMap.updateMap;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import hellofx.Bomb_Flame.*;

public class Player extends DynamicEntity {
    private static int amountBoom = 1;
    public static boolean k = true;

    public static double currentXpos;
    public static double currentYpos;

    public static boolean playerisAlive = true;

    public AnimatedTexture texture;
    public boolean isStay = true;
    public double getMyX;
    public double getMyY;
    public double spDistance = 0;
    public boolean onCollision = false;
    public boolean isWin = false;
    public boolean isDead = false;
    public boolean flamePass = false;

    public final AnimationChannel idleUp;
    public final AnimationChannel idleDown;
    public final AnimationChannel idleLeft;
    public final AnimationChannel idleRight;
    public final AnimationChannel animWin;

    /*
     * Khoi tao Animation.
     */
    public Player() {
        animDead = new AnimationChannel(FXGL.image("character/player_die.png"), 5, TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(1.1),
                0, 4);
        animWin = new AnimationChannel(FXGL.image("character/player_down.png"), 3, TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                0, 2);
        animUp = new AnimationChannel(FXGL.image("character/player_up.png"), 4, TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                0, 2);
        animLeft = new AnimationChannel(FXGL.image("character/player_left.png"), 4,
                TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                0, 2);
        animDown = new AnimationChannel(FXGL.image("character/player_down.png"), 4, TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                0, 2);
        idleLeft = new AnimationChannel(FXGL.image("character/player_left.png"), 4,
                TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                2, 2);
        idleRight = new AnimationChannel(FXGL.image("character/player_right.png"), 4, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4),
                2, 2);
        idleUp = new AnimationChannel(FXGL.image("character/player_up.png"), 4, TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                2, 2);
        idleDown = new AnimationChannel(FXGL.image("character/player_down.png"), 4, TITLE_SIZE,
                TITLE_SIZE,
                Duration.seconds(0.4),
                2, 2);
        animRight = new AnimationChannel(FXGL.image("character/player_right.png"), 4, TITLE_SIZE, TITLE_SIZE,
                Duration.seconds(0.4),
                0, 2);
        texture = new AnimatedTexture(idleRight);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    public static int getAmountBoom() {
        return amountBoom;
    }

    public String getStatus() {
        if (left_)
            return "LEFT";
        if (right_)
            return "RIGHT";
        if (up_)
            return "UP";
        if (down_)
            return "DOWN";
        else
            return "BUG!!!!";
    }

    @Override
    public void onUpdate(double tpf) {
        if (!isWin && !isDead) {
            // ham check di chuyen ben duoi
            if (down_ && !up_ && !left_ && !right_) {
                makeSupportMoveVertical();
                if (speed == 0 && isStay) {
                    setIdleAnim();
                }
                entity.translateY(speed);
                getMyX = getXPos();
                getMyY = getYPos() - speed;
                if (speed != 0) {
                    if (texture.getAnimationChannel() == idleRight
                            || texture.getAnimationChannel() == idleDown
                            || texture.getAnimationChannel() == idleUp
                            || texture.getAnimationChannel() == animUp
                            || texture.getAnimationChannel() == animRight
                            || texture.getAnimationChannel() == animLeft
                            || texture.getAnimationChannel() == idleLeft) {
                        texture.loopAnimationChannel(animDown);
                    }

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(idleDown);
                    }
                }
            }
            // ham check di chuyen ben trai
            if (left_ && !down_ && !up_ && !right_) {
                makeSupportMoveHorizontal();
                if (speed == 0) {
                    setIdleAnim();
                }
                entity.translateX(speed);
                getMyX = getXPos() - speed;
                getMyY = getYPos();
                if (speed != 0) {
                    if (texture.getAnimationChannel() == idleRight
                            || texture.getAnimationChannel() == idleDown
                            || texture.getAnimationChannel() == idleUp
                            || texture.getAnimationChannel() == animUp
                            || texture.getAnimationChannel() == animDown
                            || texture.getAnimationChannel() == idleLeft
                            || texture.getAnimationChannel() == animRight) {
                        texture.loopAnimationChannel(animLeft);
                    }

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(idleLeft);
                    }
                }
            }
            // ham check di chuyen ben phai
            if (right_ && !up_ && !down_ && !left_) {
                makeSupportMoveHorizontal();
                if (speed == 0) {
                    setIdleAnim();
                }
                entity.translateX(speed);
                getMyX = getXPos() - speed;
                getMyY = getYPos();
                if (speed != 0) {
                    if (texture.getAnimationChannel() == idleRight
                            || texture.getAnimationChannel() == idleDown
                            || texture.getAnimationChannel() == idleUp
                            || texture.getAnimationChannel() == animUp
                            || texture.getAnimationChannel() == animDown
                            || texture.getAnimationChannel() == animLeft
                            || texture.getAnimationChannel() == idleLeft) {
                        texture.loopAnimationChannel(animRight);
                    }

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(idleRight);
                    }
                }
            }
            // ham check di chuyen ben tren
            if (up_ && !down_ && !left_ && !right_) {
                makeSupportMoveVertical();
                if (speed == 0) {
                    setIdleAnim();
                }
                entity.translateY(speed);
                getMyX = getXPos();
                getMyY = getYPos() - speed;
                if (speed != 0) {
                    if (texture.getAnimationChannel() == idleRight
                            || texture.getAnimationChannel() == idleDown
                            || texture.getAnimationChannel() == idleUp
                            || texture.getAnimationChannel() == animRight
                            || texture.getAnimationChannel() == animDown
                            || texture.getAnimationChannel() == animLeft
                            || texture.getAnimationChannel() == idleLeft) {
                        texture.loopAnimationChannel(animUp);
                    }

                    speed = (speed * 0.009);

                    if (FXGLMath.abs(speed) < 0.001) {
                        speed = 0;
                        texture.loopAnimationChannel(idleUp);
                    }
                }
            }
            // Update map khi nhan vat di den DUNG 1 o moi
            double tempX = Math.round(getXPos() / 40);
            double tempY = Math.round(getYPos() / 40);
            if (Math.abs(tempX - getXPos() / 40) < 0.7 && Math.abs(tempY - getYPos() / 40) < 0.7) {
                if (!g_map.updatePlayerPosition((int) tempX, (int) tempY)) {
                    // System.out.println("Update player position");
                    for (int i = 0; i < ENEMY_NUMBER; i++) {
                        if (enemy[i].hasComponent(EnemyPass.class)) {
                            if (!enemy[i].getComponent(EnemyPass.class).isDead) {
                                enemy[i].getComponent(EnemyPass.class).findPlayer.resetFinding = true;
                            }
                        }
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
        updateMap(g_boom, "bomb");
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
                player.getComponent(Player.class).spDistance = translate;
            }
        } else {
            if (!hasLeftTile) {
                double translate = -(35 + diff);
                player.getComponent(Player.class).spDistance = translate;
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
                player.getComponent(Player.class).spDistance = translate;
            }
        } else {
            if (!hasUpTile) {
                double translate = -(41.5 + diff);
                player.getComponent(Player.class).spDistance = translate;
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
        if (up_) {
            texture.loopAnimationChannel(idleUp);
            return;
        }
        if (down_) {
            texture.loopAnimationChannel(idleDown);
        }
        if (left_) {
            texture.loopAnimationChannel(idleLeft);
        }
        if (right_) {
            texture.loopAnimationChannel(idleRight);
        }
    }

    public void loadDeadAnim() {
        isDead = true;
        texture.loopAnimationChannel(animDead);
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
        left_ = true;
        up_ = false;
        down_ = false;
        right_ = false;
    }

    public void setUp() {
        up_ = true;
        left_ = false;
        down_ = false;
        right_ = false;
    }

    public void setDown() {
        down_ = true;
        up_ = false;
        left_ = false;
        right_ = false;
    }

    public void setRight() {
        right_ = true;
        up_ = false;
        down_ = false;
        left_ = false;
    }
}