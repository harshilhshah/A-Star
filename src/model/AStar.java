package model;

import java.util.ArrayList;

import visual.Grid;

public abstract class AStar {
	
	public int weight;
	public Grid grid;
	
	public abstract ArrayList<Node> runAStar();
	
	public abstract void updateVertex(Node s, Node sPrime, double cost, Heap open_list);
	
	public abstract void setWeight();
	
}

