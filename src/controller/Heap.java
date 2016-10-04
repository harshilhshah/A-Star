package controller;

import model.Node;

public class Heap extends BST{
	
	private int size;
	
	public Heap (Node node){
		root = node;
		size = 0;
	}
	
	public int insert(Node node){
		
		return size;
		
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