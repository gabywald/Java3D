package gabywald.biosilico.cyberspace;

import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.universe.SimpleUniverse;

import gabywald.cyberspace.behaviors.Deplacement;
import gabywald.cyberspace.behaviors.Mouvement;

/**
 * This class defines a control of movement for 3D view and movement control. 
 * @author Gabriel Chandesris (2010)
 */
public class CyberMove {
	/** Keyboard Control. */
	private Deplacement dpl;
	/** Movement Control. */
	private Mouvement mvt;
	
	/**
	 * Constructor. 
	 * @param universe (SimpleUniverse)
	 */
	public CyberMove(SimpleUniverse universe) { 
		TransformGroup view = universe.getViewingPlatform().getViewPlatformTransform();
		this.mvt = new Mouvement(view,0.0f,0.0f,5.0f);
		this.dpl = new Deplacement(view,this.mvt);
		
		Thread move = new Thread(this.mvt);
		move.start();
	}
	
	/**
	 * To get the Behavior to add in a branch of scene. 
	 * @return (Deplacement)
	 */
	public Deplacement getBehaviour() { return this.dpl; }
}
