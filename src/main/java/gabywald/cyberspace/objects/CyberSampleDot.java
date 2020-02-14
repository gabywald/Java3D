package gabywald.cyberspace.objects;

import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

/**
 * This class to define and contain a dot in 3D space. 
 * <br>Default color is blue. 
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleLine
 */
public class CyberSampleDot extends Element {
	/** (0.0f, 0.0f, 1.0f) */
	public static final Color3f DEFAULT_COLOR = new Color3f(0.0f, 0.0f, 1.0f);
	/** Current dot. */
	private Point3f dot;

	/** Default Constructor. */
	public CyberSampleDot () { super();this.init(); }
	
	/** 
	 * Constructor with specific given positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	public CyberSampleDot(float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos);this.init(Xpos, Ypos, Zpos); }
	
	/**
	 * Initialization with specific given positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	private void init(float Xpos, float Ypos, float Zpos) 
		{ this.dot = new Point3f(Xpos, Ypos, Zpos); }

	protected void init() 
		{ this.init(0.0f, 0.0f, 0.0f); }
	
	public Point3f getDot() { return this.dot; }
	
	/** Overload of {@linkplain Element#setPosition(float, float, float)} to also change dot. */
	public void setPosition(float Xpos, float Ypos, float Zpos) {
		this.dot.set(Xpos, Ypos, Zpos);
		super.setPosition(Xpos, Ypos, Zpos);
	}
	
}
