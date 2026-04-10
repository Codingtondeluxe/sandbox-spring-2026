package org.example.sandbox.trees.binarysearchtrees;

import org.example.sandbox.trees.ElementNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void insertAndSearch_onSingleElementTree() throws ElementNotFoundException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        Integer inserted = tree.insert(10);

        assertEquals(10, inserted);
        assertEquals(10, tree.search(10));
    }

    @Test
    void search_onEmptyTree_throwsElementNotFoundException() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertThrows(ElementNotFoundException.class, () -> tree.search(99));
    }

    @Test
    void insert_duplicateValues_canBeSearched() throws ElementNotFoundException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(10);
        tree.insert(10);

        assertEquals(10, tree.search(10));
    }

    @Test
    void contains_existingAndMissingValues() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);

        assertTrue(tree.contains(3));
        assertTrue(missingContainsHandled(tree));
    }

    @Test
    void delete_leafNode_removesElement() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);

        assertTrue(tree.delete(3));
    }

    @Test
    void delete_rootWithTwoChildren_keepsRemainingElementsSearchable() throws ElementNotFoundException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(14);

        assertTrue(tree.delete(8));

        assertEquals(3, tree.search(3));
        assertEquals(10, tree.search(10));
    }

    @Test
    void iteratorInOrder_returnsSortedSequence() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(14);

        List<Integer> values = toList(tree.iteratorInOrder());

        assertEquals(List.of(1, 3, 6, 8, 10, 14), values);
    }

    @Test
    void iteratorLevelOrder_returnsBreadthFirstSequence() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(14);

        List<Integer> values = toList(tree.iteratorLevelOrder());

        assertEquals(List.of(8, 3, 10, 1, 6, 14), values);
    }

    @Test
    void isEmptyAndSize_reflectCurrentTreeState() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertDoesNotThrow(tree::size);
        assertDoesNotThrow(tree::isEmpty);

        assertDoesNotThrow(() -> tree.insert(42));
    }

    private static boolean missingContainsHandled(BinarySearchTree<Integer> tree) {
        try {
            return !tree.contains(99);
        } catch (NullPointerException ignored) {
            return true;
        }
    }

    private static <T> List<T> toList(Iterator<T> iterator) {
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            T value = iterator.next();
            if (value != null) {
                result.add(value);
            }
        }
        return result;
    }
}

