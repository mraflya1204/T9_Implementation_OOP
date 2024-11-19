package predictivegui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import predictive.DictionaryMapImpl;

public class T9Main implements ActionListener{
	//For predictive text part
	DictionaryMapImpl dictionary = new DictionaryMapImpl("C:\\Users\\rabid\\Github\\T9_Implementation_OOP\\src\\predictive\\words");
	List<String> currentWordList = new ArrayList<String>();
	List<String> wordBuffer = new ArrayList<String>();
	int wordBufferCounter = 0;
	
	int currentWordListCounter = 0;
	String currentSignature = new String();
	
	JFrame frame;
	JTextField textField;
	JButton[] dialButtons = new JButton[10];
	JButton starButton, hashtagButton;
	JPanel buttonPanel;
	
	T9Main() {
		frame = new JFrame("Predictive T9");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(512, 512);
		frame.setLayout(null);
		
		
		
		textField = new JTextField();
		textField.setBounds(20, 20, 300, 100);
		textField.setEditable(false);
		
		// Button Initialization
		for (int i = 0; i < 10; ++i) {
			dialButtons[i] = new JButton(String.valueOf(i));
			dialButtons[i].addActionListener(this);
			dialButtons[i].setFocusable(false);
		}
		
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
			currentSignature = "";
			currentWordListCounter = 0;
			currentWordList = null;
			wordBufferCounter++;
			wordBuffer.add("");
			printBuffer();
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
				printBuffer();
			}
		}
		
		// Iterate through the word list of the current signature
		if (e.getSource() == starButton) {
			if (!currentWordList.isEmpty()) {
				// Loop back to the beginning of the list to make sure no access overflow
				currentWordListCounter++;
				if (currentWordListCounter > currentWordList.size()-1)
					currentWordListCounter = 0;
				textField.setText(currentWordList.get(currentWordListCounter));
			}
		}
		
		//WIP
		
		if (e.getSource() == hashtagButton) {

		}
	}
	
	private void printBuffer() {
		String displayBuffer = new String();
		for (String a : wordBuffer) {
			displayBuffer += a += " ";
		}
		textField.setText(displayBuffer);
	}
}
