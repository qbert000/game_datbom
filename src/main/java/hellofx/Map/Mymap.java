package hellofx.Map;

import java.util.ArrayList;
import java.util.List;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static hellofx.Constant.GameConstant.HEIGHT_TITLE;
import static hellofx.Constant.GameConstant.TITLE_SIZE;
import static hellofx.Constant.GameConstant.WIDTH_TITLE;
import static hellofx.Constant.GameConstant.ENEMY_NUMBER;

import java.io.File;
import java.util.Scanner;

import hellofx.Enemy.Enemy;
import hellofx.Enemy.Enemy1;
import hellofx.Enemy.EnemyHorizontal;
import hellofx.Enemy.EnemyVertical;
import com.almasb.fxgl.entity.*;

public class Mymap {
    public static String myMap[][] = new String[HEIGHT_TITLE][WIDTH_TITLE];

    public static Mymap g_map = null;

    public static Entity[] enemy = new Entity[ENEMY_NUMBER];

    public static int index = 0;

    public static int playerX;
    public static int playerY;

    public Mymap() throws Exception {
        /*
         * Comment lai dong duoi khi dung tren may Quyen.
         */
        File file = new File("C:\\Users\\Admin\\Documents\\Bomberman\\src\\assets\\textures\\text\\map.txt");
        /*
         * Comment lai dong duoi khi dung tren may Dung.
         */
        //File file = new File("E:\\space_java\\Game\\game_datbom\\src\\main\\resources\\assets\\textures\\text\\map.txt");
        Scanner sc = new Scanner(file);

        int i = 0;
        while (sc.hasNextLine() && i < HEIGHT_TITLE) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                myMap[i][j] = sc.next();
            }
            i++;
        }
        sc.close();
    }

    public static void spawnComponent(int i, int j) {
        spawn("grass", j * TITLE_SIZE, i * TITLE_SIZE);

        if (Mymap.myMap[i][j].equals("1")) {
            spawn("wall", j * TITLE_SIZE, i * TITLE_SIZE);
        }
        if (Mymap.myMap[i][j].equals("5")) {
            spawn("portal", j * TITLE_SIZE, i * TITLE_SIZE);
            spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }
        if (Mymap.myMap[i][j].equals("2")) {
            spawn("brick", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (Mymap.myMap[i][j].equals("A")) {
            spawn("speedItem", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (Mymap.myMap[i][j].equals("B")) {
            spawn("flameItem", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (Mymap.myMap[i][j].equals("C")) {
            spawn("bombItem", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (Mymap.myMap[i][j].equals("D")) {
            spawn("flamePowerItem", j * TITLE_SIZE, i * TITLE_SIZE);
        }

        if (Mymap.myMap[i][j].equals("6")) {
            spawn("enemyVertical", j * TITLE_SIZE + 2, i * TITLE_SIZE + 2).getComponent(EnemyVertical.class)
                    .move();
        }

        if (Mymap.myMap[i][j].equals("7")) {
            spawn("enemyHorizontal", j * TITLE_SIZE + 2, i * TITLE_SIZE + 2).getComponent(EnemyHorizontal.class)
                    .move();
        }

        if (Mymap.myMap[i][j].equals("8")) {
            enemy[index] = spawn("enemy1", j * TITLE_SIZE, i * TITLE_SIZE);
            enemy[index].getComponent(Enemy1.class).setUp();
            index++;
        }

        if (Mymap.myMap[i][j].equals("4")) {
            spawn("enemyRandom", j * TITLE_SIZE, i * TITLE_SIZE);
        }
    }

    public boolean updatePlayerPosition(int newX, int newY) {
        boolean canUpdate = (newY == playerX) && (newX == playerY);
        // System.out.println(newX + " " + newY + " " + playerY + " " + playerX + " " + canUpdate);
        if(!canUpdate) {
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
}
