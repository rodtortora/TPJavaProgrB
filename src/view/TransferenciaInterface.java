package view;

import events.TransferRequestListener;

public interface TransferenciaInterface extends Visible {
	
	void setTransferRequestListener(TransferRequestListener listener);

	void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface);

}
