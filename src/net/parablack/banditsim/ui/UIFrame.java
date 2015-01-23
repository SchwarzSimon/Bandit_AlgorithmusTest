package net.parablack.banditsim.ui;

import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.parablack.banditsim.math.BanditMath;

public class UIFrame extends JFrame{

	private static final String SEPERATOR = System.getProperty("line.separator");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2017194884179319576L;

	private JTextArea console = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(console);
	private JButton start = new JButton("Start");
	private JLabel currentMoney = new JLabel();
	private JTextField repeat = new JTextField();
	
	
	private JRadioButton button1line = new JRadioButton("1 Line");
	private JRadioButton button3line = new JRadioButton("3 Lines");
	private JRadioButton button5line = new JRadioButton("5 Lines");
	private ButtonGroup lineGroup = new ButtonGroup();
	
	private JButton reset = new JButton("Reset");
	
	public UIFrame() {
		
		setSize(1000, 600);
		
		
	//	console.setBounds(20, 20, 900, 300);
		console.setVisible(true);
	//	log("Starting boottrap...");
		console.setVisible(true);
		
		scrollPane.setVisible(true);
		scrollPane.setBounds(20, 20, 900, 300);
		
		add(scrollPane);
		
		start.setText("Start");
		start.setBounds(20, 340, 100, 60);
		start.setVisible(true);
		start.addActionListener((ActionEvent e) -> BanditMath.start(this));
		start.setVisible(true);
		add(start);
		
		currentMoney.setBounds(300, 340, 200, 60);
		updateMoney(BanditMath.START_MONEY);
		currentMoney.setVisible(true);
		add(currentMoney);
		
		repeat.setBounds(20, 420, 100, 30);
		repeat.setVisible(true);
		repeat.setText("10000");
		add(repeat);
		
		button1line.setBounds(300, 440, 200, 20);
		button1line.setSelected(true);
		lineGroup.add(button1line);
		button3line.setBounds(300, 460, 200, 20);
		lineGroup.add(button3line);
		button5line.setBounds(300, 480, 200, 20);
		lineGroup.add(button5line);
		
		add(button1line);
		add(button3line);
		add(button5line);
		
		reset.setBounds(600, 340, 100, 60);
		reset.addActionListener((ActionEvent e) -> {
			console.setText("");
			BanditMath.reset();
		});
		add(reset);
		
		add(new JPanel());
		
		setVisible(true);
	}
	
	
	public void log(String log){
		console.append(log + SEPERATOR);
	}
	
	public void updateMoney(int money){
		String mn = money + "";
		String last, first;
		try{
		last = mn.substring(mn.length() - 2);
		first = mn.substring(0, mn.length() - 2);
		}
		catch(StringIndexOutOfBoundsException e){
			last = mn;
			first = 0 + "";
			
		}
		currentMoney.setText("Current Money: " + first  + "." + last);
	}
	
	public int getRepetitions(){
		try {
			return Integer.parseInt(repeat.getText());
		} catch (Exception e) {
			return 1;
		}
	}
	
	public int getLines(){
		if(button1line.isSelected()) return 1;
		if(button3line.isSelected()) return 3;
		if(button5line.isSelected()) return 5;
		throw new RuntimeException("No button selected!");
	}
	
}
