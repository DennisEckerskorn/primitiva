
package com.grupoclase.dynamicarray;


import java.util.Arrays;

public class GenericDynamicArray<T> {
    private static final float GROW_FACTOR = 2f;
    private static final int DEFAULT_CAPACITY = 20;
    private Object[] data;
    private int elementCount;

    //Constructor
    public GenericDynamicArray(int capacidadInicial) {
        data = new Object[capacidadInicial];
        elementCount = 0;
    }

    public GenericDynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Método para añadir un valor double a la última posición del array.
     * Primero se comprueba si el array está lleno, si no se añade el elemento.
     * @param value double, valor a añadir al array.
     * @return true si se ha realizado correctamente.
     */
    public boolean add(T value) {
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
    public boolean add(int index, T value) {
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
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if(index >= elementCount || index < 0) {
            return null;
        }
        T removedValueFromIndex = (T) data[index];
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
    @SuppressWarnings("unchecked")
    public T remove(T value) {
        T removedValue = null;
        for(int i = 0; i < elementCount; i++) {
            if(data[i] == value) {
                removedValue = (T) data[i];
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
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if(index >= elementCount || index < 0) {
            return null;
        }
        return (T) data[index];
    }

    /**
     * Method que introduce el valor al índice indicado sobreescribiendo el valor existente.
     * @param index int
     * @param value valor
     * @return true si se ha sobreescrito correctamente.
     */
    public boolean set(int index, T value) {
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
        Object[] arrayExpanded = new Object[Math.round(data.length * GROW_FACTOR)];
        for(int i = 0; i < elementCount; i++) {
            arrayExpanded[i] = data[i];
        }
        data = arrayExpanded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenericDynamicArray that = (GenericDynamicArray) o;

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
        Object[] effectiveArray = new Object[elementCount];
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
            sb.append(data[i]).append("\n");
        }
        sb.append(" ]");
        return sb.toString();
    }
}
