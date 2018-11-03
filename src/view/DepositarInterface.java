package view;

import events.DepositRequestListener;
import events.ExtractionRequestEventListener;

public interface DepositarInterface extends Visible {
	
	void setDepositRequestListener(DepositRequestListener listener);

	void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface);

}
