package core;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Class creates a window that displays static text based on the GameExampleData given.
 * 
 * @author Tarmo
 *
 */
public class GameExampleDialog extends JDialog{
	
	private static final long serialVersionUID = -3072672227997789647L;
	private JTextArea area;
	private GameExampleDialog myself;
	private JScrollPane jsp;

	/**
	 * Creates and formats the GameExampleDialog. Requires MainView reference for modality purposes.
	 * 
	 * @param view - MainView window, for modality
	 */
	GameExampleDialog(MainView view){
		super(view, "Game example", Dialog.ModalityType.APPLICATION_MODAL);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 300));
		panel.setLayout(new GridBagLayout());
		
		area = new JTextArea(3, 30);
		area.setWrapStyleWord(true);
		jsp = new JScrollPane(area);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		area.setEditable(false);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 10;
		c.weighty = 100;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(jsp, c);
		
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
	
	/**
	 * Set the content of the GameExampleDialog based on the GameExampleData.
	 * 
	 * @param data - GameExampleData containing data from a calculation.
	 */
	public void setContent(GameExampleData data){

		addLine(data.getEnemy().toUpperCase() + " hits you for " + data.getDmg() + " damage!");
		
		
		if(data.getResNames().size() > 0){
			for(int i = 0; i < data.getResNames().size(); i++){
				String name = data.getResNames().get(i);
				String amount =  ResultLabel.getMaxDecimalString(Double.toString(data.getResAmounts().get(i)), 2);
				addLine(name.toUpperCase() + " lowers the damage by " + amount + ".");
			}
		}
		if(data.getWeakNames().size() > 0){
			for(int i = 0; i < data.getWeakNames().size(); i++){
				String name = data.getWeakNames().get(i);
				String amount =  ResultLabel.getMaxDecimalString(Double.toString(data.getWeakAmounts().get(i)), 2);
				addLine(name.toUpperCase() + " raises the damage by " + amount + ".");
			}
		}
		addLine("You take " + ResultLabel.getMaxDecimalString(Double.toString(data.getFinalDmg()), 2) + " damage!");
		
		ArrayList<String> list = data.generateExtraText();
		for(int i = 0; i < list.size(); i++){
			addLine(list.get(i));
		}
		jsp.getHorizontalScrollBar().setValue(0);
		jsp.getHorizontalScrollBar().setValue(0);
		System.out.println(jsp.getVerticalScrollBar().getValue());
	}
	
	/**
	 * Adds a line of text to the JTextArea.
	 * 
	 * @param text - text to be displayed
	 */
	private void addLine(String text){
		area.append(" " + text + "\n");
	}

}
