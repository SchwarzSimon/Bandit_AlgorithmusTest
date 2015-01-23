package net.parablack.banditsim.math;

import static net.parablack.banditsim.math.SymbolPicker.line1;
import static net.parablack.banditsim.math.SymbolPicker.line2;
import static net.parablack.banditsim.math.SymbolPicker.line3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.parablack.banditsim.ui.UIFrame;

public class BanditMath {

	private static Random r = new Random();
	public static final int START_MONEY = 100000;
	private static int currentMoney = START_MONEY;
	
	
	private static int highestIndex;
	private static int lowestIndex;
	private static ArrayList<Integer> jackpots = new ArrayList<>();
	private static int lowestMoney, highestMoney = 0;
	
	private static HashMap<Integer, WinConstellation> results = new HashMap<>();
	
	public static void start(UIFrame frame) {
		
		int playedLines = frame.getLines();
		int loss = 0;
		switch (playedLines) {
		case 1: loss = 10;
			break;
		case 3: loss = 20;
			break;
		case 5: loss = 30;
			break;
		default:
			break;
		}
		int co = 0;
		for(; co < frame.getRepetitions(); co++){
		
//		frame.log("Starting Simulation");
					
		currentMoney -= loss;
		
		//frame.log(Arrays.toString(SymbolPicker.line1) + " (" + SymbolPicker.line1.length + ")");
		//frame.log(Arrays.toString(SymbolPicker.line2) + " (" + SymbolPicker.line2.length + ")");
		//frame.log(Arrays.toString(SymbolPicker.line3) + " (" + SymbolPicker.line3.length + ")");

		Symbol[] sL = getThree(line1, next());
		Symbol[] sM = getThree(line2, next());
		Symbol[] sR = getThree(line3, next());
		

		
		WinConstellation winConst = new WinConstellation(sL, sM, sR, playedLines, loss, co, currentMoney);
		
		results.put(co, winConst);
		
		if(getResult(highestIndex).getTotalWin() < winConst.getTotalWin() && winConst.getTotalWin() != 1000) highestIndex = co;
		if(getResult(lowestIndex).getTotalWin() > winConst.getTotalWin()) lowestIndex = co;
		
		if(winConst.getTotalWin() >= 1000) jackpots.add(co);
		
		currentMoney = winConst.newCurrentMoney();
		
		if(currentMoney < getResult(lowestMoney).newCurrentMoney()) lowestMoney = co;
		if(currentMoney > getResult(highestMoney).newCurrentMoney()) highestMoney = co;
		
		frame.updateMoney(currentMoney);
	
	//	winConst.logToFrame(frame);
		
		}
		frame.log("SIMULATION SUMMARY");
		frame.log("");
		
		frame.log("Highest Win (excluding Jackpots):");
		getResult(highestIndex).logToFrame(frame);
		
		frame.log("");
		
	
		double percent = (jackpots.size() / (double) frame.getRepetitions()) * 100;
		
		frame.log("Jackpots: " + jackpots.size() + " --> " +percent + "%");
		
		frame.log("");
		
		int totalWin = currentMoney - START_MONEY;
		double winPerGame = totalWin / (double) co;
		
		frame.log("Total win this round: " + totalWin + " --> " + winPerGame + " win per game.");
		
		frame.log("");
		frame.log("Highest Money at:");
		getResult(highestMoney).logToFrame(frame);
		
		frame.log("Lowest money at:");
		getResult(lowestMoney).logToFrame(frame);
		
//		for(int j : jackpots){
//			getResult(j).logToFrame(frame);
//		}
	}

	
	private static int next(){
		
		return r.nextInt(24);
		
	}
	
	
	private static Symbol[] getThree(Symbol[] line, int mid){
		
		Symbol s1;
		Symbol s2 = line[mid];
		Symbol s3;
		
		if(mid == 0){
			s1 = line[23];
			s3 = line[1];
			
		}else if(mid == 23){
			s1 = line[22];
			s3 = line[0];
			
		}else{
			s1 = line[mid - 1];
			s3 = line[mid + 1];
		}
		
		return new Symbol[] { s1, s2, s3};
		
	}
	
	public static void reset(){
		currentMoney = START_MONEY;
		results.clear();
		highestIndex = 0;
		lowestIndex = 0;
		jackpots.clear();
		lowestMoney = 0;
		highestMoney = 0;
	}

	public static WinConstellation getResult(int index){
		return results.get(index);
	}
	
}
