package game;

import java.io.Serializable;

import environment.Coordinate;

public class InfoToSend implements Serializable{
	public Coordinate cor;
	public  byte Strength;
	public boolean isHuman;
	public int id;
	public InfoToSend(Coordinate cor, Player player) {
		this.cor = cor;
		if(player!=null) {
		this.Strength = player.getCurrentStrength();
		this.isHuman = player.isHumanPlayer();
		this.id = player.getIdentification();
		}
	}
	
	public Coordinate getCor() {
		return cor;
	}
	
	public Boolean isHuman() {
		return isHuman;
	}
	
	public byte getStrength() {
		return Strength;
	}
	public int getId() {
		return id;
	}
}
