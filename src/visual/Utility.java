package visual;

import java.util.ArrayList;
import java.util.Random;

public class Utility{
	
	public static ArrayList<Point> generateRandomPoints(int numOfPoints){
		
		Random rand = new Random();
		
		ArrayList<Point> points = new ArrayList<>(numOfPoints);
		
		for(int i = 0; i < numOfPoints; i++){
			points.add(generateRandomPoint(rand));
		}

		return points;
		
	}
	
	public static boolean randomBoolean(){
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	public static void readFile(String filepath){
		
	}
	
	public static void writeFile(String filepath){
		
	}
	
	private static Point generateRandomPoint(final Random r){
		return new Point(r.nextInt(Grid.cols),r.nextInt(Grid.rows));
	}
	
	
	
}
