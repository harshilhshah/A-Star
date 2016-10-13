package model;

import java.util.ArrayList;

import controller.Utility;
import visual.Grid;
import visual.HomeScreen;

public class UniformCostSearch extends AStar{

	public UniformCostSearch(Grid g, Point startPoint, Point goalPoint){
		this.grid = g;
	}
	
	@Override
    public ArrayList<Node> runAStar(){
    	ArrayList<Node> aStarNodeSolution = new ArrayList<Node>();
    	Heap open_list = new Heap();
    	Node curr = grid[startPoint.getY()][startPoint.getX()].getNode();
    	curr.setH_value(Utility.getDistance(startPoint, goalPoint));
    	open_list.add(curr); 
    	boolean[][] closed_list = new boolean[rows][cols];
    	int i = 0;
    	
    	while(!open_list.isEmpty()){
    		curr = open_list.pop();
    		if(curr.getPoint().equals(goalPoint) || i == 200000){
    			aStarNodeSolution.add(curr);;
    			break;
    		}/*Path found!*/
    		int cx = curr.getPoint().getX();
    		int cy = curr.getPoint().getY();
    		closed_list[cy][cx] = true; /*marked that point as visited, added to closed list*/
  //  		System.out.println("Current Vertex: " + curr.toString());
    		
    		//Find 8 surrounding neighbors
    		for(Neighbor n: Neighbor.neighnbors){
    			if(cx+n.getXChange() < 0 || cx+n.getXChange() >= cols || cy+n.getYChange() < 0 || cy+n.getYChange() >= rows){ /*checking for out of bounds*/
  //  				System.out.println("OutOfBounds at:("+ (cy+n.getYChange()) + "," + (cx+n.getXChange()) +")");
    				continue;
    			}
    			else if(grid[cy+n.getYChange()][cx+n.getXChange()].getTerrain() == Terrain.BLOCKED_CELL){ /*checking for blocked cells*/
  //  				System.out.println("Blocked cell at:("+ (cy+n.getYChange()) + "," + (cx+n.getXChange()) +")");
    				continue;
    			}
    			else if(closed_list[cy+n.getYChange()][cx+n.getXChange()]){ /*checking if already visited this point before*/
 //   				System.out.println("Already visited:("+ (cy+n.getYChange()) + "," + (cx+n.getXChange()) + ")");
    				continue;
    			}
    			else{
    				Node sPrime = grid[cy+n.getYChange()][cx+n.getXChange()].getNode();
    				sPrime.setH_value(Utility.getDistance(goalPoint, sPrime.getPoint()));
    				if(!open_list.contains(sPrime)){
    					sPrime.setF_value(Integer.MAX_VALUE); 
    					sPrime.parent = null;
    				}
    				double cost = Utility.getCost(grid[curr.getPoint().getY()][curr.getPoint().getX()], grid[sPrime.getPoint().getY()][sPrime.getPoint().getX()], n.isDiagonal()); 
    				updateVertex(curr, sPrime, cost, open_list);
 //   				System.out.println("Neighbor Vertex: " + sPrime.toString());
    			}
    		}
    		i++;		
    	}
    	
    	System.out.println("Done");
    	if(aStarNodeSolution.size() == 0) aStarNodeSolution.add(curr);
    	Node nod = aStarNodeSolution.get(0);
    	while(nod != null){
    		aStarSolution.add(nod.getPoint());
    		nod = nod.parent;
    	}
    	System.out.println(aStarSolution.toString());
//    	System.out.println(open_list.toString());
    	HomeScreen.display("Drawing the path now.");
    	repaint();
    }

	@Override
    public void updateVertex(Node s, Node sPrime, double cost, Heap open_list){
    	if(s.getF_value() + cost + sPrime.getH_value() < sPrime.getF_value()){
    		sPrime.setG_value(s.getG_value() + cost);
    		sPrime.setF_value(sPrime.getG_value() + sPrime.getH_value());
    		sPrime.parent = s;
    		if(open_list.contains(sPrime)){
    			open_list.remove(sPrime);
    		}
    		open_list.add(sPrime);
    	}
    }

	@Override
	public void setWeight() {
		// TODO Auto-generated method stub
		
	}

}
