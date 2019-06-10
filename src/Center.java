import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Center implements ActionListener, MouseMotionListener,MouseWheelListener{
JFrame f;
Container controlPanel;
Surface threeSpace;
JTextArea current_matr;
JTextField[][] matr_edit;
JButton set_render;
Matrix render_matr;
Container matr_edit_contain;
Container edit_cont;
	public Center() {
		render_matr = new Matrix(new double[][] {{1,0,0},{0,1,0},{0,0,1}});
		f = new JFrame();
		f.addMouseMotionListener(this);
		f.addMouseWheelListener(this);
		f.setSize(500,500);
		f.setResizable(false);
		controlPanel = new Container();
		threeSpace = new Surface(render_matr,f);
		controlPanel.setLayout(new GridLayout(2,1));
		current_matr=new JTextArea();
		current_matr.setEditable(false);
		controlPanel.add(current_matr);
		matr_edit_contain = new Container();
		matr_edit_contain.setLayout(new BorderLayout());
		set_render = new JButton("Set Transform Matrix");
		set_render.addActionListener(this);
		matr_edit_contain.add(set_render,BorderLayout.SOUTH);
		edit_cont = new Container();
		edit_cont.setLayout(new GridLayout(3,3));
		matr_edit = new JTextField[3][3];
		for(int i =0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				matr_edit[i][j] = new JTextField();
				edit_cont.add(matr_edit[i][j]);
			}
		}
		matr_edit_contain.add(edit_cont);
		controlPanel.add(matr_edit_contain);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(1,2));
		f.add(threeSpace);
		f.add(controlPanel);
		f.setVisible(true);
		printRendMatr();
	}
	public static void main(String[] args) {
		new Center();
	}
	public void printRendMatr() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		ps.println("Currently displaying:");
		render_matr.show(ps);
		try {
			String output = os.toString("UTF8");
			current_matr.setText(output);
		} catch (UnsupportedEncodingException e) {
			current_matr.setText("broke while trying to print matrix");
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		double[][] transform_matr = new double[3][3];
		for(int i =0; i < 3; i++) {
			for(int j = 0; j<3; j++) {
				transform_matr[i][j] = Double.parseDouble(matr_edit[i][j].getText());
			}
		}
		Matrix tm = new Matrix(transform_matr);
		render_matr = tm.times(render_matr);
		threeSpace.contained = render_matr;
		printRendMatr();
		f.repaint();
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		f.repaint();
		System.out.println("out");
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		f.repaint();
		System.out.println("out");
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
