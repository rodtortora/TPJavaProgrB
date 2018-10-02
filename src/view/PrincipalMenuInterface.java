package view;

import controller.MenuController;
import events.BalanceCheckListener;
import events.MenuListener;

public interface PrincipalMenuInterface extends Visible {

	void setLblBanco(String message);

	void setMenuEventListener(MenuListener balanceCheckListener);

	void setBalanceCheckListener(BalanceCheckListener balanceCheckListener);
	
}
