package com.testter;

import com.data.tree.AVLTree;
import com.data.tree.BinarySearchTree;
import com.data.tree.Tree;

public class TreeTester {

	public static void main(String[] args) {
		// binarySearch();
		avl();
	}

	private static void binarySearch() {
		Tree<Integer> tree = new BinarySearchTree<>();
		tree.insert(60);
		tree.insert(50);
		tree.insert(70);
		tree.insert(30);
		tree.insert(55);
		tree.insert(65);
		tree.insert(80);
		tree.insert(20);
		tree.insert(31);
		tree.insert(52);
		tree.insert(51);
		tree.insert(57);
		tree.insert(63);
		tree.insert(66);
		tree.insert(75);
		tree.insert(81);
		tree.remove(50);
		tree.traversePostOrder().forEach(e -> System.out.print(e + " "));
	}

	private static void avl() {
		Tree<Integer> tree = new AVLTree<>();
		tree.insert(60);
		tree.insert(50);
		tree.insert(70);
		tree.insert(30);
		tree.insert(52);
		tree.insert(65);
		tree.insert(80);
		tree.insert(20);
		tree.insert(31);
		tree.insert(55);
		tree.insert(54);
		tree.insert(53);
		tree.traversePreOrder().forEach(e -> System.out.print(e + " "));
	}

}
