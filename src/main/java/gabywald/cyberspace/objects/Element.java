package gabywald.cyberspace.objects;

import gabywald.cyberspace.behaviors.Mouvement;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 * This class defines transformablecomponents for 3D-representation and 
 * aim is to be inherited as container for 3D-components (Box, Spheres, Planes...). 
 * <br>See {@linkplain ElementContainer} for a default inheritant class. 
 * @author Gabriel Chandesris (2010)
 */
public abstract class Element extends TransformGroup {
	/** Base length for cube's egde, sphere's radial... (5 cm) */
	public static final float EDGE_SIZE = 0.05f;
	/** X position of the element. */
	private float posX;
	/** Y position of the element. */
	private float posY;
	/** Z position of the element. */
	private float posZ;
	/** Behavior of the element. */
	private Mouvement deplace;
	
	/** 
	 * Default constructor of an element. 
	 * <br>Set position at (0, 0, 0). 
	 */
	protected Element () {
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		this.posX = 0.0f;this.posY = 0.0f;this.posZ = 0.0f;
		Element.translateTo(this, this.posX, this.posY, this.posZ);
	}
	
	/**
	 * Constructor with specific given positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	protected Element(float Xpos, float Ypos, float Zpos) {
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		this.posX = Xpos;this.posY = Ypos;this.posZ = Zpos;
		Element.translateTo(this, this.posX, this.posY, this.posZ);
	}
	
	/**
	 * Better-named translation method. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#translateTo(float, float, float)
	 */
	public void setPosition(float Xpos, float Ypos, float Zpos) 
		{ this.translateTo(Xpos, Ypos, Zpos); }
	
	/**
	 * Translation method for the current instance. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#translateTo(TransformGroup, float, float, float)
	 */
	public void translateTo(float Xpos, float Ypos, float Zpos) {
		this.posX = Xpos;this.posY = Ypos;this.posZ = Zpos;
		Element.translateTo(this, this.posX, this.posY, this.posZ);
	}
	
	/**
	 * Translation method for a given instance of TransformGroup. 
	 * @param elt (TransformGroup)
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	public static void translateTo(TransformGroup elt, float Xpos, float Ypos, float Zpos) {
		Transform3D transform	= new Transform3D();
		Vector3f vector			= new Vector3f(Xpos,Ypos,Zpos);
		transform.setTranslation(vector);
		elt.setTransform(transform);
	}
	
	/**
	 * Rotation appliance method for current instance of Element. 
	 * @param angle (float) in radians. 
	 * @param axis (int) 0 for X-axis ; 1 for Y-axis ; 2 for Z-axis. 
	 */
	public void rotate(float angle, int axis) {
		Transform3D rotator		= new Transform3D();
		Transform3D transformer	= new Transform3D();
		rotator.setIdentity();
		switch(axis) {
		case(0):rotator.rotX(angle);break;
		case(1):rotator.rotY(angle);break;
		case(2):rotator.rotZ(angle);break;
		}
		this.getTransform(transformer);
		transformer.mul(rotator);
		this.setTransform(transformer);
	}
	
	/** 
	 * To apply a default behaviour to current instance of Element. 
	 * @see gabywald.cyberspace.behaviors.Mouvement
	 */
	public void setMouvement() {
		this.deplace = new Mouvement(this);
		this.addChild(this.deplace);
	}
	
//	/**
//	 * To apply a behaviour to current instance with directions. 
//	 * @param Xdir (float) X-direction. 
//	 * @param Ydir (float) Y-direction. 
//	 * @param Zdir (float) Z-direction. 
//	 * @see gabywald.cyberspace.behavior.Mouvement
//	 */
//	public void setMouvement(float Xdir, float Ydir, float Zdir) {
//		this.deplace = new Mouvement(this,Xdir,Ydir,Zdir);
//		this.addChild(this.deplace);
//	}
	
	/**
	 * To apply a behaviour to current instance with directions and speed. 
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @param acc (int) speed. 
	 * @see gabywald.cyberspace.behaviors.Mouvement
	 */
	public void setMouvement(float Xdir, float Ydir, float Zdir, int acc) {
		this.deplace = new Mouvement(this, Xdir, Ydir, Zdir);
		this.deplace.setSpeed(acc);
		this.addChild(this.deplace);
	}
	
	/**
	 * Method to set Mouvement with angle and direction for self-rotation.
	 * @param angleSelf (float) angle for self-rotation. 
	 * @param dirSelf (int) wich direction [0-2]. 
	 * @see gabywald.cyberspace.behaviors.Mouvement
	 * @see gabywald.cyberspace.behaviors.Behaviour
	 */
	public void setMouvement(float angleSelf,int dirSelf) {
		this.deplace = new Mouvement(this, angleSelf, dirSelf);
		this.addChild(this.deplace);
	}
	
	/**
	 * Method to set Mouvement with directions, angle and direction for translation and rotation.
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @param acc (int) speed. 
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 * @see gabywald.cyberspace.behaviors.Mouvement
	 * @see gabywald.cyberspace.behaviors.Behaviour
	 */
	public void setMouvement(float Xdir, float Ydir, float Zdir,int acc,
							 float angle,int dir) {
		this.deplace = new Mouvement(this, Xdir, Ydir, Zdir, angle, dir);
		this.deplace.setSpeed(acc);
		this.addChild(this.deplace);
	}
	
	/** To launch behaviour. */
	public void startMove() {
		if (this.deplace != null) {
			Thread mvt = new Thread(this.deplace);
			mvt.start();
		}
	}
	
	public float getPosX() { return this.posX; }
	public float getPosY() { return this.posY; }
	public float getPosZ() { return this.posZ; }
	
	public void setPosX(float posX) { this.posX = posX; }
	public void setPosY(float posY) { this.posY = posY; }
	public void setPosZ(float posZ) { this.posZ = posZ; }
	
	/** To initialize contained elements with default Appearance, Edge Size... */
	protected abstract void init();
	
	public static Point3f getCenterPosition () 
		{ return new Point3f(0.0f, 0.0f, 0.0f); }
	
	public static Point3f getLeftTop () 
		{ return new Point3f(-1.0f, 0.5f, 0.0f); }
	
	public static Point3f getRightTop () 
		{ return new Point3f(1.0f, 0.5f, 0.0f); }
	
	public static Point3f getLeftBottom () 
		{ return new Point3f(-1.0f, 0.5f, 0.0f); }
	
	public static Point3f getRightBottom () 
		{ return new Point3f(1.0f, -0.5f, 0.0f); }

}
