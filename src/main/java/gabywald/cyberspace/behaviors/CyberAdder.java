package gabywald.cyberspace.behaviors;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.WakeupOnAWTEvent;

import gabywald.cyberspace.components.CyberSpace;
import gabywald.cyberspace.components.YellowBox;
import gabywald.cyberspace.objects.Element;

/**
 * How-To add new Elements in Java3D existing Scene...
 * @author Gabriel Chandesris (2012)
 */
public class CyberAdder extends Behaviour {
	
	private float Xpos = 0.0f, Ypos = 0.0f, Zpos = 0.0f;
	
	private WakeupOnAWTEvent keyEvent	= new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);

	@Override
	public void initialize()
		{ this.wakeupOn(this.keyEvent); }

	@Override
	public void processStimulus(@SuppressWarnings("rawtypes") Enumeration arg0) {
		AWTEvent events[] = this.keyEvent.getAWTEvent();
		
		int tmp				= ((KeyEvent)events[0]).getKeyCode();
		Element child2add	= null;
		
		System.out.println( tmp );
		
		switch( tmp ) {
		case KeyEvent.VK_CONTROL : 
			child2add = new YellowBox(++this.Xpos, this.Ypos, this.Zpos);
			System.out.println( "\t X:" + this.Xpos );
			break;
		case KeyEvent.VK_ALT : 
			child2add = new YellowBox(this.Xpos, ++this.Ypos, this.Zpos);
			System.out.println( "\t Y:" + this.Ypos );
			break;
		case KeyEvent.VK_SHIFT : 
			child2add = new YellowBox(this.Xpos, this.Ypos, ++this.Zpos);
			System.out.println( "\t Z:" + this.Zpos );
			break;
		}
		System.out.println( "\t => {" + child2add + "}" );
		if (child2add != null) {
			BranchGroup toAdd	= new BranchGroup();
			toAdd.addChild( child2add );
			CyberSpace.getCyberSpace().addChild( toAdd );
		}

		this.wakeupOn(this.keyEvent);
		// System.out.println("char reçu : '"+((KeyEvent)events[0]).getKeyChar()+"'");
	}
	
}
