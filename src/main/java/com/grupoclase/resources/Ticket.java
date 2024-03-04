package com.grupoclase.resources;

public class Ticket {
    Bombo bombo = new Bombo(0,49);

    /**
     * este Método, obtendrá un array de números enteros y los comparará con el resultado del bombo
     * @param numElegidos
     * array de 6 números
     * @return
     * cantidad de aciertos desde el último número hacia el primero
     */

    public int compararResultado6nums (int [] numElegidos){
        int[] numGanadores = bombo.extraerCombinacionGanadora(6);
        int cantidadAciertos = 0;
        for (int i = numElegidos.length -1; i >= 0; i--) {
            if(numElegidos[i] == numGanadores[i]){
                cantidadAciertos++;
            } else {
                return cantidadAciertos;
            }
        }
        return cantidadAciertos;
    }
}
