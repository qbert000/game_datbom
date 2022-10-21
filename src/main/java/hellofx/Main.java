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
import com.almasb.fxgl.core.math.FXGLMath;

import hellofx.Enemy.EnemyHorizontal;
import hellofx.Enemy.EnemyRandom;
import hellofx.Enemy.EnemyVertical;
import javafx.scene.input.KeyCode;
import javafx.geometry.Point2D;
import java.util.Map;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;
import static hellofx.SpawnSystem.Enum.*;
import static hellofx.Map.Mymap.g_map;
import static hellofx.Map.Mymap.index;
import static hellofx.Map.Mymap.myMap;
import static hellofx.Map.Mymap.spawnComponent;
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

    public Enum[] myList = new Enum[] { FLAME, FLAMERIGHT, FLAMELEFT, FLAMEUP, FLAMEDOWN };

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
            g_player.getComponent(AnimationComponent.class).loadDeadAnim();
        }
        getGameTimer().runOnceAfter(() -> {
            g_player.removeFromWorld();
        }, Duration.seconds(0.7));

        CONST_SPEED = 1.5;
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
            if (g_player.hasComponent(AnimationComponent.class)) {
                g_player.getComponent(AnimationComponent.class).loadWinAnim();
            }
        }, Duration.seconds(0.6));

        getGameTimer().runOnceAfter(() -> {
            g_player.removeFromWorld();
        }, Duration.seconds(2));

        CONST_SPEED = 1.5;
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

        onCollisionBegin(PLAYER, ENEMY1, (player, enemy) -> {
            replay();
        });
        // Xu li va cham Player va Flame

        for (Enum flame : myList) {
            onCollisionBegin(PLAYER, flame, (player, myFlame) -> {
                replay();
            });
        }

        // Xu li va cham nguoi choi va Enemy
        onCollisionBegin(PLAYER, ENEMYHORIZONTAL, (player, flame) -> {
            replay();
        });

        onCollisionBegin(PLAYER, ENEMYVERTICAL, (player, flame) -> {
            replay();
        });

        for (Enum myFlame : myList) {
            onCollisionBegin(FLAME_ITEM, myFlame, (flame_item, flame) -> {
                getGameTimer().runOnceAfter(() -> {
                    flame_item.removeFromWorld();
                }, Duration.seconds(0.6));
            });
        }

        // Xu li bomb va enemy
        for (Enum myFlame : myList) {
            onCollisionBegin(ENEMYHORIZONTAL, myFlame, (enemy, flame) -> {
                if (enemy.hasComponent(EnemyHorizontal.class)) {
                    enemy.getComponent(EnemyHorizontal.class).dead();
                }
                getGameTimer().runOnceAfter(() -> {
                    enemy.removeFromWorld();
                }, Duration.seconds(0.3));
            });
        }

        // Xu li enemy ngang va player
        for (Enum myFlame : myList) {
            onCollisionBegin(ENEMYVERTICAL, myFlame, (enemy, flame) -> {
                if (enemy.hasComponent(EnemyVertical.class)) {
                    enemy.getComponent(EnemyVertical.class).dead();
                }
                getGameTimer().runOnceAfter(() -> {
                    enemy.removeFromWorld();
                }, Duration.seconds(0.4));
            });
        }

        // Xu li va cham giua Speed_item va Flame
        for (Enum myFlame : myList) {
            onCollisionBegin(SPEED_ITEM, myFlame, (speed_item, flame) -> {
                getGameTimer().runOnceAfter(() -> {
                    speed_item.removeFromWorld();
                }, Duration.seconds(0.6));
            });
        }

        // Xu li va cham giua Flame_power_item va Flame
        for (Enum myFlame : myList) {
            onCollisionBegin(FLAME_POWER_ITEM, myFlame, (flamePowerItem, flame) -> {
                getGameTimer().runOnceAfter(() -> {
                    flamePowerItem.removeFromWorld();
                }, Duration.seconds(0.6));
            });
        }

        // Xu li va cham giua Bomb_item va Flame
        for (Enum myFlame : myList) {
            onCollisionBegin(BOMB_ITEM, myFlame, (bombItem, flame) -> {
                getGameTimer().runOnceAfter(() -> {
                    bombItem.removeFromWorld();
                }, Duration.seconds(0.6));
            });
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

        // Xu li va cham giua Player va Bomb_item
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, BOMB_ITEM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity bomb_item) {
                AnimationComponent.increaseBoomAmount();
                getGameTimer().runOnceAfter(() -> {
                    bomb_item.removeFromWorld();
                }, Duration.seconds(0.1));
            }
        });

        // Xu li va cham giua Player va Speed_item
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(PLAYER, FLAME_POWER_ITEM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity flame_power_item) {
                Boom.increaseFlameSize();
                getGameTimer().runOnceAfter(() -> {
                    flame_power_item.removeFromWorld();
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
                // System.out.println("Player:" + player.getPosition().getX() + " " +
                //         player.getPosition().getY());
                // System.out.println("Wall:" + wall.getPosition().getX() + " " +
                //         wall.getPosition().getY());
                String getStatus = player.getComponent(AnimationComponent.class).getStatus();
                int wallTileY = (int) wall.getPosition().getX() / TITLE_SIZE;
                int wallTileX = (int) wall.getPosition().getY() / TITLE_SIZE;
                boolean hasLeftTile = false;
                boolean hasRightTile = false;
                boolean hasUpTile = false;
                boolean hasDownTile = false;
                double diff;
                // System.out.println(wallTileX + " WALL " + wallTileY);
                if (wallTileX >= 0 && wallTileX < HEIGHT_TITLE
                        && wallTileY - 1 >= 0 && wallTileY - 1 < WIDTH_TITLE) {
                    if (myMap[wallTileX][wallTileY - 1].equals("1")
                            || myMap[wallTileX][wallTileY - 1].equals("2")) {
                        hasLeftTile = true;
                    }
                }
                if (wallTileX >= 0 && wallTileX < HEIGHT_TITLE
                        && wallTileY + 1 >= 0 && wallTileY + 1 < WIDTH_TITLE) {
                    if (myMap[wallTileX][wallTileY + 1].equals("1")
                            || myMap[wallTileX][wallTileY + 1].equals("2")) {
                        hasRightTile = true;
                    }
                }
                if (wallTileX + 1 >= 0 && wallTileX + 1 < HEIGHT_TITLE
                        && wallTileY >= 0 && wallTileY < WIDTH_TITLE) {
                    if (myMap[wallTileX + 1][wallTileY].equals("1")
                            || myMap[wallTileX + 1][wallTileY].equals("2")) {
                        hasDownTile = true;
                    }
                }
                if (wallTileX - 1 >= 0 && wallTileX - 1 < HEIGHT_TITLE
                        && wallTileY >= 0 && wallTileY < WIDTH_TITLE) {
                    if (myMap[wallTileX - 1][wallTileY].equals("1")
                            || myMap[wallTileX - 1][wallTileY].equals("2")) {
                        hasUpTile = true;
                    }
                }
                if(hasUpTile) {
                    System.out.print(" Has Up ");
                } else {
                    System.out.print(" No Up ");
                }
                if(hasDownTile) {
                    System.out.print(" Has Down ");
                } else {
                    System.out.print(" No Down ");
                }
                if(hasLeftTile) {
                    System.out.print(" Has Left ");
                } else {
                    System.out.print(" No Left ");
                }
                if(hasRightTile) {
                    System.out.print("Has Right");
                } else {
                    System.out.print("No Right");
                }
                System.out.print("\n");
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
                int i = 0;
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
