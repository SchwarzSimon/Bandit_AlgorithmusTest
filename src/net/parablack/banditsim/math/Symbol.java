package net.parablack.banditsim.math;

public enum Symbol {

	c30(30), c40(40), c60(60), c90(90), c120(120), c180(180), c240(240), c300(300),
	
	s7(-1), JOKER(-2);
	
	private int worth;
	
	private Symbol(int worth) {
		this.worth = worth;
	}
	
	public int getWorth() {
		return worth;
	}
	
	
}
