package controller;

import events.MovementsQueryEvent;
import events.MovementsQueryListener;
import events.MovementsReturnedEvent;
import events.MovementsReturnedListener;
import exceptions.NotAllowedOperation;
import view.ConsultarMovimientosInterface;
import view.MessageInterface;

public class MovementsQueryController implements MovementsQueryListener, MovementsReturnedListener {

	private AuthenticationController authController;
	private MessageInterface messageInterface;
	private ConsultarMovimientosInterface movimientosInterface;
	
	public MovementsQueryController(AuthenticationController authControler, MessageInterface messageInterface, ConsultarMovimientosInterface movimientosInterface) {
		this.messageInterface = messageInterface;
		this.authController = authControler;
		this.movimientosInterface = movimientosInterface;
	}

	@Override
	public void listenMovementsQueryEvent(MovementsQueryEvent event) {
		try {
			authController.getSessionAtm().consultarMovimientos(event.getAno(), event.getMes());
		} catch (NotAllowedOperation e) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", e.getMessage());
		}
		
	}

	@Override
	public void listenMovementsReturnedEvent(MovementsReturnedEvent e) {
		movimientosInterface.fillWithMovements(e.getTransactions());
	}

}
