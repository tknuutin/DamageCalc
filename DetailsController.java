package core;

/**
 * Controller for the Add and Edit windows. Takes care of creating and managing the windows. Also launches details help.
 * 
 * @author Tarmo
 *
 */
public class DetailsController {
	
	MainController owner;
	DetailsView view;
	
	/**
	 * Constructor that creates the DetailsController from one attribute.
	 * 
	 * @param owner - a MainController instance that created this DetailsController.
	 */
	DetailsController(MainController owner){
		
		this.owner = owner;
	}
	
	/**
	 * Creates and formats the DetailsView. This method must be run before launching other methods.
	 */
	public void createDetailsView(){
		view = new DetailsView(this, owner.view);
		view.setLocation(300, 500);
		view.pack();
	}
	
	/**
	 * Creates and shows an Add Resist window.
	 */
	public void startAddResist(){
		view.startAddMode(Modifier.TYPE_RESIST);
		view.setVisible(true);
	}
	
	/**
	 * Creates and shows an Add Weakness window.
	 */
	public void startAddWeakness(){
		view.startAddMode(Modifier.TYPE_WEAKNESS);
		view.setVisible(true);
	}
	
	/**
	 * Creates and shows an Edit Resist window.
	 */
	public void startEditResist(Modifier mod){
		view.startEditMode(mod);
		view.setVisible(true);
	}
	
	/**
	 * Creates and shows an Edit Weakness window.
	 */
	public void startEditWeakness(Modifier mod){
		view.startEditMode(mod);
		view.setVisible(true);
	}
	
	/**
	 * Launches a help window offering information about the Details window.
	 */
	public void launchDetailsHelp(){
		DetailsHelpDialog d = new DetailsHelpDialog();
		d.pack();
		d.setVisible(true);
	}
	
	

}
