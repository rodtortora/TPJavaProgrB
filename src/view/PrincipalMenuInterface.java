package view;

import events.MenuEventListener;

public interface PrincipalMenuInterface extends Visible {

	void setLblBanco(String message);

	void setMenuEventListener(MenuEventListener menuController);
	
}
