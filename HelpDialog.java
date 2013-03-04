package core;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Container class for the help texts. Contains only static Strings about help subjects.
 * 
 * @author Tarmo
 *
 */
class HelpTexts{
	final static String GENERAL = "<html>Damage Calculator works with applying modifiers to a set amount of base damage. These modifiers" +
			" can be either <b>resists</b>, which lower the amount of damage taken, or <b>weaknesses</b>, which increase it. All" +
			" numeric values in this calculator can be integers between 1 and 10000.<br><br>" +
			" Input the start damage and the modifier attributes in the corresponding fields, and calculate total damage taken" +
			" by clicking <b>Calculate</b>. You can also click <b>Show game example</b> for a generated example text of how this " +
			" event would look like in the game.</html>";
	
	final static String MODIFIER_OVERVIEW = "<html>Each modifier has a number of attributes with specific values which change the final" +
			" effect on the damage. These are: <b>base modifier, value, multiplier, and beneficial.</b> ><ul><li>The <b>base " +
			" modifier</b> attribute sets the base percentage of damage that will be either added or subtracted from the damage." +
			" For example, a resist with a base of 10 will multiply the damage with 0.9," +
			" or less. Base should be between 1 and 99.<li>The <b>value</b> attribute is the value of the modifier. It will make the effect of the modifier " +
			" stronger or weaker, depending on the <b>beneficial</b> attribute. For example, a beneficial resist will" +
			" result in less damage taken the bigger the value attribute is, while a non-beneficial resist will do the opposite." +
			" Weaknesses function similarily, therefore most weaknesses are usually non-beneficial." +
			" <li>The <b>multiplier</b> attribute determines a multiplying effect on the algorithm for gameplay purposes. A larger" +
			" multiplier value results in a smaller modifying effect. Therefore modifiers are \"less important\" the bigger the" +
			" multiplier. Multiplier can be between 1 and 5.</ul>" +
			" Resists will always lower the amount of damage taken, but never reduce it to zero. Weaknesses will always" +
			" add damage taken by a non-zero amount, and have no damage ceiling.</html>";
	
	final static String NUM_OVERVIEW = "<html>The <b>number of modifiers</b> slider will determine the number of identical modifiers" +
			"created for calculation purposes.</html>";
	
	final static String DAMAGE_TYPES = "<html>The damage inflicted as well as modifiers can be associated with <b>damage types</b>." +
			" A damage type can be any string of text. When inputting multiple damage types into the field, separate them with a <b>,-sign</b>." +
			"<br><br>Modifiers will only be activated if the damage inflicted has one of the damage types associated with the modifier." +
			" Modifiers can also be global, which means it applies to all damage types.";
	
	final static String TABLES = "<html>Add modifiers into the tables by clicking <b>Add</b>. Edit modifiers by selecting one from the" +
			" table and clicking <b>Edit</b>. Remove a modifier by selecting one and clicking <b>Remove</b>.";
			
}

/**
 * A help dialog for the Add and Edit windows. Shows a simple dialog with text about the subject.
 * Text picked from HelpTexts.
 * 
 * @author Tarmo
 *
 */
class DetailsHelpDialog extends HelpDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8471494491713041704L;

	DetailsHelpDialog(){
		
		JPanel textPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 10);
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel overview = new JLabel(HelpTexts.MODIFIER_OVERVIEW);
		overview.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		c.gridy = 0;
		textPanel.add(overview, c);
		
		JLabel dmgtypes = new JLabel(HelpTexts.DAMAGE_TYPES);
		dmgtypes.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		c.gridy = 1;
		textPanel.add(dmgtypes, c);
		
		format(textPanel, new Dimension(600, 400));
	}
}

/**
 * A help dialog for the Advanced tab. Shows a simple dialog with text about the subject.
 * Text picked from HelpTexts.
 * 
 * @author Tarmo
 *
 */
class AdvHelpDialog extends HelpDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8709383728423112602L;

	AdvHelpDialog(){
		
		JPanel textPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 10);
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel general = new JLabel(HelpTexts.GENERAL);
		general.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		textPanel.add(general, c);
		
		c.gridy = 1;
		JLabel dmgtypes = new JLabel(HelpTexts.DAMAGE_TYPES);
		dmgtypes.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		textPanel.add(dmgtypes, c);
		
		c.gridy = 2;
		JLabel tables = new JLabel(HelpTexts.TABLES);
		tables.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		textPanel.add(tables, c);
		
		format(textPanel, new Dimension(600, 350));
		setResizable(false);
	}
}

/**
 * A help dialog for the Quick tab. Shows a simple dialog with text about the subject.
 * Text picked from HelpTexts.
 * 
 * @author Tarmo
 *
 */
class QuickHelpDialog extends HelpDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8727279052340123918L;

	QuickHelpDialog(){
		
		JPanel textPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 10, 0, 10);
		c.weightx = 10;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		
		JLabel general = new JLabel(HelpTexts.GENERAL);
		general.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		textPanel.add(general, c);
		c.gridy = 1;
		
		JLabel overview = new JLabel(HelpTexts.MODIFIER_OVERVIEW);
		overview.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		textPanel.add(overview, c);
		c.gridy = 2;
		
		JLabel num = new JLabel(HelpTexts.NUM_OVERVIEW);
		num.setFont(new Font("Arial Bold", Font.PLAIN, 12));
		textPanel.add(num, c);
		
		format(textPanel, new Dimension(600, 500));
	}
}

/**
 * A superclass used for formatting the subclasses. Contains formatting information.
 * 
 * @author Tarmo
 *
 */
public class HelpDialog extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7132047527308154521L;
	private HelpDialog myself;

	protected HelpDialog(){
		super("Help");
	}
	
	/**
	 * Format the window based on the given parameter.s
	 * 
	 * @param textPanel - the panel that displays the information
	 * @param windowSize - dimension of the whole window
	 */
	protected void format(JPanel textPanel, Dimension windowSize){
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(windowSize);
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 10;
		c.weighty = 100;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(textPanel, c);
		
		JButton closeButton = new JButton("Close");
		c.weighty = 10;
		c.gridy = 3;
		c.fill = GridBagConstraints.NONE;
		panel.add(closeButton, c);
		
		setContentPane(panel);
		
		myself = this;
		closeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	myself.setVisible(false);
            }
        });
	}
}
