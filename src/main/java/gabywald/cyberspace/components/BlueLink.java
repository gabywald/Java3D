package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementLine;

/**
 * This class defines light blue linear elements. 
 * @author Gabriel Chandesris (2010)
 */
public class BlueLink extends ElementLine {
	/** Default Constructor. */
	public BlueLink() { super(0.25f, AppearanceCollection.getLightBlueAppearance()); }

	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public BlueLink (float Xpos, float Ypos, float Zpos) { 
		super(AppearanceCollection.getLightBlueAppearance());
		super.setPosition(Xpos, Ypos, Zpos);
	}
}
