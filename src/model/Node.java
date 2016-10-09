package model;

public class Node {
		
	public Node parent;
	public Node left;
	public Node right;
	private Point point;
	double f_value;
	double g_value; /*The distance from the start vertex to vertex s*/
	double h_value; /*The distance from vertex s to the goal*/
	
	public void setF_value(double f_value) {
		this.f_value = f_value;
	}

	public void setG_value(double g_value) {
		this.g_value = g_value;
	}

	public void setH_value(double h_value) {
		this.h_value = h_value;
	}

	public Node(){
		point = null;
		parent = null;
		f_value = 0.0;
		g_value = 0.0;
		h_value = 0.0;
	}
	
	public Node(Point p) {
		point = p;
	}
	
	public Node(Point p, double g, double h){
		point = p;
		g_value = g;
		h_value = h;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public void setPoint(Point p){
		this.point = p;
	}
	
	public double getF_value(){
		return f_value;
	}
	
	public double getG_value(){
		return g_value;
	}
	
	public double getH_value(){
		return h_value;
	}
	
	public int compareTo(Node node){
		return getF_value()<node.getF_value()?-1:
            getF_value()>node.getF_value()?1:0;
	/*	if(this.f_value < node.f_value)
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

		if(Math.abs(this.getF_value() - node.getF_value()) < 0.000001){
			return -1;
		}else if(Math.abs(this.getF_value() - node.getF_value()) > 0.000001){
			return 1;
		}else{ 
			if(Math.abs(this.getG_value() - node.getG_value()) < 0.000001){
				return -1;
			}else if(Math.abs(this.getG_value() - node.getG_value()) > 0.000001){
				return 1;
			}else{
				return 0;	
			}
		}*/
	}
	
	public boolean equals(Node other){
		return (this.compareTo(other) == 0 && this.getPoint().equals(other.getPoint()));
	}
	
	public String toString() {
		return point.toString();
	}
}