package init;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import controller.AuthenticationController;
import controller.ChangePassController;
import controller.MenuController;
import model.*;
import view.*;

public class RunApp {
	

	public static void main (String[] args) {	
		
		/**
		 * Listas de bancos, tarjetas, etc
		 */
		
		ArrayList<Banco> bancos = new ArrayList<>();
		ArrayList<TarjetaATM> tarjetas = new ArrayList<>();
		ArrayList<ATM> ATMs = new ArrayList<>();
		ArrayList<Cuenta> cuentas = new ArrayList<>();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		bancos.add(new Banco(1,"La Plaza",BigInteger.valueOf(5),BigInteger.valueOf(20)));
		bancos.add(new Banco(2,"Provincia",BigInteger.valueOf(21),BigInteger.valueOf(30)));
		bancos.add(new Banco(3,"Frances",BigInteger.valueOf(31),BigInteger.valueOf(80)));
		
		ATMs.add(new ATM(1, "Mar del Plata",bancos.get(0),bancos));
		ATMs.add(new ATM(2,"Buenos Aires",bancos.get(1),bancos));
						
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(10), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(20), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(30), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(40), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(50), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(60), "1234", true));

		bancos.get(0).setTarjetas(tarjetas);
		bancos.get(1).setTarjetas(tarjetas);
		bancos.get(2).setTarjetas(tarjetas);	
		
		cuentas.add(new CuentaCorriente(BigInteger.valueOf(1), BigDecimal.valueOf(100), BigDecimal.valueOf(50), BigDecimal.valueOf(10),
				2, BigDecimal.valueOf(15), BigDecimal.valueOf(100), BigDecimal.valueOf(40),
				"Cuenta Corriente"));
		cuentas.add(new CuentaSueldo(BigInteger.valueOf(2), BigDecimal.valueOf(100), BigDecimal.valueOf(50), BigDecimal.valueOf(10),
				2, BigDecimal.valueOf(15), BigDecimal.valueOf(100), "20-500-1", BigDecimal.valueOf(40),
				"Cuenta Sueldo"));
		
		usuarios.add(new Usuario(1,"Tortora","Rodrigo"));
		usuarios.get(0).setCuenta(cuentas);
		tarjetas.get(0).setUsuario(usuarios.get(0));
		
		
		
		/**
		 * Creacion de interfaces
		 */
		
		AskPinInterface askPinInterface = new AskPin();
		ATMSelectorInterface atmSelectorInterface = new ATMSelector();
		atmSelectorInterface.llenarCombobox(ATMs);
		LectorTarjetaInterface lectorTarjetaInterface = new LectorTarjetaView();
		MessageInterface messageInterface = new Message();
		PrincipalMenuInterface principalMenu = new PrincipalMenu();
		SelectorCuentaInterface selectorCuentaInterface = new SelectorCuenta();
		ChangePassInterface changePassInterface = new ChangePass();
		
		
		/**
		 * Creacion de controladores
		 */
		
		AuthenticationController authenticationController = new AuthenticationController(ATMs, lectorTarjetaInterface, bancos, askPinInterface, 
			selectorCuentaInterface, messageInterface, principalMenu);
		MenuController menuController = new MenuController(principalMenu);
		ChangePassController changePassControler = new ChangePassController(authenticationController, messageInterface);
		
		/**
		 * Asignaciones para comunicacion MVC
		 */
		
		for (Banco banco : bancos) {
			banco.setPinRequestListener(authenticationController);
			banco.setCardValidatedListener(authenticationController);
		}		
		lectorTarjetaInterface.setCardReadedListener(authenticationController);
		askPinInterface.setPinListener(authenticationController);
		atmSelectorInterface.setAtmSelectedListener(authenticationController);
		selectorCuentaInterface.setAccountSelectedListener(authenticationController);
		principalMenu.setMenuEventListener(menuController);
		changePassInterface.setChangePassListener(changePassControler);
		
		/**
		 * Inicializacion
		 */
		
		atmSelectorInterface.mostrar();
		
	}




}
