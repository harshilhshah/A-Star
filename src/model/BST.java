package model;

public class BST<T> {
	
	private T data;
	private BST<T> left;
	private BST<T> right;
	
	public BST(T data, BST<T> next) {
		this.data = data;
//		this.next = next;
	}
	
	public String toString() {
		return data.toString();
	}
}
