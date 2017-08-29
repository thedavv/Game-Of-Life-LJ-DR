package model;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GridDemo {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new GridFrame();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}
}
