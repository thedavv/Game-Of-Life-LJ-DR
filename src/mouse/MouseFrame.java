package mouse;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseFrame extends JFrame {
	public MouseFrame() {
		add(new JLabel(" "), BorderLayout.NORTH);
		add(new JLabel(" "), BorderLayout.EAST);
		add(new JLabel(" "), BorderLayout.SOUTH);
		add(new JLabel(" "), BorderLayout.WEST);
		add(new MouseComponent(), BorderLayout.CENTER);
		pack();
	}
}
