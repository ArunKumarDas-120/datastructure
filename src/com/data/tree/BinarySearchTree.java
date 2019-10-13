package com.data.tree;

import java.util.Objects;

public class BinarySearchTree<T> extends BaseTree<T>{

	@Override
	public void insert(T data) {

		Node<T> newNode = new Node<>(data);
		if (isEmpty()) {
			setRoot(newNode);
		} else {
			Node<T> currentNode = getRoot();
			do {
				if (newNode.hashCode() < currentNode.hashCode()) {
					if (Objects.isNull(currentNode.getLeft())) {
						currentNode.setLeft(newNode);
						currentNode = null;
					} else
						currentNode = currentNode.getLeft();
				} else if (newNode.hashCode() > currentNode.hashCode()) {
					if (Objects.isNull(currentNode.getRight())) {
						currentNode.setRight(newNode);
						currentNode = null;
					} else
						currentNode = currentNode.getRight();
				} else {
					System.err.println("Duplicate Data Node not allowed");
					currentNode = null;
				}

			} while (Objects.nonNull(currentNode));
		}
		
	}

	

}
