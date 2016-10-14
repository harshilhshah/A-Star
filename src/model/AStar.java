package model;

import java.util.ArrayList;

import visual.Box;

public abstract class AStar {
	
	public int weight;
	public short rows;
	public short cols;
	public Box[][] grid;
	
	public abstract ArrayList<Node> runAStar(Point startPoint, Point goalPoint);
	
	public abstract void updateVertex(Node s, Node sPrime, double cost, Heap open_list);
	
	public abstract void setWeight();
	
}

