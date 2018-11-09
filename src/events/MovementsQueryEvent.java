package events;

public class MovementsQueryEvent {
	
	private int ano, mes;

	public MovementsQueryEvent(int ano, int mes) {
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
	

