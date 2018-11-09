package controller;

import events.MovementAcceptedEvent;
import events.TransferRequestEvent;
import events.TransferRequestListener;
import events.DepositRequestEvent;
import events.DepositRequestListener;
import events.ExtractionAcceptedListener;
import events.ExtractionRequestEvent;
import events.ExtractionRequestEventListener;
import exceptions.AccountNotFoundException;
import exceptions.ExtractionLimitExceeded;
import exceptions.InvalidBillException;
import exceptions.NotEnoughBalanceException;
import model.TipoTransaccion;
import view.MessageInterface;

public class TransactionController implements ExtractionRequestEventListener, ExtractionAcceptedListener, DepositRequestListener, TransferRequestListener {
	
	private MessageInterface messageInterface;
	private AuthenticationController authController;

	public TransactionController(AuthenticationController authController, MessageInterface messageInterface) {
		this.authController = authController;
		this.messageInterface = messageInterface;
	}



	@Override
	public void listenExtractionRequestEvent(ExtractionRequestEvent event) {
		try {
			authController.getSessionAtm().pedidoExtraccion(event.getMoneyAmount());
		} catch (NotEnoughBalanceException | ExtractionLimitExceeded e) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", e.getMessage());
		}
	}
	
	@Override
	public void listenDepositRequestEvent(DepositRequestEvent e) {
		try {
			authController.getSessionAtm().depositarDinero(e.getValorBillete(), e.getCantidadBillete());
		} catch (InvalidBillException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage(error.getMessage());
		}
		
	}
	
	@Override
	public void listenTransferRequestEvent(TransferRequestEvent e) {
		try {
			authController.getSessionAtm().transferencia(e.getCbuDestino(), e.getMoneyAmmount());
		} catch (AccountNotFoundException | NotEnoughBalanceException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", error.getMessage());
		}
		
	}

	@Override
	public void listenMovementAcceptedEvent(MovementAcceptedEvent event) {
		if (event.getTipoExtraccion() == TipoTransaccion.extraccion) {
			authController.getSessionAtm().expulsarDineroReservado();
		}		
		messageInterface.mostrar(true);
		messageInterface.setMessage("Operacion completada. Saldo restante: ", event.getSaldoRestante().toString());
	}









}
