package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Direction;
import model.Heap;
import model.HeuristicType;
import model.Neighbor;
import model.Node;
import model.NodeComparator;
import model.Point;
import model.RegularAStar;
import model.Terrain;
import model.UniformCostSearch;
import model.WeightedAStar;
import controller.Utility;

public class Grid extends JFrame{
	
	final public static short rows = 120;
	final public static short cols = 160;
	final public static short region_width = 31;
	final public static short region_height = 31;
	final public static short screen_width = 980;
	final public static short screen_height = 800;
	
	private Box[][] grid;
	private Point startPoint;
	private Point goalPoint;
	private double totalPathCost;
	private ArrayList<Point> aStarSolution = new ArrayList<Point>();
	private Point[] difficultTerrain = new Point[8];
	

    public Grid() {
    	
    	grid = new Box[rows][cols];
    	short colWidth = screen_width / cols;
        short rowHeight = screen_height / rows;

        for (int row = 0; row < rows; row++) 
        	for (int col = 0; col < cols; col++) 
        		grid[row][col] = new Box(col, row, colWidth, rowHeight);
        
    	generateRegions();
    	generateHighways();
    	generateBlockedCells();
    	generateStartAndGoal();
    	setWindowProperties();
    }
    
    public Grid(Box[][] grid, Point sp, Point gp, Point[] regions){
    	this.grid = grid;
    	this.startPoint = sp;
    	this.goalPoint = gp;
    	this.difficultTerrain = regions;
    	setWindowProperties();
    }
    
    public void runUniformCostSearch(){
    	UniformCostSearch uas = new UniformCostSearch(this.grid);
    	Node nod = uas.runAStar(startPoint, goalPoint);    	
    	while(nod != null){
    		aStarSolution.add(nod.getPoint());
    		nod = nod.parent;
    	}
    	HomeScreen.display("Drawing the path now.");
    	repaint();
    }
    
    public void runRegularAStar(){
    	RegularAStar ras = new RegularAStar(this.grid, HeuristicType.MANHATTAN);
    	Node nod = ras.runAStar(startPoint, goalPoint);
    	if(nod == null)
    		HomeScreen.display("No path found");
    	while(nod != null){
    		aStarSolution.add(nod.getPoint());
    		nod = nod.parent;
    	}
    	HomeScreen.display("Drawing the path now.");
    	repaint();
    }
    
    public void runWeightedAStar(double weight){
    	WeightedAStar ras = new WeightedAStar(this.grid, weight, HeuristicType.RANDOM);
    	Node nod = ras.runAStar(startPoint, goalPoint);    	
    	while(nod != null){
    		aStarSolution.add(nod.getPoint());
    		nod = nod.parent;
    	}
    	HomeScreen.display("Drawing the path now.");
    	repaint();
    }
    
    public void generateRegions(){
    	ArrayList<Point> points = Utility.generateRandomPoints(8);
    	for(int i = 0; i < 8 ; i++){ //possibility of going out of bounds?
    		Point p = points.get(i);
    		difficultTerrain[i] = p;
        	int left = (p.getX() < region_width / 2) ? 0 : p.getX() - region_width / 2;
        	int right = (p.getX() + (region_width / 2) > cols) ? cols : p.getX() + region_width / 2;
        	int top = (p.getY() < region_height / 2) ? 0 : p.getY() - region_height / 2;
        	int bottom = (p.getY() + (region_height / 2) > rows) ? rows : p.getY() + region_height / 2;
        	for(int a = top; a < bottom; a++)
        		for(int b = left; b < right; b++)
        			grid[a][b].setTerrain(Utility.randomBoolean() ? Terrain.PARTIALLY_BLOCKED_CELL : Terrain.UNBLOCKED_CELL);
        }
    }
    
    public void generateHighways(){
    	//System.out.println("Entering highways");
    	ArrayList<Point> borderPoints = Utility.generateBorderPoints();
    	ArrayList<Point> startingPoints = new ArrayList<Point>(4);
    	ArrayList<Point> visitedPoints = new ArrayList<Point>();
    	ArrayList<Point> overallVisitedPoints = new ArrayList<Point>();
    	Direction direction = Direction.UNDECIDED;
    	Point currentPoint = new Point(0,0);
    	Point borderStart = new Point(0,0);
    	int itterations = 0;
    	boolean done = false;
    	boolean restart = true;
    	while (itterations != 4){
    		while(!done){
    			if(restart){
    				restart = false;
        			borderStart = Utility.generateRandomPointInRange(borderPoints);
        			if(borderStart.existIn(startingPoints))
        				continue;
        			startingPoints.add(borderStart);
        			visitedPoints.add(borderStart);
        			if(borderStart.getY() == rows-1)
        				direction = Direction.UP; //have to move it up
        			else if(borderStart.getY() == 0)
        				direction = Direction.DOWN; //have to move it down
        			else if(borderStart.getX() == 0)
        				direction = Direction.RIGHT; //have to move it to the right
        			else if(borderStart.getX() == cols-1)
        				direction = Direction.LEFT; //have to move it to the left
        			currentPoint = borderStart;
    			}

    			if(direction == Direction.UP){
    				for(int i = 1; i <= 20; i++){
    					currentPoint = new Point(currentPoint.getX(),currentPoint.getY()-1);
    					if(currentPoint.existIn(visitedPoints) || currentPoint.existIn(overallVisitedPoints) || currentPoint.getY() < 0){
    						restart = true;
    						visitedPoints.clear();
    						startingPoints.remove(borderStart);
    						break;
    					}
    					visitedPoints.add(currentPoint);
    					if(currentPoint.getY() == 0 && visitedPoints.size() >= 100){
    						restart = false;
    						done = true;
    						break;
    					}
    				}
    				direction = Utility.chooseNextDirection(direction);
    				continue;
    			}else if(direction == Direction.DOWN){
    				for(int i = 1; i <= 20; i++){
    					currentPoint = new Point(currentPoint.getX(),currentPoint.getY()+1);
    					if(currentPoint.existIn(visitedPoints) || currentPoint.existIn(overallVisitedPoints) || currentPoint.getY() > rows-1){
    						restart = true;
    						visitedPoints.clear();
    						startingPoints.remove(borderStart);
    						break;
    					}
    					visitedPoints.add(currentPoint);
    					if(currentPoint.getY() == 119 && visitedPoints.size() >= 100){
    						restart = false;
    						done = true;
    						break;
    					}
    				}
    				direction = Utility.chooseNextDirection(direction);
    				continue;
    			}else if(direction == Direction.LEFT){
    				for(int i = 1; i <= 20; i++){
    					currentPoint = new Point(currentPoint.getX()-1,currentPoint.getY());
    					if(currentPoint.existIn(visitedPoints) || currentPoint.existIn(overallVisitedPoints) || currentPoint.getX() < 0){
    						restart = true;
    						visitedPoints.clear();
    						startingPoints.remove(borderStart);
    						break;
    					}
    					visitedPoints.add(currentPoint);
    					if(currentPoint.getX() == 0 && visitedPoints.size() >= 100){
    						restart = false;
    						done = true;
    						break;
    					}
    				}
    				direction = Utility.chooseNextDirection(direction);
    				continue;
    			}else if(direction == Direction.RIGHT){
    				for(int i = 1; i <= 20; i++){
    					currentPoint = new Point(currentPoint.getX()+1,currentPoint.getY());
    					if(currentPoint.existIn(visitedPoints) || currentPoint.existIn(overallVisitedPoints) || currentPoint.getX() > cols-1){
    						restart = true;
    						visitedPoints.clear();
    						startingPoints.remove(borderStart);
    						break;
    					}
    					visitedPoints.add(currentPoint);
    					if(currentPoint.getX() == 159 && visitedPoints.size() >= 100){
    						restart = false;
    						done = true;
    						break;
    					}
    				}
    				direction = Utility.chooseNextDirection(direction);
    				continue;
    			}else if(direction == Direction.UNDECIDED){
    				restart = true;
    				break;
    			}
    			if(restart)
    				continue;
    		}
    		overallVisitedPoints.addAll(visitedPoints);
    		itterations++;
    		for(int i = 0; i < visitedPoints.size(); i++){
    			Point po = visitedPoints.get(i);
    			grid[po.getY()][po.getX()].setHighway_index(itterations);
    		}
    		visitedPoints.clear();
    		restart = true;
    		done = false;
    	}
    	//System.out.println("Leaving highways");
    	for(int j = 0; j < overallVisitedPoints.size(); j++){
    		Point current = overallVisitedPoints.get(j);
    		if(grid[current.getY()][current.getX()].getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL)
    			grid[current.getY()][current.getX()].setTerrain(Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL);
    		else 
    			grid[current.getY()][current.getX()].setTerrain(Terrain.UNBLOCKED_HIGHWAY_CELL);
    	}
    	//System.out.println("overall:"+overallVisitedPoints.toString());
    	//System.out.println("Starting:"+startingPoints.toString());
    }
    
    public void generateBlockedCells(){
    	
    	int numToGenerate = (int) ((0.2) * (rows * cols));
    	
    	ArrayList<Point> blockedList = new ArrayList<Point>(numToGenerate);
    	Point current = null;
    	Random random = new Random();
    	
    	for(int i = 0; i < numToGenerate; i++){
    		current = new Point(random.nextInt(Grid.cols),random.nextInt(Grid.rows));
    		if(grid[current.getY()][current.getX()].getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL || grid[current.getY()][current.getX()].getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL){
    			i--;
    			continue;
    		}
    		if(!current.existIn(blockedList))
    			blockedList.add(current);
    		else{
    			i--;
    			continue;
    		}
    	}
    	
    	for(int j = 0; j < numToGenerate; j++){
    		Point blocked = blockedList.get(j);
    		grid[blocked.getY()][blocked.getX()].setTerrain(Terrain.BLOCKED_CELL);
    	}
    	
    }
    
    public void generateStartAndGoal(){
    	startPoint = null;
    	goalPoint = null;
    	totalPathCost = 0.0;
    	aStarSolution.clear();
    	for(int run = 0; run != 2; run++){
	    	Direction topBottom = Utility.randomBoolean()? Direction.UP : Direction.DOWN;
	    	Direction leftRight = Utility.randomBoolean()? Direction.LEFT : Direction.RIGHT;
	    	Direction decision = Utility.randomBoolean()? topBottom : leftRight;
	    	boolean done = false;
	    	ArrayList<Point> possiblePoints = new ArrayList<Point>();
	    	Point chosen = null;
	    	if(decision == Direction.UP){
	    		for(int i = 0; i < 20; i++)
	    			for(int j = 0; j < cols; j++)
	    				possiblePoints.add(new Point(j,i));
	    		while(!done){
	    			chosen = Utility.generateRandomPointInRange(possiblePoints);
	    			if(grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.UNBLOCKED_CELL || grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL){
	    				done = true;
	    			}
	    		}
	    	}else if(decision == Direction.DOWN){
	    		for(int i = rows-1; i > rows-20; i--)
	    			for(int j = 0; j < cols; j++)
	    				possiblePoints.add(new Point(j,i));
	    		while(!done){
	    			chosen = Utility.generateRandomPointInRange(possiblePoints);
	    			if(grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.UNBLOCKED_CELL || grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL){
	    				done = true;
	    			}
	    		}    		
	    	}else if(decision == Direction.LEFT){	
	    		for(int i = 0; i < rows; i++)
	    			for(int j = 0; j < 20; j++)
	    				possiblePoints.add(new Point(j,i));
	    		while(!done){
	    			chosen = Utility.generateRandomPointInRange(possiblePoints);
	    			if(grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.UNBLOCKED_CELL || grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL){
	    				done = true;
	    			}
	    		}    		
	    	}else if(decision == Direction.RIGHT){
	    		for(int i = 0; i < rows; i++)
	    			for(int j = cols-1; j > cols-20; j--)
	    				possiblePoints.add(new Point(j,i));
	    		while(!done){
	    			chosen = Utility.generateRandomPointInRange(possiblePoints);
	    			if(grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.UNBLOCKED_CELL || grid[chosen.getY()][chosen.getX()].getTerrain() == Terrain.PARTIALLY_BLOCKED_CELL){
	    				done = true;
	    			}
	    		}    		
	    	}
	    	if(run == 0)
	    		startPoint = chosen;
	    	else if(run == 1){
	    		goalPoint = chosen;
	    		int euclideanDistance = (int) Utility.getDistance(startPoint, goalPoint);
//	    		System.out.println(euclideanDistance);
	    		if(euclideanDistance < 100)
	    			run--;
	    	}
    	}
    	repaint();
    }

    public void setWindowProperties(){
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent we) {
    			dispose();
    		}
    	});
    	add(new PaintPane());
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    	this.setSize(screen_width, screen_height);
    }

    public class PaintPane extends JPanel {

        public PaintPane() {
        	addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                	int x=e.getX() / (screen_width/cols);
                    int y=e.getY() / (screen_height/rows);
                    JOptionPane.showMessageDialog(rootPane, grid[y][x].getNode().visualizeValues());
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                	
                }
            });
        }
        
        

        @Override
        public Dimension getPreferredSize() {
        	return new Dimension(screen_height, screen_width);
        }

        @Override
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g); 
        	Graphics2D g2d = (Graphics2D) g;

        	for(Box[] cellArr: grid){
        		for (Box cell : cellArr) {
        			switch(cell.getTerrain()){
        				case BLOCKED_CELL:
        					g2d.setColor(Color.BLACK);
        					break;
        				case PARTIALLY_BLOCKED_CELL:
        					g2d.setColor(Color.GRAY);
        					break;
        				case UNBLOCKED_HIGHWAY_CELL:
        					g2d.setColor(Color.CYAN);
        					break;
        				case PARTIALLY_BLOCKED_HIGHWAY_CELL:
        					g2d.setColor(Color.BLUE);
        					break;
        				default:
        					g2d.setColor(Color.WHITE);
        			}
        			
        			if( aStarSolution.contains(cell.getNode().getPoint()) || cell.getNode().getPoint().equals(goalPoint)
        					|| cell.getNode().getPoint().equals(startPoint)){
        				g2d.setColor(Color.MAGENTA);
        			}
        			if(cell.getNode().getPoint().equals(startPoint))
        				g2d.setColor(Color.RED);
        			if(cell.getNode().getPoint().equals(goalPoint))
        				g2d.setColor(Color.GREEN);
      
        			g2d.fill(cell);
        			g2d.setColor(Color.DARK_GRAY);
        			g2d.draw(cell);

        		}
        	}
        }

    }
    
    public Point getStartPoint(){
    	return startPoint;
    }
    
    public Point getGoalPoint(){
    	return goalPoint;
    }
    
    public Box[][] getGrid(){
    	return grid;
    }
    
    public Point[] getDifficultTerrain(){
    	return  difficultTerrain;
    }
    
    public String printDiffictTerrain(){
    	String ret = "";
    	for(short i = 0; i < difficultTerrain.length; i++)
    		ret += difficultTerrain[i] + "\n";
    	return ret;
    }
    
    public String getPathTaken(){
    	String ret = "";
    	ret += totalPathCost + "\n";
    	for(Point p: aStarSolution)
    		ret += p.toString() + "\n";
    	return ret;
    }
    
    @Override
    public String toString(){
    	String ret = "";
    	ret += startPoint.getY() + "," + startPoint.getX() + "\n";
    	ret += goalPoint.getY() + "," + goalPoint.getX() + "\n";
    	for(Point p: difficultTerrain){
    		ret += p.getY() + "," + p.getX() + "\n";
    	}
    	ret += String.valueOf(rows) + "," + String.valueOf(cols) + "\n";
    	for(short i = 0; i < rows; i++){
    		for(short j = 0; j < cols; j++){
    			ret += grid[i][j].toString();
    			if(j < cols - 1) ret += ",";
    		}
    		ret += "\n";
    	}
    	return ret;
    } 

}

