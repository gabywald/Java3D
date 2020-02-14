package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;
import com.sun.j3d.utils.geometry.Sphere;

/**
 * A default Spherical Element which contain only a Box (cubic). 
 * <br>Default radial is {@linkplain Element#EDGE_SIZE}. 
 * <br>Default Appearance is {@linkplain AppearanceCollection#getWhiteAppearance()}. 
 * @author Gabriel Chandesris (2010)
 */
public class ElementSpherical extends Element {
	/** Current Sphere. */
	private Sphere boule;
	
	/** Default constructor. */
	public ElementSpherical() { super();this.init(); }
	
	/** 
	 * Constructor with Appearance. 
	 * @param current (Appearance)
	 * @see AppearanceCollection
	 */
	public ElementSpherical(Appearance current) 
		{ super();this.init(current); }

	/**
	 * Constructor with positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#Element(float, float, float)
	 */
	public ElementSpherical(float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos);this.init(); }
	
	/**
	 * Constructor with positions and Appearance. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @param current (Appearance)
	 * @see Element#Element(float, float, float)
	 */
	public ElementSpherical(float Xpos, float Ypos, float Zpos, Appearance current) 
		{ super(Xpos, Ypos, Zpos);this.init(current); }
	
	/**
	 * Initialization with Appearance. 
	 * @param current (Appearance)
	 */
	protected void init(Appearance current) {
		this.boule	= new Sphere(Element.EDGE_SIZE, current);
		this.addChild(this.boule);
	}

	protected void init() 
		{ this.init(AppearanceCollection.getWhiteAppearance()); }

}
