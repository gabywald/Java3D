package gabywald.cyberspace.behaviors;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.RestrictedAccessException;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 * This class defines generic behaviors and its transformation methods (rotation, translation). 
 * <br>When an int for direction (rotation) 0 is X ; 1 is Y ; 2 is Z. 
 * <br>More details / appliance is {@linkplain Behaviour#rotate(TransformGroup, float, int)} and {@linkplain Behaviour#reactualiseDirections(float, int)}. 
 * @author Gabriel Chandesris (2010)
 */
public abstract class Behaviour extends Behavior {
	/** 0.1f */
	public static final float SPEED_UNIT = 0.1f;
	/** 0.1f */
	public static final float ANGLE_UNIT = 0.1f; // (float)Math.PI/2;
	
	/** Angle for self rotation. */
	private float angleSelf;
	/** Which direction for self rotation. */
	private int rotDirSelf;
	
	/** Angle for directionnal rotation. */
	private float angleDir;
	/** Which direction for directionnal rotation. */
	private int rotDir;
	
	/** Speed for directionnal translation. */
	private float speed;
	/** Direction value for directionnal translation. */
	private float dirX,dirY,dirZ;
	/** Main group to be transformed. */
	private TransformGroup currentGroup;
	/** Other groups to be transformed with. */
	private TransformGroup associaGroup[];
	/** Z-distance between an toher group to the main. */
	private float ZdiffGroup[];
	/** A rotator. */
	private static Transform3D rotator		= new Transform3D();
	/** A transformer. */
	private static Transform3D transformer	= new Transform3D();
	/** A translater. */
	private static Vector3f translation		= new Vector3f();
	
	/** Default constructor. */
	public Behaviour () 
		{ super();this.init(null, 0.0f, 0.0f, 0.0f, 0.0f, 0); }
	
	/**
	 * Constructor with main TransformGroup. 
	 * @param currentGroup (TransformGroup)
	 */
	public Behaviour (TransformGroup currentGroup) 
		{ super();this.init(currentGroup, 0.0f, 0.0f, 0.0f, 0.0f, 0); }
	
	/**
	 * Constructor with main TransformGroup and directions.
	 * @param currentGroup (TransformGroup)
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 */
	public Behaviour (TransformGroup currentGroup,
						float Xdir,float Ydir,float Zdir) 
		{ super();this.init(currentGroup, Xdir, Ydir, Zdir, 0.0f, 0); }
	
	/**
	 * Constructor with main TransformGroup, angle and direction for self-rotation.
	 * @param currentGroup (TransformGroup)
	 * @param angleSelf (float) angle for self-rotation. 
	 * @param dirSelf (int) wich direction [0-2]. 
	 */
	public Behaviour (TransformGroup currentGroup,
						float angleSelf,int dirSelf) 
		{ super();this.init(currentGroup,  angleSelf, dirSelf); }
	
	/**
	 * Constructor with directions, angle and direction for translation and rotation.
	 * @param currentGroup (TransformGroup)
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 */
	public Behaviour (TransformGroup currentGroup,
						float Xdir,float Ydir,float Zdir,
						float angle,int dir) 
		{ super();this.init(currentGroup, Xdir, Ydir, Zdir, angle, dir); }
	
	
	/**
	 * Initialization with main TransformGroup, angle and direction for self-rotation.
	 * @param currentGroup (TransformGroup)
	 * @param angleSelf (float) angle for self-rotation. 
	 * @param dirSelf (int) wich direction [0-2]. 
	 */
	private void init(TransformGroup currentGroup,
						float angleSelf, int dirSelf) {
		this.setSchedulingBounds(new BoundingSphere(new Point3d(),1000.0));
		this.currentGroup = currentGroup;
		try { 
			this.currentGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			this.currentGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		} 
		catch (RestrictedAccessException e) { ; }
		catch (NullPointerException e) { ; }

		this.angleSelf	= angleSelf;
		this.rotDirSelf = dirSelf;

		this.angleDir	= 0.0f;
		this.rotDir		= 1;

		this.dirX		= 0.0f;
		this.dirY		= 0.0f;
		this.dirZ		= 0.0f;
		this.associaGroup	= new TransformGroup[0];
		this.ZdiffGroup 	= new float[0];
	}
	
	
	/**
	 * Initialization with directions, angle and direction for translation and rotation.
	 * @param currentGroup (TransformGroup)
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 */
	private void init(TransformGroup currentGroup,
						float Xdir,float Ydir,float Zdir,
						float angle, int dir) {
		this.setSchedulingBounds(new BoundingSphere(new Point3d(),1000.0));
		this.currentGroup = currentGroup;
		try { 
			this.currentGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			this.currentGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		} 
		catch (RestrictedAccessException e) { ; }
		catch (NullPointerException e) { ; }
		
		this.angleSelf	= 0.0f;
		this.rotDirSelf = 1;
		
		this.angleDir	= angle;
		this.rotDir		= dir;
		
		this.dirX		= Xdir;
		this.dirY		= Ydir;
		this.dirZ		= Zdir;
		this.associaGroup	= new TransformGroup[0];
		this.ZdiffGroup 	= new float[0];
	}
	
	public void addAssociateGroup(TransformGroup associaGroup,float Zdif) {
		int localLength = this.associaGroup.length;
		TransformGroup nextAssoGroup[]	= new TransformGroup[localLength+1];
		float nextZdiffGroup[]			= new float[localLength+1];
		for (int i = 0 ; i < localLength ; i++) { 
			nextAssoGroup[i]	= this.associaGroup[i];
			nextZdiffGroup[i]	= this.ZdiffGroup[i];
		}
		nextAssoGroup[localLength]	= associaGroup;
		nextZdiffGroup[localLength]	= Zdif;
		this.associaGroup	= nextAssoGroup;
		this.ZdiffGroup		= nextZdiffGroup;
	}
	
	/** 
	 * Translation of main group and associated groups (<i>To be run in a run of process</i>). 
	 * @see Behaviour#translate(TransformGroup, float, float, float, float)
	 */
	protected void translate() {
		Behaviour.translate(this.currentGroup, 
							this.dirX, this.dirY, this.dirZ, 
							this.speed);
		for (int i = 0 ; i < this.associaGroup.length ; i++) {
			Behaviour.translate(this.associaGroup[i], 
					this.dirX, this.dirY, this.dirZ, 
					this.speed);
		}
	}
	
	/** 
	 * Rotation of main group and associated groups (<i>To be run in a run of process</i>). 
	 * @see Behaviour#rotate(TransformGroup, float, int)
	 */
	protected void rotate() {
		Behaviour.rotate(this.currentGroup, this.angleDir, this.rotDir);
		for (int i = 0 ; i < this.associaGroup.length ; i++) 
			{ Behaviour.rotate(this.associaGroup[i], this.angleDir, this.rotDir); }
	}
	
	/** 
	 * Self-Rotation of main group (<i>To be run in a run of process</i>). 
	 * @see Behaviour#rotate(TransformGroup, float, int)
	 */
	protected void rotateSelf() 
		{ Behaviour.rotate(this.currentGroup, this.angleSelf, this.rotDirSelf); }
	
	/** 
	 * To provoque directionnal rotation.
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 * @see Behaviour#rotate(float, int, boolean)
	 * @deprecated see {@linkplain Behaviour#rotate(float, int, boolean)}. 
	 */
	protected void rotate(float angle,int dir) 
		{ this.rotate(angle, dir, false); }
	
	/**
	 * This method to apply rotation to main Group and associated (with correction). 
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 * @param self (boolean) self-rotation is true, else false.
	 */
	private void rotate(float angle,int dir,boolean self) {
		Behaviour.rotate(this.currentGroup, angle, dir);
		for (int i = 0 ; i < this.associaGroup.length ; i++) {
			Behaviour.rotate(this.associaGroup[i], angle, dir);
			float XYdiff = (this.ZdiffGroup[i]*((float)Math.sin(angle)));
			float ZZdiff = 1.0f - (float)Math.abs(Math.cos(angle)); // => 2*ZZdiff
			if (dir == 0) {
				Behaviour.translate(this.associaGroup[i], 
										0.0f, 
										XYdiff, 
										2*ZZdiff, 1.0f);
				/** DONE correcteur sur Z ? 2*ZZdiff */
			} else if (dir == 1) {
				Behaviour.translate(this.associaGroup[i], 
										-XYdiff, 
										0.0f, 
										2*ZZdiff, 1.0f);
				/** DONE correcteur sur Z ? 2*ZZdiff */
			}
		}
		if (!self) { this.reactualiseDirections(angle,dir); }
	}
	
	/**
	 * This method applies translation to a given transformGroup. 
	 * @param currentGroup (TransformGroup)
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @param speed (float) speed (modificator of directions). 
	 * @see Behaviour#rotator
	 * @see Behaviour#transformer
	 * @see Behaviour#translation
	 * @see Behaviour#transform(TransformGroup)
	 */
	protected static synchronized void translate(TransformGroup currentGroup,
									float Xdir,float Ydir,float Zdir,
									float speed) {
		/** On met la matrice à l'identité. */
		Behaviour.rotator.setIdentity();
		/** On récupère la matrice d'orientation actuelle du TG. */
		currentGroup.getTransform(Behaviour.transformer);
		/** On fixe le vecteur de déplacement. */
		Behaviour.translation.set(Xdir*speed,Ydir*speed,-Zdir*speed);
		/** Modification sur 'this.rotator'. */
		Behaviour.rotator.setTranslation(Behaviour.translation);
		/** On applique cette rotation à la matrice courante d'orientation du TG. */
		Behaviour.transformer.mul(Behaviour.rotator);
		
		Behaviour.transform(currentGroup);
	}
	
	/**
	 * This method applies rotation to a given transformGroup. 
	 * @param currentGroup (TransformGroup)
	 * @param angle (float) angle of rotation for direction. 
	 * @param dir (int) wich direction [0-2]. 
	 * @see Behaviour#rotator
	 * @see Behaviour#transformer
	 * @see Behaviour#translation
	 * @see Behaviour#transform(TransformGroup)
	 */
	protected static synchronized void rotate(TransformGroup currentGroup,
											  float angle,int dir) {
		/** On met la matrice à l'identité. */
		Behaviour.rotator.setIdentity();
		/** On récupère la matrice d'orientation actuelle du TG. */
		currentGroup.getTransform(Behaviour.transformer);
		
		switch(dir) {
		case (0):Behaviour.rotator.rotX(angle);break;
		case (1):Behaviour.rotator.rotY(angle);break;
		case (2):Behaviour.rotator.rotZ(angle);break;
		}
		/** On applique la rotation à la matrice courante d'orientation du TG. */
		Behaviour.transformer.mul(Behaviour.rotator);
		
		Behaviour.transform(currentGroup);
	}
	
	/**
	 * This method finishes and applies transformation. 
	 * @param currentGroup (TransformGroup)
	 * @see Behaviour#transformer
	 * @see Behaviour#rotate(TransformGroup, float, int)
	 * @see Behaviour#translate(TransformGroup, float, float, float, float)
	 */
	private static synchronized void transform(TransformGroup currentGroup) {
		/** On applique la nouvelle matrice au(x) TG(s) que l'on manipule. */
		currentGroup.setTransform(Behaviour.transformer);
	}
	
	private void reactualiseDirections(float angle,int dir) {
		Behaviour.translation.set(this.dirX,this.dirY,this.dirZ);
		// http://fr.wikipedia.org/wiki/Rotation_vectorielle
		float tmpX = this.dirX;
		float tmpY = this.dirY;
		float tmpZ = this.dirZ;
		switch(dir) {
		case (0):
			/** Rotation autour de l'axe X, dans le plan Oyz. */
			// this.rotator.rotX(angle);
			this.dirY = tmpY*((float)Math.cos(angle)) - tmpZ*((float)Math.sin(angle));
			this.dirZ = tmpY*((float)Math.sin(angle)) + tmpZ*((float)Math.cos(angle));
		break;
		case (1):
			/** Rotation autour de l'axe Y, dans le plan Oxz. */
			// this.rotator.rotY(angle);
			this.dirX = tmpX*((float)Math.cos(angle)) - tmpZ*((float)Math.sin(angle));
			this.dirZ = tmpX*((float)Math.sin(angle)) + tmpZ*((float)Math.cos(angle));
		break;
		case (2):
			/** Rotation autour de l'axe Z, dans le plan Oxy. */
			// this.rotator.rotY(angle);
			this.dirX = tmpX*((float)Math.cos(angle)) - tmpY*((float)Math.sin(angle));
			this.dirY = tmpX*((float)Math.sin(angle)) + tmpY*((float)Math.cos(angle));
		break;
		}
		
		Behaviour.translation.set(this.dirX,this.dirY,this.dirZ);
	}
	
	/** 
	 * To increase speed by speed unit. 
	 * @see Behaviour#SPEED_UNIT
	 * @see Behaviour#reinitialiseSpeed()
	 */
	public void accelerate()	{ 
		this.speed += Behaviour.SPEED_UNIT;
		this.reinitialiseSpeed();
	}
	
	/**
	 * To decrease speed until 0.0f by speed unit. 
	 * @see Behaviour#SPEED_UNIT
	 * @see Behaviour#reinitialiseSpeed()
	 */
	public void unaccelerate()	{
		if (this.speed > 0.0) 
			{ this.speed -= Behaviour.SPEED_UNIT; } 
		this.reinitialiseSpeed();
	}
	
	/**
	 * Applies a floated rounded to speed. 
	 * @see Behaviour#accelerate()
	 * @see Behaviour#unaccelerate()
	 */
	private void reinitialiseSpeed() {
		if (this.speed%Behaviour.SPEED_UNIT != 0) 
			{ this.speed = ((float)Math.round(this.speed*1000)/1000); }
	}
	
	public float getDirX () { return this.dirX; }
	public float getDirY () { return this.dirY; }
	public float getDirZ () { return this.dirZ; }
	
	public void setDirX(float dirX) { this.dirX = dirX; }
	public void setDirY(float dirY) { this.dirY = dirY; }
	public void setDirZ(float dirZ) { this.dirZ = dirZ; }
	
//	public void goRight()		{ this.dirX = -this.speed;this.dirY = 0;this.dirZ = 0; }
//	public void goLeft()		{ this.dirX = +this.speed;this.dirY = 0;this.dirZ = 0; }
//	public void goUp()			{ this.dirY = -this.speed;this.dirX = 0;this.dirZ = 0; }
//	public void goDown()		{ this.dirY = +this.speed;this.dirX = 0;this.dirZ = 0; }
//	public void goBackward()	{ this.dirZ = +this.speed;this.dirY = 0;this.dirX = 0; }
//	public void goForward()		{ this.dirZ = -this.speed;this.dirY = 0;this.dirX = 0; }
	
	public float getSpeed () { return this.speed; }
	public void setSpeed (int acc) {
		float diffSpeed = (float)acc;
		if (diffSpeed >= 0) 
			{ this.speed += (diffSpeed/10.0); }
		else {
			if ( (this.speed > 0.0) && ((this.speed-diffSpeed) > 0.0) )
				{ this.speed -= (diffSpeed/10.0); }
			else if ((this.speed-diffSpeed) > 0.0) 
				{ this.speed = 0; }
		}
	}
	
	public void rotX(float angle) { this.rotate(angle,0,false); }
	public void rotY(float angle) { this.rotate(angle,1,false); }
	public void rotZ(float angle) { this.rotate(angle,2,false); }
	
	public void rotX() { this.rotX(Behaviour.ANGLE_UNIT); }
	public void rotY() { this.rotY(Behaviour.ANGLE_UNIT); }
	public void rotZ() { this.rotZ(Behaviour.ANGLE_UNIT); }
	
	public void rotateRight()	{
		this.rotY(+Behaviour.ANGLE_UNIT);
		this.rotZ(+Behaviour.ANGLE_UNIT); 
	}
	public void rotateLeft() 	{
		this.rotY(-Behaviour.ANGLE_UNIT);
		this.rotZ(-Behaviour.ANGLE_UNIT);
	}

}
