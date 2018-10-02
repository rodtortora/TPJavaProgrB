package controller;

import events.BalanceCheckEvent;
import events.BalanceCheckListener;
import events.MenuEvent;
import events.MenuListener;
import view.MessageInterface;
import view.PrincipalMenuInterface;

public class MenuController implements MenuListener, BalanceCheckListener {
	
	private PrincipalMenuInterface principalMenu;
	private AuthenticationController authenticationController;
	private MessageInterface messageInterface;

	public MenuController(AuthenticationController authenticationController2, PrincipalMenuInterface principalMenu, MessageInterface messageInterface2) {
		this.setPrincipalMenu(principalMenu);
		this.setAuthenticationController(authenticationController2);
		this.setMessageInterface(messageInterface2);
	}

	public PrincipalMenuInterface getPrincipalMenu() {
		return principalMenu;
	}

	public void setPrincipalMenu(PrincipalMenuInterface principalMenu) {
		this.principalMenu = principalMenu;
	}
	
	public AuthenticationController getAuthenticationController() {
		return authenticationController;
	}

	public void setAuthenticationController(AuthenticationController authenticationController) {
		this.authenticationController = authenticationController;
	}

	public MessageInterface getMessageInterface() {
		return messageInterface;
	}

	public void setMessageInterface(MessageInterface messageInterface) {
		this.messageInterface = messageInterface;
	}

	@Override
	public void listenMenuEvent(MenuEvent e) {
		e.getVisible().mostrar(true);		
	}

	@Override
	public void listenBalanceCheckEvent(BalanceCheckEvent e) {
		messageInterface.setMessage("Saldo:", authenticationController.getSessionAtm().consultaSaldo().toString());
		messageInterface.mostrar(true);	
	}



}
