package org.example;


import java.io.File;
import java.util.Scanner;

public class Mymap {

    public String myMap[][] = new String[24][38];


    public Mymap() throws Exception {
        //map.clear();
        File file = new File("E:\\space_java\\game_datbom\\src\\main\\resources\\assets\\textures\\text\\map.txt");
        Scanner sc = new Scanner(file);

        int i =0;
        while (sc.hasNextLine() && i < 24) {
            for ( int j = 0; j < 38; j ++) {
                myMap[i][j] = sc.next();
                //System.out.println(name[i][j]);
            }
            i++;
        }
    }

}
