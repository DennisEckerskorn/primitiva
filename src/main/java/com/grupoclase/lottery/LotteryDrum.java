package com.grupoclase.lottery;

import com.grupoclase.dynamicarray.GenericDynamicArray;

import java.util.Random;

//jon
public class LotteryDrum {
    private int CANTIDAD_NUMS_BOMBO_GRANDE = 49;
    private int CANTIDAD_NUMS_BOMBO_PEQUENO = 10;
    private int ultimoNum = 0;
    private int primerNum = 0;
    private Random random = new Random();
    private GenericDynamicArray <Integer>bombo;


    private GenericDynamicArray <Integer>bomboGrande;
    private GenericDynamicArray <Integer>bomboPequeno;

    public LotteryDrum() {
        bomboGrande = new GenericDynamicArray<>();
        bomboPequeno = new GenericDynamicArray<>();
        for (int i = 0; i < CANTIDAD_NUMS_BOMBO_GRANDE; i++) {
            int num = i;
            bomboGrande.add(num + 1);

        }
        for (int i = 0; i < CANTIDAD_NUMS_BOMBO_PEQUENO; i++) {
            int num = i;
            bomboPequeno.add(num);
        }
    }

    public LotteryDrum(int primerNum, int ultimoNum) {
        this.ultimoNum = ultimoNum;
        this.primerNum = primerNum;
        bombo = new GenericDynamicArray<>();
        int num;
        for (int i = primerNum; i < ultimoNum; i++) {
            num = i;
            bombo.add(num);

        }
    }


    public void resetBombo(){
        GenericDynamicArray array1 = new GenericDynamicArray();
        GenericDynamicArray array2 = new GenericDynamicArray();
        for (int i = 0; i < ultimoNum; i++) {
            int num = i;
            array1.add(num+1);

        }
        bombo = array1;
    }


    public int[] extraerCombinacionGanadora (int cantidadNums){
        int[] arrayGanadores = new int[cantidadNums];

        for (int i = 0; i < cantidadNums; i++) {
            int posElegida = random.nextInt(bombo.size());
            int numElegido = bombo.remove(posElegida);
            arrayGanadores[i] = numElegido;
        }
        return arrayGanadores;
    }

}
