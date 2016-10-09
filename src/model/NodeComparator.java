package model;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node n1, Node n2) {
		return n1.getF_value()<n2.getF_value()?-1:
            n1.getF_value()>n2.getF_value()?1:0;
		/*if(n1.getPoint().equals(n2.getPoint()))
			return 0;
		if(n1.getPoint().getX() > n2.getPoint().getX())
			return 1;
		else
			return -1;
		return n1.getF_value()<n2.getF_value()?-1:
            n1.getF_value()>n2.getF_value()?1:0; 
		if(Math.abs(n1.getF_value() - n2.getF_value()) < 0.000001){
			return -1;
		}else if(Math.abs(n1.getF_value() - n2.getF_value()) > 0.000001){
			return 1;
		}else{ 
			if(Math.abs(n1.getG_value() - n2.getG_value()) < 0.000001){
				return -1;
			}else if(Math.abs(n1.getG_value() - n2.getG_value()) > 0.000001){
				return 1;
			}else{
				return 0;	
			}
		}*/
	}

}
