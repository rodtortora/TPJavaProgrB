package events;

public class ChangePassEvent {
	
	private String passActual, PassNew, ConfirmPass;
	
	public ChangePassEvent(String passActual, String passNew, String confirmPass) {
		this.setPassActual(passActual);
		this.setPassNew(passNew);
		this.setConfirmPass(confirmPass);		
	}

	public String getPassActual() {
		return passActual;
	}

	public void setPassActual(String passActual) {
		this.passActual = passActual;
	}

	public String getPassNew() {
		return PassNew;
	}

	public void setPassNew(String passNew) {
		PassNew = passNew;
	}

	public String getConfirmPass() {
		return ConfirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		ConfirmPass = confirmPass;
	}

}
