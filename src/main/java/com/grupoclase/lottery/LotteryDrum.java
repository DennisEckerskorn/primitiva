package com.grupoclase.lottery;

import com.grupoclase.dynamicarray.GenericDynamicArray;

import java.util.Objects;
import java.util.Random;

//jon
public class LotteryDrum {
    private int ultimoNum = 0;
    private int primerNum = 0;
    private Random random = new Random();
    private GenericDynamicArray <Integer>bombo;

    /**
     * Constructor for the drum.
     * @param primerNum indicates the number from which the numbers start generating.
     * @param ultimoNum indicates the last generated number.
     */

    public LotteryDrum(int primerNum, int ultimoNum) {
        if(primerNum < ultimoNum){
            this.ultimoNum = ultimoNum;
            this.primerNum = primerNum;
            bombo = new GenericDynamicArray<>();
            for (int i = primerNum; i <= ultimoNum; i++) {
                bombo.add(i);
            }
        }else {
            return;
        }
    }

    /**
     * This method regenerates the numbers inside the drum.
     */
    public void resetBombo(){
        GenericDynamicArray array1 = new GenericDynamicArray();
        for (int i = 0; i < ultimoNum; i++) {
            int num = i;
            array1.add(num+1);
        }
        bombo = array1;
    }

    /**
     * Method to extract numbers from the drum.
     * @param cantidadNums the quantity of numbers to extract from the drum
     * @return an array of integers with the extracted numbers
     */
    public int[] extraerCombinacionGanadora (int cantidadNums){
        int[] arrayGanadores = new int[cantidadNums];
        for (int i = 0; i < cantidadNums; i++) {
            int posElegida = random.nextInt(bombo.size());
            int numElegido = bombo.remove(posElegida);
            arrayGanadores[i] = numElegido;
        }
        return arrayGanadores;
    }

    public int getUltimoNum() {
        return ultimoNum;
    }

    public int getPrimerNum() {
        return primerNum;
    }

    public Random getRandom() {
        return random;
    }

    public GenericDynamicArray<Integer> getBombo() {
        return bombo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LotteryDrum that = (LotteryDrum) o;

        if (ultimoNum != that.ultimoNum) return false;
        if (primerNum != that.primerNum) return false;
        if (!Objects.equals(random, that.random)) return false;
        return Objects.equals(bombo, that.bombo);
    }

    @Override
    public int hashCode() {
        int result = ultimoNum;
        result = 31 * result + primerNum;
        result = 31 * result + (random != null ? random.hashCode() : 0);
        result = 31 * result + (bombo != null ? bombo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bombo.size(); i++) {
            sb.append(bombo.get(i)).append(" ");
        }
        return sb.toString();
    }
}
