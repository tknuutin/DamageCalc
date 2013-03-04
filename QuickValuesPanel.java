package core;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * Custom JPanel wrapper for creating a panel for inputting values for the Quick tab.
 * Includes value input fields and sliders.
 * 
 * @author Tarmo
 *
 */
public class QuickValuesPanel extends JPanel{
	
	private static final long serialVersionUID = 3581659494746703611L;
	private JTextField baseField;
	private JTextField valField;
	private JTextField dmgField;
	private JSlider mulSlider;
	private JCheckBox benCheckBox;
	private JSlider numSlider;

	/**
	 * Default constructor creates and formats the panel.
	 */
	QuickValuesPanel(){
		
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new GridBagLayout());
		
		JPanel llabelPanel = createLeftLabelPanel();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 20, 0, 0);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 0;
		add(llabelPanel, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		
		JPanel lfieldPanel = createLeftFieldPanel();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 20;
		c.weighty = 10;
		c.gridx = 1;
		c.gridy = 0;
		add(lfieldPanel, c);
		
		JPanel rlabelPanel = createRightLabelPanel();
		c = new GridBagConstraints();
		c.insets = new Insets(28, 20, 0, 0);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 2;
		c.gridy = 0;
		add(rlabelPanel, c);
		
		c.insets = new Insets(28, 0, 0, 20);
		
		JPanel rfieldPanel = createRightFieldPanel();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 20;
		c.weighty = 10;
		c.gridx = 3;
		c.gridy = 0;
		add(rfieldPanel, c);
		
	}
	
	/**
	 * Create and return the left label panel for the fields.
	 * 
	 * @return
	 */
	protected JPanel createLeftLabelPanel(){
		
		JLabel dmgLabel = new JLabel("Damage base");
		JLabel baseLabel = new JLabel("Modifier base");
		JLabel valLabel = new JLabel("Modifier value");
		JLabel mulLabel = new JLabel("Multiplier");
		
		dmgLabel.setHorizontalAlignment(JLabel.RIGHT);
		baseLabel.setHorizontalAlignment(JLabel.RIGHT);
		valLabel.setHorizontalAlignment(JLabel.RIGHT);
		mulLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(0, 0, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(dmgLabel, c);
		c.insets = new Insets(10, 0, 10, 10);
		c.gridy = 1;
		panel.add(baseLabel, c);
		c.gridy = 2;
		panel.add(valLabel, c);
		c.insets = new Insets(20, 0, 8, 10);
		c.gridy = 3;
		panel.add(mulLabel, c);
		
		return panel;
	}
	
	/**
	 * Create and return the panel of input fields.
	 * 
	 * @return
	 */
	protected JPanel createLeftFieldPanel(){
		
		dmgField = new JTextField();
		dmgField.setToolTipText("Initial damage inflicted on player. Between 1 and 10000");
		dmgField.setInputVerifier(new ValuesVerifier(10000));
		dmgField.setText("100");
		
		baseField = new JTextField();
		baseField.setToolTipText("Base modifier as percentage. Between 1 and 99");
		baseField.setInputVerifier(new ValuesVerifier(99));
		baseField.setText("10");
		
		valField = new JTextField();
		valField.setToolTipText("Value of the modifier. Between 1 and 999");
		valField.setInputVerifier(new ValuesVerifier(1000));
		valField.setText("5");
		mulSlider = new CustomSlider(1, 5, 1);
		
		Dimension dim = new Dimension(60, 20);
		dmgField.setPreferredSize(dim);
		baseField.setPreferredSize(dim);
		valField.setPreferredSize(dim);
		
		dim = new Dimension(100, 60);
		mulSlider.setToolTipText("Multiplier. Larger value decreases modifier effect");
		mulSlider.setPreferredSize(dim);
		mulSlider.setValue(3);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(35, 0, 8, 0);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(dmgField, c);
		c.insets = new Insets(9, 0, 8, 0);
		c.gridy = 1;
		panel.add(baseField, c);
		c.gridy = 2;
		panel.add(valField, c);
		c.gridy = 3;
		c.insets = new Insets(9, 0, 8, 0);
		panel.add(mulSlider, c);
		
		return panel;
	}
	
	/**
	 * Create and return right label panel.
	 * 
	 * @return
	 */
	protected JPanel createRightLabelPanel(){
		
		JPanel panel = new JPanel(new GridBagLayout());
		JLabel benLabel = new JLabel("Beneficial?");
		JLabel numLabel = new JLabel("Number of modifiers");
		
		benLabel.setHorizontalAlignment(JLabel.RIGHT);
		numLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(10, 0, 16, 10);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(benLabel, c);
		c.gridy = 1;
		c.insets = new Insets(2, 0, 45, 10);
		panel.add(numLabel, c);
		
		return panel;
	}
	
	/**
	 * Create and return right panel of value fields.
	 * 
	 * @return
	 */
	protected JPanel createRightFieldPanel(){
	
		benCheckBox = new JCheckBox();
		benCheckBox.setToolTipText("Does a bigger modifier value mean less damage taken?");
		benCheckBox.setSelected(true);
		numSlider = new CustomSlider(1, 5, 1);
		numSlider.setToolTipText("Number of identical modifiers applied");
		
		Dimension dim = new Dimension(100, 60);
		numSlider.setPreferredSize(dim);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(9, 0, 8, 0);
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(benCheckBox, c);
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = 1;
		panel.add(numSlider, c);
		
		return panel;
	}
	
	/**
	 * Get the current value of the base field.
	 * 
	 * @return
	 */
	public int getBase(){
		return Integer.parseInt(baseField.getText());
	}
	
	/**
	 * Get the current value of the value field.
	 * 
	 * @return
	 */
	public int getValue(){
		return Integer.parseInt(valField.getText());
	}
	
	/**
	 * Get the current value of the damage field.
	 * 
	 * @return
	 */
	public int getDamage(){
		return Integer.parseInt(dmgField.getText());
	}
	
	/**
	 * Get the current value of the multiplier slider.
	 * 
	 * @return
	 */
	public int getMultiplier(){
		return mulSlider.getValue();
	}
	
	/**
	 * Get the current value of the beneficial toggle.
	 * 
	 * @return
	 */
	public boolean getBeneficial(){
		return benCheckBox.isSelected();
	}
	
	/**
	 * Get the current value of the number of modifiers -slider.
	 * 
	 * @return
	 */
	public int getNumberOfMods(){
		return numSlider.getValue();
	}

}
