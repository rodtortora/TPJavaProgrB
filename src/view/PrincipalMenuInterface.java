package view;

import controller.MenuController;
import events.BalanceCheckListener;

public interface PrincipalMenuInterface extends Visible {

	void setLblBanco(String message);

	void setBalanceCheckListener(BalanceCheckListener balanceCheckListener);
	
}
