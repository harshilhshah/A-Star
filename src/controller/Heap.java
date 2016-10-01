package controller;

import model.BST;
//This is a min heap

public class Heap<T>{
	
	private int size;
	private BST<T> head;
	
	public Heap (T item){
		head = new BST<T>(item,null);
		size = 0;
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
}