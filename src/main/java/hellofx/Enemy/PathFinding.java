package hellofx.Enemy;

// import static hellofx.Map.MyMap.myMap;
import java.util.*;

import hellofx.Map.MyMap;
import javafx.util.Duration;
// import hellofx.Map.MyMap;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameTimer;

public class PathFinding {
    public Stack<String> st = new Stack<>();

    public String[][] myEnemyMap = MyMap.myMap;

    public int[][] myDistance = new int[18][32];

    public boolean resetFinding = false;

    public int playerX;
    public int playerY;

    public int enemyPosX;
    public int enemyPosY;

    public PathFinding(int enemyPosX, int enemyPosY) {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 32; j++) {
                myDistance[i][j] = 10000000;
            }
        }
        this.enemyPosX = enemyPosX;
        this.enemyPosY = enemyPosY;
    }

    public void setUpPath() {
//        System.out.println("I'm setting up Path !!!!" + );
        myDistance[enemyPosX][enemyPosY] = 0;
        getMinimum(enemyPosX, enemyPosY);
        // System.out.println("Player's Position is: " + playerX + " " + playerY);
        // for (int i = 0; i < 18; i++) {
        //     for (int j = 0; j < 32; j++) {
        //         System.out.print(myEnemyMap[i][j] + " ");
        //     }
        //     System.out.print("\n");
        // }
        if(playerX >= 1 && playerX <= 16 && playerY >=1 && playerY <= 30) {
            moving(playerX, playerY);
        } else {
//            System.out.println("HUHUHUH BUGGG KHONG FIX NUA " + enemyPosX + " " + enemyPosY);
            getGameTimer().runOnceAfter(() -> {
                myEnemyMap = MyMap.myMap;
                for (int i = 0; i < 18; i++) {
                    for (int j = 0; j < 32; j++) {
                        myDistance[i][j] = 10000000;
                    }
                }
                myDistance[enemyPosX][enemyPosY] = 0;
                getMinimum(enemyPosX, enemyPosY);
                playerX = MyMap.playerX;
                playerY = MyMap.playerY;
//                System.out.println(playerX + " " + playerY);
                moving(playerX, playerY);
            }, Duration.seconds(0.3));
        }
    }

    public void seeMyStack() {
//         Stack<String> temp = st;
// //        while (!temp.empty()) {
// //            System.out.print(temp.pop() + " ");
// //        }
// //        System.out.print("\n");
    }

    public void resetPathFinding(int newX, int newY) {
        myEnemyMap = MyMap.myMap;
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 32; j++) {
                myDistance[i][j] = 10000000;
            }
        }
        enemyPosX = newX;
        enemyPosY = newY;
        myDistance[newX][newY] = 0;
        // st = new Stack<>();
    }

    public void getMinimum(int k, int j) {
        if (k - 1 > 0 && k - 1 < 18 && j > 0 && j < 32) {
            if (!myEnemyMap[k - 1][j].equals("1") && !myEnemyMap[k - 1][j].equals("2")
                    && !myEnemyMap[k - 1][j].equals("A")
                    && !myEnemyMap[k - 1][j].equals("B")
                    && !myEnemyMap[k - 1][j].equals("C")
                    && !myEnemyMap[k - 1][j].equals("D")

                    && myDistance[k - 1][j] > myDistance[k][j] + 1) {
                myDistance[k - 1][j] = myDistance[k][j] + 1;
                getMinimum(k - 1, j);
                if (myEnemyMap[k - 1][j].equals("3")) {
                    playerX = k - 1;
                    playerY = j;
                    return;
                }
            }
        }
        if (k + 1 > 0 && k + 1 < 18 && j > 0 && j < 32) {
            if (!myEnemyMap[k + 1][j].equals("1") && !myEnemyMap[k + 1][j].equals("2")
                    && !myEnemyMap[k + 1][j].equals("A")
                    && !myEnemyMap[k + 1][j].equals("B")
                    && !myEnemyMap[k + 1][j].equals("C")
                    && !myEnemyMap[k + 1][j].equals("D")

                    && myDistance[k + 1][j] > myDistance[k][j] + 1) {
                myDistance[k + 1][j] = myDistance[k][j] + 1;
                getMinimum(k + 1, j);
                if (myEnemyMap[k + 1][j].equals("3")) {
                    playerX = k + 1;
                    playerY = j;
                    return;
                }
            }
        }
        if (k > 0 && k < 18 && j + 1 > 0 && j + 1 < 32) {
            if (!myEnemyMap[k][j + 1].equals("1") && !myEnemyMap[k][j + 1].equals("2")
                    && !myEnemyMap[k][j + 1].equals("A")
                    && !myEnemyMap[k][j + 1].equals("B")
                    && !myEnemyMap[k][j + 1].equals("C")
                    && !myEnemyMap[k][j + 1].equals("D")

                    && myDistance[k][j + 1] > myDistance[k][j] + 1) {
                myDistance[k][j + 1] = myDistance[k][j] + 1;
                getMinimum(k, j + 1);
                if (myEnemyMap[k][j + 1].equals("3")) {
                    playerX = k;
                    playerY = j + 1;
                    return;
                }
            }
        }
        if (k > 0 && k < 18 && j - 1 > 0 && j - 1 < 32) {
            if (!myEnemyMap[k][j - 1].equals("1") && !myEnemyMap[k][j - 1].equals("2")
                    && !myEnemyMap[k][j - 1].equals("A")
                    && !myEnemyMap[k][j - 1].equals("B")
                    && !myEnemyMap[k][j - 1].equals("C")
                    && !myEnemyMap[k][j - 1].equals("D")

                    && myDistance[k][j - 1] > myDistance[k][j] + 1) {
                myDistance[k][j - 1] = myDistance[k][j] + 1;
                getMinimum(k, j - 1);
                if (myEnemyMap[k][j - 1].equals("3")) {
                    playerX = k;
                    playerY = j - 1;
                    return;
                }
            }
        }
        return;
    }

    public void moving(int x, int y) {
        // System.out.println(x + " " + y);
        if (myDistance[x][y] == 0) {
            return;
        }
        if (myDistance[x + 1][y] == myDistance[x][y] - 1) {
            st.push("UP");
            moving(x + 1, y);
        } else if (myDistance[x - 1][y] == myDistance[x][y] - 1) {
            st.push("DOWN");
            moving(x - 1, y);
        } else if (myDistance[x][y + 1] == myDistance[x][y] - 1) {
            st.push("LEFT");
            moving(x, y + 1);
        } else if (myDistance[x][y - 1] == myDistance[x][y] - 1) {
            st.push("RIGHT");
            moving(x, y - 1);
        }
    }

    public void resetMap() {
        myEnemyMap = MyMap.myMap;
    }
}