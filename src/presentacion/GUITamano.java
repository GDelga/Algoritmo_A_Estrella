package presentacion;

import javax.swing.JFrame;

/**
 * @author Guillermo Delgado Yepes
 */
public abstract class GUITamano extends JFrame implements GUI{

	private static GUITamano instance;
	
	public static GUITamano getInstance() {
		if(instance == null) {
			instance = new GUITamanoImp();
		}
		return instance;
	}

}
