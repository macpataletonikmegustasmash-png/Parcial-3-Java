package com.example.parcial3.structures;

import java.util.ArrayList;
import java.util.List;

public class LinkedQueue<T> {


    private Node<T> front;

    private Node<T> rear;

    private int size;

    public LinkedQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Agrega un elemento al final de la fila
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
        } else {
            // El que era el último ahora apunta al nuevo
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
    }

    // Quita y regresa el primero de la fila
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T data = front.getData();
        front = front.getNext();
        // Si salió el único, la cola quedó vacía y rear también debe ser null
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    // Regresa el primero de la fila sin quitarlo
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return front.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node<T> current = front;
        while (current != null) {
            result.add(current.getData());
            current = current.getNext();
        }
        return result;
    }
}
