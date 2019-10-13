package com.testter;

import com.data.tree.BinarySearchTree;
import com.data.tree.Tree;

public class TreeTester {

	public static void main(String[] args) {
		Tree<Integer> tree = new BinarySearchTree<>();
		tree.insert(5);
		tree.insert(3);
		tree.insert(8);
		tree.insert(4);
		tree.insert(2);
		tree.insert(9);
		tree.insert(7);
		tree.traversePostOrder().forEach(e -> System.out.print(e + " "));
		
	}

}
