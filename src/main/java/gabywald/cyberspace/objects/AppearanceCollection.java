package gabywald.cyberspace.objects;

import java.awt.Color;
import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import com.sun.j3d.utils.image.TextureLoader;

/**
 * Aim of this (abstract) class is to contain some getAppearance's methods. 
 * @author Gabriel Chandesris (2010)
 */
public abstract class AppearanceCollection {

	/**
	 * To get an Appearance from values of red / green / blue. 
	 * @param red (float) from 0.0 to 1.0
	 * @param green (float) from 0.0 to 1.0
	 * @param blue (float) from 0.0 to 1.0
	 * @return (Appearance)
	 */
	public static Appearance getAppearance(float red, float green, float blue) {
		Appearance appearance		= new Appearance();
		ColoringAttributes color	= new ColoringAttributes();
		color.setColor(red, green, blue);
		color.setShadeModel(ColoringAttributes.NICEST);
		appearance.setColoringAttributes(color);
		return appearance;
	}
	
	/**
	 * To get an Appearance from values of red / green / blue and transparency. 
	 * @param red (float) from 0.0 to 1.0
	 * @param green (float) from 0.0 to 1.0
	 * @param blue (float) from 0.0 to 1.0
	 * @param trans (float) from 0.0 to 1.0
	 * @return (Appearance)
	 */
	public static Appearance getAppearance(float red, float green, float blue, float trans) {
		TransparencyAttributes transp = 
			new TransparencyAttributes(TransparencyAttributes.NICEST, trans);
		Appearance toFront = AppearanceCollection.getAppearance(red, green, blue);
		toFront.setTransparencyAttributes(transp);
		return toFront;
	}
	
	/**
	 * To build an Appearance from a given Color. 
	 * @param color (Color)
	 * @return (Appearance)
	 */
	public static Appearance getAppearance(Color color) {
		Appearance appearance		= new Appearance();
		Material material			= new Material();
		material.setEmissiveColor(new Color3f(color));
		appearance.setMaterial(material);
		return appearance;
//		return AppearanceCollection.getAppearance(color.getRed(), 
//												  color.getGreen(), 
//												  color.getBlue());
	}
	
	public static Appearance getSpecialAppearance(String imageLocation) {
		/** Creation de l'apparence. */ 
		Appearance app = new Appearance() ;
		/** Chargement de la texture. */
		TextureLoader tex = new TextureLoader(imageLocation, new JFrame());
		app.setTexture(tex.getTexture());
		Color3f black = new Color3f(Color.BLACK);
		Color3f white = new Color3f(Color.WHITE);
		/** On fixe les proprietes du materiau. */
		app.setMaterial(new Material(white, black, white, black, 1.0f));
		return app;
	}
	
	public static Appearance getBlueAppearance ()
		{ return AppearanceCollection.getAppearance(Color.BLUE); }

	public static Appearance getCyanAppearance ()
		{ return AppearanceCollection.getAppearance(Color.CYAN); }
	
	public static Appearance getGreenAppearance () 
		{ return AppearanceCollection.getAppearance(Color.GREEN); }
	
	public static Appearance getMagentaAppearance () 
		{ return AppearanceCollection.getAppearance(Color.MAGENTA); }
	
	public static Appearance getRedAppearance () 
		{ return AppearanceCollection.getAppearance(Color.RED); }
	
	public static Appearance getOrangeAppearance ()
		{ return AppearanceCollection.getAppearance(Color.ORANGE); }
	
	public static Appearance getWhiteAppearance ()
		{ return AppearanceCollection.getAppearance(Color.WHITE); }
	
	public static Appearance getYellowAppearance ()
		{ return AppearanceCollection.getAppearance(Color.YELLOW); }
	

	public static Color getLightBlue() 
		{ return new Color(0.4f, 0.4f, 1.0f); }
	
	public static Appearance getLightBlueAppearance ()
	{ return AppearanceCollection
				.getAppearance(AppearanceCollection.getLightBlue()); }
	
	public static Appearance getConsoleFrontAppearance () 
		{ return AppearanceCollection.getAppearance(0.5f, 0.5f, 0.5f, 0.75f); }
	
	public static Appearance getMagicBreakerAppearance ()
		{ return AppearanceCollection.getAppearance(0.5f, 0.0f, 0.75f, 0.05f); }

}
