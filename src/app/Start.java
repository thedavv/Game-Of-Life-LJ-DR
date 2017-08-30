package app;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.GridFrame;

public class Start {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new GridFrame();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}
}
