package controller;

import events.AltaCuentaRequestEvent;
import events.AltaCuentaRequestListener;
import events.AltaTarjetaRequestEvent;
import events.AltaTarjetaRequestListener;
import events.BajaRequestEvent;
import events.BajaRequestListener;
import events.ModificacionRequestEvent;
import events.ModificacionRequestListener;
import exceptions.ImpossibleCreateCardException;
import model.Administracion;
import view.MessageInterface;

public class AbmController implements AltaCuentaRequestListener, AltaTarjetaRequestListener, ModificacionRequestListener, BajaRequestListener {
	
	private MessageInterface messageInterface;
	private Administracion admin = new Administracion();

	public AbmController( MessageInterface messageInterface) {
		this.messageInterface = messageInterface;
	}

	@Override
	public void listenBajaRequestEvent(BajaRequestEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenAltaCuentaRequestEvent(AltaCuentaRequestEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenModificacionRequestEvent(ModificacionRequestEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenAltaTarjetaRequestEvent(AltaTarjetaRequestEvent e) {
		try {
			admin.altaTarjeta(e.getNroTarjeta(), e.getNroCuenta(), e.getLimiteDesc(), e.getTipoCuenta(), e.getBanco(), e.getNombre(), e.getApellido(), e.getCuit(), e.getPwd());
		} catch (ImpossibleCreateCardException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", error.getMessage());
		}
		
	}
	
	

}
