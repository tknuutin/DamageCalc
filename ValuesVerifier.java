package core;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Custom InputVerifier wrapper for verifying number input fields.
 * 
 * @author Tarmo
 *
 */
public class ValuesVerifier extends InputVerifier{
	
	private int maximum;
	
	/**
	 * Constructs the ValuesVerifier with the maximum number allowed. Numbers allowed will be 1 - max.
	 * 
	 * @param max
	 */
	ValuesVerifier(int max){
		maximum = max;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.InputVerifier#shouldYieldFocus(javax.swing.JComponent)
	 */
	public boolean shouldYieldFocus(JComponent input){
		boolean isInputValid = verify(input);
		if (isInputValid == false){
			JTextField tf = (JTextField) input;
			tf.setText("1");
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.InputVerifier#verify(javax.swing.JComponent)
	 */
	public boolean verify(JComponent input){
		JTextField tf = (JTextField) input;
		int num = 0;
		try{
			num = Integer.parseInt(tf.getText());
		}
		catch (NumberFormatException nfe){
			return false;
		}
		if (num < 1 || num > maximum){
			return false;
		}
		else return true;
	}
}

