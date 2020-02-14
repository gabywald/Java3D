package gabywald.cyberspace.objects;

import java.awt.Color;
import java.awt.Font;

import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;

/**
 * This class to define text elements to visualise in 3D space. 
 * <br>Default color is Cyan. 
 * <br>Default font size is 11. 
 * <br>Default font family is Times. 
 * <br>Default font style is Bold. 
 * <br>Specific scale : 0.005. 
 * @author Gabriel Chandesris (2010)
 *
 */
public class ElementText extends Element {
	/** Color.CYAN */
	public static final Color DEFAULT_COLOR			= Color.CYAN;
	/** 0.005 */
	public static final float DEFAULT_SCALE			= 0.005f;
	/** 11 */
	public static final int DEFAULT_FONT_SIZE		= 11;
	/** Font.BOLD */
	public static final int DEFAULT_FONT_STYLE		= Font.BOLD;
	/** "Times" */
	public static final String DEFAULT_FONT_FAMILY = "Times";
	
	private Text3D text3D;
	
	private Shape3D textShape;
	
	/** Current scale of text. */
	private float scale;
	
	/** Default constructor. */
	public ElementText () { super();this.init(); }
	
	/**
	 * Constructor with a given text. 
	 * @param text (String)
	 */
	public ElementText (String text) 
		{ super();this.init(text, ElementText.DEFAULT_COLOR); }
	
	/**
	 * Constructor with a given color. 
	 * @param color (Color)
	 */
	public ElementText (Color color) { super();this.init(color); }
	
	/**
	 * Constructor with given text and color. 
	 * @param text (String)
	 * @param color (Color)
	 */
	public ElementText (String text, Color color) 
		{ super();this.init(text, color); }
	
	/**
	 * Constructor with given position. 
	 * @param dot (Point3f)
	 * @see Element#getCenterPosition()
	 * @see Element#getLeftBottom()
	 * @see Element#getLeftTop()
	 * @see Element#getRightBottom()
	 * @see Element#getRightTop()
	 */
	public ElementText (Point3f dot) { super();this.init(dot); }
	
	/**
	 * Constructor  with given text and position. 
	 * @param text (String)
	 * @param dot (Point3f)
	 * @see Element#getCenterPosition()
	 * @see Element#getLeftBottom()
	 * @see Element#getLeftTop()
	 * @see Element#getRightBottom()
	 * @see Element#getRightTop()
	 */
	public ElementText (String text, Point3f dot) 
		{ super();this.init(text, ElementText.DEFAULT_COLOR, dot); }
	
	/**
	 * Constructor with given color and position
	 * @param color (Color)
	 * @param dot (Point3f)
	 * @see Element#getCenterPosition()
	 * @see Element#getLeftBottom()
	 * @see Element#getLeftTop()
	 * @see Element#getRightBottom()
	 * @see Element#getRightTop()
	 */
	public ElementText (Color color, Point3f dot) 
		{ super();this.init("Text : ", color, dot); }
	
	/**
	 * Constructor with given text, color and position. 
	 * @param text (String)
	 * @param color (Color)
	 * @param dot (Point3f)
	 * @see Element#getCenterPosition()
	 * @see Element#getLeftBottom()
	 * @see Element#getLeftTop()
	 * @see Element#getRightBottom()
	 * @see Element#getRightTop()
	 */
	public ElementText (String text,Color color, Point3f dot) 
		{ super();this.init(text, color, dot); }
	
	protected void init() 
		{ this.init("Text : ", ElementText.DEFAULT_COLOR, Element.getCenterPosition()); }
	
	/**
	 * Initialization with color. 
	 * @param color (Color)
	 */
	private void init(Color color) 
		{ this.init("Text : ", color, Element.getCenterPosition()); }
	
	/** 
	 * Initialization with position. 
	 * @param dot (Point3f)
	 */
	private void init(Point3f dot) 
		{ this.init("Text : ", ElementText.DEFAULT_COLOR, dot); }
	
	/**
	 * Initialization with text and color)
	 * @param text (String)
	 * @param color (Color)
	 */
	private void init(String text, Color color) 
		{ this.init(text, color, Element.getCenterPosition()); }
	
	/**
	 * Initialization with text, color and position. 
	 * @param text (String)
	 * @param color (Color)
	 * @param dot (Point3f)
	 * @see ElementText#localUpdate()
	 */
	private void init(String text,Color color, Point3f dot) {
		this.setText3D(text, dot, ElementText.DEFAULT_FONT_SIZE);
		this.textShape = new Shape3D(this.text3D);
		this.textShape.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
		this.localUpdate();
		this.setColor(color);
		this.addChild(this.textShape);
	}
	
	/**
	 * To set the capabilites of the 3D text to appear. 
	 * @param txt (String)
	 * @param dot (Point3f)
	 * @param fontSize (int)
	 * @see ElementText#DEFAULT_FONT_FAMILY
	 * @see ElementText#DEFAULT_FONT_STYLE
	 * @see ElementText#DEFAULT_FONT_SIZE
	 */
	private void setText3D(String txt, Point3f dot,int fontSize)  {
		Font3D font3D = new Font3D(new Font(ElementText.DEFAULT_FONT_FAMILY, 
				  							ElementText.DEFAULT_FONT_STYLE, 
				  							fontSize),
				  				   new FontExtrusion());
		this.text3D = new Text3D(font3D,txt,dot,
								 Text3D.ALIGN_FIRST,
								 Text3D.PATH_RIGHT);
		this.text3D.setCapability(Text3D.ALLOW_STRING_WRITE);
		this.text3D.setCapability(Text3D.ALLOW_STRING_READ);
	}
	
	/**
	 * Set the scale of current 3D text. 
	 * @param scale (float)
	 */
	private void setScale(float scale) {
		this.scale = scale;
		Transform3D transform = new Transform3D();
		Transform3D scalables = new Transform3D();
		scalables.setScale(this.scale);
		transform.mul(scalables);
		this.setTransform(transform);
	}
	
	public void setString(String txt)	{ this.text3D.setString(txt); }
	public String getString()			{ return this.text3D.getString(); }
	
	/**
	 * Overload of the method due to specific positionning of 3D text. 
	 * @see Element#setPosition(float, float, float)
	 * @see ElementText#localUpdate()
	 */
	public void setPosition(float Xpos, float Ypos, float Zpos) { 
		super.setPosition(Xpos, Ypos, Zpos);
		this.setText3D(this.text3D.getString(), 
					   new Point3f(Xpos / this.scale,
								   Ypos / this.scale,
								   Zpos / this.scale),
					   this.text3D.getFont3D().getFont().getSize());
		this.localUpdate();
	}
	
	
	/**
	 * To set the font Size of the text. 
	 * @param size (int)
	 * @see ElementText#localUpdate()
	 */
	public void setFontSize(int size) {
		Point3f dot = new Point3f();
		this.text3D.getPosition(dot);
		this.setText3D(this.text3D.getString(), dot, size);
		this.localUpdate();
	}
	
	/** Update of local geometry 3D Shape. */
	private void localUpdate() {
		/** Mise a jour de la geometrie de l'objet TextShape3D. */
		this.textShape.setGeometry(this.text3D);
		this.setScale(ElementText.DEFAULT_SCALE);
	}
	
	/** 
	 * To change the color of the text. 
	 * @param color (Color)
	 */
	public void setColor(Color color) 
		{ this.textShape.setAppearance(AppearanceCollection.getAppearance(color)); }
	
}
