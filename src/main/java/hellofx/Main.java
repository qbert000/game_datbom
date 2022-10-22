package hellofx;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.inventory.ItemConfig;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.math.FXGLMath;

import hellofx.Enemy.EnemyHorizontal;
import hellofx.Enemy.EnemyRandom;
import hellofx.Enemy.EnemyVertical;
import hellofx.Enemy.Enemy;
import hellofx.Enemy.Enemy1;
import javafx.scene.input.KeyCode;
import javafx.geometry.Point2D;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Map.Mymap.g_map;
import static hellofx.Map.Mymap.index;
import static hellofx.Map.Mymap.myMap;
import static hellofx.Map.Mymap.updateMap;
import static hellofx.Map.Mymap.spawnComponent;
import static hellofx.Map.Mymap.getSignOfEntity;
import hellofx.Menu.GameMenu;
import hellofx.Menu.MainMenu;
import hellofx.Bomb_Flame.*;
import javafx.util.Duration;
import static hellofx.Constant.GameConstant.*;
import hellofx.Animation.*;
import hellofx.SpawnSystem.Factory;
import hellofx.Map.*;
import static hellofx.Animation.AnimationComponent.currentXpos;
import static hellofx.Animation.AnimationComponent.currentYpos;

/*
 * Ham main luon phai extends GameApplication.
 */
public class Main extends GameApplication {
    private Entity g_player = new Entity();
    public AnimationComponent g_playerComponent;

    public boolean playerisAlive = true;

    public Enum[] myFlameList = new Enum[] { FLAME, FLAMERIGHT, FLAMELEFT, FLAMEUP, FLAMEDOWN };
    public Enum[] myItemList = new Enum[] { SPEED_ITEM, FLAME_POWER_ITEM, BOMB_ITEM, FLAME_ITEM };
    public Enum[] enemyType = new Enum[] {ENEMYHORIZONTAL, ENEMYVERTICAL, ENEMYRANDOM};

    public double getPlayerX() {
        return g_player.getPosition().getX();
    }

    public double getPlayerY() {
        return g_player.getPosition().getY();
    }

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

        // Tang Bomb Radius (tester).
        onKeyDown(KeyCode.F, () -> {
            // play("drop.wav"); // play am thanh
            Boom.powerBoomUp();
        });

        // Tang toc do nguoi choi (tester).
        onKeyDown(KeyCode.G, () -> {
            g_player.getComponent(AnimationComponent.class).increaseSpeed();
        });

        // Tang flame Power (tester).
        onKeyDown(KeyCode.R, () -> {
            Boom.increaseFlameSize();
        });

        // Tang so luong Boom (tester).
        onKeyDown(KeyCode.T, () -> {
            AnimationComponent.increaseBoomAmount();
        });

        // Xac dinh su kien di ben phai.
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onActionBegin() {
                if (playerisAlive) {
                    currentYpos = g_player.getComponent(AnimationComponent.class).getMyY;
                    currentXpos = g_player.getComponent(AnimationComponent.class).getMyX;
                    g_player.getComponent(AnimationComponent.class).setRight();
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
                    g_player.getComponent(AnimationComponent.class).setLeft();
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
                    g_player.getComponent(AnimationComponent.class).setUp();
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
                    g_player.getComponent(AnimationComponent.class).setDown();
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
        AnimationComponent.amountBoomDown();
        try {
            g_map = new Mymap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < HEIGHT_TITLE; i++) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                spawnComponent(i, j);
                if (Mymap.myMap[i][j].equals("3")) {
                    playerPosX = j * TITLE_SIZE;
                    playerPosY = i * TITLE_SIZE;
                    Mymap.playerX = i;
                    Mymap.playerY = j;
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
        index = 0;
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
        Boom.resetFlameSize();
        AnimationComponent.amountBoomDown();
        playerisAlive = false;
        if (g_player.hasComponent(AnimationComponent.class)) {
            // System.out.println("has animation");
            g_player.getComponent(AnimationComponent.class).loadDeadAnim();
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
     * Portal
     */
    private void nextLevel() {
        Boom.resizePowerBoom();
        Boom.resetFlameSize();
        AnimationComponent.amountBoomDown();
        playerisAlive = false;
        getGameTimer().runOnceAfter(() -> {
            // if (g_player.hasComponent(AnimationComponent.class)) {
                g_player.getComponent(AnimationComponent.class).loadWinAnim();
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
                replay();
            });
        }

        // Xu li va cham Player va Flame
        for (Enum flame : myFlameList) {
            onCollisionBegin(PLAYER, flame, (player, myFlame) -> {
                replay();
            });
        }

        // Xu li enemy va bomb trong Game
        for (Enum enemy : enemyType) {
            for (Enum myFlame : myFlameList) {
                onCollisionBegin(enemy, myFlame, (enemyEntity, flame) -> {
                    if(enemyEntity.hasComponent(EnemyRandom.class)) {
                        enemyEntity.getComponent(EnemyRandom.class).dead();
                    }
                    getGameTimer().runOnceAfter(() -> {
                        enemyEntity.removeFromWorld();
                    }, Duration.seconds(0.7));
                });
            }
        }


        // // Xu li enemy va Flame cua Bomb
        // for (Enum myFlame : myFlameList) {
        //     onCollisionBegin(ENEMYVERTICAL, myFlame, (enemy, flame) -> {
        //         if (enemy.hasComponent(EnemyVertical.class)) {
        //             enemy.getComponent(EnemyVertical.class).dead();
        //         } else if (enemy.hasComponent(EnemyVertical.class)) {
        //             enemy.getComponent(EnemyHorizontal.class).dead();
        //         } else if (enemy.hasComponent(Enemy1.class)) {

        //         }
        //         getGameTimer().runOnceAfter(() -> {
        //             enemy.removeFromWorld();
        //         }, Duration.seconds(0.4));
        //     });
        // }

        // Xu li va cham giua cac item va Flame cua Bomb
        for (Enum myItem : myItemList) {
            for (Enum myFlame : myFlameList) {
                onCollisionBegin(myItem, myFlame, (item, flame) -> {
                    // String prevSign = getSignOfEntity(item);
                    // System.out.println("Prev: " + prevSign);
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
                String sign = getSignOfEntity(portal);
                getGameTimer().runOnceAfter(() -> {
                    if (sign.equals("p")) {
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
                                AnimationComponent.increaseBoomAmount();
                                break;
                            case "FLAME_POWER_ITEM":
                                Boom.increaseFlameSize();
                                break;
                            case "FLAME_ITEM":
                                Boom.powerBoomUp();
                                break;
                            case "SPEED_ITEM":
                                player.getComponent(AnimationComponent.class).increaseSpeed();
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
                player.getComponent(AnimationComponent.class).onCollision = true;
            }

            @Override
            protected void onCollision(Entity player, Entity wall) {
                player.getComponent(AnimationComponent.class).onCollision = true;
                String getStatus = player.getComponent(AnimationComponent.class).getStatus();
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
                        player.getComponent(AnimationComponent.class).supportMoveHorizontal(player, wall, hasUpTile,
                                hasDownTile);
                        break;
                    case "RIGHT":
                        player.getComponent(AnimationComponent.class).supportMoveHorizontal(player, wall, hasUpTile,
                                hasDownTile);
                        break;
                    case "UP":
                        player.getComponent(AnimationComponent.class).supportMoveVertical(player, wall, hasRightTile,
                                hasLeftTile);
                        break;
                    case "DOWN":
                        player.getComponent(AnimationComponent.class).supportMoveVertical(player, wall, hasRightTile,
                                hasLeftTile);
                        break;
                    default:
                        break;
                }
                player.setPosition(new Point2D(currentXpos, currentYpos));
            }

            @Override
            protected void onCollisionEnd(Entity player, Entity wall) {
                player.getComponent(AnimationComponent.class).onCollision = false;
            }

        });

        // Xu li va cham Enemy doc va tuong
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ENEMYVERTICAL, WALL) {
            @Override
            protected void onCollisionBegin(Entity enemyVertical, Entity wall) {
                enemyVertical.getComponent(EnemyVertical.class).turnBack();
                enemyVertical.setPosition(new Point2D(
                        enemyVertical.getPosition().getX(),
                        enemyVertical.getComponent(EnemyVertical.class).getCurrentPosY()));
            }

            @Override
            protected void onCollision(Entity enemyVertical, Entity wall) {
                enemyVertical.setPosition(new Point2D(enemyVertical.getPosition().getX(),
                        enemyVertical.getComponent(EnemyVertical.class).getCurrentPosY()));
            }
        });
        // Xu li va cham Enemy ngang va tuong
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(ENEMYHORIZONTAL, WALL) {
            @Override
            protected void onCollisionBegin(Entity enemyHorizontal, Entity wall) {
                enemyHorizontal.getComponent(EnemyHorizontal.class).turnBack();
                enemyHorizontal.setPosition(new Point2D(
                        enemyHorizontal.getComponent(EnemyHorizontal.class).getCurrentPosX(),
                        enemyHorizontal.getPosition().getY()));
            }

            @Override
            protected void onCollision(Entity enemyHorizontal, Entity wall) {
                enemyHorizontal
                        .setPosition(new Point2D(enemyHorizontal.getComponent(EnemyHorizontal.class).getCurrentPosX(),
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
