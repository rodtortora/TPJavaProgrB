package view;

import events.PinSentListener;

public interface AskPinInterface {
	
	void setPinListener(PinSentListener listener);
	void mostrar();
	void ocultar();
	void mostrarError();

}
