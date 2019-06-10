import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel{
	private static final long serialVersionUID = 1L;
	Matrix contained;
	Camera camera;
	public Surface(Matrix torend,JFrame par) {
		contained=torend;
		camera = new Camera(par);
		this.addMouseWheelListener(camera);
		this.addMouseMotionListener(camera);
		this.addMouseListener(camera);
	}
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 250, 500);
		double[][] torender = {{100,0,0},{0,100,0},{0,0,100},contained.transpose().data[0],contained.transpose().data[1],contained.transpose().data[2]};
		double[][] todraw = camera.render(torender);
		g.setColor(Color.BLACK);
			g.drawLine(125, 250, (int)Math.round(todraw[0][0]+125),(int)Math.round(todraw[0][1]+250));
			g.drawString("x",(int)Math.round(todraw[0][0]+125),(int)Math.round(todraw[0][1]+250));
			g.drawLine(125, 250, (int)Math.round(todraw[1][0]+125),(int)Math.round(todraw[1][1]+250));
			g.drawString("y",(int)Math.round(todraw[1][0]+125),(int)Math.round(todraw[1][1]+250));
			g.drawLine(125, 250, (int)Math.round(todraw[2][0]+125),(int)Math.round(todraw[2][1]+250));
			g.drawString("z",(int)Math.round(todraw[2][0]+125),(int)Math.round(todraw[2][1]+250));
		g.setColor(Color.RED);
		g.drawLine(125, 250, (int)Math.round(todraw[3][0]+125),(int)Math.round(todraw[3][1]+250));
		g.setColor(Color.GREEN);
		g.drawLine(125, 250, (int)Math.round(todraw[4][0]+125),(int)Math.round(todraw[4][1]+250));
		g.setColor(Color.BLUE);
		g.drawLine(125, 250, (int)Math.round(todraw[5][0]+125),(int)Math.round(todraw[5][1]+250));
	}
}
