package controller;

import model.Node;

public class Heap extends BST{
	
	private int size;
	
	public Heap (Node node){
		root = node;
		size = 0;
	}
	
	public void insert(BST bst){
		
	}
	
	public Node pop(){
		findNewMin();
		return root;
	}
	
	private void findNewMin(){
		
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
}