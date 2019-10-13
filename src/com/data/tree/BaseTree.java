package com.data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

abstract class BaseTree<T> implements Tree<T> {

	private Node<T> root;

	@Override
	public boolean isEmpty() {
		return Objects.isNull(root);
	}

	@Override
	public List<T> traverseInOrder() {
		List<T> result = new ArrayList<>();
		Stack<Node<T>> nodes = new Stack<>();
		Node<T> currentNode = root;
		while (true) {
			while (Objects.nonNull(currentNode)) {
				nodes.push(currentNode);
				currentNode = currentNode.getLeft();
			}
			if (nodes.isEmpty())
				break;
			currentNode = nodes.pop();
			result.add(currentNode.getData());
			currentNode = currentNode.getRight();
		}
		return result;
	}

	@Override
	public List<T> traversePreOrder() {
		List<T> result = new ArrayList<>();
		Stack<Node<T>> nodes = new Stack<>();
		Node<T> currentNode = root;
		while (true) {
			while (Objects.nonNull(currentNode)) {
				nodes.push(currentNode);
				result.add(currentNode.getData());
				currentNode = currentNode.getLeft();
			}
			if (nodes.isEmpty())
				break;
			currentNode = nodes.pop();
			currentNode = currentNode.getRight();
		}
		return result;
	}

	@Override
	public List<T> traversePostOrder() {
		List<T> result = new ArrayList<>();
		Stack<Node<T>> nodes = new Stack<>();
		Node<T> currentNode = root;
		Node<T> previousNode = null;
		while (true) {
			while (Objects.nonNull(currentNode)) {
				nodes.push(currentNode);
				if (Objects.nonNull(currentNode.getRight()))
					nodes.push(currentNode.getRight());
				currentNode = currentNode.getLeft();
				continue;
			}
			if (nodes.isEmpty())
				break;
			Node<T> data = nodes.peek();
			if (isLeafNode(data)) {
				result.add(data.getData());
				previousNode = nodes.pop();
			} else if ((Objects.nonNull(data.getLeft()) && previousNode.equals(data.getLeft()))
					|| (Objects.nonNull(data.getRight()) && previousNode.equals(data.getRight()))) {
				result.add(data.getData());
				previousNode = nodes.pop();
			} else {
				if (Objects.nonNull(data.getRight()) && !data.getRight().equals(previousNode)) {
					nodes.push(data.getRight());
				}
				if (Objects.nonNull(data.getLeft()) && !data.getLeft().equals(previousNode)) {
					currentNode = data.getLeft();
				}
			}
		}
		return result;
	}

	boolean isLeafNode(Node<T> node) {
		return (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight()));
	}

	final Node<T> getRoot() {
		return this.root;
	}

	final void setRoot(Node<T> root) {
		this.root = root;
	}
}
