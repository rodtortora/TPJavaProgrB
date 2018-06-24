package model;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class ATM {
	private LectorTarjeta lector;
	private ArrayList<Banco> bancos = new ArrayList<>();
	private ArrayList<Billetero> billeteros = new ArrayList<>();
	private int ID;
	private String ubicacion;
	private boolean modoMantenimiento;
	private Banco bancoActual;
	private TarjetaATM tarjetaActual;
	private Scanner teclado;
	private Cuenta cuentaSeleccionada;
	private BigInteger saldoDisponible;
	
	public ATM(int ID, String ubicacion) {
		this.ID = ID;
		this.ubicacion = ubicacion;
	}
	public int getID() {
		return ID;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public boolean isModoMantenimiento() {
		return modoMantenimiento;
	}
	public void setModoMantenimiento(boolean modoMantenimiento) {
		this.modoMantenimiento = modoMantenimiento;
	}
	public Banco getBancoActual() {
		return bancoActual;
	}
	public void setBancoActual(Banco bancoActual) {
		this.bancoActual = bancoActual;
	}
	public TarjetaATM getTarjetaActual() {
		return tarjetaActual;
	}
	public void setTarjetaActual(TarjetaATM tarjetaActual) {
		this.tarjetaActual = tarjetaActual;
	}
	public void addBilletero(Billetero billetero) {
		billeteros.add(billetero);
	}

	
	public BigDecimal getSaldoDisponible() {
		for (Billetero billetero : billeteros) {
			this.saldoDisponible += billetero.getSaldo();
		}
	}
	public void validar(BigInteger idTarjetaATM, int PIN) {
		lector = new LectorTarjeta();
		lector.leerTarjeta(idTarjetaATM); // TODO: Devuelve el nro por la pantalla, solo si el cajero esta habilitado
		if (!this.isModoMantenimiento()) {
			Iterator<Banco> itbancos = bancos.iterator();
			while ((itbancos.hasNext()) && (this.getBancoActual() == null)) {
				Banco banco = itbancos.next();
				if ((idTarjetaATM.compareTo(banco.getMinRango()) == 0) || (idTarjetaATM.compareTo(banco.getMinRango()) == 1)) {
					if ((idTarjetaATM.compareTo(banco.getMaxRango()) == 0) || (idTarjetaATM.compareTo(banco.getMaxRango()) == -1)) {
						this.setBancoActual(banco);
						bancoActual = banco;
						System.out.println(banco.toString()); // Muestra datos del banco
						Iterator<TarjetaATM> tarjetas = banco.getTarjetas().iterator();
						while (tarjetas.hasNext() && this.getTarjetaActual() == null) {
							TarjetaATM tarjeta = tarjetas.next();
							if (idTarjetaATM.compareTo(tarjeta.getID()) == 0 && tarjeta.isHabilitada()) {
								this.setTarjetaActual(tarjeta);
								tarjetaActual = tarjeta;
								int fallos = 0;
								while (PIN != tarjeta.getPIN() && fallos < 3) {								
									fallos++;
									System.out.println("Ingrese pin nuevamente");
									teclado = new Scanner(System.in);
									PIN = teclado.nextInt();
								}
								if (fallos == 3) {
									lector.retenerTarjeta(idTarjetaATM);
									tarjeta.setHabilitada(false);
									System.out.println("Tarjeta bloqueada por intentos fallidos");
									break;
									//TODO: Mostrar menu de procedimiento ante retencion de tarjeta
								} else {
									System.out.println("Autenticacion correcta");
									// TODO: mostrar menu correspondiente a autenticación correcta										
								}		
							}					
						}	
						if (this.getTarjetaActual() == null) {
							System.out.println("Tarjeta no encontrada en el banco"); //TODO ir al menu correspondiente
						} else {
							this.setTarjetaActual(null);
						}
					}
				}
			}
			if (this.getBancoActual() == null) {
				System.out.println("Esta tarjeta no puede operar en este ATM");
				// TODO: mostrar menu correspondiente
			} else {
				this.setBancoActual(null);
			}			
		} else {
			System.out.println("Error: modo mantenimiento");
		}
		
	}
	
	public void elegirCuenta(Cuenta cuentaSeleccionada) {
		if (this.getTarjetaActual().getUsuario().getCuenta().size() > 1) {
			this.cuentaSeleccionada = cuentaSeleccionada;
		} else {
			System.out.println("No hay otra cuenta para elegir");
		}
	}
	
	public Cuenta getCuentaSeleccionada() {
		return this.cuentaSeleccionada;
	}
	
	public void extraer(BigDecimal valor) {
		if (this.getBancoActual() == null || this.getTarjetaActual() == null || this.getCuentaSeleccionada() == null) {
			System.out.println("Error: tarjeta, banco o cuenta son null");
		} else {
			if (this.getCuentaSeleccionada().getSaldoDisponible().compareTo(valor) == -1) {
				System.out.println("No se puede extraer tanto dinero, supera saldo disponible de la cuenta. Reintente con cantidad menor o manana");
			} else {						
					// Validar si se pueden combinar billetes para extraer la cantidad pedida
				}
			}
			// CC: X$ en descubierto, 30$ comision si saca de un banco que no es el suyo, 15$ a partir de 4ta extrac. mismo ATM. 300$ mensual
			// caja de ahorro: no descubierto, 10$ de comision por banco distinto, 150$ mant mensual
			// cuenta sueldo: caja de ahorro sin comisiones ni mantenimiento, guarda CUIT de la empresa que deposita sueldo
			// los valores de los cargos se obtienen de una lista de tarifas
	}
	
		public static void main (String[] args) {
			
		// Numeros de tarjeta
		BigInteger idtarjeta0 = BigInteger.valueOf(1200000); // En whitelist y no existente
		BigInteger idtarjeta1 = BigInteger.valueOf(2000001);// En whitelist y existente
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
		ATM cajero1 = new ATM(1, "Mar del Plata");
		Banco banco0 = new Banco("La Plaza",inicial,fin);
		Banco banco1 = new Banco("Provincia",inicial1,fin1);
		Banco banco2 = new Banco("Frances",inicial2,fin2);
		cajero1.bancos.add(banco0);
		cajero1.bancos.add(banco1);
		cajero1.bancos.add(banco2);
		TarjetaATM tarjeta0 = new TarjetaATM(idtarjeta0, 1234, true);
		TarjetaATM tarjeta1 = new TarjetaATM(idtarjeta1, 1234, true);
		TarjetaATM tarjeta2 = new TarjetaATM(idtarjeta2, 1234, true);
		TarjetaATM tarjeta3 = new TarjetaATM(idtarjeta3, 1234, true);
		TarjetaATM tarjeta5 = new TarjetaATM(idtarjeta5, 1234, true);
		
		// Pruebas
		System.out.println("Prueba 1:");
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
		System.out.println(tarjeta0.getUsuario().getCuenta1(0).tipoCta());
		
	}
}
