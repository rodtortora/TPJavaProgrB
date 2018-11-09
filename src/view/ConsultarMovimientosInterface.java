package view;

import java.util.ArrayList;

import events.MovementsQueryListener;
import model.Transaction;

public interface ConsultarMovimientosInterface extends Visible {
	
	void setPrincipalMenuInterface(PrincipalMenuInterface principalMenuInterface);
	void setMovementsQueryListener(MovementsQueryListener listener);
	void fillWithMovements(ArrayList<Transaction> transactions);
	void vaciar();

}
