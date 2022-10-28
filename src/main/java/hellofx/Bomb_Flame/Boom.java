package hellofx.Bomb_Flame;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.util.Duration;
import java.util.Vector;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.spawn;
import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Constant.GameConstant.*;
import static hellofx.Map.MyMap.updateMap;

import hellofx.Animation.*;
import hellofx.Map.MyMap;

public class Boom extends Component {

    private static int sizeBoom = 1;

    private static int sizeBreak = 1;

    private final double seconds = 0.01;

    public static int right = 0;
    public static int left = 0;
    public static int up = 0;
    public static int down = 0;

    public static int rightBreak = 0;
    public static int leftBreak = 0;
    public static int upBreak = 0;
    public static int downBreak = 0;

    public static int getSizeBoom() {
        return sizeBoom;
    }

    public static int getSizeBreak() {
        return sizeBreak;
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

    public static void setRightBreak() {
        Boom.rightBreak = 0;
    }
    public static void setLeftBreak() {
        Boom.leftBreak = 0;
    }
    public static void setUpBreak() {
        Boom.upBreak = 0;
    }

    public static void setDownBreak() {
        Boom.downBreak = 0;
    }

    public void moveRight(Vector<Entity> tex, int size, int size_break) {
        if (right == size) {
            setRight();
            setRightBreak();
        } else {
            right++;
            if (MyMap.myMap[(int) ((entity.getY()) / TITLE_SIZE)][(int) ((entity.getX() + right * TITLE_SIZE) / TITLE_SIZE)].equals("1")) {
                setRight();
                return;
            }
            tex.add(spawn("flameRight", entity.getX() + right * TITLE_SIZE, entity.getY()));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingHorizontal();
            onCollisionBegin(FLAMERIGHT, WALL, (flame, wall) -> {
                rightBreak++;
                wall.setZIndex(1000);
                wall.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    wall.removeFromWorld();
                    updateMap(wall, "brick");
                }, Duration.seconds(0.7));
                if (rightBreak == size_break) {
                    right = size;
                }
            });
            getGameTimer().runOnceAfter(() -> {
                moveRight(tex, size, size_break);
            }, Duration.seconds(seconds));
        }
    }
    public void moveLeft(Vector<Entity> tex, int size, int size_break) {
        if (left == size) {
            setLeft();
            setLeftBreak();
        } else {
            left++;
            if (MyMap.myMap[(int) ((entity.getY()) / TITLE_SIZE)][(int) ((entity.getX() - left * TITLE_SIZE) / TITLE_SIZE)].equals("1")) {
                setLeft();
                return;
            }
            tex.add(spawn("flameLeft", entity.getX() - left * TITLE_SIZE, entity.getY()));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingHorizontal();
            onCollisionBegin(FLAMELEFT, WALL, (flame, wall) -> {
                leftBreak++;
                wall.setZIndex(1000);
                wall.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    wall.removeFromWorld();
                    updateMap(wall, "brick");
                }, Duration.seconds(0.7));
                if (leftBreak == size_break) {
                    left = size;
                }
            });
            getGameTimer().runOnceAfter(() -> {
                moveLeft(tex, size, size_break);
            }, Duration.seconds(seconds));
        }
    }
    public void moveUp(Vector<Entity> tex, int size, int size_break) {
        if (up == size) {
            setUp();
            setUpBreak();
        } else {
            up++;
            if (MyMap.myMap[(int) ((entity.getY() - up * TITLE_SIZE) / TITLE_SIZE)][(int) ((entity.getX()) / TITLE_SIZE)].equals("1")) {
                setUp();
                return;
            }
            tex.add(spawn("flameUp", entity.getX(), entity.getY() - up * TITLE_SIZE));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingVertical();
            onCollisionBegin(FLAMEUP, WALL, (flame, wall) -> {
                upBreak++;
                wall.setZIndex(1000);
                wall.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    wall.removeFromWorld();
                    updateMap(wall, "brick");
                }, Duration.seconds(0.7));
                if (upBreak == size_break) {
                    up = size;
                }
            });
            getGameTimer().runOnceAfter(() -> {
                moveUp(tex, size, size_break);
            }, Duration.seconds(seconds));
        }
    }
    public void moveDown(Vector<Entity> tex, int size, int size_break) {
        if (down == size) {
            setDown();
            setDownBreak();
        } else {
            down++;
            if (MyMap.myMap[(int) ((entity.getY() + down * TITLE_SIZE) / TITLE_SIZE)][(int) ((entity.getX()) / TITLE_SIZE)].equals("1")) {
                setDown();
                return;
            }
            tex.add(spawn("flameDown", entity.getX(), entity.getY() + down * TITLE_SIZE));
            tex.get(tex.size() - 1).getComponent(FlameAnimation.class).AnimationWingVertical();
            onCollisionBegin(FLAMEDOWN, WALL, (flame, wall) -> {
                downBreak++;
                wall.setZIndex(1000);
                wall.getComponent(BrickBreakAnimation.class).BrickBreak();
                getGameTimer().runOnceAfter(() -> {
                    wall.removeFromWorld();
                    updateMap(wall, "brick");
                }, Duration.seconds(0.7));
                if (downBreak == size_break) {
                    down = size;
                }
            });
            getGameTimer().runOnceAfter(() -> {
                moveDown(tex, size, size_break);
            }, Duration.seconds(seconds));
        }
    }
    public void explodeBoom(Vector<Entity> tex, int size, int size_break) {
        Entity center = spawn("flame", entity.getX(), entity.getY());
        center.getComponent(FlameAnimation.class).AnimationWingCentral();
        moveRight(tex, size, size_break);
        moveLeft(tex, size, size_break);
        moveUp(tex, size, size_break);
        moveDown(tex, size, size_break);
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

    public static void increaseFlameSize() {
        sizeBreak++;
    }

    public static void resetFlameSize() {
        sizeBreak = 1;
    }
}
