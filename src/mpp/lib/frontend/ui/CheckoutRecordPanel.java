package mpp.lib.frontend.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import mpp.lib.constants.LibConst;
import mpp.lib.frontend.model.CustomTableModel;
import mpp.lib.utils.Util;
//import mpp.lib.frontend.model.*;

public class CheckoutRecordPanel extends JPanel implements CommonContainer {

	private static final long serialVersionUID = 6259802339157874874L;

	private final String[] DEFAULT_COLUMN_HEADERS = { "Book Title", "ISBN", "Copy number", "Checkout date", "Due date" };
	private static final int SCREEN_WIDTH = 640;
	private static final int SCREEN_HEIGHT = 480;
	private static final int TABLE_WIDTH = (int) (0.75 * SCREEN_WIDTH);
	private static final int DEFAULT_TABLE_HEIGHT = (int) (0.75 * SCREEN_HEIGHT);
	private final float[] COL_WIDTH_PROPORTIONS = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };

	private CustomTableModel model;
	private JTable table;
	private JScrollPane scrollPane;

	private JPanel pnTop;
	private JPanel pnTable;

	// topPanel components
	private JLabel memberIDLabel = new JLabel("Member ID");

	private JTextField txtMemberId;

	private JButton btnSearch = new JButton("Search");

	private MainFrame parent;

	List<String[]> checkoutRecords = new ArrayList<>();

	public CheckoutRecordPanel(MainFrame mainFrame) {
		this.parent = mainFrame;
		initializeUI();
		addListeners();
	}

	public void initializeUI() {
		setLayout(new BorderLayout());

		// Add Top panel
		JPanel pnTitle = new JPanel();
		pnTitle.setPreferredSize(new Dimension(this.getWidth(), 60));

		pnTitle.setLayout(new BorderLayout());
		JLabel lblLogin = new JLabel("Checkout Records");
		Util.adjustLabelFont(lblLogin, LibConst.DARK_BLUE, true);
		pnTitle.add(lblLogin, BorderLayout.WEST);

		defineTopPanel();
		defineTablePanel();
		JPanel pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout(10, 10));
		pnContent.add(pnTop, BorderLayout.NORTH);
		pnContent.add(pnTable, BorderLayout.CENTER);

		JPanel pnEmpty = new JPanel();
		pnEmpty.setPreferredSize(new Dimension(150, getHeight()));

		// add(pnEmpty, BorderLayout.EAST);
		add(pnTitle, BorderLayout.NORTH);
		add(pnContent, BorderLayout.CENTER);
	}

	private void defineTopPanel() {
		pnTop = new JPanel();
		pnTop.setPreferredSize(new Dimension(640, 50));

		txtMemberId = new JTextField(10);

		pnTop.add(memberIDLabel);
		pnTop.add(txtMemberId);
		btnSearch.setActionCommand(LibConst.CMD_CHECK_OUT_RECORD);
		pnTop.add(btnSearch);

	}

	private void defineTablePanel() {
		pnTable = new JPanel();
		createTableAndTablePane();
		pnTable.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTable.add(scrollPane);
	}

	private void createTableAndTablePane() {
		updateModel();

		setValues(model, checkoutRecords);

		table = new JTable(model);
		createCustomColumns(table, TABLE_WIDTH, COL_WIDTH_PROPORTIONS, DEFAULT_COLUMN_HEADERS);
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, DEFAULT_TABLE_HEIGHT));
		scrollPane.getViewport().add(table);
	}

	private void createCustomColumns(JTable table, int width, float[] proportions, String[] headers) {
		table.setAutoCreateColumnsFromModel(false);
		int num = headers.length;
		for (int i = 0; i < num; ++i) {
			TableColumn column = new TableColumn(i);
			column.setHeaderValue(headers[i]);
			column.setMinWidth(Math.round(proportions[i] * width));
			table.addColumn(column);
		}
	}

	private void setValues(CustomTableModel model, List<String[]> values) {
		model.setTableValues(values);
	}

	public void clearTableData() {
		CustomTableModel ctm = (CustomTableModel) table.getModel();
		ctm.resetData();
	}

	public void updateModel(List<String[]> list) {
		if (model == null) {
			model = new CustomTableModel();
		}

		// int rowCount = model.getRowCount();

		model.setTableValues(list);

		//System.out.println("Clearing rows" + model.getRowCount());

//		model.fireTableRowsDeleted(1, 1);
		model.fireTableDataChanged();

	}

	public void updateModel() {
		List<String[]> temp = new ArrayList<String[]>();
		updateModel(temp);
	}

	@Override
	public void initializeVariables() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListeners() {
		btnSearch.addActionListener(parent.getActionListener());
	}

	@Override
	public void resetData() {
		// clearTableData();
	}

	public String getMemberId() {
		return txtMemberId.getText();
	}

	@Override
	public void checkRequiredFields() throws Exception {
		if ("".equals(txtMemberId.getText())) {
			throw new Exception("Member ID can't be empty.");
		}

	}

}
