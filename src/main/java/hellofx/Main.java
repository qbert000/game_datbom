package hellofx;

import java.util.Map;

import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.geometry.Point2D;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
// import static com.almasb.fxgl.dsl.FXGL.*;

import hellofx.Enemy.*;
import hellofx.GameEntity.DynamicEntity.Player;
import hellofx.SmartMap.Position;
import hellofx.SmartMap.SmartMap;
import hellofx.Menu.GameMenu;
import hellofx.Menu.MainMenu;
import hellofx.Bomb_Flame.*;
import hellofx.SpawnSystem.Factory;
import hellofx.Map.*;
import static com.almasb.fxgl.dsl.FXGL.addUINode;
import hellofx.GameUI.GameUIComponent;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Map.MyMap.g_map;
import static hellofx.Map.MyMap.index;
import static hellofx.Map.MyMap.myMap;
import static hellofx.SmartMap.SmartMap.g_smartMap;
import static hellofx.Map.MyMap.updateMap;
import static hellofx.Map.MyMap.spawnComponent;
import static hellofx.Map.MyMap.getSignOfEntity;
import static hellofx.Constant.GameConstant.*;
import static hellofx.GameEntity.DynamicEntity.Player.currentXpos;
import static hellofx.GameEntity.DynamicEntity.Player.currentYpos;
import static hellofx.GameEntity.DynamicEntity.Player.playerisAlive;

public class Main extends GameApplication {
    private Entity g_player = new Entity();
    public Player g_playerComponent;

    public int enemyDeadOnce = 1;

    /*
     * Overload Game Setting.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(TITLE_SIZE * WIDTH_TITLE);
        settings.setHeight(TITLE_SIZE * 19);
        settings.setTitle("Bomberman");
        settings.setAppIcon("icon/icon.png");
        settings.setFullScreenAllowed(true);
        settings.setFullScreenFromStart(true);
        // settings.setFontUI("font/game_font.ttf");
        // to chuc file mac dinh la assets/textures/ --> them duong dan dc
        settings.setVersion("1.0");
        settings.setDeveloperMenuEnabled(true);
        settings.setIntroEnabled(false);
        settings.setIntroEnabled(false);
        settings.setFontGame("font2.ttf");
        // settings.setFontMono("font/font2.ttf");
        // settings.setFontText("font/font3.ttf");
        // settings.setFontUI("font/font4.ttf");
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
        // Tang Bomb Radius (tester).
        onKeyDown(KeyCode.F, () -> {
            // play("drop.wav"); // play am thanh
            Boom.powerBoomUp();
        });

        // Tang toc do nguoi choi (tester).
        onKeyDown(KeyCode.G, () -> {
            g_player.getComponent(Player.class).increaseSpeed();
        });

        // Tang flame Power (tester).
        onKeyDown(KeyCode.R, () -> {
            Boom.increaseFlameSize();
        });

        // Tang so luong Boom (tester).
        onKeyDown(KeyCode.T, () -> {
            Player.increaseBoomAmount();
        });

        // Xac dinh su kien di ben phai.
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    // currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    // currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(Player.class).setRight();
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    g_player.getComponent(Player.class).moveRight();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    g_player.getComponent(Player.class).stay();
                }
            }

        }, KeyCode.D);

        // Xac dinh su kien di ben trai.
        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    // currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    // currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(Player.class).setLeft();
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    g_player.getComponent(Player.class).moveLeft();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    g_player.getComponent(Player.class).stay();
                }
            }

        }, KeyCode.A);

        // Xac dinh su kien di ben tren.
        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    // currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    // currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(Player.class).setUp();
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    g_player.getComponent(Player.class).moveUp();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    g_player.getComponent(Player.class).stay();
                }
            }

        }, KeyCode.W);

        // Xac dinh su kien di ben duoi.
        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    // currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    // currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    g_player.getComponent(Player.class).setDown();
                }
            }

            @Override
            protected void onAction() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    currentYpos = g_player.getComponent(Player.class).getMyY;
                    g_player.getComponent(Player.class).moveDown();
                }
            }

            @Override
            protected void onActionEnd() {
                if (playerisAlive) {
                    currentXpos = g_player.getComponent(Player.class).getMyX;
                    currentYpos = g_player.getComponent(Player.class).getMyY;
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
        vars.put("bombAmount", 1);
        vars.put("speed", (int)CONST_SPEED * 10);
        vars.put("flameRadius", Boom.getSizeBoom());
        vars.put("flamePass", 0);
        vars.put("life", 1);
        vars.put("score", 0);
        vars.put("enemies", TOTAL_ENEMY);
        vars.put("levelTime", GAME_TIME);
    }

    /**
     * Khoi tao nhung UI cua game nhu text, node, hinh anh, ....
     */
    @Override
    protected void initUI() {
        GameUIComponent.addILabelUI("bombAmount", "%d", 45, TITLE_SIZE * 18);
        GameUIComponent.addILabelUI("speed", "%d", 206, TITLE_SIZE * 18);
        GameUIComponent.addILabelUI("flameRadius", "%d", 367, TITLE_SIZE * 18);
        GameUIComponent.addILabelUI("flamePass", "%d", 528, TITLE_SIZE * 18);
        GameUIComponent.addILabelUI("life", "%d", 689, TITLE_SIZE * 18);
        GameUIComponent.addILabelUI("score", "%d", 850, TITLE_SIZE * 18);
        GameUIComponent.addILabelUI("enemies", "%d", 1011, TITLE_SIZE * 18);
        GameUIComponent.addDLabelUI("levelTime", "%.0f", 1172, TITLE_SIZE * 18);
    }

    @Override
    protected void onUpdate(double tpf) {
        inc("levelTime", -tpf);
        if (g_player.hasComponent(Player.class)) {
            set("bombAmount", Player.getAmountBoom());
            set("speed", (int)CONST_SPEED * 100);
            if (g_player.getComponent(Player.class).flamePass) {
                set("flamePass", 1);
            }
            set("flameRadius", Boom.getSizeBoom());
        }
        if (getd("levelTime") <= 0.0) {
            showMessage("Time Up !!!");
            set("levelTime", GAME_TIME);
            getGameTimer().runOnceAfter(() -> {
                FXGL.getGameController().gotoMainMenu();
            }, Duration.seconds(0.1));
        }
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
        Player.amountBoomDown();
        try {
            g_map = new MyMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < HEIGHT_TITLE; i++) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                spawnComponent(i, j);
                if (MyMap.myMap[i][j].equals("3")) {
                    playerPosX = j * TITLE_SIZE;
                    playerPosY = i * TITLE_SIZE;
                    MyMap.playerX = i;
                    MyMap.playerY = j;
                }
            }
            set("enemies", TOTAL_ENEMY);
        }
        System.out.println("ENEMY NUMBER: " + ENEMY_NUMBER);
        System.out.println("TOTAL ENEMY: " + TOTAL_ENEMY);
        g_smartMap = new SmartMap();
        SmartMap.set();
        // //SmartMap.print();
        for (Position position : SmartMap.smartPosition) {
            position.findAround();
        }
        // SmartMap.smartPosition.get(0).print();
        g_player = spawn("player", playerPosX, playerPosY);
        g_playerComponent = g_player.getComponent(Player.class);
        currentXpos = g_player.getPosition().getX();
        currentYpos = g_player.getPosition().getY();
        g_player.getComponent(Player.class).getMyX = g_player.getPosition().getX();
        g_player.getComponent(Player.class).getMyY = g_player.getPosition().getY();
        index = 0;
        spawn("background", 0, 720);
    }

    /*
     * Reset lai nhung bien trong game khi nguoi choi chet / ve portal
     */
    private void replay() {
        g_player.getComponent(Player.class).flamePass = false;
        ENEMY_NUMBER = 0;
        TOTAL_ENEMY = 0;
        Boom.resizePowerBoom();
        Boom.resetFlameSize();
        Player.amountBoomDown();
        playerisAlive = false;
        if (g_player.hasComponent(Player.class)) {
            g_player.getComponent(Player.class).loadDeadAnim();
        }
        getGameTimer().runOnceAfter(() -> {
            g_player.removeFromWorld();
        }, Duration.seconds(1.1));

        CONST_SPEED = CONST_SPEED_BEGIN;
        getGameTimer().runOnceAfter(() -> {
            FXGL.getGameController().gotoMainMenu();
        }, Duration.seconds(2));
        index = 0;
    }

    /*
     * Dua nguoi choi den level tiep theo(neu lam :D) /ve man chinh khi di den
     */
    private void nextLevel() {
        g_player.getComponent(Player.class).flamePass = false;
        ENEMY_NUMBER = 0;
        TOTAL_ENEMY = 0;
        Boom.resizePowerBoom();
        Boom.resetFlameSize();
        Player.amountBoomDown();
        playerisAlive = false;
        getGameTimer().runOnceAfter(() -> {
            // if (g_player.hasComponent(AnimationComponent.class)) {
            g_player.getComponent(Player.class).loadWinAnim();
            // }
        }, Duration.seconds(0.05));

        getGameTimer().runOnceAfter(() -> {
            g_player.removeFromWorld();
        }, Duration.seconds(1.5));

        CONST_SPEED = CONST_SPEED_BEGIN;
        getGameTimer().runOnceAfter(() -> {
            FXGL.getGameController().gotoMainMenu();
        }, Duration.seconds(2));
        index = 0;
    }

    /*
     * Ham xu li va cham 2 vat the bat ki trong Game
     */
    @Override
    protected void initPhysics() {
        // Xu li va cham Player va cac Enemy trong game
        for (Enum enemy : enemyType) {
            onCollisionBegin(PLAYER, enemy, (player, myEnemy) -> {
                set("life", 0);
                replay();
            });
        }

        // Xu li va cham Player va Flame
        for (Enum flame : myFlameList) {
            onCollisionBegin(PLAYER, flame, (player, myFlame) -> {
                set("life", 0);
                if (!g_player.getComponent(Player.class).flamePass) replay();
            });
        }

        // Xu li enemy va bomb trong Game
        for (Enum enemy : enemyType) {
            for (Enum myFlame : myFlameList) {
                onCollisionBegin(enemy, myFlame, (enemyEntity, flame) -> {
                    if (enemyEntity.hasComponent(EnemyDahl.class)) {
                        if (!enemyEntity.getComponent(EnemyDahl.class).isDead) {
                            enemyEntity.getComponent(EnemyDahl.class).dead();
                            inc("enemies", -1);
                            TOTAL_ENEMY--;
                        }
                    }
                    if (enemyEntity.hasComponent(BalloonVertical.class)) {
                        if (!enemyEntity.getComponent(BalloonVertical.class).isDead) {
                            enemyEntity.getComponent(BalloonVertical.class).dead();
                            inc("enemies", -1);
                            TOTAL_ENEMY--;
                        }
                    }
                    if (enemyEntity.hasComponent(BalloonHorizontal.class)) {
                        if (!enemyEntity.getComponent(BalloonHorizontal.class).isDead) {
                            enemyEntity.getComponent(BalloonHorizontal.class).dead();
                            inc("enemies", -1);
                            TOTAL_ENEMY--;
                        }
                    }
                    if (enemyEntity.hasComponent(EnemyPass.class)) {
                        if (!enemyEntity.getComponent(EnemyPass.class).isDead) {
                            enemyEntity.getComponent(EnemyPass.class).dead();
                            inc("enemies", -1);
                            TOTAL_ENEMY--;
                        }
                    }
                    if (enemyEntity.hasComponent(EnemyDoria.class)) {
                        if (!enemyEntity.getComponent(EnemyDoria.class).isDead) {
                            enemyEntity.getComponent(EnemyDoria.class).dead();
                            inc("enemies", -1);
                            TOTAL_ENEMY--;
                        }
                    }
                    if (enemyEntity.hasComponent(EnemyPontan.class)) {
                        if (!enemyEntity.getComponent(EnemyPontan.class).isDead) {
                            enemyEntity.getComponent(EnemyPontan.class).dead();
                            inc("enemies", -1);
                            TOTAL_ENEMY--;
                        }
                    }
                    getGameTimer().runOnceAfter(() -> {
                        enemyEntity.removeFromWorld();
                        System.out.println("REMAINING ENEMY:" + TOTAL_ENEMY);
                    }, Duration.seconds(0.7));
                });
            }
        }

        // Xu li va cham giua cac item va Flame cua Bomb
        for (Enum myItem : myItemList) {
            for (Enum myFlame : myFlameList) {
                onCollisionBegin(myItem, myFlame, (item, flame) -> {
                    String sign = getSignOfEntity(item);
                    getGameTimer().runOnceAfter(() -> {
                        if (sign.equals("c") || sign.equals("a") || sign.equals("d") || sign.equals("b")) {
                            item.removeFromWorld();
                            updateMap(item, "item");
                        }
                    }, Duration.seconds(0.6));

                });
            }
        }

        // Xu li va cham giua Player va boom
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, BOOM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity boom) {
                if (Player.k) {
                    player.setPosition(new Point2D(currentXpos, currentYpos));
                }
            }

            @Override
            protected void onCollision(Entity player, Entity boom) {
                if (Player.k) {
                    player.setPosition(new Point2D(currentXpos, currentYpos));
                }
            }

            @Override
            protected void onCollisionEnd(Entity player, Entity boom) {
                Player.k = true;
            }
        });

        // Xu li va cham giua Player va Portal
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, PORTAL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity portal) {
                String sign = getSignOfEntity(portal);
                // System.out.println(TOTAL_ENEMY + " " + sign);
                getGameTimer().runOnceAfter(() -> {
                    if ((sign.equals("p") || sign.equals("0")) && TOTAL_ENEMY == 0) {
                        nextLevel();
                    }
                }, Duration.seconds(0.6));
            }
        });

        // Xu li va cham Player va Item
        for (Enum item : myItemList) {
            getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, item) {
                @Override
                protected void onCollisionBegin(Entity player, Entity my_item) {
                    String prevSolve = getSignOfEntity(my_item);
                    if (prevSolve.equals("a") || prevSolve.equals("b") || prevSolve.equals("c")
                            || prevSolve.equals("d")) {
                        updateMap(my_item, "item");
                        switch (item.name()) {
                            case "BOMB_ITEM":
                                Player.increaseBoomAmount();
                                break;
                            case "FLAME_POWER_ITEM":
                                Boom.increaseFlameSize();
                                g_player.getComponent(Player.class).flamePass = true;
                                break;
                            case "FLAME_ITEM":
                                Boom.powerBoomUp();
                                break;
                            case "SPEED_ITEM":
                                player.getComponent(Player.class).increaseSpeed();
                                break;
                            default:
                                break;
                        }
                        getGameTimer().runOnceAfter(() -> {
                            my_item.removeFromWorld();
                        }, Duration.seconds(0.1));
                    }
                }
            });
        }

        // Xu li va cham giua Player va Wall
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, WALL) {
            @Override
            protected void onCollisionBegin(Entity player, Entity wall) {
                player.setPosition(new Point2D(currentXpos, currentYpos));
                player.getComponent(Player.class).onCollision = true;
            }

            @Override
            protected void onCollision(Entity player, Entity wall) {
                player.getComponent(Player.class).onCollision = true;
                String getStatus = player.getComponent(Player.class).getStatus();
                int wallTileY = (int) wall.getPosition().getX() / TITLE_SIZE;
                int wallTileX = (int) wall.getPosition().getY() / TITLE_SIZE;
                boolean hasLeftTile = false;
                boolean hasRightTile = false;
                boolean hasUpTile = false;
                boolean hasDownTile = false;
                // System.out.println(wallTileX + " WALL " + wallTileY);
                if (wallTileX >= 0 && wallTileX < HEIGHT_TITLE
                        && wallTileY - 1 >= 0 && wallTileY - 1 < WIDTH_TITLE) {
                    if (myMap[wallTileX][wallTileY - 1].equals("1")
                            || myMap[wallTileX][wallTileY - 1].equals("2")
                            || myMap[wallTileX][wallTileY - 1].equals("A")
                            || myMap[wallTileX][wallTileY - 1].equals("B")
                            || myMap[wallTileX][wallTileY - 1].equals("C")
                            || myMap[wallTileX][wallTileY - 1].equals("D")) {
                        hasLeftTile = true;
                    }
                }
                if (wallTileX >= 0 && wallTileX < HEIGHT_TITLE
                        && wallTileY + 1 >= 0 && wallTileY + 1 < WIDTH_TITLE) {
                    if (myMap[wallTileX][wallTileY + 1].equals("1")
                            || myMap[wallTileX][wallTileY + 1].equals("2")
                            || myMap[wallTileX][wallTileY + 1].equals("A")
                            || myMap[wallTileX][wallTileY + 1].equals("B")
                            || myMap[wallTileX][wallTileY + 1].equals("C")
                            || myMap[wallTileX][wallTileY + 1].equals("D")) {
                        hasRightTile = true;
                    }
                }
                if (wallTileX + 1 >= 0 && wallTileX + 1 < HEIGHT_TITLE
                        && wallTileY >= 0 && wallTileY < WIDTH_TITLE) {
                    if (myMap[wallTileX + 1][wallTileY].equals("1")
                            || myMap[wallTileX + 1][wallTileY].equals("2")
                            || myMap[wallTileX + 1][wallTileY].equals("A")
                            || myMap[wallTileX + 1][wallTileY].equals("B")
                            || myMap[wallTileX + 1][wallTileY].equals("C")
                            || myMap[wallTileX + 1][wallTileY].equals("D")) {
                        hasDownTile = true;
                    }
                }
                if (wallTileX - 1 >= 0 && wallTileX - 1 < HEIGHT_TITLE
                        && wallTileY >= 0 && wallTileY < WIDTH_TITLE) {
                    if (myMap[wallTileX - 1][wallTileY].equals("1")
                            || myMap[wallTileX - 1][wallTileY].equals("2")
                            || myMap[wallTileX - 1][wallTileY].equals("A")
                            || myMap[wallTileX - 1][wallTileY].equals("B")
                            || myMap[wallTileX - 1][wallTileY].equals("C")
                            || myMap[wallTileX - 1][wallTileY].equals("D")) {
                        hasUpTile = true;
                    }
                }
                switch (getStatus) {
                    case "LEFT":
                        player.getComponent(Player.class).supportMoveHorizontal(player, wall, hasUpTile,
                                hasDownTile);
                        break;
                    case "RIGHT":
                        player.getComponent(Player.class).supportMoveHorizontal(player, wall, hasUpTile,
                                hasDownTile);
                        break;
                    case "UP":
                        player.getComponent(Player.class).supportMoveVertical(player, wall, hasRightTile,
                                hasLeftTile);
                        break;
                    case "DOWN":
                        player.getComponent(Player.class).supportMoveVertical(player, wall, hasRightTile,
                                hasLeftTile);
                        break;
                    default:
                        break;
                }
                player.setPosition(new Point2D(currentXpos, currentYpos));
            }

            @Override
            protected void onCollisionEnd(Entity player, Entity wall) {
                player.getComponent(Player.class).onCollision = false;
            }

        });

        // Xu li va cham Enemy doc va tuong
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BALLOONVERTICAL, WALL) {
            @Override
            protected void onCollisionBegin(Entity enemyVertical, Entity wall) {
                enemyVertical.getComponent(BalloonVertical.class).turnBack();
                enemyVertical.setPosition(new Point2D(
                        enemyVertical.getPosition().getX(),
                        enemyVertical.getComponent(BalloonVertical.class).getCurrentPosY()));
            }

            @Override
            protected void onCollision(Entity enemyVertical, Entity wall) {
                enemyVertical.setPosition(new Point2D(enemyVertical.getPosition().getX(),
                        enemyVertical.getComponent(BalloonVertical.class).getCurrentPosY()));
            }
        });
        // Xu li va cham Enemy ngang va tuong
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BALLOONHORIZONTAL, WALL) {
            @Override
            protected void onCollisionBegin(Entity enemyHorizontal, Entity wall) {
                enemyHorizontal.getComponent(BalloonHorizontal.class).turnBack();
                enemyHorizontal.setPosition(new Point2D(
                        enemyHorizontal.getComponent(BalloonHorizontal.class).getCurrentPosX(),
                        enemyHorizontal.getPosition().getY()));
            }

            @Override
            protected void onCollision(Entity enemyHorizontal, Entity wall) {
                enemyHorizontal
                        .setPosition(new Point2D(enemyHorizontal.getComponent(BalloonHorizontal.class).getCurrentPosX(),
                                enemyHorizontal.getPosition().getY()));
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
