package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Grid {

    public static void main(String[] args) {
        new Grid();
    }

    public Grid() {
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

        private List<Box> grid;

        public PaintPane() {
            grid = new ArrayList<>(5);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (Box b : grid) {
                        if (b.contains(e.getPoint())) {
                            b.isSelected = true;
                        }
                    }
                    repaint();
                }
            });

            int colWidth = 1000 / 120;
            int rowHeight = 1000 / 160;

            for (int row = 0; row < 160; row++) {
                for (int col = 0; col < 120; col++) {
                    grid.add(new Box(colWidth * col, rowHeight * row, colWidth, rowHeight));
                }
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1000, 1000);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            for (Box cell : grid) {
            	if(cell.isSelected){
            		g2d.setColor(Color.RED);
            		g2d.fill(cell);;
            	}else{
            		g2d.setColor(Color.GRAY);
            		g2d.draw(cell);
            	}
                
            }
        }

    }

}

enum Terrain { 
	
	BLOCKED_CELL("0"), UNBLOCKED_CELL("1"), TRAVERSE_CELL("2"), UNBLOCKED_HIGHWAY_CELL("a"), TRAVERSE_HIGHWAY_CELL("b");
	
	String state;
	Terrain(String s){
		state = s;
	}
	
	public String toString(){
		return state;
	}

};

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