package controller;

import model.ATM;
import model.Banco;
import view.AskPinInterface;
import view.ViewInicioInterface;
import events.CardReadedEvent;
import events.CardReadedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentEvent;
import events.PinSentListener;
import exceptions.ATMisOnMaintenanceException;
import exceptions.CardNotFoundException;
import exceptions.WrongPinException;

public class AuthenticationController implements CardReadedListener, PinSentListener, PinRequestListener {
	
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
		try {
			ATM.validarTarjeta(event.getCard());
		} catch (CardNotFoundException | ATMisOnMaintenanceException e) {
			//TODO enviar a interfaz
		}		
	}

	@Override
	public void listenPinSentEvent(PinSentEvent event) {
		try {
			this.ATM.sendPin(event.getPin());
		} catch (WrongPinException e) {
			this.askPinInterface.mostrarError();
		}
		
	}

	@Override
	public void listenPinRequestEvent(PinRequestEvent event) {
		this.askPinInterface.mostrar();
		
	}

}
