package controller;

import events.ChangePassEvent;
import events.ChangePassListener;
import exceptions.BlockCardException;
import exceptions.InvalidNewPinException;
import exceptions.WrongPinException;
import view.Message;
import view.MessageInterface;

public class ChangePassController implements ChangePassListener {
	
	private AuthenticationController authController;
	private MessageInterface messageInterface;
	
	public ChangePassController(AuthenticationController authControler, MessageInterface messageInterface) {
		this.messageInterface = messageInterface;
		this.authController = authControler;	
	}

	@Override
	public void listenChangePassEvent(ChangePassEvent event) {
		try {
			authController.getSessionAtm().ChangePIN(event.getPassActual(),event.getPassNew(),event.getConfirmPass());
		} catch (InvalidNewPinException e) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", e.getMessage());
			
		} catch (WrongPinException e) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", e.getMessage());

		} catch (BlockCardException e) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", e.getMessage());

		}
		
	}
}
