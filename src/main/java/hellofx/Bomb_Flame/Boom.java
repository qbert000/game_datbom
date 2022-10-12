package hellofx.Bomb_Flame;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
// import javafx.scene.paint.Color;
import javafx.util.Duration;

// import java.awt.*;
import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;

// import com.almasb.fxgl.entity.Entity;
// import com.almasb.fxgl.entity.component.Component;
// import javafx.util.Duration;

// import java.util.Vector;
// import java.util.concurrent.atomic.AtomicInteger;

// import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
// import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Constant.GameConstant.*;
import hellofx.Main;
import hellofx.Animation.*;

public class Boom extends Component {

    private static int sizeBoom = 1;

    private final double seconds = 0.01;

    public static int right = 0;
    public static int left = 0;
    public static int up = 0;
    public static int down = 0;

    public static int getSizeBoom() {
        return sizeBoom;
    }

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

    public void moveRight(Vector<Entity> tex, int size) {
        if (right == size) {
            setRight();
        } else {
            right++;
            if (Main.g_map.myMap[(int) ((entity.getY()) / TITLE_SIZE)][(int) ((entity.getX() + right * TITLE_SIZE) / TITLE_SIZE)].equals("1")) {
                setRight();
                return;
            }
            tex.add(spawn("flameRight", entity.getX() + right * TITLE_SIZE, entity.getY()));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingHorizontal();
            onCollisionBegin(FLAMERIGHT, WALL, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                right = size;
            });

            getGameTimer().runOnceAfter(() -> {
                moveRight(tex, size);
            }, Duration.seconds(seconds));
        }
    }

    public void moveLeft(Vector<Entity> tex, int size) {
        if (left == size) {
            setLeft();
        } else {
            left++;
            if (Main.g_map.myMap[(int) ((entity.getY()) / TITLE_SIZE)][(int) ((entity.getX() - left * TITLE_SIZE) / TITLE_SIZE)].equals("1")) {
                setLeft();
                return;
            }
            tex.add(spawn("flameLeft", entity.getX() - left * TITLE_SIZE, entity.getY()));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingHorizontal();
            onCollisionBegin(FLAMELEFT, WALL, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                left = size;
            });
            getGameTimer().runOnceAfter(() -> {
                moveLeft(tex, size);
            }, Duration.seconds(seconds));
        }
    }

    public void moveUp(Vector<Entity> tex, int size) {
        if (up == size) {
            setUp();
        } else {
            up++;
            if (Main.g_map.myMap[(int) ((entity.getY() - up * TITLE_SIZE) / TITLE_SIZE)][(int) ((entity.getX()) / TITLE_SIZE)].equals("1")) {
                setUp();
                return;
            }
            tex.add(spawn("flameUp", entity.getX(), entity.getY() - up * TITLE_SIZE));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingVertical();
            onCollisionBegin(FLAMEUP, WALL, (flame, coin) -> {
                coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                up = size;
            });
            getGameTimer().runOnceAfter(() -> {
                moveUp(tex, size);
            }, Duration.seconds(seconds));
        }
    }

    public void moveDown(Vector<Entity> tex, int size) {
        if (down == size) {
            setDown();
        } else {
            down++;
            if (Main.g_map.myMap[(int) ((entity.getY() + down * TITLE_SIZE) / TITLE_SIZE)][(int) ((entity.getX()) / TITLE_SIZE)].equals("1")) {
                setDown();
                return;
            }
            tex.add(spawn("flameDown", entity.getX(), entity.getY() + down * TITLE_SIZE));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingVertical();
            onCollisionBegin(FLAMEDOWN, WALL, (flame, coin) -> {
                //coin.setZIndex(1000);
                coin.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    coin.removeFromWorld();
                }, Duration.seconds(0.7));
                down = size;
            });
            getGameTimer().runOnceAfter(() -> {
                moveDown(tex, size);
            }, Duration.seconds(seconds));
        }
    }

    public void explodeBoom(Vector<Entity> tex, int size) {
        Entity center = spawn("flame", entity.getX(), entity.getY());
        center.getComponent(FlameAnimation.class).AnimationWingCentral();
        moveRight(tex, size);
        moveLeft(tex, size);
        moveUp(tex, size);
        moveDown(tex, size);
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

    public static void resizePowerBoom() {
        sizeBoom = 1;
    }
}
