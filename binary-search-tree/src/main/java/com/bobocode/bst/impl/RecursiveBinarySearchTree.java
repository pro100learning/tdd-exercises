package com.bobocode.bst.impl;

import com.bobocode.bst.BinarySearchTree;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class RecursiveBinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTree<T> {

    private Node<T> root;
    private int size = 0;
    private int leftHeight = 0;
    private int rightHeight = 0;

    @SafeVarargs
    public static <T extends Comparable<? super T>> RecursiveBinarySearchTree<T> of(T... values) {
        RecursiveBinarySearchTree<T> bst = new RecursiveBinarySearchTree<>();
        Stream.of(values).forEach(bst::insert);
        return bst;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = Node.valueOf(element);
            size++;
            return true;
        } else {
            return insertIntoTree(root, element, null);
        }
    }

    private boolean insertIntoTree(Node<T> node, T element, Boolean isLeftHeight) {
        var compareResult = node.getElement().compareTo(element);
        if (compareResult == 0) { // equals
            return false;
        } else if (compareResult > 0) { // go left
            if (isLeftHeight == null) {
                isLeftHeight = true;
            }
            return insertIntoLeftSubTree(node, element, isLeftHeight);
        } else { // go right
            if (isLeftHeight == null) {
                isLeftHeight = false;
            }
            return insertIntoRightSubTree(node, element, isLeftHeight);
        }
    }

    private boolean insertIntoLeftSubTree(Node<T> node, T element, boolean isLeftHeight) {
        if (node.getLeftChild() == null) {
            node.setLeftChild(Node.valueOf(element));
            increaseAfterInsert(node.getRightChild(), isLeftHeight);
            return true;
        } else {
            return insertIntoTree(node.getLeftChild(), element, isLeftHeight);
        }
    }

    private boolean insertIntoRightSubTree(Node<T> node, T element, boolean isLeftHeight) {
        if (node.getRightChild() == null) {
            node.setRightChild(Node.valueOf(element));
            increaseAfterInsert(node.getLeftChild(), isLeftHeight);
            return true;
        } else {
            return insertIntoTree(node.getRightChild(), element, isLeftHeight);
        }
    }

    private void increaseAfterInsert(Node<T> node, boolean isLeftHeight) {
        size++;
        var nodeIsNull = node == null;
        if (isLeftHeight) {
            if (nodeIsNull || leftHeight == 0) {
                leftHeight++;
            }
        } else {
            if (nodeIsNull || rightHeight == 0) {
                rightHeight++;
            }
        }
    }

    @Override
    public boolean find(T element) {
        return find(root, element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return Math.max(leftHeight, rightHeight);
    }

    private boolean find(Node<T> node, T element) {
        var compareResult = node.getElement().compareTo(element);
        if (compareResult == 0) {
            return true;
        } else if (compareResult > 0) { // go left
            return findInLeftSubTree(node, element);
        } else {
            return findInRightSubTree(node, element);
        }
    }

    private boolean findInLeftSubTree(Node<T> node, T element) {
        if (node.getLeftChild() == null) {
            return false;
        } else {
            return find(node.getLeftChild(), element);
        }
    }

    private boolean findInRightSubTree(Node<T> node, T element) {
        if (node.getRightChild() == null) {
            return false;
        } else {
            return find(node.getRightChild(), element);
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.getLeftChild(), consumer);
            consumer.accept(node.getElement());
            inOrderTraversal(node.getRightChild(), consumer);
        }
    }
}
