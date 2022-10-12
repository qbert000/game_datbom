package hellofx;

// import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
// import com.almasb.fxgl.app.scene.Viewport;
// import com.almasb.fxgl.dev.editor.EntityInspector;
// import com.almasb.fxgl.entity.Entity;
// import com.almasb.fxgl.input.UserAction;
// import com.almasb.fxgl.app.scene.FXGLMenu;
// import com.almasb.fxgl.physics.PhysicsWorld;
// import com.almasb.fxgl.app.scene.MenuType;
import javafx.scene.input.KeyCode;
// import javafx.scene.text.Text;
import javafx.geometry.Point2D;
import java.util.Map;
// import java.util.Vector;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.SpawnSystem.Enum.*;
import hellofx.Menu.GameMenu;
import hellofx.Menu.MainMenu;
// import hellofx.Menu.MenuButton;
import hellofx.Bomb_Flame.*;
import javafx.util.Duration;
import static hellofx.Constant.GameConstant.*;
import hellofx.Animation.*;
import hellofx.SpawnSystem.Factory;
import hellofx.Map.*;

/*
 * Ham main luon phai extends GameApplication.
 */
public class Main extends GameApplication {
    public double currentXpos;
    public double currentYpos;

    private Entity g_player = new Entity();
    public AnimationComponent g_playerComponent;

    public static Mymap g_map = null;
    public boolean playerisAlive = true;

    /*
     * Overload Game Setting.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(TITLE_SIZE * WIDTH_TITLE);
        settings.setHeight(TITLE_SIZE * HEIGHT_TITLE);
        settings.setTitle("Bomberman");
        settings.setAppIcon("icon/icon.png");
        // to chuc file mac dinh la assets/textures/ --> them duong dan dc
        settings.setVersion("1.0");
        settings.setDeveloperMenuEnabled(true);
        settings.setIntroEnabled(false);
        settings.setIntroEnabled(false);
        settings.setGameMenuEnabled(true);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new MainMenu();
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new GameMenu();
            }

        });
        settings.setDefaultCursor(new CursorInfo("cursor/cursor1.png", 0.0, 0.0));
    }

    /*
     * Handle User's Input.
     */
    @Override
    protected void initInput() {

        // Xac dinh su kien Tang Bomb (tester).
        onKeyDown(KeyCode.F, () -> {
            // play("drop.wav"); // play am thanh
            Boom.powerBoomUp();
        });
        // Xac dinh su kien di ben phai.
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(AnimationComponent.class).isRight = true;
                    g_player.getComponent(AnimationComponent.class).isUp = false;
                    g_player.getComponent(AnimationComponent.class).isDown = false;
                    g_player.getComponent(AnimationComponent.class).isLeft = false;
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(AnimationComponent.class).moveRight();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(AnimationComponent.class).stay();
                }
            }

        }, KeyCode.D);

        // Xac dinh su kien di ben trai.
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    // de goi thuoc tinh co trong class voi "with" sau khi add
                    // ta dung ham getComponent(ten.class).thuoctinh/method
                    g_player.getComponent(AnimationComponent.class).isLeft = true;
                    g_player.getComponent(AnimationComponent.class).isUp = false;
                    g_player.getComponent(AnimationComponent.class).isDown = false;
                    g_player.getComponent(AnimationComponent.class).isRight = false;
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(AnimationComponent.class).moveLeft();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(AnimationComponent.class).stay();
                }
            }

        }, KeyCode.A);

        // Xac dinh su kien di ben tren.
        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(AnimationComponent.class).isUp = true;
                    g_player.getComponent(AnimationComponent.class).isDown = false;
                    g_player.getComponent(AnimationComponent.class).isLeft = false;
                    g_player.getComponent(AnimationComponent.class).isRight = false;
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(AnimationComponent.class).moveUp();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(AnimationComponent.class).stay();
                }
            }

        }, KeyCode.W);

        // Xac dinh su kien di ben duoi.
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    // neu dao nguoc chieu cung di chuyen se di xuyen qua vat can :)))))
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(AnimationComponent.class).isDown = true;
                    g_player.getComponent(AnimationComponent.class).isUp = false;
                    g_player.getComponent(AnimationComponent.class).isLeft = false;
                    g_player.getComponent(AnimationComponent.class).isRight = false;
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(AnimationComponent.class).moveDown();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(AnimationComponent.class).stay();
                }
            }

        }, KeyCode.S);

        // Xac dinh su kien dat bom.
        getInput().addAction(new UserAction("Place boom") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    g_playerComponent.placeBoom();
                }
            }
        }, KeyCode.SPACE);

    }

    /*
     * Dua nhung UI vao trong Game.
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    /*
     * Khoi tao nhung Enity(thuc the) trong Game.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());
        playerisAlive = true;
        double playerPosX = starterPosX;
        double playerPosY = starterPosY;
        try {
            g_map = new Mymap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < HEIGHT_TITLE; i++) {
            for (int j = 0; j < WIDTH_TITLE; j++) {

                spawn("grass", j * TITLE_SIZE, i * TITLE_SIZE);

                if (g_map.myMap[i][j].equals("1")) {
                    spawn("wall", j * TITLE_SIZE, i * TITLE_SIZE);
                }
                if (g_map.myMap[i][j].equals("5")) {
                    spawn("portal", j * TITLE_SIZE, i * TITLE_SIZE);
                    spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
                }
                if (g_map.myMap[i][j].equals("2")) {
                    spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
                }

                if (g_map.myMap[i][j].equals("A")) {
                    spawn("speedItem", j * TITLE_SIZE + 4, i * TITLE_SIZE + 4);
                }

                if (g_map.myMap[i][j].equals("B")) {
                    spawn("flameItem", j * TITLE_SIZE + 4, i * TITLE_SIZE + 4);
                }

                if (g_map.myMap[i][j].equals("3")) {
                    playerPosX = j * TITLE_SIZE;
                    playerPosY = i * TITLE_SIZE;
                }
            }
        }

        // spawn nhan vat sau cung de z-index >> / hien thi ben tren theo chieu Oz
        g_player = spawn("player", playerPosX, playerPosY);
        g_playerComponent = g_player.getComponent(AnimationComponent.class);

        currentXpos = g_player.getPosition().getX();
        currentYpos = g_player.getPosition().getY();
        g_player.getComponent(AnimationComponent.class).getMyX = g_player.getPosition().getX();
        g_player.getComponent(AnimationComponent.class).getMyY = g_player.getPosition().getY();
    }

    /**
     * Khoi tao nhung UI cua game nhu text, node, hinh anh, ....
     */
    @Override
    protected void initUI() {

    }

    /*
     * Reset lai nhung bien trong game khi nguoi choi chet / ve portal
     */
    private void replay() {
        Boom.resizePowerBoom();
        playerisAlive = false;
        getGameTimer().runOnceAfter(() -> {
            g_player.getComponent(AnimationComponent.class).loadDeadAnim();
        }, Duration.seconds(0.4));

        getGameTimer().runOnceAfter(() -> {
            g_player.removeFromWorld();
        }, Duration.seconds(1.5));

        CONST_SPEED = 90;
        getGameTimer().runOnceAfter(() -> {
            FXGL.getGameController().gotoMainMenu();
        }, Duration.seconds(2));
    }

    /*
     * Dua nguoi choi den level tiep theo(neu lam :D) /ve man chinh khi di den Portal
     */
    private void nextLevel() {
        Boom.resizePowerBoom();
        playerisAlive = false;
        getGameTimer().runOnceAfter(() -> {
            g_player.getComponent(AnimationComponent.class).loadWinAnim();
        }, Duration.seconds(0.6));

        getGameTimer().runOnceAfter(() -> {
            g_player.removeFromWorld();
        }, Duration.seconds(2));

        CONST_SPEED = 90;
        getGameTimer().runOnceAfter(() -> {
            FXGL.getGameController().gotoMainMenu();
        }, Duration.seconds(2));
    }

    /*
     * Ham xu li va cham 2 vat the bat ki trong Game
     */
    @Override
    protected void initPhysics() {
        // Xu li va cham Player va Flame
        onCollisionBegin(PLAYER, FLAME, (player, flame) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMERIGHT, (player, flame) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMELEFT, (player, flame) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMEUP, (player, flame) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMEDOWN, (player, flame) -> {
            replay();
        });

        // Xu li va cham Flame_item va Flame
        onCollisionBegin(FLAME_ITEM, FLAME, (flame_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                flame_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(FLAME_ITEM, FLAMERIGHT, (flame_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                flame_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(FLAME_ITEM, FLAMELEFT, (flame_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                flame_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(FLAME_ITEM, FLAMEUP, (flame_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                flame_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(FLAME_ITEM, FLAMEDOWN, (flame_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                flame_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });

        // Xu li va cham giua Speed_item va Flame
        onCollisionBegin(SPEED_ITEM, FLAME, (speed_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                speed_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(SPEED_ITEM, FLAMERIGHT, (speed_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                speed_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(SPEED_ITEM, FLAMELEFT, (speed_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                speed_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(SPEED_ITEM, FLAMEUP, (speed_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                speed_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });
        onCollisionBegin(SPEED_ITEM, FLAMEDOWN, (speed_item, flame) -> {
            getGameTimer().runOnceAfter(() -> {
                speed_item.removeFromWorld();
            }, Duration.seconds(0.6));
        });

        // Xu li va cham giua Player va boom
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, BOOM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity boom) {
                if (AnimationComponent.k) {
                    player.setPosition(new Point2D(currentXpos, currentYpos));
                }
            }
            @Override
            protected void onCollision(Entity player, Entity boom) {
                if (AnimationComponent.k) {
                    player.setPosition(new Point2D(currentXpos, currentYpos));
                }
            }
            @Override
            protected void onCollisionEnd(Entity player, Entity boom) {
                AnimationComponent.k = true;
            }
        });

        // Xu li va cham giua Player va Portal
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, PORTAL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity portal) {
                nextLevel();
            }
        });

        // Xu li va cham giua Player va Flame_Item
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, FLAME_ITEM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity flame_item) {
                Boom.powerBoomUp();
                getGameTimer().runOnceAfter(() -> {
                    flame_item.removeFromWorld();
                }, Duration.seconds(0.1));
            }
        });

        // Xu li va cham giua Player va Speed_item
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, SPEED_ITEM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity speed_item) {
                player.getComponent(AnimationComponent.class).increaseSpeed();
                getGameTimer().runOnceAfter(() -> {
                    speed_item.removeFromWorld();
                }, Duration.seconds(0.1));
            }
        });

        // Xu li va cham giua Player va Wall
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, WALL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity wall) {
                player.setPosition(new Point2D(currentXpos, currentYpos));
            }

            @Override
            protected void onCollision(Entity player, Entity wall) {
                player.setPosition(new Point2D(currentXpos, currentYpos));
            }
        });

    }

    /*
     * Ham Launch(khoi dong) Game
     */
    public static void main(String[] args) {
        launch(args);
    }
}
