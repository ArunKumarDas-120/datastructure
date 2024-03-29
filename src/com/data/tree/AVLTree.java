package com.data.tree;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class AVLTree<T> extends BaseTree<T> {

	@Override
	public void insert(T data) {
		Node<T> newNode = new Node<>(data);
		if (isEmpty()) {
			setRoot(newNode);
		} else {
			Node<T> currentNode = getRoot();
			Stack<Node<T>> ancestors = new Stack<>();
			ancestors.push(currentNode);
			while (Objects.nonNull(currentNode)) {
				if (newNode.hashCode() < currentNode.hashCode()) {
					if (Objects.isNull(currentNode.getLeft())) {
						currentNode.setLeft(newNode);
						currentNode = null;
					} else {
						currentNode = currentNode.getLeft();
						ancestors.push(currentNode);
					}
				} else if (newNode.hashCode() > currentNode.hashCode()) {
					if (Objects.isNull(currentNode.getRight())) {
						currentNode.setRight(newNode);
						currentNode = null;
					} else {
						currentNode = currentNode.getRight();
						ancestors.push(currentNode);
					}
				} else {
					System.err.println("Duplicate Data Node not allowed");
					currentNode = null;
				}
			}

			rebalance(newNode, ancestors, false);
		}

	}

	@Override
	public void insert(List<T> data) {
		data.forEach(d -> insert(d));
	}

	/* Rebalancing the tree */
	private void rebalance(Node<T> newlyInsertedNode, Stack<Node<T>> ancestors, boolean rebalanceOnDelete) {
		while (!ancestors.isEmpty()) {
			Node<T> temp = ancestors.pop();
			updateHeight(temp);
			int balanceFactor = getBalanceFactor(temp);
			/* Left heavy */
			if (balanceFactor > 1) {
				if (rebalanceOnDelete ? getBalanceFactor(temp.getLeft()) >= 0
						: newlyInsertedNode.hashCode() < temp.getLeft().hashCode()) {
					if (ancestors.isEmpty())
						setRoot(rotateRight(temp));
					else {
						Node<T> temp1 = ancestors.pop();
						if (Objects.nonNull(temp1.getRight()) && temp1.getRight().equals(temp))
							temp1.setRight(rotateRight(temp));
						else
							temp1.setLeft(rotateRight(temp));
					}

				} else if (rebalanceOnDelete ? getBalanceFactor(temp.getLeft()) < 0
						: newlyInsertedNode.hashCode() > temp.getLeft().hashCode()) {
					temp.setLeft(rotateLeft(temp.getLeft()));
					if (ancestors.isEmpty())
						setRoot(rotateRight(temp));
					else {
						Node<T> temp1 = ancestors.pop();
						if (Objects.nonNull(temp1.getRight()) && temp1.getRight().equals(temp))
							temp1.setRight(rotateRight(temp));
						else
							temp1.setLeft(rotateRight(temp));
					}
				}
			}
			/* Right heavy */
			if (balanceFactor < -1) {
				if (rebalanceOnDelete ? getBalanceFactor(temp.getRight()) <= 0
						: newlyInsertedNode.hashCode() > temp.getRight().hashCode()) {
					if (ancestors.isEmpty())
						setRoot(rotateLeft(temp));
					else {
						Node<T> temp1 = ancestors.pop();
						if (Objects.nonNull(temp1.getRight()) && temp1.getRight().equals(temp))
							temp1.setRight(rotateLeft(temp));
						else
							temp1.setLeft(rotateLeft(temp));
					}
				} else if (rebalanceOnDelete ? getBalanceFactor(temp.getRight()) > 0
						: newlyInsertedNode.hashCode() < temp.getRight().hashCode()) {
					temp.setRight(rotateRight(temp.getRight()));
					if (ancestors.isEmpty())
						setRoot(rotateLeft(temp));
					else {
						Node<T> temp1 = ancestors.pop();
						if (Objects.nonNull(temp1.getRight()) && temp1.getRight().equals(temp))
							temp1.setRight(rotateLeft(temp));
						else
							temp1.setLeft(rotateLeft(temp));
					}
				}
			}

		}
	}

	@Override
	public boolean remove(T data) {
		Stack<Node<T>> ancestors = new Stack<>();
		boolean result = deleteNode(new Node<>(data), ancestors);
		if (result)
			rebalance(null, ancestors, true);
		return result;
	}

	/* Updating height of Node */
	private void updateHeight(Node<T> node) {
		node.setHeight(1 + Math.max(calulateHeight(node.getLeft()), calulateHeight(node.getRight())));
	}

	/* calculate balance factor */
	private int getBalanceFactor(Node<T> node) {
		return calulateHeight(node.getLeft()) - calulateHeight(node.getRight());
	}

	private int calulateHeight(Node<T> node) {
		return Objects.isNull(node) ? 0 : node.getHeight();
	}

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> n1 = node.getRight();
		Node<T> n2 = n1.getLeft();
		n1.setLeft(node);
		node.setRight(n2);
		updateHeight(node);
		updateHeight(n1);
		return n1;
	}

	private Node<T> rotateRight(Node<T> node) {
		Node<T> n1 = node.getLeft();
		Node<T> n2 = n1.getRight();
		n1.setRight(node);
		node.setLeft(n2);
		updateHeight(node);
		updateHeight(n1);
		return n1;
	}

}
