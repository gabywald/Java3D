package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;

/**
 * Plane definition. 
 * <br>Default width is {@linkplain Element#EDGE_SIZE}*3.
 * <br>Default height is {@linkplain Element#EDGE_SIZE}*5.
 * <br>Default Appearance is {@linkplain AppearanceCollection#getConsoleFrontAppearance()}. 
 * @author Gabriel Chandesris (2010)
 */
public class ElementPlane extends Element {
	/** Width of plane. */
	private float width;
	/** Height of plane. */
	private float height;
	/** Face Description with dots. */
	private Point3f[] points;

	/** Default Constructor. */
	public ElementPlane() { super();this.init(); }
	
	/**
	 * Constructor with positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#Element(float, float, float)
	 */
	public ElementPlane(float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos);this.init(); }

	/**
	 * Constructor with positions and width and height. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @param width (float) in meter. 
	 * @param height (float) in meter. 
	 */
	public ElementPlane(float Xpos, float Ypos, float Zpos, float width, float height) 
		{ super(Xpos, Ypos, Zpos);this.init(width, height); }
	
	/**
	 * Initialization with Appearance, width and height. 
	 * @param current (Appearance)
	 * @param width (float) in meter. 
	 * @param height (float) in meter. 
	 */
	private void init(Appearance current, float width, float height) {
		this.width	= width;
		this.height = height;
		this.computeDotsPositions();
		QuadArray geom = new QuadArray(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);
		geom.setCoordinates(0, this.points);
		Shape3D face = new Shape3D(geom);
		face.setAppearance(current);
		this.addChild(face);
	}

	/**
	 * Initialization with width and height. 
	 * @param width (float) in meter. 
	 * @param height (float) in meter. 
	 */
	private void init(float width, float height) 
		{ this.init(AppearanceCollection.getConsoleFrontAppearance(), width, height); }
	
	/**
	 * Initialization with Appearance and Edge Size (width/3 and height/5). 
	 * @param current (Appearance)
	 * @param edgeSize (float) in meter. 
	 */
	private void init(Appearance current, float edgeSize) 
		{ this.init(current, edgeSize*3, edgeSize*5); }
	
	/**
	 * Initialization with Appearance. 
	 * @param current (Appearance)
	 */
	private void init(Appearance current) 
		{ this.init(current, Element.EDGE_SIZE); }

	protected void init() 
		{ this.init(AppearanceCollection.getConsoleFrontAppearance()); }
	
	/** Construction  of the four dots to build the plane. */
	private void computeDotsPositions() { 
		this.points = new Point3f[4];
		this.points[0] = new Point3f(-this.width, -this.height, 0.0f);
		this.points[1] = new Point3f(+this.width, -this.height, 0.0f);
		this.points[2] = new Point3f(+this.width, +this.height, 0.0f);
		this.points[3] = new Point3f(-this.width, +this.height, 0.0f);
	}

}
