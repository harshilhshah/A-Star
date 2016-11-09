package visual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import controller.Utility;
import model.HeuristicType;
import model.IntegratedAStar;
import model.RegularAStar;
import model.SequentialAStar;
import model.UniformCostSearch;
import model.WeightedAStar;

public class GridTestCases {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Grid[] gList = new Grid[5];
		gList[0] = Utility.readFile("src/map1.txt");
		gList[1] = Utility.readFile("src/map2.txt");
		gList[2] = Utility.readFile("src/map3.txt");
		gList[3] = Utility.readFile("src/map4.txt");
		gList[4] = Utility.readFile("src/map5.txt");
		
		for(int i = 0; i < 5; i++){
			System.out.println("FOR MAP"+ (i+1) +" : Regular A*\n");
			runTestsAvgForAllHeuristicsFor1GridRegularAStar(gList[i]);
			System.out.println();
			System.out.println("FOR MAP"+ (i+1) +" : Weighted A* = 1.5\n");
			runTestsAvgForAllHeuristicsFor1GridWeightedAStar1_5(gList[i]);
			System.out.println();
			System.out.println("FOR MAP"+ (i+1) +" : Weighted A* = 3.0\n");
			runTestsAvgForAllHeuristicsFor1GridWeightedAStar3_0(gList[i]);
			System.out.println();
			System.out.println("FOR MAP"+ (i+1) +" : UniformCostSearch\n");
			runTestsAvgForUniformCostSeach(gList[i]);
			System.out.println("FOR MAP"+ (i+1) +" : Sequential A*\n");
			runTestsAvgForAllHeuristicsFor1GridSequentialAStar3_0(gList[i]);
			System.out.println("FOR MAP"+ (i+1) +" : Integrated A*\n");
			runTestsAvgForAllHeuristicsFor1GridIntegratedAStar3_0(gList[i]);
			System.out.println();
		}
		

//			g.runAStar(new RegularAStar(g.getGrid(), HeuristicType.EUCLIDEAN));
		

	}

	public static void runTestsAvgForAllHeuristicsFor1GridRegularAStar(Grid g){
		double[] averages = new double[5];
		for(int i = 1; i < 6; i++){
			System.out.println(HeuristicType.values()[i]);
			double[] currAverages = g.runAStar(new RegularAStar(g.getGrid(), HeuristicType.values()[i]));
			for(int j = 0; j < 5; j++){
				averages[j] += currAverages[j];
			}
			g.refresh();
			System.out.println("Refresh complete");
			System.out.println();
		}
		for(int k = 0; k < 5; k++){
			averages[k] /= 5;
			System.out.println(k+") "+averages[k]);
		}		
	}
	
	public static void runTestsAvgForAllHeuristicsFor1GridWeightedAStar1_5(Grid g){
		double[] averages = new double[5];
		for(int i = 1; i < 6; i++){
			System.out.println(HeuristicType.values()[i]);
			double[] currAverages = g.runAStar(new WeightedAStar(g.getGrid(), 1.5, HeuristicType.values()[i]));
			for(int j = 0; j < 5; j++){
				averages[j] += currAverages[j];
			}
			g.refresh();
			System.out.println("Refresh complete");
			System.out.println();
		}
		for(int k = 0; k < 5; k++){
			averages[k] /= 5;
			System.out.println(k+") "+averages[k]);
		}		
	}
	
	public static void runTestsAvgForAllHeuristicsFor1GridWeightedAStar3_0(Grid g){
		double[] averages = new double[5];
		for(int i = 1; i < 6; i++){
			System.out.println(HeuristicType.values()[i]);
			double[] currAverages = g.runAStar(new WeightedAStar(g.getGrid(), 3.0, HeuristicType.values()[i]));
			for(int j = 0; j < 5; j++){
				averages[j] += currAverages[j];
			}
			g.refresh();
			System.out.println("Refresh complete");
			System.out.println();
		}
		for(int k = 0; k < 5; k++){
			averages[k] /= 5;
			System.out.println(k+") "+averages[k]);
		}		
	}
	
	public static void runTestsAvgForAllHeuristicsFor1GridSequentialAStar3_0(Grid g){
		System.out.println("w1 = 1.25 and w2 = 2");
		g.runAStar(new SequentialAStar(g.getGrid(), 1.25, 2.0));
		g.refresh();
		System.out.println("Refresh complete");
		System.out.println();	
		System.out.println("w1 = 1 and w2 = 8");
		g.runAStar(new SequentialAStar(g.getGrid(), 1, 8));
		g.refresh();
		System.out.println("Refresh complete");
		System.out.println("---------------");	
	}
	
	public static void runTestsAvgForAllHeuristicsFor1GridIntegratedAStar3_0(Grid g){
		System.out.println("w1 = 1.25 and w2 = 2");
		g.runAStar(new IntegratedAStar(g.getGrid(), 1.25, 2.0));
		g.refresh();
		System.out.println("Refresh complete");
		System.out.println();	
		System.out.println("w1 = 1 and w2 = 8");
		g.runAStar(new IntegratedAStar(g.getGrid(), 1, 8));
		g.refresh();
		System.out.println("Refresh complete");
		System.out.println("---------------");
	}
	
	public static void runTestsAvgForUniformCostSeach(Grid g){
//		double[] averages = new double[5];
		g.runAStar(new UniformCostSearch(g.getGrid()));
	}
	
}
