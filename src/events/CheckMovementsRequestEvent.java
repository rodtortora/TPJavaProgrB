package events;

public class CheckMovementsRequestEvent {
	private int mes, ano;

	public CheckMovementsRequestEvent(int mes, int ano) {
		this.setAno(ano);
		this.setMes(mes);
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

}
