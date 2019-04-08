package model.player;

import model.insect.Insect;

public class Player {
	
	private Insect selectedInsect = null;

	public Insect getSelectedInsect() {
		return selectedInsect;
	}

	public void setSelectedInsect(Insect selectedInsect) {
		this.selectedInsect = selectedInsect;
	}
	
}
