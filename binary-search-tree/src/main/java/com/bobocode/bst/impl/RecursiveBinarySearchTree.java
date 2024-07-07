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
        var inserted = insertIntoRoot(element);
        if (inserted) {
            size++;
        }
        return inserted;
    }

    private boolean insertIntoRoot(T element) {
        if (root == null) {
            root = Node.valueOf(element);
            return true;
        } else {
            return insertIntoTree(root, element);
        }
    }

    private boolean insertIntoTree(Node<T> node, T element) {
        var compareResult = node.getElement().compareTo(element);
        if (compareResult == 0) { // equals
            return false;
        } else if (compareResult > 0) { // go left
            return insertIntoLeftSubTree(node, element);
        } else { // go right
            return insertIntoRightSubTree(node, element);
        }
    }

    private boolean insertIntoLeftSubTree(Node<T> node, T element) {
        if (node.getLeftChild() == null) {
            node.setLeftChild(Node.valueOf(element));
            //increaseAfterInsert(node.getRightChild(), isLeftHeight);
            return true;
        } else {
            return insertIntoTree(node.getLeftChild(), element);
        }
    }

    private boolean insertIntoRightSubTree(Node<T> node, T element) {
        if (node.getRightChild() == null) {
            node.setRightChild(Node.valueOf(element));
            //increaseAfterInsert(node.getLeftChild(), isLeftHeight);
            return true;
        } else {
            return insertIntoTree(node.getRightChild(), element);
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
        //return Math.max(leftHeight, rightHeight);
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
        }
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
