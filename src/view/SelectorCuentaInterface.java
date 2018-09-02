package view;

import java.util.ArrayList;

import events.AccountSelectedListener;
import model.Cuenta;

public interface SelectorCuentaInterface extends Visible {
	
	public void llenarCombobox(ArrayList<Cuenta> cuentas);

	public void setAccountSelectedListener(AccountSelectedListener e);

}
