package predictivegui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import predictive.DictionaryMapImpl;

public class T9Main implements ActionListener{
	//For predictive text part
	private DictionaryMapImpl dictionary = new DictionaryMapImpl("C:\\Users\\rabid\\Github\\T9_Implementation_OOP\\src\\predictive\\words");
	private List<String> currentWordList = new ArrayList<String>();
	private List<String> wordBuffer = new ArrayList<String>();
	private int wordBufferCounter = 0;
	
	private int currentWordListCounter = 0;
	private String currentSignature = new String();
	
	private JFrame frame;
	private JTextField textField;
	private JLabel signatureDisplay;
	private JButton[] dialButtons = new JButton[10];
	private JButton starButton, hashtagButton;
	private JPanel buttonPanel;
	
	T9Main() {
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
		dialButtons[0].addActionListener(this);
		dialButtons[0].setFocusable(false);
		
		dialButtons[1] = new JButton(String.valueOf(1));
		dialButtons[1].addActionListener(this);
		dialButtons[1].setFocusable(false);
		
		dialButtons[2] = new JButton("<html><div text-align:center>2<br/>abc</div></html>");
		dialButtons[2].addActionListener(this);
		dialButtons[2].setFocusable(false);
		
		dialButtons[3] = new JButton("<html><div text-align:center>3<br/>def</div></html>");
		dialButtons[3].addActionListener(this);
		dialButtons[3].setFocusable(false);
		
		dialButtons[4] = new JButton("<html><div text-align:center>4<br/>ghi</div></html>");
		dialButtons[4].addActionListener(this);
		dialButtons[4].setFocusable(false);
		
		dialButtons[5] = new JButton("<html><div text-align:center>5<br/>jkl</div></html>");
		dialButtons[5].addActionListener(this);
		dialButtons[5].setFocusable(false);
		
		dialButtons[6] = new JButton("<html><div text-align:center>6<br/>mno</div></html>");
		dialButtons[6].addActionListener(this);
		dialButtons[6].setFocusable(false);
		
		dialButtons[7] = new JButton("<html><div text-align:center>7<br/>pqrs</div></html>");
		dialButtons[7].addActionListener(this);
		dialButtons[7].setFocusable(false);
		
		dialButtons[8] = new JButton("<html><div text-align:center>8<br/>tuv</div></html>");
		dialButtons[8].addActionListener(this);
		dialButtons[8].setFocusable(false);
		
		dialButtons[9] = new JButton("<html><div text-align:center>9<br/>wxyz</div></html>");
		dialButtons[9].addActionListener(this);
		dialButtons[9].setFocusable(false);
		
		starButton = new JButton("*");
		starButton.addActionListener(this);
		starButton.setFocusable(false);
		
		hashtagButton = new JButton("#");
		hashtagButton.addActionListener(this);
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
	
	public static void main(String[] args) {
		T9Main window = new T9Main();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Append the word buffer with new blank one, resetting current signature and list
		if (e.getSource() == dialButtons[0]) {
			if (wordBuffer.get(wordBufferCounter).length() > 0) {
				currentSignature = "";
				currentWordListCounter = 0;
				currentWordList = null;
				wordBufferCounter++;
				wordBuffer.add("");
				setTextField();
			}
		}
		
		// Append the current signature with the pressed numbers, 
		// Find the matching words with the signature
		for (int i = 1; i < 10; ++i) {
			if (e.getSource() == dialButtons[i]) {
				currentSignature += String.valueOf(i);
				// Find the list of word with signature
				currentWordList = dictionary.signatureToWords(currentSignature);
				currentWordListCounter = 0;
				if (!currentWordList.isEmpty()) {
					// Loop back to the beginning of the list to make sure no access overflow
					if (currentWordListCounter > currentWordList.size()-1)
						currentWordListCounter = 0;
					if (!wordBuffer.isEmpty())
						wordBuffer.set(wordBufferCounter, currentWordList.get(currentWordListCounter));
					else 
						wordBuffer.add(currentWordList.get(currentWordListCounter));
				}
				
				// Set current word to empty, meaning no matching word
				else {
					if (!wordBuffer.isEmpty())
						wordBuffer.set(wordBufferCounter, "");
					else
						wordBuffer.add("");
				}
				setTextField();
			}
		}
		
		// Iterate through the word list of the current signature
		if (e.getSource() == starButton) {
			if (!currentWordList.isEmpty()) {
				
				currentWordListCounter++;

				// Loop back to the beginning of the list to make sure no access overflow
				if (currentWordListCounter > currentWordList.size()-1)
					currentWordListCounter = 0;
				wordBuffer.set(wordBufferCounter, currentWordList.get(currentWordListCounter));
			}
			setTextField();
		}
		
		//Remove last letter from the current buffer, also removing the last number from the signature
		if (e.getSource() == hashtagButton) {
			if (!wordBuffer.isEmpty()) {
				String currentWord = wordBuffer.get(wordBufferCounter);
				//Update with last letter removed
				if (wordBuffer.get(wordBufferCounter).length() > 0)
					wordBuffer.set(wordBufferCounter, currentWord.substring(0, currentWord.length()-1));
			}
			
			if (currentSignature.length() > 0)
				currentSignature = currentSignature.substring(0, currentSignature.length()-1);
			
			currentWordList = dictionary.signatureToWords(currentSignature);
			currentWordListCounter = 0;
			setTextField();
		}
	}
	
	private void setTextField() {
		String displayBuffer = new String();
		for (String a : wordBuffer) {
			displayBuffer += a += " ";
		}
		textField.setText(displayBuffer);
	}
	
}
