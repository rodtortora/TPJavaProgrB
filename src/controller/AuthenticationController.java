package controller;

import model.ATM;
import model.Banco;
import view.AskPinInterface;
import view.MessageInterface;
import view.PrincipalMenuInterface;
import view.SelectorCuentaInterface;
import view.LectorTarjetaInterface;

import java.util.ArrayList;

import events.AccountSelectedEvent;
import events.AccountSelectedListener;
import events.AtmSelectedEvent;
import events.AtmSelectedListener;
import events.CardReadedEvent;
import events.CardReadedListener;
import events.CardValidatedEvent;
import events.CardValidatedListener;
import events.PinRequestEvent;
import events.PinRequestListener;
import events.PinSentEvent;
import events.PinSentListener;
import exceptions.ATMisOnMaintenanceException;
import exceptions.BlockCardException;
import exceptions.CardNotFoundException;
import exceptions.WrongPinException;

public class AuthenticationController implements CardReadedListener, PinSentListener, PinRequestListener, CardValidatedListener, AtmSelectedListener, AccountSelectedListener{
	
	private ArrayList<ATM> ATMs;
	private ATM sessionAtm;
	private ArrayList<Banco> bancos = new ArrayList<>();
	private LectorTarjetaInterface lectorTarjetaInterface;
	private AskPinInterface askPinInterface;
	private SelectorCuentaInterface selectorCuentaInterface;
	private MessageInterface messageInterface;
	private PrincipalMenuInterface menuInterface;

	public AuthenticationController(ArrayList<ATM> atms, LectorTarjetaInterface inicioInterface, ArrayList<Banco> bancos, AskPinInterface askPinInterface,
			SelectorCuentaInterface selectorCuentaInterface, MessageInterface messageInterface, PrincipalMenuInterface menuInterface) {
		this.ATMs = atms;
		this.bancos = bancos;
		this.lectorTarjetaInterface = inicioInterface;	
		this.askPinInterface = askPinInterface;
		this.selectorCuentaInterface = selectorCuentaInterface;
		this.messageInterface = messageInterface;
		this.menuInterface = menuInterface;
	}
	
	/**
	 * Getters & Setters
	 */
	
	public ATM getSessionAtm() {
		return sessionAtm;
	}

	public void setSessionAtm(ATM sessionAtm) {
		this.sessionAtm = sessionAtm;
	}

	public ArrayList<ATM> getATMs() {
		return ATMs;
	}

	public void setATMs(ArrayList<ATM> aTMs) {
		ATMs = aTMs;
	}

	public ArrayList<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(ArrayList<Banco> bancos) {
		this.bancos = bancos;
	}
	
	/**
	 * Functions
	 */

	@Override
	public void listenCardReadedEvent(CardReadedEvent event) {
		try {
			this.getSessionAtm().validarTarjeta(event.getCard());
		} catch (CardNotFoundException e) {
			this.messageInterface.setMessage("Error",e.getMessage());
			this.messageInterface.mostrar();
		} catch (ATMisOnMaintenanceException e) {
			this.messageInterface.setMessage("Error",e.getMessage());
			this.messageInterface.mostrar();
		}
	}

	@Override
	public void listenPinSentEvent(PinSentEvent event) {
		try {
			this.getSessionAtm().sendPin(event.getPin());getClass();
			this.askPinInterface.ocultar();
		} catch (WrongPinException e) {
			this.askPinInterface.mostrarError();
		} catch (BlockCardException e) {
			this.messageInterface.setMessage("Error",e.getMessage());
			this.messageInterface.mostrar();
		}
		
	}

	@Override
	public void listenPinRequestEvent(PinRequestEvent event) {
		this.askPinInterface.mostrar();
		
	}

	@Override
	public void listenCardValidatedEvent(CardValidatedEvent event) {
		this.selectorCuentaInterface.mostrar();
		this.selectorCuentaInterface.llenarCombobox(event.getCard().getUsuario().getCuentas());
		
	}

	@Override
	public void listenAtmSelectedEvent(AtmSelectedEvent e) {	
		this.lectorTarjetaInterface.editarTexto(e.getAtm().toString());
		this.lectorTarjetaInterface.mostrar();
		this.setSessionAtm(e.getAtm());
	}

	@Override
	public void ListenAccountSelectedEvent(AccountSelectedEvent e) {
		this.menuInterface.mostrar();
		
	}



}
