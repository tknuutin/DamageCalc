package core;

/**
 * Launching class. Only creates the MainController class and queues it for running.
 * 
 * @author Tarmo
 *
 */
public class Launcher {
	
	public static void main ( String [] args ){
		
		final MainController ctrl = new MainController();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ctrl.createMainView();
            }
        });
	}

}
