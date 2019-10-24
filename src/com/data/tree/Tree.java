package com.data.tree;

import java.util.List;
import java.util.Optional;

public interface Tree<T> {

	public void insert(T data);
	
	public void insert(List<T> data);

	public List<T> traverseInOrder();

	public List<T> traversePreOrder();

	public List<T> traversePostOrder();

	public boolean isEmpty();
	
	public boolean contains(T data);
	
	public Optional<T> search(T data);
	
	public boolean remove(T data);
}
