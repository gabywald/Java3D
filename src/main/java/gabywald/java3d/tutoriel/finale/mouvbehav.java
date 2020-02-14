package gabywald.java3d.tutoriel.finale;

/*

écrit par:Roswell
email:philgauthier_@hotmail.com

 */

import javax.media.j3d.Behavior;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Vector3f;
import java.awt.AWTEvent;
import java.util.Enumeration;
import java.awt.event.KeyEvent;

class mouvbehav extends Behavior
{
	private TransformGroup TG;
	private Transform3D rot			= new Transform3D();
	private Transform3D rotation	= new Transform3D();
	private Vector3f translation	= new Vector3f();

	private WakeupOnAWTEvent keyEvent = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);

	mouvbehav(TransformGroup TG)
	{
		this.TG = TG;
	}

	public void initialize()
	{
		this.wakeupOn(this.keyEvent);
	}

	public void processStimulus(Enumeration criteria)
	{
		AWTEvent events[] = this.keyEvent.getAWTEvent();
		this.rot.setIdentity();
		this.TG.getTransform(this.rotation);

		switch ( ((KeyEvent) events[0]).getKeyCode() ) {
		case KeyEvent.VK_NUMPAD1:
		case KeyEvent.VK_U:
			this.translation.set(0.075f, 0f, 0f);
			this.rot.setTranslation(this.translation);
			break;
		case KeyEvent.VK_NUMPAD3:
		case KeyEvent.VK_O:
			this.translation.set(-0.075f, 0f, 0f);
			this.rot.setTranslation(this.translation);
			break;
		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_I:
			this.translation.set(0f, 0f, 0.075f);
			this.rot.setTranslation(this.translation);
			break;
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_SEMICOLON:
			this.translation.set(0f, 0f, -0.075f);
			this.rot.setTranslation(this.translation);
			break;
		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_J:
			this.rot.rotY(0.0125d);
			break;
		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_L:
			this.rot.rotY(-0.0125d);
			break;
		default:

		}

//		if (((KeyEvent)events[0]).getKeyCode() == KeyEvent.VK_NUMPAD1)
//		{
//			translation.set(0.075f,0f,0f);
//			rot.setTranslation(translation);
//		}
//		else
//			if (((KeyEvent)events[0]).getKeyCode() == KeyEvent.VK_NUMPAD3)
//			{
//				translation.set(-0.075f,0f,0f);
//				rot.setTranslation(translation);
//			}
//			else
//				if (((KeyEvent)events[0]).getKeyCode() == KeyEvent.VK_NUMPAD2)
//				{
//					translation.set(0f,0f,0.075f);		
//					rot.setTranslation(translation);
//				}
//				else
//					if (((KeyEvent)events[0]).getKeyCode() == KeyEvent.VK_NUMPAD8)
//					{
//						translation.set(0f,0f,-0.075f);
//						rot.setTranslation(translation);
//					}
//					else
//						if (((KeyEvent)events[0]).getKeyCode() == KeyEvent.VK_NUMPAD4)
//							rot.rotY(0.0125d);
//						else
//							if (((KeyEvent)events[0]).getKeyCode() == KeyEvent.VK_NUMPAD6)			
//								rot.rotY(-0.0125d);

		this.rotation.mul(this.rot);
		this.TG.setTransform(this.rotation);

		this.wakeupOn(this.keyEvent);
	}
}
