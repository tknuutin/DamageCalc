package core;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Custom JPanel that displays the calculation and game example buttons and contains the ResultPanel.
 * 
 * @author Tarmo
 *
 */
class CalculationPanel extends JPanel{
	
	private static final long serialVersionUID = 1392985150528341912L;
	private ResultLabel result;
	private JButton calcButton;
	private JButton gameButton;
	private JButton helpButton;
	
	/**
	 * Default constructor. Constructs and formats a working CalculationPanel with no parameters.
	 */
	CalculationPanel(){
		
		setLayout(new GridBagLayout());
		
		JPanel lowerPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		calcButton = new JButton("Calculate");
		calcButton.setToolTipText("Calculate result");
		gameButton = new JButton("Show game example");
		gameButton.setToolTipText("Launch game example window");
		gameButton.setEnabled(false);
		lowerPanel.add(calcButton);
		lowerPanel.add(gameButton);
		
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 10;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 0, 0, 0);
		add(lowerPanel, c);
		
		result = new ResultLabel();
		c.insets = new Insets(20, 10, 20, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(result, c);
		
		helpButton = new JButton("Help");
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 10, 0, 10);
		c.gridx = 1;
		c.gridy = 1;
		add(helpButton, c);
		
		
	}
	
	/**
	 * Set the ResultPanel view into specified string. ResultPanel will take care of the final formatting.
	 * 
	 * @param text - result as string
	 */
	public void setResult(String text){
		result.setResult(text);
	}
	
	public JButton getCalcButton(){
		return calcButton;
	}
	
	public JButton getGameButton(){
		return gameButton;
	}
	
	public JButton getHelpButton(){
		return helpButton;
	}

}

