package view;

import events.ChangePassListener;

public interface ChangePassInterface extends Visible {

	void setChangePassListener(ChangePassListener changePassListener);	
	void mostrarError();

}