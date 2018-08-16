package init;

import java.math.BigDecimal;
import java.math.BigInteger;

import controller.AuthenticationController;
import model.*;
import view.*;

public class RunApp {
	

	public static void main (String[] args) {
		
		// Numeros de tarjeta
		BigInteger idtarjeta0 = BigInteger.valueOf(1200000); // En whitelist y no existente
		BigInteger idtarjeta1 = BigInteger.valueOf(2000001);// En whitelist y existente
		BigInteger idtarjetax = BigInteger.valueOf(2000003);
		BigInteger idtarjeta2 = BigInteger.valueOf(3500000);// En whitelist y existente
		BigInteger idtarjeta3 = BigInteger.valueOf(4000000); // En whitelist y existente
		BigInteger idtarjeta4 = BigInteger.valueOf(4000001); // Fuera de whitelist
		BigInteger idtarjeta5 = BigInteger.valueOf(1000000); // En whitelist y existente
		
		//Whitelist bancos
		BigInteger inicial = BigInteger.valueOf(1000000);
		BigInteger fin = BigInteger.valueOf(2000000);
		BigInteger inicial1 = BigInteger.valueOf(2000001);
		BigInteger fin1 = BigInteger.valueOf(3000000);
		BigInteger inicial2 = BigInteger.valueOf(3000001);
		BigInteger fin2 = BigInteger.valueOf(4000000);
		
		//
		BigInteger cbu0 = BigInteger.valueOf(999999990);
		BigDecimal saldo0 = BigDecimal.valueOf(1000.00);
		
		// Declaracion de cajeros, bancos y tarjetas
		Banco banco0 = new Banco("La Plaza",inicial,fin);
		Banco banco1 = new Banco("Provincia",inicial1,fin1);
		Banco banco2 = new Banco("Frances",inicial2,fin2);
		ATM cajero1 = new ATM(1, "Mar del Plata",banco0);
		
		cajero1.addBanco(banco0);
		cajero1.addBanco(banco1);
		cajero1.addBanco(banco2);
		TarjetaATM tarjeta0 = new TarjetaATM(idtarjeta0, "1234", true);
		TarjetaATM tarjeta1 = new TarjetaATM(idtarjeta1, "1234", true);
		TarjetaATM tarjeta2 = new TarjetaATM(idtarjeta2, "1234", true);
		TarjetaATM tarjeta3 = new TarjetaATM(idtarjeta3, "1234", true);
		TarjetaATM tarjeta5 = new TarjetaATM(idtarjeta5, "1234", true);
		TarjetaATM tarjetax = new TarjetaATM(idtarjetax, "1234", true);
		

		
		// Lote Pruebas
		/*System.out.println("Prueba 1:");
		banco0.addTarjeta(tarjeta1); // Asignacion incorrecta: Error en banco.setTarjetas
		banco1.addTarjeta(tarjeta1); // Asignacion correcta: Tarjeta agregada con exito
		cajero1.validar(idtarjeta1, 1234); // Banco Provincia: Autenticacion correcta
		System.out.println("Prueba 2:");
		cajero1.validar(idtarjeta4, 1234); // Error: Esta tarjeta no puede operar en este ATM
		System.out.println("Prueba 3:");
		cajero1.validar(idtarjeta0, 1234); // Banco La Plaza: Tarjeta no encontrada en el banco
		
		tarjeta0.setUsuario(new Usuario(1, "Tortora", "Rodrigo"));
		tarjeta0.getUsuario().setCuenta(new CajaAhorro(cbu0,saldo0,100.00,5000.00));
		System.out.println("-----------");
		System.out.println(tarjeta0.getUsuario().getCuenta(0).tipoCta());*/
		
		// Lote Pruebas1
		banco1.addTarjeta(tarjeta1); // Asignacion correcta: Tarjeta agregada con exito
		banco1.addTarjeta(tarjetax); // Asignacion correcta: Tarjeta agregada con exito
		cajero1.setBancoATM(banco0); // Banco del cajero es La Plaza
		//cajero1.validarBanco(idtarjeta1, 1234); // Banco Provincia: Autenticacion correcta
		
		
		/**
		 * Asignaciones MVC
		 */
		ViewInicioInterface inicioInterface = new Inicio();
		AskPinInterface askPinInterface = new AskPin();
		AuthenticationController controller = new AuthenticationController(cajero1, inicioInterface, banco1, askPinInterface);
		banco1.setPinFailedListener(controller);
		banco1.setPinRequestListener(controller);
		inicioInterface.setCardReadedListener(controller); // Se indica X clase que hereda/implementa de la interface inicioInterface, va a tener como listener (o sea receptor de sus eventos) a un objeto 'controller'
		askPinInterface.setPinListener(controller);
		inicioInterface.inicializar();
		
	}

}
