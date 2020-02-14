package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.ElementLine;

/**
 * This class defines purple linear elements. 
 * @author Gabriel Chandesris (2010)
 */
public class MagicBreaker extends ElementLine {
	/** Default Constructor. */
	public MagicBreaker() { super(0.25f, AppearanceCollection.getMagicBreakerAppearance()); }

	/**
	 * Constructor with given positions. 
	 * @param Xpos (float) in meter. 
	 * @param Ypos (float) in meter. 
	 * @param Zpos (float) in meter. 
	 */
	public MagicBreaker (float Xpos, float Ypos, float Zpos) { 
		super(AppearanceCollection.getMagicBreakerAppearance());
		super.setPosition(Xpos, Ypos, Zpos);
	}

}
