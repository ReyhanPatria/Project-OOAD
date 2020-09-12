package example.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TaskSearchResultDisplay extends JFrame {
	// STATIC ATTRIBUTES
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	// NON-STATIC ATTRIBUTES
	private JTable searchResultTable;

	
	
	
	
	// STATIC FUNCTIONS
	// Constructor
	public TaskSearchResultDisplay() {
		// Frame configuration
		this.setTitle("Task Search Result");
		this.setSize(700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Scroll pane for table
		JScrollPane scrollPane = new JScrollPane();
		
		// Create search result table
		searchResultTable = new JTable();
		searchResultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Set table header to not be able to reorder
		searchResultTable.getTableHeader().setReorderingAllowed(false);
		
		// Adding table to scroll pane
		scrollPane.setViewportView(searchResultTable);
		// Adding scroll pane to frame
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	
		// Set fram visible
		this.setVisible(true);
	}

	
	
	
	
	// GETTERS
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getSearchResultTable() {
		return searchResultTable;
	}
}
