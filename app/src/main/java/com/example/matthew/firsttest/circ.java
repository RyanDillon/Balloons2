package com.example.matthew.firsttest;

import java.util.Random;

/**
 * Created by Matthew on 3/8/2017.
 */

public class circ {
    int x;
    int y;
    int rad;
    circ [] arr;

    circ(){
        Random rand = new Random();
        x = rand.nextInt(200) + 40;
        y = rand.nextInt(50) + -50;
        arr = new circ[5];
        for(int i=0; i<5; i++){
            arr[i] = new circ();
        }
        rad=40;
    }
}
