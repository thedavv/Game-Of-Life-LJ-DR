package dialog;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	PaternsDialog d = new PaternsDialog();

	public MyFrame() {
		JButton button = new JButton("asd");
		add(button);

		button.addActionListener(e -> {
			d.setVisible(true);
		});

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MyFrame();
	}
}
