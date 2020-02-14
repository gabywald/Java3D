package gabywald.cyberspace.behaviors;

import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

/**
 * This class defines behaviour for 3D view and movement in view. 
 * @author Gabriel Chandesris (2010)
 */
public class Deplacement extends Behaviour {

	private Mouvement movement;
	
	private WakeupOnAWTEvent keyEvent	= new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
	
	public Deplacement(TransformGroup currentGroup,Mouvement move) { 
		super(currentGroup);
		this.movement = move; 
	}
	
	public void setMouvement(Mouvement move) 
		{ this.movement = move; }
	
	public void initialize()
		{ this.wakeupOn(this.keyEvent); }
		
	@SuppressWarnings({ "rawtypes" })
	public void processStimulus(Enumeration criteria) {
		/** On récupere le tableau d'evenements enregistrés. */
		AWTEvent events[] = this.keyEvent.getAWTEvent();
		
		switch (((KeyEvent)events[0]).getKeyCode()) {
		// case (KeyEvent.VK_Q):
		case (KeyEvent.VK_PAGE_UP):
		case (KeyEvent.VK_NUMPAD1):
			this.movement.rotZ(+0.1f);
			break;
		// case (KeyEvent.VK_S):
		case (KeyEvent.VK_PAGE_DOWN):
		case (KeyEvent.VK_NUMPAD3):
			this.movement.rotZ(-0.1f);
			break;
		case (KeyEvent.VK_DOWN):
		// case (KeyEvent.VK_K):
		case (KeyEvent.VK_NUMPAD5):
		case (KeyEvent.VK_NUMPAD2):
			this.movement.rotX(+0.1f);
			break;
		case (KeyEvent.VK_UP):
		// case (KeyEvent.VK_I):
		case (KeyEvent.VK_NUMPAD8):
			this.movement.rotX(-0.1f);
			break;
		case (KeyEvent.VK_RIGHT):
		// case (KeyEvent.VK_L):
		case (KeyEvent.VK_NUMPAD6):
			this.movement.rotY(-0.1f);
			// this.movement.rotZ(+0.1d);
			break;
		case (KeyEvent.VK_LEFT):
		// case (KeyEvent.VK_J):
		case (KeyEvent.VK_NUMPAD4):
			this.movement.rotY(+0.1f);
			// this.movement.rotZ(-0.1d);
			break;
		// case (KeyEvent.VK_A):
		case (KeyEvent.VK_INSERT):
		case (KeyEvent.VK_NUMPAD7):
			this.movement.accelerate();
			break;
		// case (KeyEvent.VK_Z):
		case (KeyEvent.VK_DELETE):
		case (KeyEvent.VK_NUMPAD9):
			this.movement.unaccelerate();
			break;
		}
		
		/** On se remet en mode d'attente des évenements de keyEvent : touche pressée ? */
		this.wakeupOn(this.keyEvent);
	}
}


