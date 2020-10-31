import javax.swing.*;

public class BouncingBallMain extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** main program (entry point) */
	public static void main(String[] args) {
		// Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Set up main window (using Swing's Jframe)
				JFrame frame = new JFrame("A Bouncing Ball");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setContentPane(new BouncingWindow());
	            frame.pack();
	            frame.setVisible(true);
	        }
		});
	}
	
}