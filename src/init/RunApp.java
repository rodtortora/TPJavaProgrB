package init;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import controller.AuthenticationController;
import controller.ChangePassController;
import controller.MenuController;
import controller.SerializeController;
import controller.TransactionController;
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
		HashMap<String, Tarifa> tarifas = new HashMap<String, Tarifa>();
		SortedMap<BigInteger, Billetero> billeteros = new TreeMap(java.util.Collections.reverseOrder());
		
		bancos.add(new Banco(1,"La Plaza",BigInteger.valueOf(5),BigInteger.valueOf(20)));
		bancos.add(new Banco(2,"Provincia",BigInteger.valueOf(5),BigInteger.valueOf(30)));
		bancos.add(new Banco(3,"Frances",BigInteger.valueOf(31),BigInteger.valueOf(80)));
		
		ATM atmMdq = new ATM(1, "Mar del Plata",bancos.get(1),bancos);
		ATM atmBsAs = new ATM(2,"Buenos Aires",bancos.get(1),bancos);
		
		ATMs.add(atmMdq);
		ATMs.add(atmBsAs);
		
		Tarifa cajaAhorroExtraccionForanea = new Tarifa("CajaAhorroTransaccionForanea",BigDecimal.valueOf(10));
		Tarifa cuentaCorrienteExtraccionForanea = new Tarifa("CuentaCorrienteTransaccionForanea",BigDecimal.valueOf(30));
		Tarifa extraccion = new Tarifa("Extraccion",BigDecimal.valueOf(15));
		tarifas.put(cajaAhorroExtraccionForanea.getTipoTarifa(), cajaAhorroExtraccionForanea);
		tarifas.put(cuentaCorrienteExtraccionForanea.getTipoTarifa(), cuentaCorrienteExtraccionForanea);
		tarifas.put(extraccion.getTipoTarifa(), extraccion);
		
		billeteros.put(BigInteger.valueOf(100), new Billetero(BigInteger.valueOf(100),BigInteger.valueOf(60)));
		billeteros.put(BigInteger.valueOf(500), new Billetero(BigInteger.valueOf(500),BigInteger.valueOf(20)));
		
		atmMdq.setTarifas(tarifas);
		atmMdq.setBilleteros(billeteros);
						
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(10), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(20), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(30), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(40), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(50), "1234", true));
		tarjetas.add(new TarjetaATM(BigInteger.valueOf(60), "1234", true));
		try {
			SerializeController.escribir(tarjetas);
		} catch (IOException e) {
			e.printStackTrace();
		}

		bancos.get(0).setTarjetas(tarjetas);
		bancos.get(1).setTarjetas(tarjetas);
		bancos.get(2).setTarjetas(tarjetas);	
		
		cuentas.add(new CuentaCorriente(BigInteger.valueOf(1),BigDecimal.valueOf(0),BigDecimal.valueOf(15000), BigDecimal.valueOf(10000), BigDecimal.valueOf(500), "CUENTA CORRIENTE", 3));
		cuentas.add(new CuentaSueldo(BigInteger.valueOf(2),BigDecimal.valueOf(0),BigDecimal.valueOf(50000), BigDecimal.valueOf(10000), BigDecimal.valueOf(0), "CUENTA SUELDO", 0, "231312"));
		
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
		SelectorCuentaInterface selectorCuentaInterface = new SelectorCuenta();
		ChangePassInterface changePassInterface = new ChangePass();
		ExtraccionInterface extraccionInterface = new Extraccion();
		PrincipalMenuInterface principalMenu = new PrincipalMenu(changePassInterface,extraccionInterface);
		
		/**
		 * Creacion de controladores
		 */
		
		AuthenticationController authenticationController = new AuthenticationController(ATMs, lectorTarjetaInterface, bancos, askPinInterface, 
			selectorCuentaInterface, messageInterface, principalMenu);
		MenuController menuController = new MenuController(principalMenu);
		ChangePassController changePassController = new ChangePassController(authenticationController, messageInterface);
		TransactionController transactionController = new TransactionController(authenticationController, messageInterface);
		
		/**
		 * Asignaciones para comunicacion MVC
		 */
		
		for (Banco banco : bancos) {
			banco.setPinRequestListener(authenticationController);
			banco.setCardValidatedListener(authenticationController);
			banco.setExtractionAcceptedListener(transactionController);
		}		
		lectorTarjetaInterface.setCardReadedListener(authenticationController);
		askPinInterface.setPinListener(authenticationController);
		atmSelectorInterface.setAtmSelectedListener(authenticationController);
		selectorCuentaInterface.setAccountSelectedListener(authenticationController);
		principalMenu.setMenuEventListener(menuController);
		changePassInterface.setChangePassListener(changePassController);
		extraccionInterface.setExtractionRequestEventListener(transactionController);
		
		/**
		 * Inicializacion
		 */
		
		atmSelectorInterface.mostrar(true);
		
	}

}
