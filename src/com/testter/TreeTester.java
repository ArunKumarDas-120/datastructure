package com.testter;

import java.util.Arrays;

import com.data.tree.AVLTree;
import com.data.tree.BinarySearchTree;
import com.data.tree.Tree;

public class TreeTester {

	public static void main(String[] args) {
		binarySearch();
		avl();
	}

	private static void binarySearch() {
		Tree<Integer> tree = new BinarySearchTree<>();
		tree.insert(Arrays.asList(9,1,16,0,12,18,11,13,17,19));
		tree.traverseLevelOrder().forEach(e -> System.out.print(e + " "));
	}

	private static void avl() {
		Tree<Integer> tree = new AVLTree<>();
		tree.insert(Arrays.asList(9,1,16,0,12,17,11,13));
		tree.traverseLevelOrder().forEach(e -> System.out.print(e + " "));
	}

}
