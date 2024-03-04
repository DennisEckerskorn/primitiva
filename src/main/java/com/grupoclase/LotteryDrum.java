package com.grupoclase;

import com.grupoclase.resources.JuegoPrimitiva;

import java.util.ArrayList;
import java.util.Random;

public class LotteryDrum {

        private final ArrayList<Integer> numerosDisponibles;

        public LotteryDrum() {
            // Inicializamos el bombo con los números del 1 al 49
            numerosDisponibles = new ArrayList<>();
            for (int i = 1; i <= 49; i++) {
                numerosDisponibles.add(i);
            }
        }

        // Método para extraer un número aleatorio del bombo
        public int extraerNumero() {
            Random random = new Random();
            int index = random.nextInt(numerosDisponibles.size());
            return numerosDisponibles.remove(index);
        }

        // Método para reiniciar el bombo con todos los números disponibles
        public void reiniciar() {
            numerosDisponibles.clear();
            for (int i = 1; i <= 49; i++) {
                numerosDisponibles.add(i);
            }
        }
    }

