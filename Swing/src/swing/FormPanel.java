package swing;

import java.awt.Dimension;

import javax.swing.JPanel;

public class FormPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public FormPanel()
	{
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
	}
}
