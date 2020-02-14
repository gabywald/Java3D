package gabywald.cyberspace.objects;

import java.awt.Color;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

/**
 * This class to define a background. 
 * <br>Default RGB is (0.05f, 0.05f, 0.25f). 
 * @author Gabriel Chandesris (2010)
 */
public class EnvironmentFond extends Background {
	
	/** Default Constructor. */
	public EnvironmentFond () 
		{ super(0.05f, 0.05f, 0.25f);this.init(); }
	
	/**
	 * Constructor with a given Color. 
	 * @param color (Color)
	 */
	public EnvironmentFond (Color color) { 
		super(color.getRed(), color.getGreen(), color.getBlue());
		this.init();
	}
	
	/**
	 * Constructor with given values for Red / Green / Blue. 
	 * @param red (float) from 0.0 to 1.0
	 * @param green (float) from 0.0 to 1.0
	 * @param blue (float) from 0.0 to 1.0
	 */
	public EnvironmentFond (float red, float green, float blue) 
		{ super(red, green, blue);this.init(); }
	
	/** Initialization helper. */
	protected void init() 
		{ this.setApplicationBounds(new BoundingSphere(new Point3d(), 1000.0)); }

}
