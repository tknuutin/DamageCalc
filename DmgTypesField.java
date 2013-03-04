package core;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.InputVerifier;
import javax.swing.JComponent;

/**
 * Class defines the valid inputs for damage type fields, by wrapping InputVerifier.
 * 
 * @author Tarmo
 *
 */
class DmgTypesVerifier extends InputVerifier{
	
	public boolean shouldYieldFocus(JComponent input){
		boolean isInputValid = verify(input);
		if (isInputValid == false){
			JTextField tf = (JTextField) input;
			tf.setText("");
		}
		return true;
	}
	
	public boolean verify(JComponent input){
		JTextField tf = (JTextField) input;
		String text = tf.getText();
		if (text.contains(",,")){
			return false;
		}
		else{
			return true;
		}
	}

}

/**
 * Custom JTextField wrapper for layout and input verification purposes. Creates a JTextField that only accepts 
 * lists of strings separated by ", ". Each string is treated as its own damage type.
 * 
 * @author Tarmo
 *
 */
public class DmgTypesField extends JTextField{
	
	private static final long serialVersionUID = 7885627248872897529L;

	/**
	 * 
	 */
	/**
	 * Default constructor that sets up the data field.
	 */
	DmgTypesField(){
		setInputVerifier(new DmgTypesVerifier());
		setPreferredSize(new Dimension(150, 20));
	}
	
	/**
	 * Method used for returning the list of Strings from the data field.
	 * 
	 * @return - list of Strings
	 */
	public String[] getList(){
		String text = getText();
		
		if (text == "" || text == null){
			return new String[]{};
		}
		else{
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(text.split(",")));
			for (int i = 0; i < list.size(); i++){
				list.set(i, list.get(i).trim());
			}
			for (int i = 0; i < list.size(); i++){
				if(list.get(i).length() == 0){
					list.remove(i);
				}
			}
			
			return list.toArray(new String[]{});
		}
	}

}
