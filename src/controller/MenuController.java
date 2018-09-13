package controller;

import events.MenuEvent;
import events.MenuEventListener;
import view.PrincipalMenuInterface;

public class MenuController implements MenuEventListener {
	
	private PrincipalMenuInterface principalMenu;

	public MenuController(PrincipalMenuInterface principalMenu) {
		this.setPrincipalMenu(principalMenu);
	}

	public PrincipalMenuInterface getPrincipalMenu() {
		return principalMenu;
	}

	public void setPrincipalMenu(PrincipalMenuInterface principalMenu) {
		this.principalMenu = principalMenu;
	}

	@Override
	public void listenMenuEvent(MenuEvent e) {
		e.getVisible().mostrar(true);		
	}

}
