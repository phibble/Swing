package swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Toolbar toolbar;
	private TextPanel textPanel;

	public MainFrame()
	{
		super("Hello World");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		textPanel = new TextPanel();

		toolbar.setTextPanel(textPanel);
		
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);

		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}