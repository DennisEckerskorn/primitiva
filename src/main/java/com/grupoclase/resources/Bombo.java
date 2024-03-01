package com.grupoclase.resources;

import com.grupoclase.dynamicarray.GenericDynamicArray;

import java.util.Random;

//jon
public class Bombo {
    int CANTIDAD_NUMS_BOMBO_GRANDE = 49-1;
    int CANTIDAD_NUMS_BOMBO_PEQUENO = 10-1;

    GenericDynamicArray bomboGrande;
    GenericDynamicArray bomboPequeno;

    public Bombo() {
        bomboGrande = new GenericDynamicArray(CANTIDAD_NUMS_BOMBO_GRANDE);
        bomboPequeno = new GenericDynamicArray(CANTIDAD_NUMS_BOMBO_PEQUENO);
        Random random = new Random();
        for (int i = 0; i < CANTIDAD_NUMS_BOMBO_GRANDE; i++) {
            int num = i;
            bomboGrande.add(num+1);

        }
        for (int i = 0; i < CANTIDAD_NUMS_BOMBO_PEQUENO; i++) {
            int num = i;
            bomboPequeno.add(num+1);
        }
    }
    private int extraerNum(){
           return 1;
    }
}
