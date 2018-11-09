package controller;

import events.AltaCuentaRequestEvent;
import events.AltaCuentaRequestListener;
import events.AltaTarjetaRequestEvent;
import events.AltaTarjetaRequestListener;
import events.BajaRequestEvent;
import events.BajaRequestListener;
import events.ModificacionRequestEvent;
import events.ModificacionRequestListener;
import exceptions.ImpossibleCreateAccountException;
import exceptions.ImpossibleCreateCardException;
import exceptions.ImpossibleDeactivateCardException;
import exceptions.ImpossibleModificateException;
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
		try {
			admin.baja(e.getNroTarjeta(), e.getBanco());
			messageInterface.mostrar(true);
			messageInterface.setMessage("Tarjeta dada de baja");
		} catch (ImpossibleDeactivateCardException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", error.getMessage());
		}		
	}

	@Override
	public void listenAltaCuentaRequestEvent(AltaCuentaRequestEvent e) {
		try {
			admin.altaCuenta(e.getNroTarjeta(), e.getNroCuenta(), e.getLimiteDesc(), e.getTipoCuenta(), e.getBanco(), e.getCuit());
			messageInterface.mostrar(true);
			messageInterface.setMessage("Cuenta dada de alta");
		} catch (ImpossibleCreateAccountException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", error.getMessage());
		}
		
	}

	@Override
	public void listenModificacionRequestEvent(ModificacionRequestEvent e) {
		try {
			admin.modificar(e.getNroTarjeta(), e.getNroCuenta(), e.getLimiteDesc(), e.getBanco(), e.getNombre(), e.getApellido(), e.getCuit(), e.getPwd());
			messageInterface.mostrar(true);
			messageInterface.setMessage("Tarjeta / Cuenta modificada");
		} catch (ImpossibleModificateException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", error.getMessage());
		}
		
	}

	@Override
	public void listenAltaTarjetaRequestEvent(AltaTarjetaRequestEvent e) {
		try {
			admin.altaTarjeta(e.getNroTarjeta(), e.getNroCuenta(), e.getLimiteDesc(), e.getTipoCuenta(), e.getBanco(), e.getNombre(), e.getApellido(), e.getCuit(), e.getPwd());
			messageInterface.mostrar(true);
			messageInterface.setMessage("Tarjeta habilitada");
		} catch (ImpossibleCreateCardException error) {
			messageInterface.mostrar(true);
			messageInterface.setMessage("Error", error.getMessage());
		}
		
	}
	
	

}
