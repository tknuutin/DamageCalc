package core;

/**
 * Interface for classes that own a GameExample and therefore be called from the algorithms to add events to the game data.
 * 
 * @author Tarmo
 *
 */
interface GameExampleOwner{
	/**
	 * Add resist information to the data.
	 * 
	 * @param name - name of the resist
	 * @param amount - amount of damage nullified.
	 */
	public void addGameDataResist(String name, double amount);
	/**
	 * Add weakness information to the data.
	 * 
	 * @param name - name of the weakness
	 * @param amount - amount of damage caused.
	 */
	public void addGameDataWeakness(String name, double amount);
}

/**
 * Main controller class. Controls the Main window, launches calculation, and creates subwindows when needed.
 * 
 * @author Tarmo
 *
 */
public class MainController implements GameExampleOwner{
	
	public MainView view;
	protected ModifierCollection resistData;
	protected ModifierCollection weaknessData;
	private int currentEditable;
	protected GameExampleData gameData;
	
	/**
	 * Default MainController constructor. 
	 */
	MainController(){
		resistData = new ModifierCollection();
		weaknessData = new ModifierCollection();
	}
	
	/**
	 * Create MainView and set it as visible.
	 */
	public void createMainView(){
		
		view = new MainView(this);
		view.setLocation(400, 400);
		view.setVisible(true);
	}
	
	/**
	 * Launches the help window associated with the Quick tab.
	 */
	public void launchQuickHelp(){
		QuickHelpDialog d = new QuickHelpDialog();
		d.pack();
		d.setVisible(true);
	}
	
	/**
	 * Launches the help window associated with the Advanced tab.
	 */
	public void launchAdvHelp(){
		AdvHelpDialog d = new AdvHelpDialog();
		d.pack();
		d.setVisible(true);
	}
	
	/**
	 * Launches the add resist window.
	 */
	public void launchAddResist(){
		DetailsController detailCtrl = new DetailsController(this);
		
        detailCtrl.createDetailsView();
        detailCtrl.startAddResist();
	}
	
	/**
	 * Add resist to the list of current resists and show it on the MainView.
	 * 
	 * @param mod - resist to be added
	 */
	public void addResist(Modifier mod){
		
		resistData.addModifier(mod);
        
        view.appendToResists( resistData.getObjectArrayAt(resistData.getSize() - 1) );
	}
	
	/**
	 * Launches the add weakness window.
	 */
	public void launchAddWeakness(){
		DetailsController detailCtrl = new DetailsController(this);
		
        detailCtrl.createDetailsView();
        detailCtrl.startAddWeakness();
	}
	
	
	/**
	 * Add weakness to the list of current resists and show it on the MainView.
	 * 
	 * @param mod - weakness to be added
	 */
	public void addWeakness(Modifier mod){
		
		weaknessData.addModifier(mod);
        
        view.appendToWeakness( weaknessData.getObjectArrayAt(weaknessData.getSize() - 1) );
	}
	
	/**
	 * Launches the edit resist window for the resist on the specified index. Can cause OutOfBoundsException.
	 * Sets the currentEditable value as the parameter.
	 * 
	 * @param num - index of the resist to be edited.
	 */
	public void launchEditResist(int num){
		DetailsController detailCtrl = new DetailsController(this);
		
		currentEditable = num;
		
        detailCtrl.createDetailsView();
        detailCtrl.startEditResist(resistData.get(num));
	}
	
	
	/**
	 * Replaces the resist at the currentEditable index with the modifier specified.
	 * 
	 * @param newMod - modifier that replaces the old one.
	 */
	public void editResist(Modifier newMod){
		resistData.replaceAt(currentEditable, newMod);
		view.replaceResistInTable(currentEditable, newMod.getObjectArray());
	}
	
	/**
	 * Launches the edit weakness window for the weakness on the specified index. Can cause OutOfBoundsException.
	 * Sets the currentEditable value as the parameter.
	 * 
	 * @param num - index of the weakness to be edited.
	 */
	public void launchEditWeakness(int num){
		DetailsController detailCtrl = new DetailsController(this);
		
		currentEditable = num;
		
        detailCtrl.createDetailsView();
        detailCtrl.startEditWeakness(weaknessData.get(num));
	}
	
	/**
	 * Replaces the weakness at the currentEditable index with the modifier specified.
	 * 
	 * @param newMod - modifier that replaces the old one.
	 */
	public void editWeakness(Modifier newMod){
		weaknessData.replaceAt(currentEditable, newMod);
		view.replaceWeaknessInTable(currentEditable, newMod.getObjectArray());
	}
	
	/**
	 * Remove resist at index specified.
	 * 
	 * @param num - index of resist to be removed
	 */
	public void removeResist(int num){
		resistData.removeModifierAt(num);
	}
	
	/**
	 * Remove weakness at index specified.
	 * 
	 * @param num - index of weakness to be removed
	 */
	public void removeWeakness(int num){
		weaknessData.removeModifierAt(num);
	}
	
	/**
	 * Calculates a result from the current advanced tab data and launches a game example window
	 * with text based on the calculation.
	 */
	public void launchAdvGameExample(){
		advCalculate();
		GameExampleDialog d = new GameExampleDialog(view);
		d.setContent(gameData);
		d.setLocation(300, 500);
		d.pack();
		d.setVisible(true);
	}
	
	/**
	 * Calculates a result from the current quick tab data and launches a game example window
	 * with text based on the calculation.
	 */
	public void launchQuickGameExample(){
		quickCalculate();
		GameExampleDialog d = new GameExampleDialog(view);
		d.setContent(gameData);
		d.setLocation(300, 500);
		d.pack();
		d.setVisible(true);
	}
	
	
	/**
	 * Calculates a result from the data of the advanced tab and displays the result. Also adds
	 * data about the calculation to the GameExampleData.
	 */
	public void advCalculate(){
		
		view.enableAdvExample();
		System.out.println("Calculatelating");
		
		int damage = view.getAdvancedDamage();
		String[] dmgTypes = view.getAdvancedDamageTypes();
		
		double result = calculate(damage, dmgTypes, resistData, weaknessData);
		view.setAdvancedResult(new Double(result).toString());
	}
	
	/**
	 * Calculates a result from the data of the quick tab and displays the result. Also adds
	 * data about the calculation to the GameExampleData.
	 */
	public void quickCalculate(){
		view.enableQuickExample();
		
		Modifier mod = view.getQuickCalcModifier();
		int damage = view.getQuickDamage();
		int iterations = view.getQuickNumOfMods();
		ModifierCollection tempCollection = new ModifierCollection();
		
		for(int i = 0; i < iterations; i++){
			tempCollection.addModifier(mod);
		}
		
		String[] dmgTypes = {};
		
		double result;
		if(mod.isResist() == true){
			result = calculate(damage, dmgTypes, tempCollection, new ModifierCollection());
		}
		else result = calculate(damage, dmgTypes, new ModifierCollection(), tempCollection);
		
		view.setQuickResult(new Double(result).toString());
	}
	
	/**
	 * Calls the algorithms needed for the calculation, based on current program settings.
	 * 
	 * @param dmg_par - initial damage to be dealt
	 * @param dmgTypes - damage types as list of Strings.
	 * @param resists - resists as ModiferCollection
	 * @param weaks - weaknesses as ModiferCollection
	 * @return - final damage from the calculation
	 */
	private double calculate(int dmg_par, String[] dmgTypes, ModifierCollection resists, ModifierCollection weaks){
		
		
		double dmg = dmg_par;
		
		gameData = new GameExampleData();
		gameData.setDamage(dmg_par);
		
		if(view.getAlgorithmType() == Algorithms.ALGORITHM_MULTIPLY){
			dmg = Algorithms.calculateMultiply(this, dmg_par, dmgTypes, resists, weaks);
		}
		else{
			dmg = Algorithms.calculateAdd(this, dmg_par, dmgTypes, resists, weaks);
		}
		
		if(view.getAlgorithmRandomness() == true){
			dmg = Algorithms.addRandomFactor(dmg);
			if(dmg < 0){
				dmg = 0;
			}
		}
		
		gameData.setFinalDmg(dmg);
		return dmg;
	}
	
	public void addGameDataResist(String name, double amount){
		gameData.addResist(name, amount);
	}
	
	public void addGameDataWeakness(String name, double amount){
		gameData.addWeakness(name, amount);
	}
	
}
