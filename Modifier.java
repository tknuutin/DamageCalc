package core;

import java.util.ArrayList;

/**
 * Modifier describes either a resist or a weakness - an entity that has a modifying effect on the damage values.
 * Modifiers have a complicated set of attributes that determine the final modifier.
 * 
 * Base - base modifier. Should be between 1 and 99. Means that the modifier will always be at least this percentage.
 * 
 * Value - value of the modifier. This determines the strength of the modifier on top of the base.
 * 
 * Beneficial - a boolean value determining if the modifying will cause the damage value to go down, or up.
 * Modifiers can have varying behavior depending on what the combination of modifier type and beneficiality is.
 * 
 * Multiplier - The bigger the multiplier, the less effect a modifier has.
 * 
 * @author Tarmo
 *
 */
public class Modifier {
	
	public final static int TYPE_RESIST = 0;
	public final static int TYPE_WEAKNESS = 1;
	protected int type;
	protected String name;
	protected int base;
	protected int value;
	protected boolean beneficial;
	protected int multiplier;
	protected ArrayList<String> dmgTypes;
	
	
	/**
	 * Full constructor for object. All parameters are present. DmgTypes will be copied into the list of dmgtypes.
	 * 
	 * @param type - either Modifier.TYPE_RESIST or Modifier.TYPE_WEAKNESS. 
	 * @param name - name of the modifier as string. Purely cosmetic.
	 * @param base - base modifier. Should be between 1 and 99.
	 * @param value - value of the modifier. Should be 1 or bigger.
	 * @param beneficial - beneficial or not?
	 * @param multiplier - between 1 and 5. Bigger means less effect.
	 * @param dmgTypes - list of damage types as string.
	 */
	Modifier(int type, String name, int base, int value, boolean beneficial, int multiplier, String dmgTypes[]){
		if (type == TYPE_RESIST || type == TYPE_WEAKNESS){
			this.type = type;
		}
		else this.type = 0;
		this.name = name;
		this.base = base;
		this.value = value;
		this.beneficial = beneficial;
		this.multiplier = multiplier;
		
		this.dmgTypes = new ArrayList<String>();
		for (int i = 0; i < dmgTypes.length; i++){
			this.dmgTypes.add(dmgTypes[i]);
		}
	}
	
	/**
	 * Single dmgType constructor.
	 * 
	 * @param type - either Modifier.TYPE_RESIST or Modifier.TYPE_WEAKNESS. 
	 * @param name - name of the modifier as string. Purely cosmetic.
	 * @param base - base modifier. Should be between 1 and 99.
	 * @param value - value of the modifier. Should be 1 or bigger.
	 * @param beneficial - beneficial or not?
	 * @param multiplier - between 1 and 5. Bigger means less effect.
	 * @param dmgType - single dmgType as String.
	 */
	Modifier(int type, String name, int base, int value, boolean beneficial, int multiplier, String dmgType){
		if (type == TYPE_RESIST || type == TYPE_WEAKNESS){
			this.type = type;
		}
		else this.type = 0;
		this.name = name;
		this.base = base;
		this.value = value;
		this.beneficial = beneficial;
		this.multiplier = multiplier;
		
		dmgTypes = new ArrayList<String>();
		this.dmgTypes.add(dmgType);
	}
	
	/**
	 * No dmgType constructor.
	 * 
	 * @param type - either Modifier.TYPE_RESIST or Modifier.TYPE_WEAKNESS. 
	 * @param name - name of the modifier as string. Purely cosmetic.
	 * @param base - base modifier. Should be between 1 and 99.
	 * @param value - value of the modifier. Should be 1 or bigger.
	 * @param beneficial - beneficial or not?
	 * @param multiplier - between 1 and 5. Bigger means less effect.
	 */
	Modifier(int type, String name, int base, int value, boolean beneficial, int multiplier){
		if (type == TYPE_RESIST || type == TYPE_WEAKNESS){
			this.type = type;
		}
		else this.type = 0;
		this.name = name;
		this.base = base;
		this.value = value;
		this.beneficial = beneficial;
		this.multiplier = multiplier;
		
		dmgTypes = new ArrayList<String>();
	}
	
	/**
	 * No name constructor. Name will be default.
	 * 
	 * @param type - either Modifier.TYPE_RESIST or Modifier.TYPE_WEAKNESS. 
	 * @param base - base modifier. Should be between 1 and 99.
	 * @param value - value of the modifier. Should be 1 or bigger.
	 * @param beneficial - beneficial or not?
	 * @param multiplier - between 1 and 5. Bigger means less effect.
	 */
	Modifier(int type, int base, int value, boolean beneficial, int multiplier){
		if (type == 0 || type == 1){
			this.type = type;
		}
		else this.type = 0;
		this.name = "Something";
		this.base = base;
		this.value = value;
		this.beneficial = beneficial;
		this.multiplier = multiplier;
		dmgTypes = new ArrayList<String>();
	}
	
	/**
	 * Default constructor for placeholder modifiers.
	 */
	Modifier(){
		this.type = 0;
		this.name = "Something";
		this.base = 10;
		this.value = 10;
		this.beneficial = true;
		this.multiplier = 1;
		dmgTypes = new ArrayList<String>();
	}
	
	/**
	 * Return the dmgType at index.
	 * 
	 * @param index
	 * @return
	 */
	public String getDamageTypeAt(int index){
		return dmgTypes.get(index);
	}
	
	/**
	 * Returns number of dmgTypes.
	 * 
	 * @return
	 */
	public int getDamageTypeAmount(){
		return dmgTypes.size();
	}
	
	/**
	 * Returns all damage types as a string deparated by ", "
	 * 
	 * @return - string of all dmgypes
	 */
	public String getDmgTypesString(){
		
		if (isGlobal()){
			return "GLOBAL";
		}
		else{
			String s = "";
			for (int i = 0; i < dmgTypes.size(); i++){
				s = s + dmgTypes.get(i);
				if (i < dmgTypes.size() - 1){
					s = s + ", ";
				}
			}
			return s;
		}
	}
	
	/**
	 * Converts Modifier attributes into an array of Objects.
	 * 
	 * @return - Modifier as an array of Objects.
	 */
	public Object[] getObjectArray(){
		
		Object[] array = new Object[]{name,
									  new Integer(base),
									  new Integer(value),
									  new Boolean(beneficial),
									  new Integer(multiplier),
									  getDmgTypesString(),
									 };
		return array;
	}
	
	/**
	 * Returns True if Modifier is resist, false otherwise.
	 * 
	 * @return
	 */
	public boolean isResist(){
		if (type == TYPE_RESIST){
			return true;
		}
		else return false;
	}
	
	/**
	 * Returns True if Modifier is weakness, false otherwise.
	 * 
	 * @return
	 */
	public boolean isWeakness(){
		if (type == TYPE_WEAKNESS){
			return true;
		}
		else return false;
	}
	
	/**
	 * Returns True if modifier has no dmgtypes.
	 * 
	 * @return
	 */
	public boolean isGlobal(){
		return dmgTypes.isEmpty();
	}
	
	public boolean isBeneficial(){
		return beneficial;
	}

	public String getName() {
		return name;
	}

	public int getBase() {
		return base;
	}

	public int getValue() {
		return value;
	}

	public int getMultiplier() {
		return multiplier;
	}
	
	public String[] getDmgTypes(){
		return dmgTypes.toArray(new String[]{});
	}

}
