package mpp.lib.frontend.ui;

public interface CommonContainer {
	
	void initializeVariables();
	
	void initializeUI();
	
	void addListeners();
	
	void resetData();
	
	void checkRequiredFields() throws Exception;
}
