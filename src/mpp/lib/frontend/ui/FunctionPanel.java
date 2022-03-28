package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import mpp.lib.constants.LibConst;
import mpp.lib.frontend.model.ListItem;

public class FunctionPanel extends JPanel implements CommonContainer {
	
	private static final long serialVersionUID = 567396689663481551L;

	private MainFrame parent;
	
	private JList<ListItem>    listFunctions;
	
	private int lastSelectedIndex = 0;
	
	public FunctionPanel(MainFrame mainFrame) {
		this.parent = mainFrame;
		initializeVariables();
		initializeUI();
		addListeners();
	}
	
	@Override
	public void initializeUI() {
		setLayout(new BorderLayout());
		listFunctions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(listFunctions, BorderLayout.CENTER);
	}
	
	@Override
	public void addListeners() {
		listFunctions.addListSelectionListener(parent.getListSelectionListener());
	}
	
	public String getSelectedValue() {
		ListItem selItem = listFunctions.getSelectedValue();
		if (selItem.highlight()) {
			this.lastSelectedIndex = listFunctions.getSelectedIndex();
			return selItem.getItemName();
		}
		listFunctions.setSelectedIndex(lastSelectedIndex);
		return null;
	}
	
	@SuppressWarnings("serial")
	@Override
	public void initializeVariables() {
	    DefaultListModel<ListItem> model = new DefaultListModel<>();
		for (ListItem item: LibConst.FUNC_ALL) {
			model.addElement(item);
		}
	
		listFunctions = new JList<ListItem>(model);
		listFunctions.setCellRenderer(new DefaultListCellRenderer() {

			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(JList list, 
					Object value, int index,
					boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, 
						value, index, isSelected, cellHasFocus);
				if (value instanceof ListItem) {
					ListItem nextItem = (ListItem) value;
					setText(nextItem.getItemName());
					if (nextItem.highlight()) {
						setForeground(LibConst.LINK_AVAILABLE);
					} else {
						setForeground(LibConst.LINK_NOT_AVAILABLE);
					}
					if (isSelected) {
						setForeground(Color.BLACK);
						setBackground(new Color(236,243,245));
					}
				} else {
					setText("illegal item");
				}
				return c;
			}

		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateList(ListItem[] items) {
		
		DefaultListModel<ListItem> model = (DefaultListModel) listFunctions.getModel();
		int size = model.getSize();
		if (items != null) {
			java.util.List<Integer> indices = new ArrayList<>();
			for (ListItem item : items) {
				int index = model.indexOf(item);
				indices.add(index);
				ListItem next = (ListItem) model.get(index);
				next.setHighlight(true);

			}
			for (int i = 0; i < size; ++i) {
				if (!indices.contains(i)) {
					ListItem next = (ListItem) model.get(i);
					next.setHighlight(false);
				}
			}
		} else {
			for (int i = 0; i < size; ++i) {
				ListItem next = (ListItem) model.get(i);
				next.setHighlight(true);
			}
		}
		repaint();
	}
	
	@Override
	public void resetData () {
		
	}
	
	@Override
	public void checkRequiredFields() throws Exception {
		
	}
}
