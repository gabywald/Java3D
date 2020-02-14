package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;

/** 
 * A default "conic" or "line" Element containing a cylinder. 
 * <br>Default radius of cone / cylinder is 0.01. 
 * <br>Default height of cone / cylinder is 0.50. 
 * <br>Default Appearance is {@linkplain AppearanceCollection#getMagentaAppearance()}.
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleLine
 */
public abstract class ElementConicOrLine extends Element {
	/** 0.01f */
	public static final float DEFAULT_RADIUS = 0.01f;
	/** 0.50f */
	public static final float DEFAULT_HEIGHT = 0.50f;
	/** Current radius. */
	private float radius;
	/** Current height. */
	private float height;

	/**
	 * Initialization with radius, height and Appearance. 
	 * @param radius (float) in radian. 
	 * @param height (float) in meter. 
	 * @param current (Appearance)
	 */
	protected abstract void init(float radius, float height, Appearance current);

	/**
	 * Initialization with radius and height. 
	 * @param radius (float) in radian. 
	 * @param height (float) in meter. 
	 */
	protected void init(float radius, float height) {
		this.radius	= radius;
		this.height	= height;
	}
	
	/**
	 * Initialization with Appearance. 
	 * @param current (Appearance)
	 */
	protected void init(Appearance current) 
		{ this.init(ElementConicOrLine.DEFAULT_RADIUS, 
					ElementConicOrLine.DEFAULT_HEIGHT, 
					current); }
	
	/**
	 * Initialization with height and Appearance. 
	 * @param height (float) in meter. 
	 */
	protected void init(float height, Appearance current) 
		{ this.init(ElementConicOrLine.DEFAULT_RADIUS, 
					height, 
					current); }
	
	/**
	 * Initialization with height. 
	 * @param height (float) in meter. 
	 */
	protected void init(float height) 
		{ this.init(ElementConicOrLine.DEFAULT_RADIUS, 
					height, 
					AppearanceCollection.getWhiteAppearance()); }
	
	protected void init() 
		{ this.init(ElementConicOrLine.DEFAULT_RADIUS, 
					ElementConicOrLine.DEFAULT_HEIGHT, 
					AppearanceCollection.getWhiteAppearance()); }
	
	public float getRadius() { return this.radius; }
	public float getHeight() { return this.height; }

}
