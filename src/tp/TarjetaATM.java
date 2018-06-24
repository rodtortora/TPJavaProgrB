package tp;
import java.math.BigInteger;

public class TarjetaATM {
	private Usuario usuario;
	private BigInteger ID;
	private int PIN;
	private boolean habilitada;
	public TarjetaATM(BigInteger iD, int pIN, boolean habilitada) {
		super();
		ID = iD;
		PIN = pIN;
		this.habilitada = habilitada;
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
}
