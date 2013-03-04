package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



/**
 * Class can be used as a container to store the data from calculation events. Data can then be used to partially randomly
 * create game example texts. Mostly for humorous purposes.
 * 
 * @author Tarmo
 *
 */
public class GameExampleData {
	
	private int dmg;
	private String enemy;
	private ArrayList<String> resNames;
	private ArrayList<Double> resAmount;
	private ArrayList<String> weakNames;
	private ArrayList<Double> weakAmount;
	private double finalDmg;
	
	/**
	 * Default constructor. Formats the object.
	 */
	GameExampleData(){
		dmg = 100;
		enemy = randomizeEnemy();
		resNames = new ArrayList<String>();
		weakNames = new ArrayList<String>();
		resAmount = new ArrayList<Double>();
		weakAmount = new ArrayList<Double>();
		finalDmg = 100;
	}
	
	/**
	 * A static convenience method that returns a random name of an enemy.
	 * 
	 * @return - name of an enemy as String
	 */
	public static String randomizeEnemy(){
		String[] list = {"A wizard", "A smelly hobo", "A bad guy", "Barack Obama", "The guy", "Your mother",
						 "The king", "My fist", "A traitorous leopard", "The floor", "The concept of a non-deterministic universe",
						 "Spaceman", "Percy Genjin", "Baramck Obana"};
		
		Random gen = new Random();
		if(list.length == 0){
			return "Something";
		}
		else return list[gen.nextInt(list.length)];
	}
	
	/**
	 * A static convenience method that returns a random name of an entity, for example to be used as a name of a
	 * resist or weakness.
	 * 
	 * @return - name of an entity as String
	 */
	public static String randomizeResist(){
		String[] list = {"Leather pants", "Your smell", "Styrofoam Armor", "A clever manouver to the left", "Jason", "A cat",
				"Confused elder man", "Your sweet moves", "Majyyk Ennyrjies", "Lvl. 5 Cardboard Shield", "Your enormous gut"};

		Random gen = new Random();
		if(list.length == 0){
			return "Something";
		}
		else return list[gen.nextInt(list.length)];
	}
	
	/**
	 * Creates extra text based on the data of the object, mostly for humorous purposes. Text is returned
	 * as an ArrayList.
	 * 
	 * @return - ArrayList of Strings containing extra text that should be displayed after the other data.
	 */
	public ArrayList<String> generateExtraText(){
		Random gen = new Random();
		ArrayList<String> wounded_parts = new ArrayList<String>(Arrays.asList(new String[]{"Face", "Left arm", "Right arm", "Right leg",
				"Torso", "Beard", "Treacle aegis", "Left ear", "Digestive organ"}));
		ArrayList<String> inc_parts = new ArrayList<String>(wounded_parts);
		ArrayList<String> list = new ArrayList<String>();
		
		inc_parts.add("Suit");
		int rand;
		String part;
		double finaldmg = getFinalDmg();
		
		if((finaldmg > 10 && gen.nextInt(10) > 5) || finaldmg > 40){
			rand = gen.nextInt(wounded_parts.size());
			part = wounded_parts.get(rand).toUpperCase();
			wounded_parts.remove(rand);
			list.add("Your " + part + " is wounded!");
		}
		if((finaldmg > 10 && gen.nextInt(10) > 8) || finaldmg > 40){
			rand = gen.nextInt(wounded_parts.size());
			part = wounded_parts.get(rand).toUpperCase();
			wounded_parts.remove(rand);
			list.add("Your " + part + " is wounded!");
		}
		
		if((finaldmg > 50 && gen.nextInt(10) > 7) || finaldmg > 90){
			rand = gen.nextInt(inc_parts.size());
			part = inc_parts.get(rand).toUpperCase();
			inc_parts.remove(rand);
			list.add("Your " + part + " is incinerated!");
		}
		if((finaldmg > 50 && gen.nextInt(10) > 7) || finaldmg > 90){
			rand = gen.nextInt(inc_parts.size());
			part = inc_parts.get(rand).toUpperCase();
			inc_parts.remove(rand);
			list.add("Your " + part + " is incinerated!");
		}
		if(finaldmg > 100){
			rand = gen.nextInt(inc_parts.size());
			part = inc_parts.get(rand).toUpperCase();
			inc_parts.remove(rand);
			list.add("Your " + part + " is incinerated!");
		}
		if(finaldmg > 150){
			rand = gen.nextInt(inc_parts.size());
			part = inc_parts.get(rand).toUpperCase();
			inc_parts.remove(rand);
			list.add("Your " + part + " is incinerated!");
		}
		if(finaldmg > 190){
			rand = gen.nextInt(inc_parts.size());
			part = inc_parts.get(rand).toUpperCase();
			inc_parts.remove(rand);
			list.add("Your " + part + " is incinerated!");
		}
		if(inc_parts.contains("Suit") && finaldmg > 100 && gen.nextInt(10) > 3){
			list.add("Your SUIT is incinerated!");
			inc_parts.remove(inc_parts.indexOf("Suit"));
			
			if(gen.nextInt(10) > 3){
				list.add("Your NUDE DICK is incinerated!");
			}
		}
		if(getFinalDmg() >= 100){
			list.add("You are incinerated!");
			list.add("You are dead!");
		}
		else{
			list.add("You have " + ResultLabel.getMaxDecimalString(Double.toString(100-getFinalDmg()), 2) + " health left!");
			if(getFinalDmg() > 30 && getFinalDmg() <= 50){
				list.add("You are now bleeding!");
			}
			else{
				if(getFinalDmg() < 80 && getFinalDmg() > 50){
					list.add("You are now bleeding profusely!");
				}
				else if(getFinalDmg() > 80){
					list.add("You are now bleeding like a faucet!");
				}
			}
		}
		if(getEnemy() == "Spaceman"){
			list.add("");
			list.add(getEnemy().toUpperCase() + " reloads his strange gun.");
		}
		
		return list;
	}
	
	public void setDamage(int dmg){
		this.dmg = dmg;
	}
	
	/**
	 * Adds a resist to the list of data.
	 * 
	 * @param name - name of the resist
	 * @param amount - amount of dmg nullified as double
	 */
	public void addResist(String name, double amount){
		resNames.add(name);
		resAmount.add(new Double(amount));
	}
	
	/**
	 * Adds a weakness to the list of data.
	 * 
	 * @param name - name of the weakness
	 * @param amount - amount of dmg created as double
	 */
	public void addWeakness(String name, double amount){
		weakNames.add(name);
		weakAmount.add(new Double(amount));
	}

	public int getDmg() {
		return dmg;
	}

	public double getFinalDmg() {
		return finalDmg;
	}

	public void setFinalDmg(double finalDmg) {
		this.finalDmg = finalDmg;
	}

	public String getEnemy() {
		return enemy;
	}

	public ArrayList<String> getResNames() {
		return resNames;
	}

	public ArrayList<Double> getResAmounts() {
		return resAmount;
	}

	public ArrayList<String> getWeakNames() {
		return weakNames;
	}

	public ArrayList<Double> getWeakAmounts() {
		return weakAmount;
	}

}
