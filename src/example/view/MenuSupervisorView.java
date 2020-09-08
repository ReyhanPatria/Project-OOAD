package example.view;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuSupervisorView extends JPanel implements ViewPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel backgroundImage;
	
	public MenuSupervisorView() {
		this.setSize(new Dimension(1440,1024));
		setLayout(null);
		
		backgroundImage = new JLabel("");
		backgroundImage.setBounds(0, 0, 1370, 750);
		add(backgroundImage);
		
	}
}