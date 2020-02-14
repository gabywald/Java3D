package gabywald.cyberspace.behaviors;

import gabywald.cyberspace.components.CyberConsole;
import java.util.Enumeration;

/**
 * This class defines specific behavior to interact with 3D view. 
 * @author Gabriel Chandesris (2010)
 */
public class TextCyberInteraction extends Behaviour implements Runnable {
	private CyberConsole console;
	
	public TextCyberInteraction (CyberConsole con) 
		{ this.console = con; }
	
	public void addString(String text) { this.console.addString(text); }

	public void initialize() { ; }

	@SuppressWarnings({ "rawtypes" })
	public void processStimulus(Enumeration arg0) { ; }

	public void run() {
		while(true) {
			this.console.upLines();
			try { Thread.sleep(1000); } 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public CyberConsole getConsole() { return this.console; }

}
