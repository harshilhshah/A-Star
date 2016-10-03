package model;

public class Node {
		
	public Node left;
	public Node right;
	public Node parent;
	public Point point;
	public double f_value, g_value, h_value;
	
	public Node(){
		point = null;
		parent = null;
		left = null;
		right = null;
		f_value = 0.0;
		g_value = 0.0;
		h_value = 0.0;
	}
	
	public Node( Node left, Node right, Node parent) {
	
//		this.next = next;
	}
	
	public float getF_value(){
		return 0.0f;
	}
	
	public String toString() {
		return String.valueOf(f_value);
	}
}