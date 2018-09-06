package events;

import view.Visible;

public class MenuEvent {
	
	private Visible visible;
	
	public MenuEvent(Visible e) {
		this.setVisible(e);
	}

	public Visible getVisible() {
		return visible;
	}

	public void setVisible(Visible visible) {
		this.visible = visible;
	}

}
