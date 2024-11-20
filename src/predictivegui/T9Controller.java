package predictivegui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class T9Controller{
	private T9Model model;
	private T9View view;
	
	public T9Controller(T9View view, T9Model model) {
		this.model = model;
		this.view = view;
		this.view.buttonListener(new T9Listener());
	}
	class T9Listener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {	
			for (int i = 1; i < 10; ++i) {
				if (e.getSource() == view.getNumberButton()[i]) {
					view.setTextField(model.updateCurrentSignature(model.getCurrentSignature() + String.valueOf(i)));
				}
			}
			if (e.getSource() == view.getNumberButton()[0]) {
				view.setTextField(model.addSpaces());
			}
			
			if (e.getSource() == view.getStarButton()) {
				view.setTextField(model.iterateCurrentWordList());
			}
			
			if (e.getSource() == view.getHashtagButton()) {
				view.setTextField(model.deleteLetter());
			}
		}
	}
}
