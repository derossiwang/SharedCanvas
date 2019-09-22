import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class drawListener implements MouseListener,MouseMotionListener{
	private JPanel canvas;
	private Graphics2D graph;
	private Color color = Color.black; //default color is black
	private Tool tool;
	private int x1,y1,x2,y2; //starting point(x1,y1) ending point(x2,y2)
	private ArrayList<Graph> shapes;
	public drawListener(JPanel canvas, Graphics g,Tool tool) {
		this.canvas = canvas;
		this.graph = (Graphics2D)g;
		shapes = new ArrayList<Graph>();
		this.tool=tool;
	}
	public drawListener(JPanel canvas,Graphics g,ArrayList<Graph> shapes,Tool tool) {
		this.canvas = canvas;
		this.graph = (Graphics2D)g;
		this.shapes = shapes;
		this.tool = tool;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		x2 = e.getX();
		y2 = e.getY();
		assert tool!=null;
		assert graph!=null;
		switch(tool.getType()) {
			case "pencil":
				graph.setStroke(new BasicStroke(tool.getThickness()));
				Color c = tool.getColor();
				graph.setColor(c);			
				this.graph.drawLine(x1, y1, x2, y2);
				shapes.add(new Graph(x1, y1, x2, y2, "line", c, "not text"));
				
//				System.out.println(shapes.size());
				
				x1=x2;
				y1=y2;
				break;
			case "eraser":
				graph.setStroke(new BasicStroke(tool.getThickness()));
				graph.setColor(Color.WHITE);
				this.graph.drawLine(x1, y1, x2, y2);
				shapes.add(new Graph(x1,y1,x2,y2,"eraser",Color.WHITE, "not text"));
				
				x1=x2;
				y1=y2;
				break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.x1 = e.getX();
		this.y1 = e.getY();	
		switch(this.tool.getType()) {
		case "text":
			 Map<Point, String> pointTextMap = new LinkedHashMap<>();
			 String prompt = "Please add text to display";
	         String input = JOptionPane.showInputDialog(canvas, prompt);
	         pointTextMap.put(e.getPoint(), input);
	         for (Point p : pointTextMap.keySet()) {
	             String text = pointTextMap.get(p);
	             
//	             System.out.println("WHERE you type the text is at"+p);
//	        	 System.out.println("the text you entered is:  "+text);
//	             System.out.println();
	             System.out.println("previous shape size is:  "+shapes.size());
	             Color c = tool.getColor();
				 graph.setColor(c);
				 if (text!= null) {
					 this.graph.drawString(text, p.x, p.y);
//		             System.out.println(x1);
//		             System.out.println(y1);
//		             System.out.println(x2);
//		             System.out.println(y2);
		             
		             //g2.drawString(accStr, xLoc, yLoc);
		             //so x2,y2 are irrelevant, just keep it
		             shapes.add(new Graph(x1, y1, x2, y2, "text", c, text));
				 }
	             
	             System.out.println("later shape size is:  "+shapes.size());
	          }
	        
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.x2 = e.getX();
		this.y2 = e.getY();
		assert this.tool!=null;
		switch(this.tool.getType()) {
			case "line":
				graph.setStroke(new BasicStroke(tool.getThickness()));
				Color c = tool.getColor();
				graph.setColor(c);
				graph.drawLine(x1,y1,x2,y2);
				shapes.add(new Graph(x1,y1,x2,y2,"line",c, "not text"));
				break;
			case "oval":
				graph.setStroke(new BasicStroke(tool.getThickness()));
				Color c1 = tool.getColor();
				graph.setColor(c1);
				graph.drawOval(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
				shapes.add(new Graph(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2),"oval",c1, "not text"));
				break;
			case "rect":
				graph.setStroke(new BasicStroke(tool.getThickness()));
				Color c2 = tool.getColor();
				graph.setColor(c2);
				graph.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
				shapes.add(new Graph(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2),"rect",c2, "not text"));
				break;
			case "circle":
				graph.setStroke(new BasicStroke(tool.getThickness()));
				Color c3 = tool.getColor();
				graph.setColor(c3);
				graph.drawOval(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
				shapes.add(new Graph(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2),"oval",c3, "not text"));
				break;
		}
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
