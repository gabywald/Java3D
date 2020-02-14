package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementSpherical;

/** 
 * This class defines orange spherical elements. 
 * @author Gabriel Chandesris (2010)
 */
public class User extends ElementSpherical {
	/** Default Constructor. */
	public User () { super(AppearanceCollection.getOrangeAppearance()); }
	
	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public User (float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos, AppearanceCollection.getOrangeAppearance()); }
}
