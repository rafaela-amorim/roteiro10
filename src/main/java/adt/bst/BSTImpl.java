package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}

	@Override
	public void insert(T element) {
		if (!isEmpty())
			insert(root, element);
	}	
	
	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> answer = null;
		BSTNode<T> node = search(element);
		
		if (!node.getRight().isEmpty()) 
			answer = minimum((BSTNode<T>) node.getRight());
		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			
			while (!parent.isEmpty() && node.equals(parent.getRight())) {
				node = parent;
				parent = (BSTNode<T>) node.getParent();
			}
			
			answer = parent;
		}
		
		return answer;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> answer = null;
		BSTNode<T> node = search(element);
		
		if (!node.getLeft().isEmpty())
			answer = maximum((BSTNode<T>) node.getLeft());
		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			
			while (!parent.isEmpty() && node.equals(parent.getLeft())) {
				node = parent;
				parent = (BSTNode<T>) node.getParent();
			}
			
			answer = parent;
		}
		
		return answer;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		
		if (!node.isEmpty()) {
			if (node.isLeaf()) 
				node.setData(null);
			else if (hasOnlyChild(node)) {
				
				if (node.getParent() != null) {
					if (node.getParent().getData().compareTo(node.getData()) > 0) {	
						if (!node.getLeft().isEmpty())
							node.getParent().setLeft(node.getLeft());
						else
							node.getParent().setLeft(node.getRight());
					} else {
						if (!node.getLeft().isEmpty()) 
							node.getParent().setRight(node.getLeft());
						else
							node.getParent().setRight(node.getRight());
					}
					
				} else {
					if (node.getLeft().isEmpty())
						root = (BSTNode<T>) node.getRight();
					else
						root = (BSTNode<T>) node.getLeft();
				}
				
			} else {	// complete node
				BSTNode<T> sucessor = sucessor(element);
				node.setData(sucessor.getData());
				remove(sucessor.getData());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		ArrayList<T> list = new ArrayList<>();
		preOrder(list, root);
		T[] array = (T[]) list.toArray(new Comparable[list.size()]);
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		ArrayList<T> list = new ArrayList<>();
		order(list, root);
		T[] array = (T[]) list.toArray(new Comparable[list.size()]);
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		ArrayList<T> list = new ArrayList<>();
		postOrder(list, root);
		T[] array = (T[]) list.toArray(new Comparable[list.size()]);
		return array;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
	// Métodos Auxiliares
	
	private void insert(BSTNode<T> node, T elem) {
		if (node.isEmpty()) {
			node.setData(elem);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(elem) > 0)
				insert((BSTNode<T>) node.getLeft(), elem);
			else
				insert((BSTNode<T>) node.getRight(), elem);
		}
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> answer = null;
		
		if (node.isEmpty() || node.getData().equals(element))
			answer = node;
		else {
			if (node.getData().compareTo(element) > 0)
				answer = search((BSTNode<T>) node.getLeft(), element);
			else
				answer = search((BSTNode<T>) node.getRight(), element);
		}
		
		return answer;
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> answer = null;
				
		while (!node.isEmpty()) {
			answer = node;
			node = (BSTNode<T>) node.getLeft();
		}
		
		return answer;
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> answer = null;
		
		while (!node.isEmpty()) {
			answer = node;
			node = (BSTNode<T>) node.getRight();
		}
		
		return answer;
	}
	
	private void preOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			preOrder(list, (BSTNode<T>) node.getLeft());
			preOrder(list, (BSTNode<T>) node.getRight());
		}
	}
	
	private void order(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			order(list, (BSTNode<T>) node.getLeft());
			list.add(node.getData());
			order(list, (BSTNode<T>) node.getRight());
		}
	}
	
	private void postOrder(ArrayList<T> list, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(list, (BSTNode<T>) node.getLeft());
			postOrder(list, (BSTNode<T>) node.getRight());
			list.add(node.getData());
		}
	}
	
	private boolean hasOnlyChild(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && !node.getRight().isEmpty()) || 
			   (!node.getLeft().isEmpty() && node.getRight().isEmpty());
	}
}





















