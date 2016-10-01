package controller;

import java.util.ArrayList;
import java.util.Random;

import visual.Box;
import visual.Grid;
import model.Direction;
import model.Point;

public class Utility{
	
	public static ArrayList<Point> generateRandomPoints(int numOfPoints){
		
		Random rand = new Random();
		
		ArrayList<Point> points = new ArrayList<>(numOfPoints);
		
		for(int i = 0; i < numOfPoints; i++){
			Point randPoint = generateRandomPoint(rand);
			if(!randPoint.existIn(points))
				points.add(randPoint);
			else
				i--;
		}

		return points;
		
	}
	
	public static ArrayList<Point> generateBorderPoints(){
		
		ArrayList<Point> points = new ArrayList<>(552);
		
		for(int i = 1; i <= Grid.cols-2; i++)	//top layer stagnant row, move col
			points.add(new Point(i,0));
		for(int j = 1; j <= Grid.cols-2; j++)	//bottom layer stagnant row, move col
			points.add(new Point(j,Grid.rows-1)); 
		for(int k = 1; k <= Grid.rows-2; k++)	//left layer move row, stagnant col
			points.add(new Point(0,k)); 
		for(int l = 1; l <= Grid.rows-2; l++)	//right layer move row, stagnant col
			points.add(new Point(Grid.cols-1,l)); 
		
		return points;
	}
	
	public static Point generateRandomPointInRange(ArrayList<Point> points){
		
		Random rand = new Random();
		rand.nextInt(points.size());
		return points.get(rand.nextInt(points.size()));
	}
	
	public static Direction chooseNextDirection(Direction direction){
		
		Random rand = new Random();
		int val = rand.nextInt(100);
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
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	public static Box[][] readFile(String filepath){
		// Harshil will write code here.
		return null;
	}
	
	public static void writeFile(String filepath){
		
	}
	
	private static Point generateRandomPoint(final Random r){
		return new Point(r.nextInt(Grid.cols),r.nextInt(Grid.rows));
	}
	
	
	
}
