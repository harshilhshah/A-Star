package model;

import controller.Utility;
import visual.Box;

public class SequentialAStar extends AStar {
	
	private double w1;
	private double w2;
	private AStar[] searches;

	public SequentialAStar(Box[][] grid, double weight1, double weight2) {
		super(grid);
		w1 = weight1;
		w2 = weight2;
	}
	
	@Override
	public Node runAStar(Point startPoint, Point goalPoint){
		
		int n = 5;
		searches = new AStar[n];
		searches[0] = new RegularAStar(grid.clone(), HeuristicType.EUCLIDEAN);
		searches[1] = new RegularAStar(grid.clone(), HeuristicType.MANHATTAN);
		searches[2] = new RegularAStar(grid.clone(), HeuristicType.BORDERPOINT);
		searches[3] = new RegularAStar(grid.clone(), HeuristicType.CENTER);
		searches[4] = new RegularAStar(grid.clone(), HeuristicType.AVOIDH2T);
		
		for(int i = 0; i < n; i++){
			searches[i].grid[goalPoint.getY()][goalPoint.getX()].getNode().setG_value(Integer.MAX_VALUE);
			double k = key(startPoint,i); 
			searches[i].open_list.add(Utility.pointToNode(searches[i].grid, startPoint),k);
		}
		
		while(searches[0].open_list.MinKey() < Integer.MAX_VALUE){
			for(int i = 1; i < n; i++){
				if(searches[i].open_list.MinKey() <= w2 * searches[0].open_list.MinKey()){
					if(searches[i].grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= searches[0].open_list.MinKey()){
						if(searches[i].grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() < Integer.MAX_VALUE){
							return searches[i].grid[goalPoint.getY()][goalPoint.getX()].getNode().parent;
						}
					}
					else{
						Node s = searches[i].open_list.Top();
						ExpandState(s,i);
						int cx = s.getPoint().getX();
			    		int cy = s.getPoint().getY();
			    		searches[i].closed_list[cy][cx] = true; 
			    		numNodesExpanded++;
					}
				}
				else{
					if(searches[0].grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= searches[0].open_list.MinKey()){
						if(searches[0].grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() < Integer.MAX_VALUE){
							return searches[0].grid[goalPoint.getY()][goalPoint.getX()].getNode().parent;
						}
					}
					else{
						Node s = searches[0].open_list.Top();
						ExpandState(s,0);
						int cx = s.getPoint().getX();
			    		int cy = s.getPoint().getY();
			    		searches[0].closed_list[cy][cx] = true;
			    		numNodesExpanded++;
					}
				}
			}
		}
		
		return null;
	}
	
	private void ExpandState(Node s, int i){
		searches[i].open_list.remove(s);
		int cx = s.getPoint().getX();
		int cy = s.getPoint().getY();
		for(Neighbor n: Neighbor.neighnbors){
			if(cx+n.getXChange() < 0 || cx+n.getXChange() >= cols || cy+n.getYChange() < 0 || cy+n.getYChange() >= rows){ /*checking for out of bounds*/
				continue;
			}
			else if(grid[cy+n.getYChange()][cx+n.getXChange()].getTerrain() == Terrain.BLOCKED_CELL){ /*checking for blocked cells*/
				continue;
			}
			else if(searches[i].closed_list[cy+n.getYChange()][cx+n.getXChange()]){ /*checking if already visited this point before*/
				continue;
			}
			else{
				Node sPrime = grid[cy+n.getYChange()][cx+n.getXChange()].getNode();
				if(!searches[i].open_list.contains(sPrime)){
					sPrime.setF_value(Integer.MAX_VALUE); 
					sPrime.parent = null;
				}
				double cost = Utility.getCost(grid[s.getPoint().getY()][s.getPoint().getX()], grid[sPrime.getPoint().getY()][sPrime.getPoint().getX()], n.isDiagonal()); 
				searches[i].updateVertex(s, sPrime, cost);
			}
		}	
	}
	
	private double key(Point s, int i){
		return searches[i].grid[s.getY()][s.getX()].getNode().getG_value() + (w1 * searches[i].grid[s.getY()][s.getX()].getNode().getH_value());
	}
}
