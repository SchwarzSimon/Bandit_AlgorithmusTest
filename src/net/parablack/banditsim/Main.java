package net.parablack.banditsim;

import net.parablack.banditsim.ui.UIFrame;

public class Main {
	
	private static UIFrame frame;
	
	public static void main(String[] args) {
		
		frame = new UIFrame();
//		frame.log(WinAlgorithm.getWin(Symbol.s7, Symbol.s7, Symbol.s7, 1) + "");
	}
	
	public static UIFrame getFrame() {
		return frame;
	}
}
