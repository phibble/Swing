package swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList<String> ageList;

	public FormPanel()
	{
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList<String>();
		
		DefaultListModel<String> ageModel = new DefaultListModel<String>();
		ageModel.addElement("Under 18");
		ageModel.addElement("18 to 64");
		ageModel.addElement("65 or over");
		ageList.setModel(ageModel);
		
		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		okBtn = new JButton("OK");

		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = nameField.getText();
				String occupation = occupationField.getText();
				String ageCat = ageList.getSelectedValue();
				
				System.out.println(ageCat);
				
				FormEvent ev = new FormEvent(this, name, occupation);
				
				if(formListener != null)
				{
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		{
			gc.weightx = 1;
			gc.weighty = 0.1;

			// FIRST ROW
			{
				gc.gridx = 0;
				gc.gridy = 0;
				gc.fill = GridBagConstraints.NONE;
				gc.anchor = GridBagConstraints.LINE_END;
				gc.insets = new Insets(0, 0, 0, 5);
				add(nameLabel, gc);

				gc.gridx = 1;
				gc.anchor = GridBagConstraints.LINE_START;
				gc.insets = new Insets(0, 0, 0, 0);
				add(nameField, gc);
			}

			// SECOND ROW
			{
				gc.gridx = 0;
				gc.gridy = 1;
				gc.anchor = GridBagConstraints.LINE_END;
				gc.insets = new Insets(0, 0, 0, 5);
				add(occupationLabel, gc);

				gc.gridx = 1;
				gc.anchor = GridBagConstraints.LINE_START;
				gc.insets = new Insets(0, 0, 0, 0);
				add(occupationField, gc);
			}
			
			// THIRD ROW
			{
				gc.weighty = 0.2;
				
				gc.gridy = 2;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				add(ageList, gc);
			}

			// FOURTH ROW
			{
				gc.weighty = 2;

				gc.gridy = 3;
				add(okBtn, gc);
			}
		}
	}

	public void setFormListener(FormListener listener)
	{
		this.formListener = listener;
	}
}
