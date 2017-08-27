package mouse;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MouseDemo {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new MouseFrame();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
	}
}
