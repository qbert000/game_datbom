package hellofx.SmartMap;

import hellofx.Map.MyMap;

import java.util.ArrayList;
import java.util.List;

import static hellofx.Constant.GameConstant.*;

public class SmartMap {

    public static SmartMap g_smartMap = null;
    public static int corner = 0;
    public static boolean[][] smartMap = new boolean[HEIGHT_TITLE][WIDTH_TITLE];

    public static boolean[] gate = new boolean[4];

    public static List<Position> smartPosition = new ArrayList<Position>();
    public static void setGate() {
        for (int i = 0; i < 4; i++) {
            gate[i] = false;
        }
    }
    public SmartMap() {
        for (int i = 0; i < HEIGHT_TITLE; i++) {
            for (int j = 0; j < WIDTH_TITLE; j++) {
                smartMap[i][j] = false;
            }
        }
        for (int i = 0; i < 4; i++) {
            gate[i] = false;
        }
    }

    public static void set() {
        for (int i = 1; i < HEIGHT_TITLE - 1; i++) {
            for (int j = 1; j < WIDTH_TITLE - 1; j++) {
                if (checkCorner(i, j)) {
                    smartMap[i][j] = true;
                    smartPosition.add(new Position(i, j, gate[0], gate[1], gate[2], gate[3] ));

                }
                setGate();
            }
        }
    }

    public static void print() {
//        for (int i = 0; i < HEIGHT_TITLE; i++) {
//            for (int j = 0; j < WIDTH_TITLE; j++) {
//                if (smartMap[i][j]) {
//                    System.out.print(1 + " ");
//                } else {
//                    System.out.print(0 + " ");
//                }
//            }
//            System.out.println();
//        }
//        for (Position position : smartPosition) {
//            System.out.println(position.index_x + " " + position.index_y + " "
//                    + position.direction.get(0) + " "
//                    + position.direction.get(1) + " "
//                    + position.direction.get(2) + " "
//                    + position.direction.get(3));
//        }

    }

    public static void checkAround(int y, int x) {
        corner = 0;
        if (MyMap.canGoThisWay(x + 1, y, "1")) {
            corner++;
            gate[0] = true;
        } else {
            gate[0] = false;
        }
        if (MyMap.canGoThisWay(x - 1, y, "1")) {
            corner++;
            gate[1] = true;
        } else {
            gate[1] = false;
        }
        if (MyMap.canGoThisWay(x, y - 1, "1")) {
            corner++;
            gate[2] = true;
        } else {
            gate[2] = false;
        }
        if (MyMap.canGoThisWay(x, y + 1, "1")) {
            corner++;
            gate[3] = true;
        } else {
            gate[3] = false;
        }
    }

    public static boolean checkCorner(int y, int x) {
        if (!MyMap.canGoThisWay(x, y, "1")) {
            return false;
        }
        checkAround(y,x);
        return corner > 2;
    }
}
