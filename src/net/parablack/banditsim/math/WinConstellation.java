package net.parablack.banditsim.math;

import net.parablack.banditsim.ui.UIFrame;

public class WinConstellation {

	private Symbol[] sL, sM, sR;
	private int playedLines, loss;
	
	private int winMid, winUp, winDown, winDiaUp, winDiaDown;
	
	private int totalWin;
	
	private int co, currentMoney;
	
	public WinConstellation(Symbol[] sL, Symbol[] sM, Symbol[] sR, int playedLines, int loss, int co, int currentMoney) {
		
		this.sL = sL;
		this.sM = sM;
		this.sR = sR;
		
		this.playedLines = playedLines;
		this.loss = loss;
		this.co = co;
		this.currentMoney = currentMoney;
		
		winMid = WinAlgorithm.getWin(sL[1], sM[1], sR[1], 1);
		winUp = playedLines >= 3 ? WinAlgorithm.getWin(sL[0], sM[0], sR[0], 2) : 0;
		winDown = playedLines >= 3 ?  WinAlgorithm.getWin(sL[2], sM[2], sR[2], 2) : 0;
		
		winDiaUp = playedLines >= 5 ? WinAlgorithm.getWin(sL[2], sM[1], sR[0], 3) : 0;
		winDiaDown = playedLines >= 5 ? WinAlgorithm.getWin(sL[0], sM[1], sR[2], 3) : 0;
		
		totalWin = winDown + winMid + winUp + winDiaUp + winDiaDown;

	}
	
	
	public void logToFrame(UIFrame frame){
		frame.log("-------------------ROUND "+co+"--------------");
		frame.log("=============================================: +" + winDiaUp);
		frame.log(sL[0] + " | " + sM[0] + " | " + sR[0] + " : +" + 	winUp);
		frame.log(sL[1] + " | " + sM[1] + " | " + sR[1] + " : +" + 	winMid);
		frame.log(sL[2] + " | " + sM[2] + " | " + sR[2] + " : +" + 	winDown);
		frame.log("=============================================: +" + winDiaDown);		
		frame.log("Total: +" + totalWin + " , Loss ("+ playedLines+ "): " + loss + ", Sum: " + (totalWin - loss) + " : CurrentMoney: " + newCurrentMoney());
	}
	
	public int getTotalWin(){
		return totalWin;
	}
	
	public int newCurrentMoney() {
		return currentMoney + totalWin;
	}
	
}