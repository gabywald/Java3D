package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementCubic;

/**
 * This class defines specific red cubic elements. 
 * @author Gabriel Chandesris (2010)
 */
public class AlarmBox extends ElementCubic {
	
	/** Default Constructor. */
	public AlarmBox () { super(AppearanceCollection.getRedAppearance()); }
	
	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public AlarmBox (float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos, AppearanceCollection.getRedAppearance()); }
}
