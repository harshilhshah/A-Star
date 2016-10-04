package controller;

import model.Node;
import model.Point;

public class BST {
	
	public BST node;
	public BST left;
	public BST right;
	
	public BST(){
		node = null;
	}
	
	public BST( Node node) {
		node = node;
	}
	
	public int insert(BST node){
		BST curr = null;
		for(curr = node; curr != null; ){
			if(curr.compareTo(node) == -1){
				if(curr.left == null){
					curr.left = node;
					node.parent = curr;
					return 1;
				}else{
					curr = curr.left;
				}
			}else if(curr.compareTo(node) == 1){
				if(curr.right == null){
					curr.right = node;
					node.parent = curr;
					return 1;
				}else{
					curr = curr.right;
				}
			}else{
				if(curr.right == null){
					curr.right = node;
					node.parent = curr;
					return 0;
				}else{
					curr = curr.right;
				}
			}
		}
		return -1;
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
	
}
