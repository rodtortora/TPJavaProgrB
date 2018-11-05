package model;

public class TipoTransaccion {
	public final static int extraccion = 1;
	public final static int transferenciaEnviar = 2;
	public final static int compraComercio = 3;
	public final static int cargoBancoForaneo = 4;
	public final static int cargoMantenimiento = 5;
	public final static int depositoEfectivo = 6;
	public final static int acredHaberes = 7;
	public final static int liqIntereses = 8;
	public final static int transferenciaRecibir = 10;
	public final static int cargoExtraccion = 11;
	
	public final static String getDescription(int tipoTransaccion) {
		switch (tipoTransaccion) {
		case TipoTransaccion.extraccion: return "EXTRACCION";
		case TipoTransaccion.transferenciaEnviar: return "TRANSFERENCIA A OTRA CUENTA";
		case TipoTransaccion.compraComercio: return "COMPRA COMERCIO";
		case TipoTransaccion.cargoBancoForaneo: return "CARGO POR EXT BANCO FORANEO";
		case TipoTransaccion.cargoMantenimiento: return "CARGO POR MANTENIMIENTO";
		case TipoTransaccion.depositoEfectivo: return "DEPOSITO EN EFECTIVO";
		case TipoTransaccion.acredHaberes: return "ACREDITACION HABERES";
		case TipoTransaccion.liqIntereses: return "LIQUIDACION INTERESES";
		case TipoTransaccion.transferenciaRecibir: return "TRANSFERENCIA DESDE OTRA CUENTA";
		case TipoTransaccion.cargoExtraccion: return "CARGO POR EXTRACCION";
		}
		return null;
	}
}
