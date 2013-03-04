package core;

import java.util.ArrayList;

/**
 * Custom collection of Modifiers. 
 * 
 * @author Tarmo
 *
 */
public class ModifierCollection{
	
	ArrayList<Modifier> data;
	
	/**
	 * Formats the collection with an empty content.
	 */
	ModifierCollection(){
		data = new ArrayList<Modifier>();
	}
	
	/**
	 * Append a modifier.
	 * 
	 * @param mod - modifier to be appended.
	 */
	public void addModifier(Modifier mod){
		data.add(mod);
	}
	
	/**
	 * Removes the modifier at specified index. Exception safe.
	 * 
	 * @param index
	 */
	public void removeModifierAt(int index){
		if (index <= data.size() - 1){
			data.remove(index);
		}
	}
	
	/**
	 * Get Modifier at index.
	 * 
	 * @param index
	 * @return
	 */
	public Modifier get(int index){
		return data.get(index);
	}
	
	/**
	 * Get size of the collection.
	 * 
	 * @return
	 */
	public int getSize(){
		return data.size();
	}
	
	/**
	 * Get the member at index as an Object array.
	 * 
	 * @param index
	 * @return
	 */
	public Object[] getObjectArrayAt(int index){
		return data.get(index).getObjectArray();
	}
	
	/**
	 * Replace Modifier at index.
	 * 
	 * @param index - index of the modifier to be replaced
	 * @param newMod - replacing modifier.
	 */
	public void replaceAt(int index, Modifier newMod){
		data.set(index, newMod);
	}
	
	public boolean isEmpty(){
		return data.isEmpty();
	}
}
