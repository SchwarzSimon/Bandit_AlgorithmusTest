package net.parablack.banditsim.math;

public class WinAlgorithm {

	public static int getWin(Symbol s1, Symbol s2, Symbol s3, int prio) {

		int win = 0;

		int sevens = 0;
		if(s1 == Symbol.s7) sevens++;
		if(s2 == Symbol.s7) sevens++;
		if(s3 == Symbol.s7) sevens++;

		
		switch (sevens) {
		case 1:
			win += byPrio(prio, 10, 5, 0);
			break;
		case 2:
			win += byPrio(prio, 50, 25, 20);
			break;
		case 3:
			win += byPrio(prio, 1000, 500, 300);
			break;
		default:
			break;
		}

		
		if((s1 == s2 || s2 == Symbol.JOKER) && s1 == s3 && s1 != Symbol.s7){
			win += byPrio(prio, s1.getWorth() * 3, s1.getWorth() * 2, s1.getWorth());
		}
		if(prio == 3 && s2 == Symbol.s7 && s1 == s2) win+=s1.getWorth() * 2;
	
		
		return win;
	}

	private static int byPrio(int prio, int w1, int w2, int w3) {
		switch (prio) {
		case 1:
			return w1;
		case 2:
			return w2;
		case 3:
			return w3;
		default:
			return 0;
		}
	}

}
