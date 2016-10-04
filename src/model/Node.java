package model;

public class Node {
		
	public Node left;
	public Node right;
	public Node parent;
	private Point point;
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
	
	public Point getPoint(){
		return point;
	}
	
	public float getF_value(){
		return 0.0f;
	}
	
	public int compareTo(Node node){
		if(this.f_value < node.f_value)
			return -1;
		else if(Math.abs(this.f_value - node.f_value) < 0.0001){
			if(this.g_value < node.g_value)
				return -1;
			else if(Math.abs(this.g_value - node.g_value) < 0.0001){
				if(this.h_value < node.h_value)
					return -1;
				else if(Math.abs(this.h_value - node.h_value) < 0.0001)
					return 0;
				else
					return 1;
			}else{
				return 1;
			}
		}else
			return 1;
	}
	
	public String toString() {
		return String.valueOf(f_value);
	}
}