package model.players;

import controller.Config;

public abstract class Player {
	
	private static final int MAX_INSECTS = Config.COLUMN_COUNT;
	private int noOfInsects_Selected = 0;
	private int noOfInsects_Current = 0;

	public Player() {
		super();
	}

	public int getNoOfInsects_Selected() {
		return noOfInsects_Selected;
	}

	public void setNoOfInsects_Selected(int noOfInsects_Selected) {
		this.noOfInsects_Selected = noOfInsects_Selected;
	}

	public int getNoOfInsects_Current() {
		return noOfInsects_Current;
	}

	public void setNoOfInsects_Current(int noOfInsects_Current) {
		this.noOfInsects_Current = noOfInsects_Current;
	}

	public static int getMaxInsects() {
		return MAX_INSECTS;
	}
	
	

}
