package hellofx.Map;

import java.io.File;
import java.util.Scanner;

import static hellofx.Constant.GameConstant.*;

public class Mymap {
    public String myMap[][] = new String[HEIGHT_TITLE][WIDTH_TITLE];

    public Mymap() throws Exception {
        /*
         * Comment lai dong nay khi dung tren may Quyen.
         */
        //File file = new File("C:\\Users\\Admin\\Documents\\Bomberman\\src\\assets\\textures\\text\\map.txt");
        /*
         * Comment lai dong nay khi dung tren may Dung.
         */
         File file = new File("E:\\space_java\\Game\\game_datbom\\src\\main\\resources\\assets\\textures\\text\\map.txt");
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

}
