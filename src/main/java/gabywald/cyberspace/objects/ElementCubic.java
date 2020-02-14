package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;

import com.sun.j3d.utils.geometry.Box;

/**
 * A default Cubic Element which contain only a Box (cubic). 
 * <br>Default size is {@linkplain Element#EDGE_SIZE}. 
 * <br>Default Appearance is {@linkplain AppearanceCollection#getWhiteAppearance()}. 
 * @author Gabriel Chandesris (2010)
 */
public class ElementCubic extends Element {
	/** Current Box. */
	private Box cube;
	
	/** Default constructor. */
	public ElementCubic() { super();this.init(); }

	/** 
	 * Constructor with Appearance. 
	 * @param current (Appearance)
	 * @see AppearanceCollection
	 */
	public ElementCubic(Appearance current) 
		{ super();this.init(current); }
	
	/** 
	 * Constructor with Edge Size of the cube.
	 * @param edgeSize (float) in meter
	 * @see Element#EDGE_SIZE
	 */
	public ElementCubic(float edgeSize) 
		{ super();this.init(edgeSize); }
	
	/**
	 * Constructor with Appearance and Edge Size. 
	 * @param current (Appearance)
	 * @param edgeSize (float) in meter
	 * @see AppearanceCollection
	 * @see Element#EDGE_SIZE
	 */
	public ElementCubic(float edgeSize, Appearance current) 
		{ super();this.init(current,edgeSize); }
	
	/**
	 * Constructor with positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#Element(float, float, float)
	 */
	public ElementCubic(float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos);this.init(); }
	
	/**
	 * Constructor with positions and Appearance. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @param current (Appearance)
	 * @see Element#Element(float, float, float)
	 */
	public ElementCubic(float Xpos, float Ypos, float Zpos,Appearance current) 
		{ super(Xpos, Ypos, Zpos);this.init(current); }

	/**
	 * Initialization with Appearance and Edge Size. 
	 * @param current (Appearance)
	 * @param edgeSize (float) in meter
	 */
	private void init(Appearance current, float edgeSize) {
		this.cube	= new Box(edgeSize, edgeSize, edgeSize, current);
		this.addChild(this.cube);
	}
	
	/**
	 * Initialization with Edge Size. 
	 * @param edgeSize (float) in meter
	 */
	private void init(float edgeSize) 
		{ this.init(AppearanceCollection.getWhiteAppearance(), edgeSize); }
	
	/**
	 * Initialization with Appearance. 
	 * @param current (Appearance)
	 */
	protected void init(Appearance current) 
		{ this.init(current, Element.EDGE_SIZE); }

	protected void init() 
		{ this.init(AppearanceCollection.getWhiteAppearance(), Element.EDGE_SIZE); }
	
}
