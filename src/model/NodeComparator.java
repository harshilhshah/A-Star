package model;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{

	@Override
	public int compare(Node n1, Node n2) {
		if(n1.f_value < n2.f_value)
			return -1;
		else if(Math.abs(n1.f_value - n2.f_value) < 0.0001){
			if(n1.g_value < n2.g_value)
				return -1;
			else if(Math.abs(n1.g_value - n2.g_value) < 0.0001){
				if(n1.h_value < n2.h_value)
					return -1;
				else if(Math.abs(n1.h_value - n2.h_value) < 0.0001)
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
