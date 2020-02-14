package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;

import com.sun.j3d.utils.geometry.Cone;

/** 
 * A default "conic" Element containing a cylinder. 
 * @author Gabriel Chandesris (2010)
 */
public class ElementConic extends ElementConicOrLine {
	/** Current Cone. */
	private Cone cone;
	
	/** Default constructor. */
	public ElementConic () { super();this.init(); }
	
	/**
	 * Constructor with height. 
	 * @param height (float) in meter. 
	 */
	public ElementConic (float height) { super();this.init(height); }
	
	/**
	 * Constructor with height and Appearance. 
	 * @param height (float) in meter. 
	 * @param current (Appearance)
	 */
	public ElementConic (float height, Appearance current) 
		{ super();this.init(height, current); }
	
	/**
	 * Constructor with Appearance. 
	 * @param current (Appearance)
	 */
	public ElementConic (Appearance current) { super();this.init(current); }
	
	protected void init(float radius, float height, Appearance current) {
		super.init(radius*10.0f, height);
		this.cone	= new Cone(this.getRadius(), this.getHeight(), current);
		this.addChild(this.cone);
	}
	
//	protected void init() 
//		{ this.init(ElementConicOrLine.DEFAULT_RADIUS*10.0f, 
//					ElementConicOrLine.DEFAULT_HEIGHT*5.0f, 
//					AppearanceCollection.getMagentaAppearance()); }

}
