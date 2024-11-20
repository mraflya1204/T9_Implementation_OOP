package predictivegui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class T9View {
	private JFrame frame;
	private JTextField textField;
	//private JLabel signatureDisplay;
	private JButton[] dialButtons = new JButton[10];
	private JButton starButton, hashtagButton;
	private JPanel buttonPanel;
	
	T9View() {
		frame = new JFrame("Predictive T9");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(365, 512);
		frame.setLayout(null);
		
		
		// Text field initialization
		textField = new JTextField();
		textField.setBounds(20, 20, 300, 100);
		textField.setEditable(false);
		
		// Button Initialization
		//"<html>" + String.valueOf(0) + "<br>" + "_" + "</html>"
		dialButtons[0] = new JButton("<html><div text-align:center>0<br/>_</div></html>");
		//dialButtons[0].addActionListener(this);
		dialButtons[0].setFocusable(false);
		
		dialButtons[1] = new JButton(String.valueOf(1));
		//dialButtons[1].addActionListener(this);
		dialButtons[1].setFocusable(false);
		
		dialButtons[2] = new JButton("<html><div text-align:center>2<br/>abc</div></html>");
		//dialButtons[2].addActionListener(this);
		dialButtons[2].setFocusable(false);
		
		dialButtons[3] = new JButton("<html><div text-align:center>3<br/>def</div></html>");
		//dialButtons[3].addActionListener(this);
		dialButtons[3].setFocusable(false);
		
		dialButtons[4] = new JButton("<html><div text-align:center>4<br/>ghi</div></html>");
		//dialButtons[4].addActionListener(this);
		dialButtons[4].setFocusable(false);
		
		dialButtons[5] = new JButton("<html><div text-align:center>5<br/>jkl</div></html>");
		//dialButtons[5].addActionListener(this);
		dialButtons[5].setFocusable(false);
		
		dialButtons[6] = new JButton("<html><div text-align:center>6<br/>mno</div></html>");
		//dialButtons[6].addActionListener(this);
		dialButtons[6].setFocusable(false);
		
		dialButtons[7] = new JButton("<html><div text-align:center>7<br/>pqrs</div></html>");
		//dialButtons[7].addActionListener(this);
		dialButtons[7].setFocusable(false);
		
		dialButtons[8] = new JButton("<html><div text-align:center>8<br/>tuv</div></html>");
		//dialButtons[8].addActionListener(this);
		dialButtons[8].setFocusable(false);
		
		dialButtons[9] = new JButton("<html><div text-align:center>9<br/>wxyz</div></html>");
		//dialButtons[9].addActionListener(this);
		dialButtons[9].setFocusable(false);
		
		starButton = new JButton("*");
		//starButton.addActionListener(this);
		starButton.setFocusable(false);
		
		hashtagButton = new JButton("#");
		//hashtagButton.addActionListener(this);
		hashtagButton.setFocusable(false);
		
		// Create panel for button
		buttonPanel = new JPanel();
		buttonPanel.setBounds(20, 150, 300, 300);
		buttonPanel.setLayout(new GridLayout(4,4, 10, 10));
		
		// Add buttons to the panel
		for (int i = 1; i < 10; ++i) {
			buttonPanel.add(dialButtons[i]);
		}
		
		buttonPanel.add(starButton);
		buttonPanel.add(dialButtons[0]);
		buttonPanel.add(hashtagButton);
		
		frame.add(textField);
		
		frame.add(buttonPanel);
		
		//frame.add(signatureDisplay);
		
		//Make frame visible
		frame.setVisible(true);
	}
	public JButton[] getNumberButton() {
		return dialButtons;
	}
	
	public JButton getStarButton() {
		return starButton;
	}
	
	public JButton getHashtagButton() {
		return hashtagButton;
	}
	public void buttonListener(ActionListener listenForButtons) {
		dialButtons[0].addActionListener(listenForButtons);
		dialButtons[1].addActionListener(listenForButtons);
		dialButtons[2].addActionListener(listenForButtons);
		dialButtons[3].addActionListener(listenForButtons);
		dialButtons[4].addActionListener(listenForButtons);
		dialButtons[5].addActionListener(listenForButtons);
		dialButtons[6].addActionListener(listenForButtons);
		dialButtons[7].addActionListener(listenForButtons);
		dialButtons[8].addActionListener(listenForButtons);
		dialButtons[9].addActionListener(listenForButtons);
		
		starButton.addActionListener(listenForButtons);
		hashtagButton.addActionListener(listenForButtons);
	}
	public void setTextField(String buffer) {
		this.textField.setText(buffer);
	}
}
