package com.grupoclase.dynamicarray;


import java.util.Arrays;

public class DynamicArray {
    private static final float GROW_FACTOR = 2f;
    private static final int DEFAULT_CAPACITY = 20;
    private double[] data;
    private int elementCount;

    //Constructor
    public DynamicArray(int capacidadInicial) {
        data = new double[capacidadInicial];
        elementCount = 0;
    }

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Método para añadir un valor double a la última posición del array.
     * Primero se comprueba si el array está lleno, si no se añade el elemento.
     * @param value double, valor a añadir al array.
     * @return true si se ha realizado correctamente.
     */
    public boolean add(double value) {
        if(isFull()) {
            expandArray();
        }
        data[elementCount++] = value;
        return true;
    }

    /**
     * Método que recibe el valor a insertar en el índice indicado.
     * Una vez se encuentre la posición del índice, se mueven todos los valores de la derecha una posición a la derecha.
     * El bucle itera desde el final hasta el índice indicado.
     * @param index int, índice donde se inserta el valor.
     * @param value double, valor que se inserta.
     * @return devuelve true si el valor se ha insertado correctamente y false si no se ha podido hacer correctamente.
     */
    public boolean add(int index, double value) {
        if(index >= elementCount || index < 0) {
            return false;
        }
        if(isFull()) {
           expandArray();
        }
        //Bucle para mover los valores a la derecha.
       moveToRight(index);
       //Inserta el valor en el indice indicado.
       data[index] = value;
       return true;
    }

    private void moveToRight(int index) {
        for(int i = elementCount; i > index; i--) {
            data[i] = data[i - 1];
        }
        elementCount++;
    }

    private void moveToLeft(int index) {
        for(int i = index; i < elementCount - 1; i++) {
            data[i] = data[i + 1];
        }
        elementCount--;
    }

    /**
     * Método que recibe un índice a encontrar y mueve todos los elementos a la izquierda reemplazando
     * el valor buscado y decrementando el tamaño del array.
     * @param index índice int.
     * @return devuelve el valor del índice bbuscado en double.
     */
    public double remove(int index) {
        if(index >= elementCount || index < 0) {
            return Double.POSITIVE_INFINITY;
        }
        double removedValueFromIndex = data[index];
        moveToLeft(index);
        return removedValueFromIndex;
    }

    /**
     * Método que recibe el valor a buscar y quitar el en el array como double.
     * Primero itera y busca la primera ocurrencia del valor, luego mueve todos los valores
     * de la derecha a la izaquierda sobreescribiendo el valor buscado.
     * El valor que se busca se guarda en una variable.
     * @param value double, valor a eliminar.
     * @return el valor que se busca y se quita del array.
     */
    public double remove(double value) {
        double removedValue = Double.POSITIVE_INFINITY;
        for(int i = 0; i < elementCount; i++) {
            if(data[i] == value) {
                removedValue = data[i];
                moveToLeft(i);
                return removedValue;
            }
        }
        return removedValue;
    }

    /**
     * Método que obtiene el valor del array en el índice que se le pasa
     * @param index índice buscado.
     * @return el valor del índice buscado.
     */
    public double get(int index) {
        if(index >= elementCount || index < 0) {
            return Double.POSITIVE_INFINITY;
        }
        return data[index];
    }

    /**
     * Method que introduce el valor al índice indicado sobreescribiendo el valor existente.
     * @param index int
     * @param value valor
     * @return true si se ha sobreescrito correctamente.
     */
    public boolean set(int index, double value) {
        if(index >= elementCount || index < 0) {
            return false;
        }
        data[index] = value;
        return true;
    }

    /**
     * Método que comprueba si el array está lleno o no.
     * @return true si está lleno y false si no está lleno.
     */
    private boolean isFull() {
        return elementCount == data.length;
    }

    public int size() {
        return elementCount;
    }

    /**
     * Método para expandir el array si está lleno, se amplia el doble de la cantidad inicial.
     * Finalmente se apunta el array expandido al array original para realizar la copia.
     */
    private void expandArray() {
        double[] arrayExpanded = new double[Math.round(data.length * GROW_FACTOR)];
        for(int i = 0; i < elementCount; i++) {
            arrayExpanded[i] = data[i];
        }
        data = arrayExpanded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DynamicArray that = (DynamicArray) o;

        if (elementCount != that.elementCount) return false;
        for(int i = 0; i < elementCount; i++) {
            if(data[i] != that.data[i]) {
                return false;
            }
        }
        return true;
        //return Arrays.equals(dynamicArray, that.dynamicArray);
    }

    @Override
    public int hashCode() {
        // Habría que crear una función hash que solo tuviese en cuenta
        // los primeros tope - 1 elementos.
        double[] effectiveArray = new double[elementCount];
        for(int i = 0; i < effectiveArray.length; i++) {
            effectiveArray[i] = data[i];
        }
        int result = Arrays.hashCode(effectiveArray);
        result = 31 * result + elementCount;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0; i < elementCount; i++) {
            sb.append(String.format("%.02f", data[i]));
        }
        sb.append(" ]");
        return sb.toString();
    }
}
