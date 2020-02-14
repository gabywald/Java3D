package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementSpherical;

/**
 * this class defines cyan spherical elements. 
 * @author gabriel Chandesris (2010)
 *
 */
public class Daemon extends ElementSpherical {
	
	/** Default Constructor. */
	public Daemon () 
		{ super(AppearanceCollection.getCyanAppearance()); }

	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public Daemon (float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos, AppearanceCollection.getCyanAppearance()); }
}
