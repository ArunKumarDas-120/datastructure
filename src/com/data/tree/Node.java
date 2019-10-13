package com.data.tree;

final class Node<T> {
	
	private T data;
	private Node<T> left;
	private Node<T> right;
	private int height;
	
	public Node(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.height = 0;
	}

	protected T getData() {
		return data;
	}

	protected void setData(T data) {
		this.data = data;
	}

	protected Node<T> getLeft() {
		return left;
	}

	protected void setLeft(Node<T> left) {
		this.left = left;
	}

	protected Node<T> getRight() {
		return right;
	}

	protected void setRight(Node<T> right) {
		this.right = right;
	}

	protected int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node<?> other = (Node<?>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
