package gabywald.cyberspace.behaviors;

import gabywald.cyberspace.objects.ElementText;

import java.util.Enumeration;
import javax.media.j3d.TransformGroup;

/**
 * A threadble behaviour for Elements, and to be interfaced (with GUI, and called by a controller if necessary). 
 * @author Gabriel Chandesris (2010)
 */
public class Mouvement extends Behaviour implements Runnable {
	/** A text Element to indicate Speed. */
	private ElementText text;
	/** GUI interaction (console */
	private TextCyberInteraction tint;
	
	/**
	 * Constructor with main TransformGroup. 
	 * @param currentGroup (TransformGroup)
	 * @see Behaviour#Behaviour(TransformGroup)
	 */
	public Mouvement(TransformGroup currentGroup) {  super(currentGroup); }
	
	/**
	 * Constructor with main TransformGroup and directions.
	 * @param currentGroup (TransformGroup)
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @see Behaviour#Behaviour(TransformGroup, float, float, float)
	 */
	public Mouvement(TransformGroup currentGroup, float Xdir, float Ydir,  float Zdir) 
		{ super(currentGroup,Xdir,Ydir,Zdir); }
	
	/**
	 * Constructor with main TransformGroup, angle and direction for self-rotation.
	 * @param currentGroup (TransformGroup)
	 * @param angleSelf (float) angle for self-rotation. 
	 * @param dirSelf (int) wich direction [0-2]. 
	 * @see Behaviour#Behaviour(TransformGroup, float, int)
	 */
	public Mouvement(TransformGroup currentGroup, float angleSelf, int dirSelf) 
		{ super(currentGroup,angleSelf,dirSelf); }
	
	/**
	 * Constructor with directions, angle and direction for translation and rotation.
	 * @param currentGroup (TransformGroup)
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 * @see Behaviour#Behaviour(TransformGroup, float, float, float, float, int)
	 */
	public Mouvement(TransformGroup currentGroup,
					float Xdir,float Ydir,float Zdir,
					float angle,int dir) 
		{ super(currentGroup,Xdir,Ydir,Zdir,angle,dir); }
	
	/** 
	 * Overload to give information to GUI. 
	 * @see Behaviour#accelerate()
	 * @see Mouvement#actualizeSpeed()
	 * @see Mouvement#actualizeText(String)
	 * @see Mouvement#actualizeTint(String)
	 */
	public void accelerate() 	{
		super.accelerate();
		this.actualizeSpeed();
		this.actualizeTint("accelerate");
	}
	
	/** 
	 * Overload to give information to GUI. 
	 * @see Behaviour#unaccelerate()
	 * @see Mouvement#actualizeSpeed()
	 * @see Mouvement#actualizeText(String)
	 * @see Mouvement#actualizeTint(String)
	 */
	public void unaccelerate()	{ 
		super.unaccelerate();
		this.actualizeSpeed();
		this.actualizeTint("UNaccelerate");
	}

//	public void goRight()		{ super.goRight();this.actualizeTint("go right"); }
//	public void goLeft()		{ super.goLeft();this.actualizeTint("go left"); }
//	public void goUp()			{ super.goUp();this.actualizeTint("go up"); }
//	public void goDown()		{ super.goDown();this.actualizeTint("go down"); }
//	public void goBackward()	{ super.goBackward();this.actualizeTint("go backward"); }
//	public void goForward()		{ super.goForward();this.actualizeTint("go forward"); }
	
	private void actualizeSpeed() 
		{ this.actualizeText("Speed : "+(Math.round(this.getSpeed()*1000)/100) ); }
	
	private void actualizeText(String text) 
		{ if (this.text != null) { this.text.setString(text); } }
	
	private void actualizeTint(String text) 
		{ if (this.tint != null) { this.tint.addString(text); } }
	
	public void setTextInteraction(TextCyberInteraction tint)	{ 
		this.tint = tint;
		this.text = this.tint.getConsole().getFrontText();
	}
	
	public void run() {
		while(true) {
			this.translate();
			this.rotate();
			this.rotateSelf();
			int waitTime = 750+(int)Math.round(Math.random()*100);
			try { Thread.sleep(waitTime); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	
	public void initialize() { ; }

	@SuppressWarnings({ "rawtypes" })
	public void processStimulus(Enumeration arg0) { ; }
	
}
