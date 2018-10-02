package controller;

import events.MovementAcceptedEvent;
import events.DepositRequestEvent;
import events.DepositRequestListener;
import events.ExtractionAcceptedListener;
import events.ExtractionRequestEvent;
import events.ExtractionRequestEventListener;
import exceptions.ExtractionLimitExceeded;
import exceptions.NotEnoughBalanceException;
import model.ATM;
import view.MessageInterface;

public class TransactionController implements ExtractionRequestEventListener, ExtractionAcceptedListener, DepositRequestListener {
	
	private ATM atm;
	private MessageInterface messageInterface;
	private AuthenticationController authController;

	public TransactionController(AuthenticationController authController, MessageInterface messageInterface) {
		this.authController = authController;
		this.atm = atm;
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
	public void listenMovementAcceptedEvent(MovementAcceptedEvent event) {
		authController.getSessionAtm().expulsarDineroReservado();
		messageInterface.mostrar(true);
		messageInterface.setMessage("Operacion completada", "Saldo extraido: " + event.getCantidadExtraida() + ". Saldo restante: " + event.getSaldoRestante());
	}



	@Override
	public void listenDepositRequestEvent(DepositRequestEvent e) {
		authController.getSessionAtm().depositarDinero(e.getValorBillete(), e.getCantidadBillete());
		
	}

}
