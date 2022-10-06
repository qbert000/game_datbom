package hellofx;

import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dev.editor.EntityInspector;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.app.scene.MenuType;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.geometry.Point2D;

import java.util.Map;
import java.util.Vector;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.Enum.*;

import hellofx.Menu.GameMenu;
import hellofx.Menu.MainMenu;
import hellofx.Menu.MenuButton;
import javafx.util.Duration;

//  Ham Main cua ban luon phai extend GameApplication
public class Main extends GameApplication {
    // Luon phai override initsetting


    public static final int TITLE_SIZE = 40;
    public static final int WIDTH_TITLE = 32;
    public static final int HEIGHT_TITLE = 18;
    public double currentXpos;
    public double currentYpos;
    private Entity g_player = new Entity();
    public AnimationComponent g_playerComponent;

    public static Mymap g_map = null;
    public boolean playerisAlive = true;
    

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

    // Xu li input
    @Override
    protected void initInput() {

        onKeyDown(KeyCode.F, () -> {
            play("drop.wav"); // play am thanh
            Boom.powerBoomUp();
        });
        // Xac dinh su kien di ben phai
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

        // Xac dinh su kien di ben trai
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

        // Xac dinh su kien di ben tren
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

        // Xac dinh su kien di ben duoi
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

        /**
         * .
         * place boom in this place
         */
        getInput().addAction(new UserAction("Place boom") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    g_playerComponent.placeBoom();
                }
            }
        }, KeyCode.SPACE);

    }

    // puts nhung UI game vao Map nay
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }

    // Tao player cho game

    // khoi tao nhung thuc the trong game
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());

        playerisAlive = true;

        double playerPosX = 200;
        double playerPosY = 200;

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
                // do something here
                if (g_map.myMap[i][j].equals("3")) {
                    playerPosX = j * TITLE_SIZE;
                    playerPosY = i * TITLE_SIZE;
                }
            }
        }

        // spawn nhan vat sau cung de z-index >>>
        g_player = spawn("player", playerPosX, playerPosY);
        g_playerComponent = g_player.getComponent(AnimationComponent.class);

        currentXpos = g_player.getPosition().getX();
        currentYpos = g_player.getPosition().getY();
        g_player.getComponent(AnimationComponent.class).getMyX = g_player.getPosition().getX();
        g_player.getComponent(AnimationComponent.class).getMyY = g_player.getPosition().getY();

        // khoi tao bien
    }

    // khoi tao nhung UI - trong game la Text
    @Override
    protected void initUI() {

    }

    private void replay() {
        playerisAlive = false;
        g_player.removeFromWorld();
        getGameTimer().runOnceAfter(() -> {
            FXGL.getGameController().gotoMainMenu();
        }, Duration.seconds(1.5));
    }

    // Xu li va cham theo 2 vat the
    @Override
    protected void initPhysics() {
        onCollisionBegin(PLAYER, FLAME, (player, coin) -> {
            replay();
        });
        // collision flame
        onCollisionBegin(PLAYER, FLAMERIGHT, (player, coin) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMELEFT, (player, coin) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMEUP, (player, coin) -> {
            replay();
        });
        onCollisionBegin(PLAYER, FLAMEDOWN, (player, coin) -> {
            replay();
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, BOOM) {
            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                if (g_playerComponent.k) {
                    player.setPosition(new Point2D(currentXpos, currentYpos));
                }

            }

            @Override
            protected void onCollision(Entity player, Entity coin) {
                if (g_playerComponent.k) {
                    player.setPosition(new Point2D(currentXpos, currentYpos));
                }
            }

            @Override
            protected void onCollisionEnd(Entity a, Entity b) {
                g_playerComponent.k = true;
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, PORTAL) {
            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity portal) {

                getGameTimer().runOnceAfter(() -> {
                    FXGL.getGameController().gotoMainMenu();
                }, Duration.seconds(0.6));
            }
        });

        /**
         * .
         * colliision between player with coin.
         */
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, Enum.COIN) {
            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                player.setPosition(new Point2D(currentXpos, currentYpos));
            }

            @Override
            protected void onCollision(Entity player, Entity coin) {
                player.setPosition(new Point2D(currentXpos, currentYpos));
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
        // Khoi dong
    }
}
