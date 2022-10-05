package hellofx;

import java.io.File;
import java.util.Scanner;

import static hellofx.Main.HEIGHT_TITLE;
import static hellofx.Main.WIDTH_TITLE;

public class Mymap {

    public String myMap[][] = new String[HEIGHT_TITLE][WIDTH_TITLE];

    public Mymap() throws Exception {
        // map.clear();
        File file = new File("C:\\Users\\Admin\\Documents\\Bomberman\\src\\assets\\textures\\text\\map.txt");
        Scanner sc = new Scanner(file);

        int i = 0;
        while (sc.hasNextLine() && i < 18) {
            for (int j = 0; j < 32; j++) {
                myMap[i][j] = sc.next();
                // System.out.println(name[i][j]);
            }
            i++;
        }
    }

}
