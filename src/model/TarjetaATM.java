package model;
import java.math.BigInteger;

public class TarjetaATM {
	private Usuario usuario;
	private BigInteger ID;
	String PIN;
	private int intentosFallidos;
	private boolean habilitada;
	public TarjetaATM(BigInteger ID, String pin, boolean habilitada) {
		super();
		this.ID = ID;
		this.PIN = pin;
		this.habilitada = habilitada;
		this.setIntentosFallidos(0);
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pin) {
		this.PIN = pin;
	}
	public boolean isHabilitada() {
		return habilitada;
	}
	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	public void setID(BigInteger iD) {
		ID = iD;
	}
	public BigInteger getID() {
		return ID;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getIntentosFallidos() {
		return intentosFallidos;
	}
	public void setIntentosFallidos(int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}
	public void setIntentosFallidos() {
		this.intentosFallidos++;
	}
}
