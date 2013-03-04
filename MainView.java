package core;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Custom JPanel wrapper that includes a value input and damage type field.
 * 
 * @author Tarmo
 *
 */
class ValueInputPanel extends JPanel{
	
	private static final long serialVersionUID = -2195058487199619017L;
	JTextField dmgField;
	DmgTypesField typeField;

	/**
	 * Construct value input panel with the specified string as label.
	 * 
	 * @param valueName - label for the value input
	 */
	ValueInputPanel(String valueName){
		super(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.NONE;
		c.weightx = 20;
		c.weighty = 20;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel(valueName), c);
		
		dmgField = new JTextField();
		dmgField.setToolTipText("Initial damage inflicted on player. Between 1 and 10000");
		dmgField.setText("100");
		c.insets = new Insets(0, 10, 0, 0);
		c.weightx = 50;
		c.ipadx = 60;
		c.ipady = 4;
		c.gridx = 1;
		add(dmgField, c);
		
		JLabel midText = new JLabel("with damage types");
		c.ipadx = 0;
		c.weightx = 20;
		c.gridx = 2;
		
		add(midText, c);
		
		typeField = new DmgTypesField();
		typeField.setToolTipText("Damage types separated by a comma");
		c.ipadx = 150;
		c.weightx = 70;
		c.gridx = 3;
		add(typeField, c);
		
	}
	
	/**
	 * Returns the current value of the damage field as String.
	 * 
	 * @return
	 */
	public String getDmgFieldValue(){
		return dmgField.getText();
	}
	
	/**
	 * Returns the current value of the damage field as Integer.
	 * 
	 * @return
	 */
	public int getDmgFieldValueAsInt(){
		Integer value = new Integer(dmgField.getText());
		return value;
	}
	
	public String[] getDamageTypes(){
		return typeField.getList();
	}
}

/**
 * The Main view class. Contains the layouts for the tabbed view and their contents.
 * 
 * @author Tarmo
 *
 */
public class MainView extends JFrame{
	
	private static final long serialVersionUID = -4337756735643911513L;
	private JPanel panel;
	private MainController ctrl;
	private ModifierTable resistTable;
	private ModifierTable weaknessTable;
	private MainView myself;
	private JRadioButton qresistRButton;
	private JRadioButton qweaknessRButton;
	private QuickValuesPanel qvaluesPanel;
	private CalculationPanel quickCalcPanel;
	private CalculationPanel advCalcPanel;
	private ValueInputPanel damagePanel;
	private JRadioButton algMultiply;
	private JRadioButton algAdd;
	private JCheckBox algRandom;

	/**
	 * Creates and formats the MainView. Takes a reference to the MainController as attribute.
	 * 
	 * @param controller - controlling entity
	 */
	MainView(MainController controller){
		super("Damage Calculator Beta");
		this.ctrl = controller;
		myself = this;
		
		panel = new JPanel();
		
		JTabbedPane tab = new JTabbedPane();
		tab.setPreferredSize(new Dimension(700, 440));
		JPanel quickPanel = createQuickTab();
		JPanel advPanel = createAdvancedTab();
		JPanel setPanel = createSettingsTab();
		
		tab.addTab("Quick", quickPanel);
		tab.addTab("Advanced", advPanel);
		tab.addTab("Settings", setPanel);
		
		tab.setSelectedIndex(0);
		
		setLayout(new GridLayout(1, 1));
		panel.add(tab);
		setContentPane(panel);
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
		
		addActionListeners();
		
		pack();
		setResizable(false);
	}
	
	public ModifierTable getResistTable() {
		return resistTable;
	}
	
	/**
	 * Appends the list of resists to the table on the advanced tab.
	 * 
	 * @param array - array to append
	 */
	public void appendToResists(Object[] array){
		resistTable.appendRow(array);
	}
	
	/**
	 * Appends the list of weaknesses to the table on the advanced tab.
	 * 
	 * @param array - array to append
	 */
	public void appendToWeakness(Object[] array){
		weaknessTable.appendRow(array);
	}
	
	/**
	 * List of general action listeners.
	 */
	protected void addActionListeners(){
		
		quickCalcPanel.getHelpButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.ctrl.launchQuickHelp();
            }
        });
		
		advCalcPanel.getHelpButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.ctrl.launchAdvHelp();
            }
        });
		
		resistTable.getAddButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.ctrl.launchAddResist();
            }
        });
		
		resistTable.getRemoveButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int num = myself.resistTable.getSelectedRowNum();
                if (num >= 0){
                	myself.ctrl.removeResist(num);
                	myself.resistTable.removeRow(num);
                }
            }
        });
		
		weaknessTable.getAddButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.ctrl.launchAddWeakness();
            }
        });
		
		weaknessTable.getRemoveButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int num = myself.weaknessTable.getSelectedRowNum();
                if (num >= 0){
                	myself.ctrl.removeWeakness(num);
                	myself.weaknessTable.removeRow(num);
                }
            }
        });
		
		resistTable.getEditButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int num = myself.resistTable.getSelectedRowNum();
                if (num >= 0){
                	myself.ctrl.launchEditResist(num);
                }
            }
        });
		
		weaknessTable.getEditButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	int num = myself.weaknessTable.getSelectedRowNum();
                if (num >= 0){
                	myself.ctrl.launchEditWeakness(num);
                }
            }
        });
		
		advCalcPanel.getCalcButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                myself.ctrl.advCalculate();
            }
		});
		
		quickCalcPanel.getCalcButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                myself.ctrl.quickCalculate();
            }
		});
		
		advCalcPanel.getGameButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                myself.ctrl.launchAdvGameExample();
            }
		});
		
		quickCalcPanel.getGameButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
                myself.ctrl.launchQuickGameExample();
            }
		});
	}
	
	/**
	 * Replaces the indicated resist row with the indicated object array.
	 * 
	 * @param rowNum - index of the row
	 * @param row - row as an array of objects.
	 */
	public void replaceResistInTable(int rowNum, Object[] row){
		resistTable.replaceRowAt(rowNum, row);
	}
	
	/**
	 * Replaces the indicated weakness row with the indicated object array.
	 * 
	 * @param rowNum - index of the row
	 * @param row - row as an array of objects.
	 */
	public void replaceWeaknessInTable(int rowNum, Object[] row){
		weaknessTable.replaceRowAt(rowNum, row);
	}
	
	/**
	 * Creates and formats the JPanel containing all the content of the Quick tab.
	 * 
	 * @return - Quick tab JPanel
	 */
	protected JPanel createQuickTab(){
		
		JPanel tpanel = new JPanel();
		tpanel.setLayout(new GridBagLayout());
		
		JPanel radioPanel = createModifierChoicePanel();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20, 20, 0, 0);
		c.fill = GridBagConstraints.NONE;
		c.weightx = 10;
		c.weighty = 10;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		tpanel.add(radioPanel, c);
		
		c.insets = new Insets(0, 0, 0, 0);
		
		qvaluesPanel = new QuickValuesPanel();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 10;
		c.weighty = 80;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 20;
		tpanel.add(qvaluesPanel, c);
		
		c.gridwidth = 1;
		
		quickCalcPanel = new CalculationPanel();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 0, 0);
		c.gridwidth = 2;
		c.weightx = 50;
		c.weighty = 15;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		tpanel.add(quickCalcPanel, c);
		
		return tpanel;
	}
	
	/**
	 * Creates and formats the JPanel containing the JRadioButtons for selecting the modifier type on the Quick tab.
	 * 
	 * @return - modifier choice JPanel
	 */
	protected JPanel createModifierChoicePanel(){
		JPanel panel = new JPanel();
		qresistRButton = new JRadioButton();
		qweaknessRButton = new JRadioButton();
		ButtonGroup modifierChoice = new ButtonGroup();
		qresistRButton.setSelected(true);
		modifierChoice.add(qresistRButton);
		modifierChoice.add(qweaknessRButton);
		JLabel res = new JLabel("Resist");
		JLabel weak = new JLabel("Weakness");
		
		panel.add(res);
		panel.add(qresistRButton);
		panel.add(new JLabel("  "));
		panel.add(weak);
		panel.add(qweaknessRButton);
		
		return panel;
	}
	
	/**
	 * Creates and formats the JPanel containing all of the content of the Advanced tab.
	 * 
	 * @return - Advanced tab JPanel
	 */
	protected JPanel createAdvancedTab(){
		
		JPanel tpanel = new JPanel();
		tpanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		damagePanel = new ValueInputPanel("Damage: ");
		c.fill = GridBagConstraints.NONE;
		c.weightx = 50;
		c.weighty = 10;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20, 10, 10, 0);
		tpanel.add(damagePanel, c);
		
		c.ipadx = 0;
		
		resistTable = new ModifierTable("Resists");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 50;
		c.weighty = 70;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 30, 0, 0);
		tpanel.add(resistTable, c);
		
		weaknessTable = new ModifierTable("Weaknesses");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 30, 0, 0);
		tpanel.add(weaknessTable, c);
		
		advCalcPanel = new CalculationPanel();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 10, 0, 0);
		c.gridwidth = 2;
		c.weightx = 50;
		c.weighty = 10;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 3;
		tpanel.add(advCalcPanel, c);
		
		return tpanel;
	}
	
	/**
	 * Creates and formats the JPanel with all of the content of the Settings tab.
	 * 
	 * @return
	 */
	protected JPanel createSettingsTab(){
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		JPanel choicePanel = new JPanel(new GridBagLayout());
		TitledBorder border = BorderFactory.createTitledBorder("Algorithm settings");
		border.setTitleJustification(TitledBorder.LEFT);
		choicePanel.setBorder(border);
		
		JLabel mlabel = new JLabel("<html><b>Multiply mode</b> - less damage overall</html>");
		mlabel.setFont(new Font("Arial", Font.PLAIN, 12));
		mlabel.setToolTipText("Modifiers will be applied as: damage * resists * weaks");
		JLabel alabel = new JLabel("<html><b>Add mode</b> - more damage overall</html>");
		alabel.setFont(new Font("Arial", Font.PLAIN, 12));
		alabel.setToolTipText("Modifiers will be applied as: damage - (damage*resists) + (damage*weaks)");
		JLabel rlabel = new JLabel("<html><b>Enable randomness</b> - adds a small random element</html>");
		rlabel.setFont(new Font("Arial", Font.PLAIN, 12));
		rlabel.setToolTipText("Final damage will have a small variation. Can cause damage to be zero.");
		
		algMultiply = new JRadioButton();
		algMultiply.setToolTipText("Modifiers will be applied as: damage * a * b");
		algAdd = new JRadioButton();
		algAdd.setToolTipText("Modifiers will be applied as: damage - (damage*a) + (damage*b)");
		algRandom = new JCheckBox();
		algRandom.setSelected(false);
		algRandom.setToolTipText("Final damage will have a small variation. Can cause damage to be zero.");
		
		ButtonGroup algorithmChoice = new ButtonGroup();
		algMultiply.setSelected(true);
		algorithmChoice.add(algMultiply);
		algorithmChoice.add(algAdd);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.weightx = 10;
		c.weighty = 10;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 0);
		choicePanel.add(algMultiply, c);
		c.gridy = 1;
		choicePanel.add(algAdd, c);
		c.gridy = 2;
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 90;
		c.gridx = 1;
		c.gridy = 0;
		choicePanel.add(mlabel, c);
		c.gridy = 1;
		choicePanel.add(alabel, c);
		
		JPanel randomPanel = new JPanel(new GridBagLayout());
		//randomPanel.setBorder(BorderFactory.createEtchedBorder());
		c.fill = GridBagConstraints.NONE;
		c.weightx = 10;
		c.weighty = 10;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 0);
		randomPanel.add(algRandom, c);
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		randomPanel.add(rlabel, c);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.insets = new Insets(10, 0, 0, 0);
		choicePanel.add(randomPanel, c);
		c.gridwidth = 1;
		
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 10;
		c.weighty = 10;
		c.insets = new Insets(50, 50, 10, 0);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		choicePanel.setPreferredSize(new Dimension(360, 160));
		panel.add(choicePanel, c);
		
		return panel;
	}
	
	/**
	 * Constructs a Modifier from the current values of the Quick tab and returns it.
	 * 
	 * @return - Quick tab modifier
	 */
	public Modifier getQuickCalcModifier(){
		int type;
		if (qresistRButton.isSelected()){
			type = Modifier.TYPE_RESIST;
		}
		else type = Modifier.TYPE_WEAKNESS;
		
		int base = qvaluesPanel.getBase();
		int value = qvaluesPanel.getValue();
		int multiplier = qvaluesPanel.getMultiplier();
		boolean beneficial = qvaluesPanel.getBeneficial();
		
		return new Modifier(type, GameExampleData.randomizeResist(), base, value, beneficial, multiplier);
	}
	
	/**
	 * Enables the game example button on the Quick tab.
	 */
	public void enableQuickExample(){
		quickCalcPanel.getGameButton().setEnabled(true);
	}
	
	/**
	 * Enables the game example button on the Advanced tab.
	 */
	public void enableAdvExample(){
		advCalcPanel.getGameButton().setEnabled(true);
	}
	
	
	/**
	 * Returns the value on the damage panel on the Quick tab.
	 * 
	 * @return - damage as int
	 */
	public int getQuickDamage(){
		return qvaluesPanel.getDamage();
	}
	
	/**
	 * Returns the value on the damage panel on the Advanced tab.
	 * 
	 * @return - damage as int
	 */
	public int getAdvancedDamage(){
		return Integer.parseInt(damagePanel.getDmgFieldValue());
	}
	
	/**
	 * Returns the array of damage types from the Advanced tab.
	 * 
	 * @return - array of damage types
	 */
	public String[] getAdvancedDamageTypes(){
		return damagePanel.getDamageTypes();
	}
	
	
	/**
	 * Returns the value of the "Number of Modifiers" slider from the Quick tab.
	 * 
	 * @return - number of modifiers
	 */
	public int getQuickNumOfMods(){
		return qvaluesPanel.getNumberOfMods();
	}
	
	/**
	 * Sets the result display of the Quick tab to specified String.
	 * 
	 * @param text
	 */
	public void setQuickResult(String text){
		quickCalcPanel.setResult(text);
	}
	
	/**
	 * Sets the result display of the Advanced tab to specified String.
	 * 
	 * @param text
	 */
	public void setAdvancedResult(String text){
		advCalcPanel.setResult(text);
	}
	
	/**
	 * Returns the type of algorithm from the Settings tab. Possible values ALGORITHM_MULTIPLY and ALGORITHM_ADD.
	 * 
	 * @return - algorithm type
	 */
	public int getAlgorithmType(){
		if(algMultiply.isSelected() == true){
			return Algorithms.ALGORITHM_MULTIPLY;
		}
		else{
			return Algorithms.ALGORITHM_ADD;
		}
		
	}
	
	/**
	 * Whether algorithm randomness is enabled in the Settings tab.
	 * 
	 * @return - algorithm randomness enabled?
	 */
	public boolean getAlgorithmRandomness(){
		return algRandom.isSelected();
	}

}
