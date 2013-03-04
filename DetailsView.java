package core;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 * View class for the Add and Edit windows. Contains all the layout formatting and input handling.
 * 
 * @author Tarmo
 *
 */
public class DetailsView extends JDialog{
	
	private static final long serialVersionUID = 5593342508105629045L;
	protected DetailsController ctrl;
	protected DetailsView myself;
	protected JButton okButton;
	protected JButton cancelButton;
	protected JButton helpButton;
	protected JTextField nameField;
	protected JTextField dmgField;
	protected JTextField baseField;
	protected JTextField valField;
	protected JSlider mulSlider;
	protected JCheckBox benCheckBox;
	protected DmgTypesField typeField;
	protected JRadioButton globalButton;
	protected JRadioButton customButton;
	protected JLabel gtext;
	
	/**
	 * Creates DetailsView from two attributes. DetailsController is needed for control, MainView is needed for modality.
	 * 
	 * @param controller - controlling instance, the one who created this
	 * @param view - the MainView window that is the owner window.
	 */
	DetailsView(DetailsController controller, MainView view){
		super(view, "Add//Edit modifier", Dialog.ModalityType.DOCUMENT_MODAL);
		ctrl = controller;
		
		myself = this;
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 440));
		panel.setLayout(new GridBagLayout());
		
		JPanel setupPanel = new JPanel(new GridLayout(1, 2));
		setupPanel.add(createLabelPanel());
		setupPanel.add(createFieldPanel());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(setupPanel, c);
		
		c.weighty = 20;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(createDmgTypePanel(), c);
		
		JPanel bpanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		bpanel.add(okButton, c);
		bpanel.add(cancelButton, c);
		c.insets = new Insets(0, 20, 20, 20);
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		panel.add(bpanel, c);
		
		helpButton = new JButton("Help");
		c.insets = new Insets(0, 20, 25, 20);
		c.gridwidth = 1;
		c.gridx = 1;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		panel.add(helpButton, c);
		
		addActionListeners();
		
		setContentPane(panel);
		setResizable(false);
		
	}
	
	/**
	 * Method builds a resist object from the data currently help in the input fields.
	 * 
	 * @return - resist object, as Modifier
	 */
	protected Modifier buildResist(){
		
		String name = myself.nameField.getText();
    	if (name.isEmpty()) name = "Something";
    	
    	int base = Integer.parseInt(myself.baseField.getText());
    	int value = Integer.parseInt(myself.valField.getText());
    	int multiplier = myself.mulSlider.getValue();
    	boolean beneficial = myself.benCheckBox.isSelected();
    	
    	Modifier mod;
    	
    	if (myself.globalButton.isSelected()){
    		mod = new Modifier(Modifier.TYPE_RESIST, name, base, value, beneficial, multiplier);
    	}
    	else{
    		String dmgTypes[] = myself.typeField.getList();
    		
    		mod = new Modifier(Modifier.TYPE_RESIST, name, base, value, beneficial, multiplier, dmgTypes);
    	}
		
		return mod;
	}
	
	/**
	 * Method builds a weakness object from the data currently help in the input fields.
	 * 
	 * @return - weakness object, as Modifier
	 */
	protected Modifier buildWeakness(){
		
		String name = myself.nameField.getText();
    	if (name.isEmpty()) name = "Something";
    	
    	int base = Integer.parseInt(myself.baseField.getText());
    	int value = Integer.parseInt(myself.valField.getText());
    	int multiplier = myself.mulSlider.getValue();
    	boolean beneficial = myself.benCheckBox.isSelected();
    	
    	Modifier mod;
    	
    	if (myself.globalButton.isSelected()){
    		mod = new Modifier(Modifier.TYPE_WEAKNESS, name, base, value, beneficial, multiplier);
    	}
    	else{
    		String dmgTypes[] = myself.typeField.getList();
    		
    		mod = new Modifier(Modifier.TYPE_WEAKNESS, name, base, value, beneficial, multiplier, dmgTypes);
    	}
		
		return mod;
	}
	
	
	/**
	 * Formats the DetailsView for editing mode by defining actionListeners.
	 * 
	 * @param mod - the modifier to be edited.
	 */
	public void startEditMode(Modifier mod){
		
		nameField.setText(mod.getName());
		baseField.setText(Integer.toString(mod.getBase()));
		valField.setText(Integer.toString(mod.getValue()));
		mulSlider.setValue(mod.getMultiplier());
		benCheckBox.setSelected(mod.isBeneficial());
		
		if (mod.isGlobal()){
			globalButton.setSelected(true);
		}
		else{
			customButton.setSelected(true);
			typeField.setText(mod.getDmgTypesString());
		}
		
		if (mod.isResist()){
			this.setTitle("Edit resist");
			
			okButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	            	myself.ctrl.owner.editResist(buildResist());
	            	myself.setVisible(false);
	            }
	        });
		}
		else{
			this.setTitle("Edit weakness");
			
			okButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	            	myself.ctrl.owner.editWeakness(buildWeakness());
	            	myself.setVisible(false);
	            }
	        });
		}
		
	}
	
	/**
	 * Formats the DetailsView for add mode by defining actionListeners.
	 * 
	 * @param type - must be Modifier.TYPE_RESIST or Modifier.TYPE_WEAKNESS.
	 */
	public void startAddMode(int type){
		
		if (type == Modifier.TYPE_RESIST){
			this.setTitle("Add resist");
			
			okButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	            	myself.ctrl.owner.addResist(buildResist());
	            	myself.setVisible(false);
	            }
	        });
		}
		else{
			this.setTitle("Add weakness");
			benCheckBox.setSelected(false);
			
			okButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){  
	            	myself.ctrl.owner.addWeakness(buildWeakness());
	            	myself.setVisible(false);
	            }
	        });
		}
	}
	
	
	/**
	 * Method sets up the general actionListeners for the DetailsView.
	 */
	protected void addActionListeners(){
		
		helpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.ctrl.launchDetailsHelp();
            }
        });
		
		cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.setVisible(false);
            }
        });
		
		globalButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				myself.typeField.setEnabled(false);
				myself.gtext.setForeground(Color.BLACK);
			}
		});
		
		customButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				myself.typeField.setEnabled(true);
				myself.gtext.setForeground(Color.GRAY);
			}
		});
		
	}
	
	/**
	 * Method formats and creates a JPanel that holds the labels for the input fields.
	 * 
	 * @return - formatted label JPanel
	 */
	protected JPanel createLabelPanel(){
		
		JLabel nameLabel = new JLabel("Name");
		JLabel baseLabel = new JLabel("Modifier base");
		JLabel valLabel = new JLabel("Modifier value");
		JLabel mulLabel = new JLabel("Multiplier");
		
		nameLabel.setHorizontalAlignment(JLabel.RIGHT);
		baseLabel.setHorizontalAlignment(JLabel.RIGHT);
		valLabel.setHorizontalAlignment(JLabel.RIGHT);
		mulLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel benLabel = new JLabel("Beneficial?");
		JLabel typeLabel = new JLabel("Damage types");
		
		benLabel.setHorizontalAlignment(JLabel.RIGHT);
		typeLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(10, 0, 10, 10);
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(nameLabel, c);
		c.insets = new Insets(10, 0, 10, 10);
		c.gridy = 1;
		panel.add(baseLabel, c);
		c.gridy = 2;
		panel.add(valLabel, c);
		c.insets = new Insets(7, 0, 8, 10);
		c.gridy = 3;
		panel.add(mulLabel, c);
		c.insets = new Insets(30, 0, 0, 10);
		c.gridy = 4;
		panel.add(benLabel, c);
		
		return panel;
	}
	
	/**
	 * Method formats and creates a JPanel that holds the input fields.
	 * 
	 * @return - formatted field JPanel
	 */
	protected JPanel createFieldPanel(){
		
		nameField = new JTextField();
		nameField.setToolTipText("Name of the modifier for identification purposes.");
		nameField.setText("Something");
		
		baseField = new JTextField();
		baseField.setInputVerifier(new ValuesVerifier(99));
		baseField.setToolTipText("Base modifier as percentage. Between 1 and 99");
		baseField.setText("10");
		
		valField = new JTextField();
		valField.setInputVerifier(new ValuesVerifier(999));
		valField.setToolTipText("Value of the modifier. Between 1 and 999");
		valField.setText("5");
		mulSlider = new CustomSlider(1, 5, 1);
		mulSlider.setToolTipText("Multiplier. Larger value decreases modifier effect");
		
		benCheckBox = new JCheckBox();
		benCheckBox.setSelected(true);
		benCheckBox.setToolTipText("Does a bigger modifier value mean less damage taken?");
		
		Dimension dim = new Dimension(60, 20);
		nameField.setPreferredSize(new Dimension(150, 20));
		baseField.setPreferredSize(dim);
		valField.setPreferredSize(dim);
		
		mulSlider.setPreferredSize(new Dimension(100, 60));
		mulSlider.setValue(3);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 0, 8, 10);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(nameField, c);
		c.insets = new Insets(9, 0, 8, 0);
		c.gridy = 1;
		panel.add(baseField, c);
		c.gridy = 2;
		panel.add(valField, c);
		c.gridy = 3;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(mulSlider, c);
		c.gridy = 4;
		c.insets = new Insets(0, 0, 0, 0);
		panel.add(benCheckBox, c);
		
		return panel;
	}
	
	/**
	 * Method formats and creates a JPanel for inputting Damage Type information.
	 * 
	 * @return - formatted damagetype JPanel
	 */
	protected JPanel createDmgTypePanel(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Damage types"));
		
		typeField = new DmgTypesField();
		typeField.setEnabled(false);
		typeField.setToolTipText("Damage types separated by a comma");
		
		globalButton = new JRadioButton();
		customButton = new JRadioButton();
		ButtonGroup typeChoice = new ButtonGroup();
		globalButton.setSelected(true);
		typeChoice.add(globalButton);
		typeChoice.add(customButton);
		
		gtext = new JLabel("Global");
		gtext.setToolTipText("Set damage type as GLOBAL, meaning all damage types.");
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 10;
		c.weighty = 50;
		panel.add(globalButton, c);
		
		c.gridx = 1;
		c.weightx = 50;
		panel.add(gtext, c);
		
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 10;
		c.weighty = 50;
		panel.add(customButton, c);
		
		c.gridx = 1;
		c.weightx = 50;
		panel.add(typeField, c);
		
		return panel;
	}
	
}
