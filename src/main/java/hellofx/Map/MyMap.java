package hellofx.Map;

import java.util.Objects;

// import static com.almasb.fxgl.dsl.FXGL.spawn;
import static hellofx.Constant.GameConstant.HEIGHT_TITLE;
import static hellofx.Constant.GameConstant.TITLE_SIZE;
import static hellofx.Constant.GameConstant.WIDTH_TITLE;
import static hellofx.Constant.GameConstant.ENEMY_NUMBER;
import static hellofx.Constant.GameConstant.TOTAL_ENEMY;
// import static hellofx.Constant.GameConstant.ENEMY_NUMBER;

import java.io.File;
import java.util.Scanner;

import com.almasb.fxgl.dsl.FXGL;

// import hellofx.Enemy.Enemy;
import hellofx.Enemy.EnemyPass;
import javafx.util.Duration;
import com.almasb.fxgl.entity.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

public class MyMap {
    public static String[][] myMap = new String[HEIGHT_TITLE][WIDTH_TITLE];

    public static MyMap g_map = null;

    public static Entity[] enemy;

    public static int index = 0;

    public static int playerX;
    public static int playerY;

    public File file;

    public MyMap(String myLevelMapText, String owner) throws Exception {
        if(owner.equals("Q")) {
            String filePath = "E:\\space_java\\Game\\game_datbom\\src\\main\\resources\\assets\\textures\\text\\" + myLevelMapText;
            file = new File(filePath);
        }
        if(owner.equals("D")) {
            String filePath = "C:\\Users\\Admin\\Documents\\Bomberman\\src\\assets\\textures\\text\\" +myLevelMapText;
            file = new File(filePath);
        }

        Scanner sc = new Scanner(file);

        int i = 0;
        while (sc.hasNextLine() && i < HEIGHT_TITLE) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                myMap[i][j] = sc.next();
                if(myMap[i][j].equals("F")) ENEMY_NUMBER ++;
            }
            i++;
        }
        System.out.println(ENEMY_NUMBER);
        enemy = new Entity[ENEMY_NUMBER];
        sc.close();
    }

    public static void spawnComponent(int i, int j) {
        FXGL.spawn("grass", j * TITLE_SIZE, i * TITLE_SIZE);

        if (MyMap.myMap[i][j].equals("1")) {
            FXGL.spawn("wall", j * TITLE_SIZE, i * TITLE_SIZE);
        }
        if (MyMap.myMap[i][j].equals("P")) {
            FXGL.spawn("portal", j * TITLE_SIZE, i * TITLE_SIZE);
            FXGL.spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }
        if (MyMap.myMap[i][j].equals("2")) {
            FXGL.spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("A")) {
            FXGL.spawn("speedItem", j * TITLE_SIZE, i * TITLE_SIZE);
            FXGL.spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("B")) {
            FXGL.spawn("flameItem", j * TITLE_SIZE, i * TITLE_SIZE);
            FXGL.spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("C")) {
            FXGL.spawn("bombItem", j * TITLE_SIZE, i * TITLE_SIZE);
            FXGL.spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("D")) {
            FXGL.spawn("flamePowerItem", j * TITLE_SIZE, i * TITLE_SIZE);
            FXGL.spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("V")) {
            TOTAL_ENEMY++;
            FXGL.spawn("balloonVertical", j * TITLE_SIZE, i * TITLE_SIZE );
        }

        if (MyMap.myMap[i][j].equals("H")) {
            TOTAL_ENEMY++;
            FXGL.spawn("balloonHorizontal", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("F")) {
            TOTAL_ENEMY++;
            enemy[index] = FXGL.spawn("enemyPass", j * TITLE_SIZE, i * TITLE_SIZE);
            enemy[index].getComponent(EnemyPass.class).setUp();
            index++;
        }

        if (MyMap.myMap[i][j].equals("R")) {
            TOTAL_ENEMY++;
            FXGL.spawn("enemyPontan", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("S")) {
            TOTAL_ENEMY++;
            FXGL.spawn("enemyDahl", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (MyMap.myMap[i][j].equals("X")) {
            TOTAL_ENEMY++;
            FXGL.spawn("enemyDoria", j * TITLE_SIZE, i * TITLE_SIZE);
        }
    }

    public boolean updatePlayerPosition(int newX, int newY) {
        boolean canUpdate = (newY == playerX) && (newX == playerY);
        if (!canUpdate) {
            myMap[playerX][playerY] = "0";
            myMap[newY][newX] = "3";
        }
        playerX = newY;
        playerY = newX;
        return canUpdate;
    }

    public void getPlayerTile() {
        // System.out.println(playerX + " " + playerY);
    }

    public static void updateEnemy() {
        for (int i = 0; i < ENEMY_NUMBER; i++) {
            if (enemy[i].hasComponent(EnemyPass.class)) {
                if (!enemy[i].getComponent(EnemyPass.class).isDead) {
                    enemy[i].getComponent(EnemyPass.class).findPlayer.resetFinding = true;
                    enemy[i].getComponent(EnemyPass.class).findPlayer.resetMap();
                }
            }
        }
    }

    public static void updateMap(Entity updateTile, String tileType) {
        int tileY = (int) updateTile.getPosition().getX() / TITLE_SIZE;
        int tileX = (int) updateTile.getPosition().getY() / TITLE_SIZE;
        if (tileType.equals("item")) {
            myMap[tileX][tileY] = "0";
            updateEnemy();
        }
        if (tileType.equals("brick")) {
            if (!myMap[tileX][tileY].equals("2")) {
                myMap[tileX][tileY] = myMap[tileX][tileY].toLowerCase();
            } else {
                myMap[tileX][tileY] = "0";
            }
            updateEnemy();
        }
        if (tileType.equals("bomb")) {
            getGameTimer().runOnceAfter(() -> {
                myMap[tileX][tileY] = "1";
                updateEnemy();
                getGameTimer().runOnceAfter(() -> {
                    myMap[tileX][tileY] = "0";
                }, Duration.seconds(1));
            }, Duration.seconds(0.4));

        }
    }

    public static void printMap() {
        for (int i = 0; i < HEIGHT_TITLE; i++) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                System.out.print(myMap[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static String getSignOfEntity(Entity tile) {
        int tileY = (int) tile.getPosition().getX() / TITLE_SIZE;
        int tileX = (int) tile.getPosition().getY() / TITLE_SIZE;
        return myMap[tileX][tileY];
    }

    public static boolean canGoThisWay(int x, int y) {
        return !Objects.equals(myMap[y][x], "1") &&
                !Objects.equals(myMap[y][x], "2")
                && !Objects.equals(myMap[y][x], "A")
                && !Objects.equals(myMap[y][x], "B")
                && !Objects.equals(myMap[y][x], "C")
                && !Objects.equals(myMap[y][x], "D");
    }

    public static boolean canGoThisWay(int x, int y, String k) {
        return !Objects.equals(myMap[y][x], k);
    }
}
