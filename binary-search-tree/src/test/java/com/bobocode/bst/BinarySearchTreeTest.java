package com.bobocode.bst;

import com.bobocode.bst.impl.RecursiveBinarySearchTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void createBinarySearchTree_test() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
    }

    @Test
    void insertToEmptyTree_test() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        var inserted = bst.insert(35);

        assertTrue(inserted);
    }

    @Test
    void insertSameElementTwice_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(35);

        var insertedSecondTime = bst.insert(35);

        assertFalse(insertedSecondTime);
    }

    @Test
    void insertElements_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(35, 25, 35, 44, 21, 32);

        var insertedNewElement = bst.insert(1);
        var insertedExistingElement = bst.insert(32);
        var insertedRootElementSecondTime = bst.insert(35);

        assertTrue(insertedNewElement);
        assertFalse(insertedExistingElement);
        assertFalse(insertedRootElementSecondTime);
    }

    @Test
    void findExistingElement_test() {
        var element = 35;
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(element);

        var elementExists = bst.find(element);

        assertTrue(elementExists);
    }

    @Test
    void findNotExistingElement_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(35);

        var elementNotExists = bst.find(25);

        assertFalse(elementNotExists);
    }

    @Test
    void getSizeOnEmptyTree_test() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        var bstSize = bst.size();

        assertEquals(0, bstSize);
    }

    @Test
    void getSizeOnNotEmptyTree_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(1, 2, 3);

        var bstSize = bst.size();

        assertEquals(3, bstSize);
    }

    @Test
    void getSizeOnNotEmptyTreeWithSameElementsInConstructor_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(1, 2, 3, 3, 3, 3);

        var bstSize = bst.size();

        assertEquals(3, bstSize);
    }

    @Test
    void getHeightOnEmptyTree_test() {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();

        var bstHeight = bst.height();

        assertEquals(0, bstHeight);
    }

    @Test
    void getHeightOnTreeWithOneElement_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(1);

        var bstHeight = bst.height();

        assertEquals(1, bstHeight);
    }

    @Test
    void getHeightOnNotEmptyTree1_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(2, 1, 3);

        var bstHeight = bst.height();

        assertEquals(2, bstHeight);
    }

    @Test
    void getHeightOnNotEmptyTree2_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(22, 25, 24, 23, 1, 2, 5, 20, 10);

        var bstHeight = bst.height();

        assertEquals(6, bstHeight);
    }

    @Test
    void inOrderTraversal_test() {
        BinarySearchTree<Integer> bst = RecursiveBinarySearchTree.of(3, 25, 35, 44, 35, 21, 32);

        List<Integer> bctElements = new ArrayList<>();
        bst.inOrderTraversal(bctElements::add);

        assertThat(bctElements, contains(3, 21, 25, 32, 35, 44));
    }
}
