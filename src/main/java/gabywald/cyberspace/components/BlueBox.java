package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementCubic;

/**
 * This class defines specific light blue cubic elements. 
 * @author Gabriel Chandesris (2010)
 */
public class BlueBox extends ElementCubic {
	/** Default Constructor. */
	public BlueBox () { super(AppearanceCollection.getLightBlueAppearance()); }
	
	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public BlueBox (float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos, AppearanceCollection.getLightBlueAppearance()); }
}
