package com.bobocode.bst.impl;

public class Node<T> {

    private T element;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public static <T> Node<T> valueOf(T element) {
        return new Node<>(element);
    }

    private Node(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
}
