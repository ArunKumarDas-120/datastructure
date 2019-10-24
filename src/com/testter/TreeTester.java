package com.testter;

import java.util.Arrays;

import com.data.tree.AVLTree;
import com.data.tree.BinarySearchTree;
import com.data.tree.Tree;

public class TreeTester {

	public static void main(String[] args) {
		 binarySearch();
		//avl();
	}

	private static void binarySearch() {
		Tree<Integer> tree = new BinarySearchTree<>();
		tree.insert(Arrays.asList(9,1,16,0,12,17,11,13));
		tree.remove(1);
		tree.traversePreOrder().forEach(e -> System.out.print(e + " "));
	}

	private static void avl() {
		Tree<Integer> tree = new AVLTree<>();
		/*
		 * tree.insert(9); tree.insert(1); tree.insert(10); tree.insert(11);
		 * tree.insert(0); tree.insert(5); tree.insert(2); tree.insert(6);
		 * tree.remove(10);
		 */
		tree.insert(Arrays.asList(9,1,16,0,12,17,11,13));
		tree.remove(1);
		tree.traversePreOrder().forEach(e -> System.out.print(e + " "));
	}

}
