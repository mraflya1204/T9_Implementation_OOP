package predictivegui;

public class T9Main {
	public static void main (String[] args) {
		T9Model model = new T9Model();
		T9View window = new T9View();
		
		T9Controller controller = new T9Controller(window, model);
		//window.setVisible(true);
	}
}