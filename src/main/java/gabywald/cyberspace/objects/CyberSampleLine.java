package gabywald.cyberspace.objects;

import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;

/**
 * This class to define and contain a line in 3D space. 
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleDot
 */
public class CyberSampleLine extends Element {
	/** Current line. */
	private LineArray line;

	/** Default Constructor. */
	public CyberSampleLine() { super();this.init(); }
	
	/**
	 * Constructor with given dots. 
	 * @param beginDot (CyberSampleDot)
	 * @param endinDot (CyberSampleDot)
	 */
	public CyberSampleLine(CyberSampleDot beginDot, 
						   CyberSampleDot endinDot) 
		{ super();this.init(beginDot, endinDot); }
	
	/**
	 * Constructor with given dots and colors. 
	 * @param beginDot (CyberSampleDot)
	 * @param endinDot (CyberSampleDot)
	 * @param beginColor (Color3f)
	 * @param endinColor (Color3f)
	 * @see CyberSampleDot#DEFAULT_COLOR
	 */
	public CyberSampleLine(CyberSampleDot beginDot, 
						   CyberSampleDot endinDot, 
						   Color3f beginColor, 
						   Color3f endinColor) 
		{ super();this.init(beginDot, endinDot, beginColor, endinColor); }
	
	/**
	 * Constructor with given positions of beginning and end. 
	 * @param beginPosX (float) in meter. 
	 * @param beginPosY (float) in meter. 
	 * @param beginPosZ (float) in meter. 
	 * @param endinPosX (float) in meter. 
	 * @param endinPosY (float) in meter. 
	 * @param endinPosZ (float) in meter. 
	 */
	public CyberSampleLine(float beginPosX, float beginPosY, float beginPosZ, 
			 		       float endinPosX, float endinPosY, float endinPosZ) {
		super();
		CyberSampleDot beginDot = new CyberSampleDot(beginPosX, beginPosY, beginPosZ);
		CyberSampleDot endinDot = new CyberSampleDot(endinPosX, endinPosY, endinPosZ);
		this.init(beginDot, endinDot);
	}
	
	/**
	 * Initialization with given dots and colors. 
	 * @param beginDot (CyberSampleDot)
	 * @param endinDot (CyberSampleDot)
	 * @param beginColor (Color3f)
	 * @param endinColor (Color3f)
	 * @see CyberSampleDot#DEFAULT_COLOR
	 */
	private void init(CyberSampleDot beginDot, 
					  CyberSampleDot endinDot, 
			  		  Color3f beginColor, 
			  		  Color3f endinColor) {
		this.line = new LineArray(2, LineArray.COORDINATES|LineArray.COLOR_3);
		this.line.setCoordinate(0, beginDot.getDot());
		this.line.setCoordinate(1, endinDot.getDot());
		this.line.setColor(0, beginColor);
		this.line.setColor(1, endinColor);
		this.addChild(new Shape3D(this.line));
	}
	
	/**
	 * Initialization with given dots. 
	 * @param beginDot (CyberSampleDot)
	 * @param endinDot (CyberSampleDot)
	 */
	private void init(CyberSampleDot beginDot, 
					  CyberSampleDot endinDot) 
		{ this.init(beginDot, endinDot, 
					CyberSampleDot.DEFAULT_COLOR, 
					CyberSampleDot.DEFAULT_COLOR); }
	
	
	protected void init() { ; }	
	
	/**
	 * To get the X-axis with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 * @return (CyberSampleLine)
	 */
	public static CyberSampleLine getAxisX (float minRef, float maxRef) {
		/** Ligne horizontale */
		CyberSampleDot beginDot = new CyberSampleDot(minRef, 0f, 0f);
		CyberSampleDot endinDot = new CyberSampleDot(maxRef, 0f, 0f);
		return new CyberSampleLine(beginDot, endinDot);
	}
	
	/**
	 * To get the Y-axis with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 * @return (CyberSampleLine)
	 */
	public static CyberSampleLine getAxisY (float minRef, float maxRef) {
		/** Ligne verticale */
		CyberSampleDot beginDot = new CyberSampleDot(0f, minRef, 0f);
		CyberSampleDot endinDot = new CyberSampleDot(0f, maxRef, 0f);
		return new CyberSampleLine(beginDot, endinDot);
	}
	
	/**
	 * To get the Z-axis with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 * @return (CyberSampleLine)
	 */
	public static CyberSampleLine getAxisZ (float minRef, float maxRef) {
		/** Ligne en profondeur */
		CyberSampleDot beginDot = new CyberSampleDot(0f, 0f, minRef);
		CyberSampleDot endinDot = new CyberSampleDot(0f, 0f, maxRef);
		return new CyberSampleLine(beginDot, endinDot);
	}

	/**
	 * To get the X-axis that appears infinite. 
	 * @return (CyberSampleLine)
	 */
	public static CyberSampleLine getAxisX () 
		{ return CyberSampleLine.getAxisX(Float.MIN_VALUE, Float.MAX_VALUE); }
	
	/**
	 * To get the Y-axis that appears infinite. 
	 * @return (CyberSampleLine)
	 */
	public static CyberSampleLine getAxisY () 
		{ return CyberSampleLine.getAxisY(Float.MIN_VALUE, Float.MAX_VALUE); }
	
	/**
	 * To get the Z-axis that appears infinite. 
	 * @return (CyberSampleLine)
	 */
	public static CyberSampleLine getAxisZ () 
		{ return CyberSampleLine.getAxisZ(Float.MIN_VALUE, Float.MAX_VALUE); }
	
	
}