package events;

import java.util.ArrayList;

import model.Transaction;

public interface MovementsReturnedListener {
	
	void listenMovementsReturnedEvent(MovementsReturnedEvent e);	

}
