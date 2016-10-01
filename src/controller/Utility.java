package controller;

import java.util.ArrayList;
import java.util.Random;

import visual.Box;
import visual.Grid;
import model.Direction;
import model.Point;

public class Utility{
	
	public static final Random random = new Random();
	
	public static ArrayList<Point> generateRandomPoints(int numOfPoints){
		
		ArrayList<Point> points = new ArrayList<>(numOfPoints);
		
		for(int i = 0; i < numOfPoints; i++){
			Point randPoint = generateRandomPoint();
			if(!randPoint.existIn(points))
				points.add(randPoint);
			else
				i--;
		}

		return points;
		
	}
	
	public static ArrayList<Point> generateBorderPoints(){
		
		ArrayList<Point> points = new ArrayList<>(552);
		
		for(int i = 1; i < Grid.cols-1; i++)	//top layer stagnant row, move col
			points.add(new Point(i,0));
		for(int j = 1; j < Grid.cols-1; j++)	//bottom layer stagnant row, move col
			points.add(new Point(j,Grid.rows-1)); 
		for(int k = 1; k < Grid.rows-1; k++)	//left layer move row, stagnant col
			points.add(new Point(0,k)); 
		for(int l = 1; l < Grid.rows-1; l++)	//right layer move row, stagnant col
			points.add(new Point(Grid.cols-1,l)); 
		
		return points;
	}
	
	public static Point generateRandomPointInRange(ArrayList<Point> points){
		return points.get(random.nextInt(points.size()));
	}
	
	public static Direction chooseNextDirection(Direction direction){

		int val = random.nextInt(100);
		if(direction == Direction.UP){
			if(val < 21)
				direction = Direction.LEFT;
			else if(val > 20 && val < 81)
				direction = Direction.UP;
			else if(val > 80)
				direction = Direction.RIGHT;
		}else if(direction == Direction.DOWN){
			if(val < 21)
				direction = Direction.LEFT;
			else if(val > 20 && val < 81)
				direction = Direction.DOWN;
			else if(val > 80)
				direction = Direction.RIGHT;
		}else if(direction == Direction.LEFT){
			if(val < 21)
				direction = Direction.DOWN;
			else if(val > 20 && val < 81)
				direction = Direction.LEFT;
			else if(val > 80)
				direction = Direction.UP;
		}else if(direction == Direction.RIGHT){
			if(val < 21)
				direction = Direction.UP;
			else if(val > 20 && val < 81)
				direction = Direction.RIGHT;
			else if(val > 80)
				direction = Direction.DOWN;
		}else{
			direction = Direction.UNDECIDED;
		}
		return direction;
	}
	
	public static boolean randomBoolean(){
		return random.nextBoolean();
	}
	
	public static Box[][] readFile(String filepath){
		// Harshil will write code here.
		return null;
	}
	
	public static void writeFile(String filepath){
		
	}
	
	private static Point generateRandomPoint(){
		return new Point(random.nextInt(Grid.cols),random.nextInt(Grid.rows));
	}
	
	
	
}
