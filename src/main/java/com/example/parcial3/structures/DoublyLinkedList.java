package com.example.parcial3.structures;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList<T> {
    // "Es el inicio de la lista"
    private DoublyNode<T> head;
    // "Es el final de la lista"
    private DoublyNode<T> tail;
    // "Cantidad de nodos en la lista"
    private int size;

    public DoublyLinkedList() {
        // "Como es una lista vacía, no hay primer ni último nodo"
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        // "La verdad no tengo idea, pero yo lo haría así:"
        if (head == null){
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    // "Insertar un nodo al inicio"
    public void insertFirst(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);

        // "Si la lista está vacía, el nuevo nodo es head y tail a la vez"
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        else {
            // "El nuevo nodo apunta hacia adelante al que antes era el primero"
            newNode.setNext(head);
            // "El que antes era el primero ahora apunta hacia atrás al nuevo"
            head.setPrev(newNode);
            // "Ahora el nuevo nodo es el primero"
            head = newNode;
        }
        // (Comentario de Pat) Aumenta el tamaño de la lista
        size++;
    }

    // "Insertar un nodo al final"
    public void insertLast(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);

        // "Si la lista está vacía, el nuevo nodo es head y tail a la vez"
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        else {
            // "tail nos da el último nodo directo, sin recorrer"
            tail.setNext(newNode);   // "El último nodo ahora apunta al nuevo"
            newNode.setPrev(tail);   // "El nuevo apunta hacia atrás al que era último"
            tail = newNode;          // "Ahora el nuevo nodo es el último"
        }
        // (Comentario de Pat) Aumenta el tamaño de la lista
        size++;
    }

    // "Busca un dato en la lista"
    public boolean contains(T data) {
        DoublyNode<T> current = head;
        while (current != null) {
            // "Comparamos objetos con equals, no con =="
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // "Elimina la primera aparición del dato y regresa true si lo encuentra"
    public boolean remove(T data) {
        // "Buscamos el nodo que tiene el dato"
        // "Ya no necesitamos "previous" porque cada nodo conoce a su anterior"
        DoublyNode<T> current = head;
        while (current != null && !current.getData().equals(data)) {
            current = current.getNext();
        }

        if (current == null) {
            return false;
        }

        // "Reconectamos el lado izquierdo"
        if (current.getPrev() == null) {
            head = current.getNext();
        }
        else {
            current.getPrev().setNext(current.getNext());
        }

        // "Reconectamos el lado derecho"
        if (current.getNext() == null) {
            tail = current.getPrev();
        } else {
            current.getNext().setPrev(current.getPrev());
        }
        // Como borramos un dato, ahora disminuimos el tamaño
        size--;
        return true;
    }

    // "Vacía la lista"
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // (NUEVO) Copia los datos a una List de Java para poder regresarlos como JSON
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        DoublyNode<T> current = head;
        // recorre la lista y copia los datos de uno en uno
        while (current != null) {
            result.add(current.getData());
            current = current.getNext();
        }
        // regresa los datos recaudados
        return result;
    }
}
