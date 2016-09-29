package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Grid {
	
	final public static short rows = 120;
	final public static short cols = 160;
	final public static short region_width = 31;
	final public static short region_height = 31;
	
	private Box[][] grid = new Box[rows][cols];

    public static void main(String[] args) {
        new Grid();
    }

    public Grid() {
    	
    	short colWidth = 1000 / cols;
        short rowHeight = 800 / rows;

        for (int row = 0; row < rows; row++) {
        	for (int col = 0; col < cols; col++) {
        		grid[row][col] = new Box(colWidth * col, rowHeight * row, colWidth, rowHeight);
        	}
        }
        
        for(visual.Point p: Utility.generateRandomPoints(8)){
        	int left = (p.getX() < region_width / 2) ? 0 : p.getX() - region_width / 2;
        	int right = (p.getX() + (region_width / 2) > cols) ? cols : p.getX() + region_width / 2;
        	int top = (p.getY() < region_height / 2) ? 0 : p.getY() - region_height / 2;
        	int bottom = (p.getY() + (region_height / 2) > rows) ? rows : p.getY() + region_height / 2;
        	for(int a = top; a < bottom; a++){
        		for(int b = left; b < right; b++)
        			grid[a][b].terrain = Utility.rand.nextBoolean() ? Terrain.BLOCKED_CELL : Terrain.UNBLOCKED_CELL;
        	}
        }
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new PaintPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        
    }

    public class PaintPane extends JPanel {

        public PaintPane() {
            /*addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (Box[] bArr : grid) {
                    	for(Box b: bArr){
                    		if (b.contains(e.getPoint())) {
                    			b.isSelected = true;
                    		}
                    	}
                    }
                    repaint();
                }
            });*/
        }

        @Override
        public Dimension getPreferredSize() {
        	return new Dimension(1000, 1000);
        }

        @Override
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g); 
        	Graphics2D g2d = (Graphics2D) g;
        	for(Box[] cellArr: grid){
        		for (Box cell : cellArr) {
        			g2d.setColor(Color.DARK_GRAY);
    				g2d.draw(cell);
        			if(cell.isSelected){
        				g2d.setColor(Color.RED);
        			}
        			switch(cell.terrain){
        				case BLOCKED_CELL:
        					g2d.setColor(Color.BLACK);
        					break;
        				case PARTIALLY_BLOCKED_CELL:
        					g2d.setColor(Color.GRAY);
        					break;
        				default:
        					g2d.setColor(Color.WHITE);
        			}
        			g2d.fill(cell);
        		}
        	}
        }

    }

}

class Box extends Rectangle implements MouseListener{

	int col, row = 0;
	boolean isSelected = false;
	Terrain terrain = Terrain.UNBLOCKED_CELL;
	
	Box(int c, int r, int cellWidth, int cellHeight){
		super(c,r,cellWidth,cellHeight);
		col = c;
		row = r;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		isSelected = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}