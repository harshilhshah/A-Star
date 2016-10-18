package model;

import controller.Utility;
import visual.Box;
import visual.Grid;

public class IntegratedAStar extends AStar{
	
	private double w1;
	private double w2;
	private int num = 0;
	private AStar[] searches;
	private boolean[][][] closed_list;
	private Point goalPoint;

	public IntegratedAStar(Box[][] grid, double weight1, double weight2) {
		super(grid);
		num = 5;
		w1 = weight1;
		w2 = weight2;
	}
	
	@Override
	public Node runAStar(Point startPoint, Point goalP){
		goalPoint = goalP;
		closed_list = new boolean[2][rows][cols];
		searches = new AStar[num];
		searches[0] = new RegularAStar(grid, HeuristicType.EUCLIDEAN);
		searches[1] = new RegularAStar(grid, HeuristicType.MANHATTAN);
		searches[2] = new RegularAStar(grid, HeuristicType.BORDERPOINT);
		searches[3] = new RegularAStar(grid, HeuristicType.CENTER);
		searches[4] = new RegularAStar(grid, HeuristicType.AVOIDH2T);
		grid[goalPoint.getY()][goalPoint.getX()].getNode().setG_value(Integer.MAX_VALUE);
		
		for(int i = 0; i < num; i++){
			Utility.pointToNode(grid, startPoint).setH_value(searches[i].hType.getDistance(grid,startPoint, goalPoint)*weight);
			searches[i].open_list.add(Utility.pointToNode(grid, startPoint),key(startPoint,i));
			searches[i].closed_list = null;
		}
		
		while(searches[0].open_list.MinKey() < Integer.MAX_VALUE){
			for(int i = 1; i < num; i++){
				if(searches[i].open_list.MinKey() <= w2 * searches[0].open_list.MinKey()){
					if(grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= searches[0].open_list.MinKey()){
						if(grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() < Integer.MAX_VALUE){
							return grid[goalPoint.getY()][goalPoint.getX()].getNode().parent;
						}
					}
					else{
						Node s = searches[i].open_list.Top();
						ExpandState(s);
						int cx = s.getPoint().getX();
			    		int cy = s.getPoint().getY();
			    		closed_list[1][cy][cx] = true; 
					}
				}
				else{
					if(grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() <= searches[0].open_list.MinKey()){
						if(grid[goalPoint.getY()][goalPoint.getX()].getNode().getG_value() < Integer.MAX_VALUE){
							return grid[goalPoint.getY()][goalPoint.getX()].getNode().parent;
						}
					}
					else{
						Node s = searches[0].open_list.Top();
						ExpandState(s);
						int cx = s.getPoint().getX();
			    		int cy = s.getPoint().getY();
			    		closed_list[0][cy][cx] = true;
					}
				}
			}
		}
		
		return null;
	}
	
	private void ExpandState(Node s){
		System.out.println(searches[0].open_list.getSize());
		for(int i = 0; i < num; i++){
			searches[i].open_list.remove(s);
		}
		int cx = s.getPoint().getX();
		int cy = s.getPoint().getY();
		
		for(Neighbor n: Neighbor.neighnbors){

			
			if(cx+n.getXChange() < 0 || cx+n.getXChange() >= cols || cy+n.getYChange() < 0 || cy+n.getYChange() >= rows){ /*checking for out of bounds*/
				continue;
			}
			else if(grid[cy+n.getYChange()][cx+n.getXChange()].getTerrain() == Terrain.BLOCKED_CELL){ /*checking for blocked cells*/
				continue;
			}
			else if(closed_list[0][cy+n.getYChange()][cx+n.getXChange()] || closed_list[1][cy+n.getYChange()][cx+n.getXChange()]){
				continue;
			}
			
			Node sPrime = grid[cy+n.getYChange()][cx+n.getXChange()].getNode();
			double cost = Utility.getCost(grid[s.getPoint().getY()][s.getPoint().getX()], grid[sPrime.getPoint().getY()][sPrime.getPoint().getX()], n.isDiagonal()); 
			
			boolean b = false;
			for(int i = 0; i < num; i++)
				if(searches[i].open_list.contains(sPrime))
					b = true;
			if(!b){
				sPrime.setG_value(Integer.MAX_VALUE); 
				sPrime.parent = null;
			}

			if(s.getG_value() + cost < sPrime.getG_value()){	

				sPrime.setG_value(s.getG_value() + cost);
				sPrime.parent = s;

				if(searches[0].open_list.contains(sPrime)){
					searches[0].open_list.remove(sPrime);
				}
				searches[0].open_list.add(sPrime,key(sPrime.getPoint(),0));
				if(!closed_list[1][cy+n.getYChange()][cx+n.getXChange()]){
					for(int i = 1; i < num; i++){
						if(key(sPrime.getPoint(),i) <= w2 * key(sPrime.getPoint(),0)){
							sPrime.setH_value(searches[i].hType.getDistance(grid,sPrime.getPoint(), goalPoint)*weight);
							searches[i].updateVertex(s,sPrime,cost);
						}
					}
				}
			}
		}	
	}
	
	private double key(Point s, int i){
		return grid[s.getY()][s.getX()].getNode().getG_value() + (w1 * searches[i].grid[s.getY()][s.getX()].getNode().getH_value());
	}
}
