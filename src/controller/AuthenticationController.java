package controller;

import model.ATM;
import model.Banco;
import view.AskPinInterface;
import view.ViewInicioInterface;
import events.CardReadedEvent;
import events.CardReadedListener;
import events.PinFailedEvent;
import events.PinFailedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentEvent;
import events.PinSentListener;

public class AuthenticationController implements CardReadedListener, PinSentListener, PinFailedListener, PinRequestListener {
	
	private ATM ATM;
	private Banco banco;
	private ViewInicioInterface inicioInterface;
	private AskPinInterface askPinInterface;

	public AuthenticationController(ATM atm, ViewInicioInterface inicioInterface, Banco banco, AskPinInterface askPinInterface) {
		this.ATM = atm;
		this.banco = banco;
		this.inicioInterface = inicioInterface;	
		this.askPinInterface = askPinInterface;
	}

	@Override
	public void listenCardReadedEvent(CardReadedEvent event) {
		ATM.validarTarjeta(event.getCard());		
	}

	@Override
	public void listenPinFailedEvent(PinFailedEvent event) {
		this.askPinInterface.mostrarError();
		
	}

	@Override
	public void listenPinSentEvent(PinSentEvent event) {
		this.banco.validarPIN(event.getPin());
		
	}

	@Override
	public void listenPinRequestEvent(PinRequestEvent event) {
		this.askPinInterface.mostrar();
		
	}

}
