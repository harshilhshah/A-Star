package controller;

import model.Node;

public class Heap<T> {

	private int size;
	private Node<T> head;
	
	public Heap(T item){
		head = new Node<T>(item,null);
		size = 0;
	}
	
	public int getSize(){
		return size;
	}
	
    public boolean isEmpty() {
        return size == 0;
    }
}
