package core;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * DefaultTableModel wrapper for ModifierTable. Sets all cells as non-editable.
 * 
 * @author Tarmo
 *
 */
class ModifierTableModel extends DefaultTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8156515935549974628L;

	public boolean isCellEditable(int row, int col){
		return false;
	}
}

/**
 * Custom JPanel that includes a JTable and buttons to manipulate the table with add, remove, and edit-buttons.
 * Content should be Modifiers.
 * 
 * @author Tarmo
 *
 */
public class ModifierTable extends JPanel{
	
	private static final long serialVersionUID = 6709692768767195649L;
	private JTable table;
	private DefaultTableModel model;
	private JButton addButton;
	private JButton removeButton;
	private JButton editButton;
	
	/**
	 * Constructs the table with the label text provided, constructing a border with the text as title.
	 * Constructor also formats and creates the elements of the element.
	 * 
	 * @param text
	 */
	ModifierTable(String text){
		
		TitledBorder border = BorderFactory.createTitledBorder(text);
		border.setTitleJustification(TitledBorder.LEFT);
		setBorder(border);
		
		setupTable();
		
		addButton = new JButton("Add");
		addButton.setToolTipText("Add a new modifier");
		editButton = new JButton("Edit");
		editButton.setToolTipText("Edit selected modifier");
		removeButton = new JButton("Remove");
		removeButton.setToolTipText("Remove selected modifier");
		
		setLayout(new GridBagLayout());
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 20;
		c.weighty = 20;
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		add(scrollPane, c);
		
		JPanel buttonPanel = createButtonPanel();
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		add(buttonPanel, c);
        
	}
	
	/**
	 * Appends array of objects to the bottom of the table.
	 * 
	 * @param row
	 */
	public void appendToTable(Object[] row){
		appendRow(row);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	/**
	 * Creates a JPanel with the manipulator buttons.
	 * 
	 * @return - JPanel with manipulator buttons.
	 */
	private JPanel createButtonPanel(){
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.weightx = 20;
		c.weighty = 20;
		c.insets = new Insets(0, 5, 5, 5);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(addButton, c);
		c.insets = new Insets(5, 5, 5, 5);
		c.gridy = 1;
		buttonPanel.add(editButton, c);
		c.gridy = 2;
		buttonPanel.add(removeButton, c);
		
		return buttonPanel;
	}
	
	/**
	 * Sets up the table layout, labels, column widths, and such.
	 */
	private void setupTable(){
		
		model = new ModifierTableModel();
		
		String[] columnNames = {"name", "base", "value", "beneficial", "multiplier", "damage types"};
		model.setColumnIdentifiers(columnNames);
		
		table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(15);
        table.getColumnModel().getColumn(2).setPreferredWidth(15);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
	}
	
	/**
	 * Append array of objects to the tablemodel.
	 * 
	 * @param row
	 */
	public void appendRow(Object[] row){
		model.addRow(row);
	}
	
	/**
	 * Remove row from tablemodel.
	 * 
	 * @param rowNum - index of the row to be removed.
	 */
	public void removeRow(int rowNum){
		model.removeRow(rowNum);
	}
	
	/**
	 * Returns the number of the row that user has selected on the table. If none, returns -1.
	 * 
	 * @return - number of selection
	 */
	public int getSelectedRowNum(){
		return table.getSelectedRow();
	}
	
	/**
	 * Replaces the row on the indicated index with the given row of objects.
	 * 
	 * @param num - index of the row to be replaced
	 * @param row - replacing row
	 */
	public void replaceRowAt(int num, Object[] row){
		model.insertRow(num, row);
		model.removeRow(num + 1);
	}

}
