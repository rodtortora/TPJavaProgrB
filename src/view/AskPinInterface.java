package view;

import events.PinSentListener;

public interface AskPinInterface extends Visible {
	
	void setPinListener(PinSentListener listener);
	void mostrarError();
	void setAtmSelectorInterface(ATMSelectorInterface atmSelectorInterface);

}
