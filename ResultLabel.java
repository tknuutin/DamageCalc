package core;

import java.awt.Font;

import javax.swing.JLabel;

/**
 * Class defines the result display. Dynamically formats the display with regard to content.
 * Prefaces input with 'Damage taken: '
 * 
 * @author Tarmo
 *
 */
public class ResultLabel extends JLabel{
	
	private static final long serialVersionUID = -5208035056411740480L;
	private static String resultPreface = "Damage taken: ";
	
	/**
	 * Constructs ResultLabel with no value.
	 */
	ResultLabel(){
		super(resultPreface);
		format();
	}
	
	/**
	 * Constructs ResultLabel with the specified value.
	 * 
	 * @param num
	 */
	ResultLabel(int num){
		super(resultPreface + num);
		format();
	}
	
	/**
	 * Constructs ResultLabel with the specified value.
	 * 
	 * @param num
	 */
	ResultLabel(double num){
		super(resultPreface + new Double(num).toString());
		format();
	}
	
	/**
	 * Format the ResultLabel.
	 */
	private void format(){
		setFont(new Font("Arial Bold", Font.PLAIN, 20));
		setVerticalAlignment(JLabel.CENTER);
	}
	
	/**
	 * Set the result to specified text. Text will be prefaced by the label preface and decimals might be cut off from doubles.
	 * 
	 * @param result - value of the result field to set
	 */
	public void setResult(String result){
		this.setText(resultPreface + getMaxDecimalString(result, 2));
	}
	
	/**
	 * Formats a double in string form to specified accuracy.
	 * 
	 * @param s - wanted double as String
	 * @param n - number of decimals wanted
	 * @return - resulting String
	 */
	public static String getMaxDecimalString(String s, int n){
		if(s.contains(".")){
			int loc = s.indexOf(".");
			if(s.length() > (loc + n)){
				s = s.substring(0, (loc+n+1));
			}
			return s;
		}
		else return s;
	}
	
	

}
