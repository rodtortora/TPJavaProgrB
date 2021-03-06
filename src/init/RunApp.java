package init;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import controller.AbmController;
import controller.AuthenticationController;
import controller.ChangePassController;
import controller.MenuController;
import controller.MovementsQueryController;
import controller.TransactionController;
import model.*;
import view.*;

public class RunApp {
	

	public static void main (String[] args) {	
		
		/**
		 * Listas de bancos, tarjetas, etc
		 */
		
		ArrayList<BigInteger> billetes = new ArrayList<>();
		ArrayList<Banco> bancos = new ArrayList<>();
		ArrayList<ATM> ATMs = new ArrayList<>();
		SortedMap<BigInteger, Billetero> billeterosmdp = new TreeMap(java.util.Collections.reverseOrder());
		SortedMap<BigInteger, Billetero> billeterosbsas = new TreeMap(java.util.Collections.reverseOrder());
		
		/**
		 * Billetes aceptados
		 */
		billetes.add(BigInteger.valueOf(10));
		billetes.add(BigInteger.valueOf(20));
		billetes.add(BigInteger.valueOf(50));
		billetes.add(BigInteger.valueOf(100));
		billetes.add(BigInteger.valueOf(200));
		billetes.add(BigInteger.valueOf(500));
		billetes.add(BigInteger.valueOf(1000));
		
		
		/**
		 * Bancos
		 */
		
		bancos.add(new Banco(1,"La Plaza",BigInteger.valueOf(5),BigInteger.valueOf(20), true));
		bancos.add(new Banco(2,"Provincia",BigInteger.valueOf(21),BigInteger.valueOf(30), false));
		bancos.add(new Banco(3,"Frances",BigInteger.valueOf(31),BigInteger.valueOf(80), false));
		
		/**
		 * Billeteros, reconocedor de billetes y ATMs
		 */
		
		billeterosmdp.put(BigInteger.valueOf(100), new Billetero(BigInteger.valueOf(100),BigInteger.valueOf(60)));
		billeterosmdp.put(BigInteger.valueOf(500), new Billetero(BigInteger.valueOf(500),BigInteger.valueOf(20)));
		billeterosbsas.put(BigInteger.valueOf(100), new Billetero(BigInteger.valueOf(100),BigInteger.valueOf(40)));
		billeterosbsas.put(BigInteger.valueOf(500), new Billetero(BigInteger.valueOf(500),BigInteger.valueOf(30)));
		
		ReconocedorBilletes reconocedorBilletes = new ReconocedorBilletes(billetes);
		
		ATM atmMdq = new ATM(1, "Mar del Plata",bancos.get(1),bancos, billeterosmdp, reconocedorBilletes);
		ATM atmBsAs = new ATM(2,"Buenos Aires",bancos.get(2),bancos, billeterosbsas, reconocedorBilletes);
		
		ATMs.add(atmMdq);
		ATMs.add(atmBsAs);	
		
		/**
		 * Creacion de interfaces
		 */
		
		AskPinInterface askPinInterface = new AskPinView();
		AdministracionInterface administracion = new AdministracionView();
		ATMSelectorInterface atmSelectorInterface = new ATMSelectorView(administracion);
		atmSelectorInterface.llenarCombobox(ATMs);
		LectorTarjetaInterface lectorTarjetaInterface = new LectorTarjetaView(atmSelectorInterface);
		MessageInterface messageInterface = new MessageView();
		SelectorCuentaInterface selectorCuentaInterface = new SelectorCuentaView();
		ChangePassInterface changePassInterface = new ChangePassView();
		ExtraccionInterface extraccionInterface = new ExtraccionView();
		ConsultarMovimientosInterface consultarMovimientosInterface = new ConsultarMovimientosView();
		DepositarInterface depositarInterface = new DepositarView();
		TransferenciaInterface transferenciaInterface = new TransferenciaView();
		PrincipalMenuInterface principalMenu = new PrincipalMenuView(changePassInterface, extraccionInterface, depositarInterface, transferenciaInterface, consultarMovimientosInterface, atmSelectorInterface);

		
		/**
		 * Creacion de controladores
		 */
		
		AuthenticationController authenticationController = new AuthenticationController(ATMs, lectorTarjetaInterface, bancos, askPinInterface, 
			selectorCuentaInterface, messageInterface, principalMenu);
		MenuController menuController = new MenuController(authenticationController, principalMenu, messageInterface);
		ChangePassController changePassController = new ChangePassController(authenticationController, messageInterface);
		TransactionController transactionController = new TransactionController(authenticationController, messageInterface);
		MovementsQueryController movementsQueryController = new MovementsQueryController(authenticationController, messageInterface, consultarMovimientosInterface);
		AbmController abmController = new AbmController(messageInterface);
		
		/**
		 * Asignaciones para comunicacion MVC
		 */
		
		for (Banco banco : bancos) {
			banco.setPinRequestListener(authenticationController);
			banco.setCardValidatedListener(authenticationController);
			banco.setExtractionAcceptedListener(transactionController);
			banco.setMovementsReturnedListener(movementsQueryController);
		}	
		administracion.setAtmSelectorInterface(atmSelectorInterface);
		administracion.llenarCombobox(bancos);
		lectorTarjetaInterface.setCardReadedListener(authenticationController);
		askPinInterface.setPinListener(authenticationController);
		atmSelectorInterface.setAtmSelectedListener(authenticationController);
		askPinInterface.setAtmSelectorInterface(atmSelectorInterface);
		selectorCuentaInterface.setAccountSelectedListener(authenticationController);
		selectorCuentaInterface.setAtmSelectorInterface(atmSelectorInterface);
		principalMenu.setBalanceCheckListener(menuController);
		changePassInterface.setPrincipalMenuInterface(principalMenu);
		extraccionInterface.setPrincipalMenuInterface(principalMenu);
		depositarInterface.setPrincipalMenuInterface(principalMenu);
		transferenciaInterface.setPrincipalMenuInterface(principalMenu);
		consultarMovimientosInterface.setPrincipalMenuInterface(principalMenu);
		consultarMovimientosInterface.setMovementsQueryListener(movementsQueryController);
		changePassInterface.setChangePassListener(changePassController);
		extraccionInterface.setExtractionRequestEventListener(transactionController);
		depositarInterface.setDepositRequestListener(transactionController);
		transferenciaInterface.setTransferRequestListener(transactionController);
		administracion.setAltaCuentaRequestListener(abmController);
		administracion.setAltaTarjetaRequestListener(abmController);
		administracion.setModificacionRequestListener(abmController);
		administracion.setBajaRequestListener(abmController);
		
		
		/**
		 * Inicializacion
		 */
		
		atmSelectorInterface.mostrar(true);
		
	}

}
