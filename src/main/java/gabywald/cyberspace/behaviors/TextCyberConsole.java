package gabywald.cyberspace.behaviors;

import gabywald.cyberspace.components.CyberConsole;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.WakeupOnAWTEvent;

/**
 * This class defines a specific Behavior to add and interact in a text console in a 3D view. 
 * @author Gabriel Chandesris (2010)
 */
public class TextCyberConsole extends Behaviour {
	
	private WakeupOnAWTEvent keyEvent	= new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
	
	private CyberConsole cc;
	
	public TextCyberConsole(CyberConsole cc) 
		{ this.cc = cc; }

	public void initialize()
		{ this.wakeupOn(this.keyEvent); }

	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration arg0) {
		AWTEvent events[] = this.keyEvent.getAWTEvent();
		
		int tmp = ((KeyEvent)events[0]).getKeyCode();
		if ( (tmp >= KeyEvent.VK_NUMPAD0) && (tmp <= KeyEvent.VK_NUMPAD9) ) {
			/** Passed to the movement controller !! */
		} else {
			if ( (tmp == KeyEvent.VK_ENTER) && ( ! this.cc.getConsole().equals("")) ) { 
				this.cc.addString(this.cc.getConsole());
				this.cc.emptyConsole();
			} else { 
				if (TextCyberConsole.isValidKeyEvent(tmp))
					{ this.cc.addChar(((KeyEvent)events[0]).getKeyChar()); }
				if (tmp == KeyEvent.VK_BACK_SPACE) 
					{ this.cc.remLastChar(); }
			}
		}

		this.wakeupOn(this.keyEvent);
		// System.out.println("char reçu : '"+((KeyEvent)events[0]).getKeyChar()+"'");
	}
	
	private static boolean isValidKeyEvent (int keyEvent) {
		if ( ( (keyEvent >= KeyEvent.VK_A) && (keyEvent <= KeyEvent.VK_Z) )
				|| ( (keyEvent >= KeyEvent.VK_0) && (keyEvent <= KeyEvent.VK_9) ) )
			{ return true; }
//		if (keyEvent == KeyEvent.VK_COLON)		{ return true; }
//		if (keyEvent == KeyEvent.VK_SEMICOLON)	{ return true; }
//		if (keyEvent == KeyEvent.VK_COMMA)		{ return true; }
//		if (keyEvent == KeyEvent.VK_MINUS)		{ return true; }
//		if (keyEvent == KeyEvent.VK_PLUS)		{ return true; }
		return false;
	}

}
