package view;

import events.ExtractionRequestEventListener;

public interface ExtraccionInterface extends Visible {

	void setExtractionRequestEventListener(ExtractionRequestEventListener listener);

	void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface);

}
