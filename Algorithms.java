package core;

import java.util.Random;

/**
 * Class contains a collection of methods used to actually calculate the damage. 
 * 
 * @author Tarmo
 *
 */
public class Algorithms {
	
	public final static int ALGORITHM_MULTIPLY = 0;
	public final static int ALGORITHM_ADD = 1;
	public final static double THRESHOLD = 0.8;
	
	private Algorithms(){
		//this will never be called!
	}
	
	/**
	 * Checks if at least one of the of the members of the "mod" array is found in the "dmg" array.
	 * Returns true if found, false otherwise.
	 * 
	 * @param dmg - damage types of the damage inflicted
	 * @param mod - damage types of the modifier
	 * @return - whether one of the damage types of the modifier is found in the damage types
	 */
	public static boolean checkDmgTypeMatch(String[] dmg, String[] mod){
		
		if(mod.length == 0){
			return true;
		}
		else if(dmg.length == 0){
			return false;
		}
		else{
			boolean matchFound = false;
			
			for(int i = 0; i < mod.length; i++){
				for(int j = 0; j < dmg.length; j++){
					if(mod[i].equals(dmg[j])){
						matchFound = true;
					}
				}
			}
			return matchFound;
		}
	}
	
	/**
	 * Calculates the damage by separately multiplying the results and adding them together.. Results is 
	 * damage - (damage - damage * resists) + (damage * weaknesses - damage). 
	 * 
	 * @param owner - The object that handles the game example data generated by the algorithms.
	 * @param dmg_par - Base damage inflicted.
	 * @param dmgTypes - Damage types of the damage inflicted.
	 * @param resists - List of resists.
	 * @param weaks - List of weaknesses.
	 * @return - resulting final damage.
	 */
	public static double calculateAdd(MainController owner, int dmg_par, String[] dmgTypes, ModifierCollection resists, ModifierCollection weaks){
		
		double dmgRes = dmg_par;
		
		if (weaks.isEmpty() == false){
			for (int i = 0; i < weaks.getSize(); i++){
				
				int base = weaks.get(i).getBase();
				int value = weaks.get(i).getValue();
				int multi = weaks.get(i).getMultiplier();
				boolean isBeneficial = weaks.get(i).isBeneficial();
				
				if( checkDmgTypeMatch(dmgTypes, weaks.get(i).getDmgTypes()) == true){
					double temp = calculateWeakness(dmgRes, base, value, multi, isBeneficial);
					owner.addGameDataWeakness(weaks.get(i).getName(), temp-dmgRes);
					dmgRes = temp;
				}
			}
		}
		dmgRes = dmg_par - dmgRes;
		
		double dmgWeak = dmg_par;
		
		if (resists.isEmpty() == false){
			for (int i = 0; i < resists.getSize(); i++){
				
				int base = resists.get(i).getBase();
				int value = resists.get(i).getValue();
				int multi = resists.get(i).getMultiplier();
				boolean isBeneficial = resists.get(i).isBeneficial();
				
				if( checkDmgTypeMatch(dmgTypes, resists.get(i).getDmgTypes()) == true){
					double temp = calculateResist(dmgWeak, base, value, multi, isBeneficial);
					owner.addGameDataResist(resists.get(i).getName(), dmgWeak-temp);
					dmgWeak = temp;
				}
			}
		}
		dmgWeak = dmgWeak - dmg_par;
		
		if(dmgWeak == dmgRes){
			return dmg_par;
		}
		else{
			return dmg_par - dmgRes + dmgWeak;
		}
	}
	
	
	/**
	 * Calculates the damage by multiplying the different resists and weaknesses together. Results is damage * (resists)
	 * * (weaknesses). 
	 * 
	 * @param owner - The object that handles the game example data generated by the algorithms.
	 * @param dmg_par - Base damage inflicted.
	 * @param dmgTypes - Damage types of the damage inflicted.
	 * @param resists - List of resists.
	 * @param weaks - List of weaknesses.
	 * @return - resulting final damage.
	 */
	public static double calculateMultiply(GameExampleOwner owner, int dmg_par, String[] dmgTypes, ModifierCollection resists, ModifierCollection weaks){
		
		double dmg = dmg_par;
		
		if (resists.isEmpty() == false){
			for (int i = 0; i < resists.getSize(); i++){
				
				int base = resists.get(i).getBase();
				int value = resists.get(i).getValue();
				int multi = resists.get(i).getMultiplier();
				boolean isBeneficial = resists.get(i).isBeneficial();
				
				if( checkDmgTypeMatch(dmgTypes, resists.get(i).getDmgTypes()) == true){
					double temp = calculateResist(dmg, base, value, multi, isBeneficial);
					owner.addGameDataResist(resists.get(i).getName(), dmg-temp);
					dmg = temp;
				}
			}
		}
		
		if (weaks.isEmpty() == false){
			for (int i = 0; i < weaks.getSize(); i++){
				
				int base = weaks.get(i).getBase();
				int value = weaks.get(i).getValue();
				int multi = weaks.get(i).getMultiplier();
				boolean isBeneficial = weaks.get(i).isBeneficial();
				
				if( checkDmgTypeMatch(dmgTypes, weaks.get(i).getDmgTypes()) == true){
					double temp = calculateWeakness(dmg, base, value, multi, isBeneficial);
					owner.addGameDataWeakness(weaks.get(i).getName(), temp-dmg);
					dmg = temp;
				}
			}
		}
		return dmg;
	}
	
	/**
	 * Generates adds a random factor to a double. Variation is +-2.5.
	 * 
	 * @param dmg - original damage
	 * @return - damage with random factor added
	 */
	public static double addRandomFactor(double dmg){
		Random gen = new Random();
		double add = (gen.nextInt(100) - 50) * 0.1;
		dmg = dmg + add;
		return dmg;
	}
	
	/**
	 * Algorithm used for calculating weaknesses.
	 * 
	 * @param startDmg - base damage.
	 * @param base - modifier base.
	 * @param val - modifier value.
	 * @param multiOrig - multiplier
	 * @param isBeneficial - beneficial boolean.
	 * @return - resulting damage
	 */
	private static double calculateWeakness(double startDmg, double base, double val, int multiOrig, boolean isBeneficial){
		double result;
		
		if (val == 0){
			result = 0;
		}
		else{
			
			double multi;
			switch(multiOrig){ // convert multiplier to actual values used for calculation
				case 1:
					multi = 1.5;
					break;
				case 2:
					multi = 2.2;
					break;
				case 3:
					multi = 3.1;
					break;
				case 4:
					multi = 4.0;
					break;
				case 5:
					multi = 5.2;
					break;
				default:
					multi = 1.5;
					break;
			}
			
			if(isBeneficial){ // weakness is beneficial, therefore damage taken should go DOWN as value rises
				result = Math.pow((THRESHOLD), (val/multi * 0.1)) * startDmg;
				result = result / 1.2;
				result = startDmg + result;
			}
			else{ // weakness is non-beneficial, therefore damage taken should go UP as value rises
				double breakPoint = 100 + ((multiOrig - 1) * 10);
				if (val < breakPoint){ // if value is below the breakpoint, use smooth exponential curve
					result = Math.pow(THRESHOLD, (val/multi * 0.1)) * startDmg;
					result = startDmg + (startDmg - result);
				}
				else{ // if value is above breakpoint, use line with specific curve slope. ensures no damage ceiling with big values
					result = Math.pow(THRESHOLD, (breakPoint/multi * 0.1)) * startDmg;
					result = startDmg + (startDmg - result);
					
					double curveSlope;
					switch(multiOrig){
						case 1:
							curveSlope = 0.4;
							break;
						case 2:
							curveSlope = 0.32;
							break;
						case 3:
							curveSlope = 0.3;
							break;
						case 4:
							curveSlope = 0.225;
							break;
						case 5:
							curveSlope = 0.21;
							break;
						default:
							curveSlope = 0.18;
							break;
					}
					
					result = result + (curveSlope * (val-breakPoint));
				}
			}
			
		}
		result = result * (100 + base) * 0.01;
		return result;
	}
	
	/**
	 * Algorithm used for calculating resists..
	 * 
	 * @param startDmg - base damage.
	 * @param base - modifier base.
	 * @param val - modifier value.
	 * @param multiOrig - multiplier
	 * @param isBeneficial - beneficial boolean.
	 * @return - resulting damage
	 */
	private static double calculateResist(double startDmg, double base, double val, int multiOrig, boolean isBeneficial){
		double result;
		
		if (val == 0){
			result = 0;
		}
		else{
			
			double multi;
			switch(multiOrig){ // convert multiplier to actual values used for calculation
				case 1:
					multi = 1.5;
					break;
				case 2:
					multi = 2.3;
					break;
				case 3:
					multi = 3.5;
					break;
				case 4:
					multi = 4.2;
					break;
				case 5:
					multi = 5.0;
					break;
				default:
					multi = 1.5;
					break;
			}
			
			double dmg;
			
			if(isBeneficial){ // if resist is beneficial, ensure damage floor to be set correctly
				dmg = startDmg * (100 - base) * 0.01;
			}
			else dmg = startDmg; 
			
			result = Math.pow((THRESHOLD), ( (val/multi) * 0.1)) * dmg;		
			if (!isBeneficial){ // if resist is non-beneficial, ensure damage ceiling to be set correctly
				result = startDmg - result;
				result = result * (100 - base) * 0.01;
			}
			
		}
		return result;
	}

}
