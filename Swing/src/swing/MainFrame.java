package swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Toolbar toolbar;
	private TextPanel textPanel;
	private FormPanel formPanel;

	public MainFrame()
	{
		super("Hello World");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		textPanel = new TextPanel();
		formPanel = new FormPanel();

		toolbar.setStringListener(new StringListener()
		{
			public void textEmitted(String text)
			{
				textPanel.appendText(text);
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);

		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}