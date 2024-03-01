package com.grupoclase.resources;

import com.grupoclase.dynamicarray.GenericDynamicArray;

import java.util.Random;

//jon
public class Bombo {
    private int CANTIDAD_NUMS_BOMBO_GRANDE = 50;
    private int CANTIDAD_NUMS_BOMBO_PEQUENO = 10;
    private Random random = new Random();

    private GenericDynamicArray bomboGrande;
    private GenericDynamicArray bomboPequeno;

    public Bombo() {
        bomboGrande = new GenericDynamicArray();
        bomboPequeno = new GenericDynamicArray();
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

    public void resetBombo(){
        GenericDynamicArray array1 = new GenericDynamicArray();
        GenericDynamicArray array2 = new GenericDynamicArray();

        for (int i = 0; i < CANTIDAD_NUMS_BOMBO_GRANDE; i++) {
            int num = i;
            array1.add(num+1);

        }
        for (int i = 0; i < CANTIDAD_NUMS_BOMBO_PEQUENO; i++) {
            int num = i;
            array2.add(num+1);

        }
        bomboGrande = array1;
        bomboPequeno = array2;
    }


    public int[] extraerCombinacionGanadora (int cantidadNums){

        int[] arrayGanadores = new int[cantidadNums];

        for (int i = 0; i < cantidadNums; i++) {
            int posElegida = random.nextInt(bomboGrande.size());
            int numElegido = bomboGrande.remove(posElegida);
            arrayGanadores[i] = numElegido;
        }
        return arrayGanadores;
    }
}
