package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.geometry.Point2D;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

//  Ham Main cua ban luon phai extend GameApplication
public class Main extends GameApplication {
    // Luon phai override initsetting

    public static final int TITLE_SIZE = 40;
    public static final int WIDTH_TITLE = 38;
    public static final int HEIGHT_TITLE = 24;
    public double currentXpos;
    public double currentYpos;
    private Entity player;
    public PlayerComponent playerComponent;
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(TITLE_SIZE * WIDTH_TITLE);
        settings.setHeight(TITLE_SIZE * HEIGHT_TITLE);
        settings.setTitle("Basic Game App");
        settings.setAppIcon("icon/icon.png");
        // to chuc file mac dinh la assets/textures/ --> them duong dan dc
        settings.setVersion("1.0");
        settings.setDeveloperMenuEnabled(true);
        settings.setIntroEnabled(false);
    }


    // Xu li input
    @Override
    protected void initInput() {

        onKeyDown(KeyCode.F, () -> {
            play("drop.wav"); // play am thanh
        });

        // Xac dinh su kien di ben phai
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                player.getComponent(AnimationComponent.class).isRight = true;
                player.getComponent(AnimationComponent.class).isUp = false;
                player.getComponent(AnimationComponent.class).isDown = false;
                player.getComponent(AnimationComponent.class).isLeft = false;
            }
            @Override
            protected void onAction() {
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                player.getComponent(AnimationComponent.class).moveRight();
            }
            @Override
            protected void onActionEnd() {
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                player.getComponent(AnimationComponent.class).stay();
            }

        }, KeyCode.D);

        // Xac dinh su kien di ben trai
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                // de goi thuoc tinh co trong class voi "with" sau khi add
                // ta dung ham getComponent(ten.class).thuoctinh/method
                player.getComponent(AnimationComponent.class).isLeft = true;
                player.getComponent(AnimationComponent.class).isUp = false;
                player.getComponent(AnimationComponent.class).isDown = false;
                player.getComponent(AnimationComponent.class).isRight = false;
            }

            @Override
            protected void onAction() {
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                player.getComponent(AnimationComponent.class).moveLeft();
            }

            @Override
            protected void onActionEnd() {
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                player.getComponent(AnimationComponent.class).stay();
            }

        }, KeyCode.A);

        // Xac dinh su kien di ben tren
        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                player.getComponent(AnimationComponent.class).isUp = true;
                player.getComponent(AnimationComponent.class).isDown = false;
                player.getComponent(AnimationComponent.class).isLeft = false;
                player.getComponent(AnimationComponent.class).isRight = false;
            }

            @Override
            protected void onAction() {
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                player.getComponent(AnimationComponent.class).moveUp();
            }

            @Override
            protected void onActionEnd() {
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                player.getComponent(AnimationComponent.class).stay();
            }

        }, KeyCode.W);

        // Xac dinh su kien di ben duoi
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                // neu dao nguoc chieu cung di chuyen se di xuyen qua vat can :)))))
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                player.getComponent(AnimationComponent.class).isDown = true;
                player.getComponent(AnimationComponent.class).isUp = false;
                player.getComponent(AnimationComponent.class).isLeft = false;
                player.getComponent(AnimationComponent.class).isRight = false;
            }

            @Override
            protected void onAction() {
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                player.getComponent(AnimationComponent.class).moveDown();
            }

            @Override
            protected void onActionEnd() {
                currentXpos = player.getComponent(AnimationComponent.class).getMyX;
                currentYpos = player.getComponent(AnimationComponent.class).getMyY;
                player.getComponent(AnimationComponent.class).stay();
            }

        }, KeyCode.S);

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

        Mymap g_map = null;
        try {
            g_map = new Mymap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0 ; i < HEIGHT_TITLE; i++) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                if (g_map.myMap[i][j].equals("1")) {
                    spawn("coin", j * TITLE_SIZE, i * TITLE_SIZE);
                }
                if (g_map.myMap[i][j].equals("0")) {
                    spawn("grass", j * TITLE_SIZE, i * TITLE_SIZE);
                }
                // do something here






            }
        }

        player = spawn("player", 200, 200);
        playerComponent = player.getComponent(PlayerComponent.class);


        currentXpos = player.getPosition().getX();
        currentYpos = player.getPosition().getY();
        player.getComponent(AnimationComponent.class).getMyX = player.getPosition().getX();
        player.getComponent(AnimationComponent.class).getMyY = player.getPosition().getY();


        // khoi tao bien
    }

    // khoi tao nhung UI - trong game la Text
    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(50); // x = 50
        textPixels.setTranslateY(100); // y = 100
        textPixels.textProperty().bind(getWorldProperties().intProperty("pixelsMoved").asString());
        // bind bien text voi bien pixeslMoved
        textPixels.textProperty().bind(getWorldProperties().intProperty("pixelsMoved").asString());

        getGameScene().addUINode(textPixels); // add to the scene graph

    }

    // Xu li va cham theo 2 vat the
    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(Enum.PLAYER, Enum.COIN) {
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

    public static void main(String[] args) throws Exception {
        launch(args);
        // Khoi dong
    }
}
