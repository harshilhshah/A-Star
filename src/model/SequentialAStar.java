package model;

import controller.Utility;
import visual.Box;

public class SequentialAStar extends AStar {
	
	private Box[][][] grids;
	private double w1;
	private double w2;
	private Heap[] open_lists;
	private boolean[][][] closed_lists;

	public SequentialAStar(Box[][] grid) {
		super(grid);
	}
	
	@Override
	public Node runAStar(Point startPoint, Point goalPoint){
		
		int n = 3; // Not sure how to set this
		open_lists = new Heap[n];
		grids = new Box[n][][];
		closed_lists = new boolean[n][rows][cols];
		
		for(int i = 0; i < n; i++){
			open_lists[i] = new Heap();
			grids[i] = grid.clone();
			grids[i][goalPoint.getY()][goalPoint.getX()].getNode().setG_value(Integer.MAX_VALUE);
			double k = key(startPoint,i); // need to pass this key
			open_lists[i].add(Utility.pointToNode(grids[i], startPoint),k);
		}
		
		while(open_lists[0].MinKey() < Integer.MAX_VALUE){
			for(int i = 1; i < n; i++){
				if(open_lists[i].MinKey() <= w2 * open_lists[0].MinKey()){
					if(grids[i][goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= open_lists[0].MinKey()){
						if(grids[i][goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= Integer.MAX_VALUE){
							// return path pointed by bpi(sgoal);
							break;
						}
					}
					else{
						Node s = open_lists[i].Top();
						ExpandState(s,i);
						int cx = s.getPoint().getX();
			    		int cy = s.getPoint().getY();
			    		closed_lists[i][cy][cx] = true; 
					}
				}
				else{
					if(grids[0][goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= open_lists[0].MinKey()){
						if(grids[0][goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= Integer.MAX_VALUE){
							// return path pointed by bp0(sgoal);
							break;
						}
					}
					else{
						Node s = open_lists[0].Top();
						ExpandState(s,0);
						int cx = s.getPoint().getX();
			    		int cy = s.getPoint().getY();
			    		closed_lists[0][cy][cx] = true;
					}
				}
			}
		}
		
		return null;
	}
	
	private void ExpandState(Node s, int i){
		open_lists[i].remove(s);
		int cx = s.getPoint().getX();
		int cy = s.getPoint().getY();
		for(Neighbor n: Neighbor.neighnbors){
			if(cx+n.getXChange() < 0 || cx+n.getXChange() >= cols || cy+n.getYChange() < 0 || cy+n.getYChange() >= rows){ /*checking for out of bounds*/
				continue;
			}
			else if(grid[cy+n.getYChange()][cx+n.getXChange()].getTerrain() == Terrain.BLOCKED_CELL){ /*checking for blocked cells*/
				continue;
			}
			else if(closed_lists[i][cy+n.getYChange()][cx+n.getXChange()]){ /*checking if already visited this point before*/
				continue;
			}
			else{
				Node sPrime = grid[cy+n.getYChange()][cx+n.getXChange()].getNode();
				if(!open_lists[i].contains(sPrime)){
					sPrime.setF_value(Integer.MAX_VALUE); 
					sPrime.parent = null;
				}
				double cost = Utility.getCost(grid[s.getPoint().getY()][s.getPoint().getX()], grid[sPrime.getPoint().getY()][sPrime.getPoint().getX()], n.isDiagonal()); 
				updateVertex(s, sPrime, cost, open_lists[i]);
			}
		}	
	}
	
	private double key(Point s, int i){
		return grids[i][s.getY()][s.getX()].getNode().getG_value() + (w1 * grids[i][s.getY()][s.getX()].getNode().getH_value());
	}
}
