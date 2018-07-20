package controller;

import model.ATMCore;
import view.ViewInicioInterface;
import events.CardReadedEvent;
import events.CardReadedListener;

public class AuthenticationController implements CardReadedListener {
	
	private ATMCore ATMCore;
	private ViewInicioInterface inicioInterface;

	public AuthenticationController(ATMCore atmCore, ViewInicioInterface inicioInterface) {
		this.ATMCore = atmCore;
		this.inicioInterface = inicioInterface;
		
	}

	@Override
	public void listenCardReadedEvent(CardReadedEvent event) {
		ATMCore.validarTarjeta(event.getCard(), event.getPIN());
		
	}

}
