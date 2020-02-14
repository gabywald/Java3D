package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementConic;

/**
 * This class defines green conic element. 
 * @author Gabriel Chandesris (2010)
 */
public class ChristmasPine extends ElementConic {
	/** Default Constructor. */
	public ChristmasPine() { super(0.40f, AppearanceCollection.getGreenAppearance()); }

	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public ChristmasPine (float Xpos, float Ypos, float Zpos) { 
		super(AppearanceCollection.getGreenAppearance());
		super.setPosition(Xpos, Ypos, Zpos);
	}
}
