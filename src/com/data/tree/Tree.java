package com.data.tree;

import java.util.List;

public interface Tree<T> {

	public void insert(T data);

	public List<T> traverseInOrder();

	public List<T> traversePreOrder();

	public List<T> traversePostOrder();

	public boolean isEmpty();
}
