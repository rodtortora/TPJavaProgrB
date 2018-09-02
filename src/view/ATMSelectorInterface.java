package view;

import java.util.ArrayList;

import events.AtmSelectedListener;
import model.ATM;

public interface ATMSelectorInterface extends Visible {
	
	void llenarCombobox(ArrayList<ATM> atms);
	void setAtmSelectedListener(AtmSelectedListener atmSelectedListener);
	
}
