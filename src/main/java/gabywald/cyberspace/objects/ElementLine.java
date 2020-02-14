package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;

import com.sun.j3d.utils.geometry.Cylinder;

/** 
 * A default "line" Element containing a cylinder. 
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleLine
 */
public class ElementLine extends ElementConicOrLine {
	/** Current Cylinder. */
	private Cylinder line;
	
	/** Default constructor. */
	public ElementLine () { super();this.init(); }
	
	/**
	 * Constructor with height. 
	 * @param height (float) in meter. 
	 */
	public ElementLine (float height) { super();this.init(height); }
	
	/**
	 * Constructor with height and Appearance. 
	 * @param height (float) in meter. 
	 * @param current (Appearance)
	 */
	public ElementLine (float height, Appearance current) 
		{ super();this.init(height, current); }
	
	/**
	 * Constructor with Appearance. 
	 * @param current (Appearance)
	 */
	public ElementLine (Appearance current) { super();this.init(current); }
	
	/**
	 * Initialization with radius, height and Appearance. 
	 * @param radius (float) in radian. 
	 * @param height (float) in meter. 
	 * @param current (Appearance)
	 */
	protected void init(float radius, float height, Appearance current) {
		super.init(radius, height);
		this.line	= new Cylinder(this.getRadius(), this.getHeight(), current);
		this.addChild(this.line);
	}
	
}
