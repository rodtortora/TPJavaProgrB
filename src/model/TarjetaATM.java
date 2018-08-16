package model;
import java.math.BigInteger;

public class TarjetaATM {
	private Usuario usuario;
	private BigInteger ID;
	private int PIN, intentosFallidos;
	private boolean habilitada;
	public TarjetaATM(BigInteger iD, int pIN, boolean habilitada) {
		super();
		ID = iD;
		PIN = pIN;
		this.habilitada = habilitada;
		this.setIntentosFallidos(0);
	}
	public int getPIN() {
		return PIN;
	}
	public void setPIN(int pIN) {
		PIN = pIN;
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
