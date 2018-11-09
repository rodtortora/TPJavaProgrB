package view;

import java.util.ArrayList;

import events.AltaCuentaRequestListener;
import events.AltaTarjetaRequestListener;
import events.BajaRequestListener;
import events.ModificacionRequestListener;
import model.Banco;

public interface AdministracionInterface extends Visible {

	void llenarCombobox(ArrayList<Banco> bancos);

	void setModificacionRequestListener(ModificacionRequestListener listener);

	void setAltaTarjetaRequestListener(AltaTarjetaRequestListener listener);

	void setBajaRequestListener(BajaRequestListener listener);

	void setAltaCuentaRequestListener(AltaCuentaRequestListener listener);
	
	void setAtmSelectorInterface(ATMSelectorInterface atmInterface);


}
