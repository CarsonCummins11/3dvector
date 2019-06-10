import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

public class Camera implements MouseWheelListener,MouseMotionListener,MouseListener{
public double pitch;
public double yaw;
public double originDist;
Point mousept;
JFrame f;
	public Camera(JFrame ff) {
		pitch = 45;
		f=ff;
		yaw = 45;
		originDist = 5;
	}
	public double[][] render(double[][] vectors){
		//calculate origin of new camera space
		Matrix camera_loc = new Matrix(new double[][] {{originDist},{0},{0}}); 
		//construct yaw,pitch,roll matrices
		camera_loc = yawMatrix(yaw).times(camera_loc);
		camera_loc = pitchMatrix(pitch).times(camera_loc);

		Matrix normal = camera_loc;
		//do projections
		double[][] ret = new double[vectors.length][2];
		for(int i = 0; i< vectors.length;i++) {
			Matrix vector = new Matrix(new double[][] {vectors[i]}).transpose();
			double scalar = Matrix.dotproduct(vector,normal)/(Matrix.magnitude(normal)*Matrix.magnitude(normal));
			normal.scale(scalar);
			ret[i] = vector.minus(normal).transpose().data[0];
		}
		return ret;
	}
	private Matrix pitchMatrix(double p) {
		double y = Math.toRadians(p);
		return new Matrix(new double[][] {{Math.cos(y),0,Math.sin(y)},{0,1,0},{-Math.sin(y),0,Math.cos(y)}});
	}
	private Matrix yawMatrix(double ya) {
		double y = Math.toRadians(ya);
		new Matrix(new double[][] {{Math.cos(y),-Math.sin(y),0},{Math.sin(y),Math.cos(y),0},{0,0,1}}).show(System.out);
		System.out.println();
		return new Matrix(new double[][] {{Math.cos(y),-Math.sin(y),0},{Math.sin(y),Math.cos(y),0},{0,0,1}});
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		originDist+=e.getWheelRotation()/3;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		 int dx = e.getXOnScreen() - mousept.x;
         int dy = e.getYOnScreen() - mousept.y;
         yaw-=dx;
         pitch-=dy;
         mousept = e.getLocationOnScreen();
         f.repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mousept = e.getLocationOnScreen();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		//dont care
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//dont care
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//dont care
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//dont care
		
	}
	

}
