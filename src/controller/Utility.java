package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import visual.Box;
import visual.Grid;
import model.Direction;
import model.Point;
import model.Terrain;

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
	
	public static double getDistance(Point p1, Point p2){
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(),2));
	}
	
	public static boolean randomBoolean(){
		return random.nextBoolean();
	}
	
	public static double getCost(Box start, Box end, boolean isDiagonal){
		if(start.getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL && end.getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL)
				return isDiagonal ? Math.sqrt(2) : 0.25;
		if(start.getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL && end.getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL)
				return isDiagonal ? Math.sqrt(8) / 4 : 0.5;	
		if(start.getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL && end.getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL)
				return isDiagonal ? Math.sqrt(3) / 4 : 0.325;
		if(start.getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL && end.getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL)
				return isDiagonal ? Math.sqrt(3) / 4 : 0.325;	
		if(start.getTerrain() == Terrain.UNBLOCKED_CELL && end.getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL)
				return isDiagonal ? (Math.sqrt(2) + Math.sqrt(8)) / 2 : 1.5;
		if(start.getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL && end.getTerrain() == Terrain.UNBLOCKED_CELL)
				return isDiagonal ? (Math.sqrt(2) + Math.sqrt(8)) / 2 : 1.5;
		if(start.getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL && end.getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL)
				return isDiagonal ? Math.sqrt(8) : 2.0;
		return isDiagonal ? Math.sqrt(2) : 1.0;
	}

	public static Grid readFile(String filepath) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line = br.readLine();
		String[] split = line.split(",");
		Point startPoint = new Point(Integer.parseInt(split[1]),Integer.parseInt(split[0]));
		line = br.readLine();
		split = line.split(",");
		Point goalPoint = new Point(Integer.parseInt(split[1]),Integer.parseInt(split[0]));
		Point[] difficultTerrain = new Point[8];
		for(int i = 0; i < 8; i++){
			line = br.readLine();
			split = line.split(",");
			difficultTerrain[i] = new Point(Integer.parseInt(split[1]),Integer.parseInt(split[0]));
		}
		line = br.readLine();
		String[] dimensions = line.split(",");
		short rows = (short) Integer.parseInt(dimensions[0]);
		short cols = (short) Integer.parseInt(dimensions[1]);
		Box[][] grid = new Box[rows][cols];
		int i = 0;

		while ((line = br.readLine()) != null) {
			int j = 0;
			for(String s: line.split(",")){	
				grid[i][j] = new Box(j, i, Grid.screen_width/cols, Grid.screen_height/rows);
				switch(s.charAt(0)){
				case '0':
					grid[i][j].setTerrain(Terrain.BLOCKED_CELL);
					break;
				case '1':
					grid[i][j].setTerrain(Terrain.UNBLOCKED_CELL);
					break;
				case '2':
					grid[i][j].setTerrain(Terrain.PARTIALLY_BLOCKED_CELL);
					break;
				case 'a':
					grid[i][j].setTerrain(Terrain.UNBLOCKED_HIGHWAY_CELL);	
					grid[i][j].setHighway_index(Integer.parseInt(s.substring(1)));
					break;
				case 'b':
					grid[i][j].setTerrain(Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL);
					grid[i][j].setHighway_index(Integer.parseInt(s.substring(1)));
					break;
				default:
					break;		
				}
				j++;
			}
			i++;
		}
		br.close();
		return new Grid(grid,startPoint,goalPoint,difficultTerrain);
	}

	public static void writeFile(String filename, String bigString) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer;
		writer = new PrintWriter(filename, "UTF-8");
		for(String s: bigString.split("\n")){
			writer.println(s);
		}
		writer.close();
	}

	private static Point generateRandomPoint(){
		return new Point(random.nextInt(Grid.cols),random.nextInt(Grid.rows));
	}
	
	
}
