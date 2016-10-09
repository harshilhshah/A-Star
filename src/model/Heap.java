package model;

public class Heap { 
	
	public Node root;
	private int size;
	
	public Heap (Node node){
		root = node;
		size = 1;
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
	
	public boolean contains(){
		return true;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
}