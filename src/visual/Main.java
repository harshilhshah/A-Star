package visual;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import controller.Utility;
import model.Point;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid g = new Grid();
		PrintWriter writer;
		try {
			writer = new PrintWriter("map.txt", "UTF-8");
			for(String s: g.toString().split("\n")){
				writer.println(s);
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Start:"+g.getStartPoint());
		System.out.println("Goal:"+g.getGoalPoint());
		System.out.println("Difficult Terrain:");
		System.out.println(g.printDiffictTerrain());
		
	//	System.out.println("starting");
   // 	for(Point p: Utility.generateBorderPoints()){
  //  		System.out.println(p.toString());
 //   	}
//    	System.out.println("ending");
	/*	ArrayList<Integer> points = new ArrayList<Integer>();
		points.add(3);
		points.add(2);
		System.out.println(points.size() +"done");
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
			System.out.println(rand.nextInt(2+1)); */
	} 

}
