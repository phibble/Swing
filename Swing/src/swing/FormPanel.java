package swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JList<AgeCategory> ageList;
	private JComboBox<String> empCombo;

	public FormPanel()
	{
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList<AgeCategory>();
		empCombo = new JComboBox<String>();

		// set up List Box
		DefaultListModel<AgeCategory> ageModel = new DefaultListModel<AgeCategory>();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 64"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		ageList.setModel(ageModel);

		ageList.setPreferredSize(new Dimension(110, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);

		// set up Combo Box
		DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<String>();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);

		// BUTTON
		okBtn = new JButton("OK");

		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = ageList.getSelectedValue();
				String empCat = (String) empCombo.getSelectedItem();

				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getID(), empCat);

				if(formListener != null)
				{
					formListener.formEventOccurred(ev);
				}
			}
		});

		// BORDER
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
	}

	public void layoutComponents()
	{
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		{
			gc.weightx = 1;
			gc.weighty = 0.1;

			gc.fill = GridBagConstraints.NONE;

			gc.gridy = 0;

////////////// FIRST ROW //////////////////////////////////////////////
			gc.weightx = 1;
			gc.weighty = 0.1;

			gc.gridx = 0;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 5);
			add(nameLabel, gc);

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(nameField, gc);

////////////// NEXT ROW ///////////////////////////////////////////////
			gc.gridy++;

			gc.weightx = 1;
			gc.weighty = 0.1;

			gc.gridx = 0;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 5);
			add(occupationLabel, gc);

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(occupationField, gc);

////////////// NEXT ROW ///////////////////////////////////////////////
			gc.gridy++;

			gc.weightx = 1;
			gc.weighty = 0.2;

			gc.gridx = 0;
			gc.anchor = GridBagConstraints.FIRST_LINE_END;
			gc.insets = new Insets(0, 0, 0, 5);
			add(new JLabel("Age: "), gc);

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(ageList, gc);

////////////// NEXT ROW ///////////////////////////////////////////////
			gc.gridy++;

			gc.weightx = 1;
			gc.weighty = 0.2;

			gc.gridx = 0;
			gc.anchor = GridBagConstraints.FIRST_LINE_END;
			gc.insets = new Insets(0, 0, 0, 5);
			add(new JLabel("Employment: "), gc);

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(empCombo, gc);

////////////// NEXT ROW ///////////////////////////////////////////////
			gc.gridy++;

			gc.weightx = 1;
			gc.weighty = 2;

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			gc.insets = new Insets(0, 0, 0, 0);
			add(okBtn, gc);
		}
	}

	public void setFormListener(FormListener listener)
	{
		this.formListener = listener;
	}
}

class AgeCategory
{
	private String text;
	private int id;

	public AgeCategory(int id, String text)
	{
		this.id = id;
		this.text = text;
	}

	public String toString()
	{
		return text;
	}

	public int getID()
	{
		return id;
	}
}
