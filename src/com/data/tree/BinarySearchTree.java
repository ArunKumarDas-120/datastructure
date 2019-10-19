package com.data.tree;

import java.util.Objects;

public class BinarySearchTree<T> extends BaseTree<T> {

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

	@Override
	public boolean remove(T data) {
		boolean result = false;
		if (!isEmpty()) {
			Node<T> currentNode = getRoot();
			Node<T> previousNode = null;
			Node<T> nodeToRemove = new Node<>(data);
			while (Objects.nonNull(currentNode)) {
				if (currentNode.equals(nodeToRemove)) {
					if (isLeafNode(currentNode)) {/* Removing Leaf Nodes */
						if (Objects.nonNull(previousNode) && Objects.nonNull(previousNode.getLeft())
								&& previousNode.getLeft().equals(nodeToRemove)) {
							previousNode.setLeft(null);
							currentNode = null;
							result = true;
						} else if (Objects.nonNull(previousNode) && Objects.nonNull(previousNode.getRight())
								&& previousNode.getRight().equals(nodeToRemove)) {
							previousNode.setRight(null);
							currentNode = null;
							result = true;
						}
					} else {/* Removing Non Leaf Nodes */

						if (isRootNode(currentNode)) {/* Removing Node with two child */
							if (Objects.nonNull(currentNode.getRight().getLeft())) {/* With left node */
								previousNode = currentNode.getRight();
								Node<T> minValueNode = currentNode.getRight().getLeft();
								while (Objects.nonNull(minValueNode.getLeft())) {
									previousNode = minValueNode;
									minValueNode = minValueNode.getLeft();
								}
								currentNode.setData(minValueNode.getData());
								previousNode.setLeft(isLeafNode(minValueNode) ? null : minValueNode.getRight());
								result = true;
							} else {/* Leaf node or empty left node */
								currentNode.setData(currentNode.getRight().getData());
								currentNode.setRight(
										isLeafNode(currentNode.getRight()) ? null : currentNode.getRight().getRight());
								result = true;
							}
						} else {/* Removing Node with single child */
							if (Objects.nonNull(previousNode) && Objects.nonNull(previousNode.getLeft())
									&& previousNode.getLeft().equals(nodeToRemove)) {
								previousNode.setLeft(Objects.nonNull(currentNode.getLeft()) ? currentNode.getLeft()
										: Objects.nonNull(currentNode.getRight()) ? currentNode.getRight() : null);
								currentNode = null;
								result = true;
							} else if (Objects.nonNull(previousNode) && Objects.nonNull(previousNode.getRight())
									&& previousNode.getRight().equals(nodeToRemove)) {
								previousNode.setRight(Objects.nonNull(currentNode.getLeft()) ? currentNode.getLeft()
										: Objects.nonNull(currentNode.getRight()) ? currentNode.getRight() : null);
								currentNode = null;
								result = true;
							}
						}
					}
				} else {
					previousNode = currentNode;
					if (nodeToRemove.hashCode() < currentNode.hashCode())
						currentNode = currentNode.getLeft();
					else if (nodeToRemove.hashCode() > currentNode.hashCode())
						currentNode = currentNode.getRight();
				}
			}
		}
		return result;
	}

}
