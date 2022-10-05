package hellofx;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;
import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import static hellofx.Enum.*;

public class Boom extends Component {

    private static int sizeBoom = 1;

    private final double seconds = 0.01;

    public static int right = 0;
    public static int left = 0;
    public static int up = 0;
    public static int down = 0;

    public static void setRight() {
        Boom.right = 0;
    }

    public static void setLeft() {
        Boom.left = 0;
    }

    public static void setUp() {
        Boom.up = 0;
    }

    public static void setDown() {
        Boom.down = 0;
    }

    public void moveRight(Vector<Entity> tex) {
        if (right == sizeBoom) {
            setRight();
        } else {
            right++;
            if (Main.g_map.myMap[(int) ((entity.getY()) / 40)][(int) ((entity.getX() + right * 40) / 40)].equals("1")) {
                setRight();
                return;
            }
            tex.add(spawn("flameRight", entity.getX() + right * 40, entity.getY()));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingHorizontal();
            onCollisionBegin(FLAMERIGHT, COIN, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                right = sizeBoom;
            });

            getGameTimer().runOnceAfter(() -> {
                moveRight(tex);
            }, Duration.seconds(seconds));
        }
    }

    public void moveLeft(Vector<Entity> tex) {
        if (left == sizeBoom) {
            setLeft();
        } else {
            left++;
            if (Main.g_map.myMap[(int) ((entity.getY()) / 40)][(int) ((entity.getX() - left * 40) / 40)].equals("1")) {
                setLeft();
                return;
            }
            tex.add(spawn("flameLeft", entity.getX() - left * 40, entity.getY()));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingHorizontal();
            onCollisionBegin(FLAMELEFT, COIN, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                left = sizeBoom;
            });
            getGameTimer().runOnceAfter(() -> {
                moveLeft(tex);
            }, Duration.seconds(seconds));
        }
    }

    public void moveUp(Vector<Entity> tex) {
        if (up == sizeBoom) {
            setUp();
        } else {
            up++;
            if (Main.g_map.myMap[(int) ((entity.getY() - up * 40) / 40)][(int) ((entity.getX()) / 40)].equals("1")) {
                setUp();
                return;
            }
            tex.add(spawn("flameUp", entity.getX(), entity.getY() - up * 40));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingVertical();
            onCollisionBegin(FLAMEUP, COIN, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                up = sizeBoom;
            });
            getGameTimer().runOnceAfter(() -> {
                moveUp(tex);
            }, Duration.seconds(seconds));
        }
    }

    public void moveDown(Vector<Entity> tex) {
        if (down == sizeBoom) {
            setDown();
        } else {
            down++;
            if (Main.g_map.myMap[(int) ((entity.getY() + down * 40) / 40)][(int) ((entity.getX()) / 40)].equals("1")) {
                setDown();
                return;
            }
            tex.add(spawn("flameDown", entity.getX(), entity.getY() + down * 40));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingVertical();
            onCollisionBegin(FLAMEDOWN, COIN, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                down = sizeBoom;
            });
            getGameTimer().runOnceAfter(() -> {
                moveDown(tex);
            }, Duration.seconds(seconds));
        }
    }

    public void explodeBoom(Vector<Entity> tex) {
        Entity center = spawn("flame", entity.getX(), entity.getY());
        center.getComponent(FlameAnimation.class).AnimationWingCentral();
        moveRight(tex);
        moveLeft(tex);
        moveUp(tex);
        moveDown(tex);
        getGameTimer().runOnceAfter(() -> {
            for (int i = 0; i < tex.size(); i++) {
                tex.get(i).removeFromWorld();
            }
            this.entity.removeFromWorld();
            tex.clear();
            center.removeFromWorld();
        }, Duration.seconds(0.5));
    }

    public static void powerBoomUp() {
        sizeBoom++;
    }

    public static void setBoomSize() {
        sizeBoom = 1;
    }
}
