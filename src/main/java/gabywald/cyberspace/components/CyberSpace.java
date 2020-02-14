package gabywald.cyberspace.components;

import gabywald.cyberspace.behaviors.Deplacement;
import gabywald.cyberspace.behaviors.Mouvement;
import gabywald.cyberspace.behaviors.TextCyberConsole;
import gabywald.cyberspace.behaviors.TextCyberInteraction;
import gabywald.cyberspace.objects.ElementContainer;
import gabywald.cyberspace.objects.EnvironmentCyber;
import gabywald.cyberspace.objects.Scene;

/**
 * This class defines a specific kind of Scene, which looks like a cyberspace view as in Tron (1980's) or similar cyberpunk movies or books. 
 * @author Gabriel Chandesris (2010)
 * @see Scene
 */
public class CyberSpace extends Scene {
	private static CyberSpace instance	= null;
	// private static float NUL_REF = 000.0f;
	private static float MIN_REF		= -20.0f;
	private static float MAX_REF		= +20.0f;
	
	private ElementContainer localSpace;
	private EnvironmentCyber grid;
	private CyberConsole console;

	private CyberSpace () {
		this.localSpace = new ElementContainer();
		
		/** L'environnement : grille et couleur de fond. */
		this.grid = new EnvironmentCyber(CyberSpace.MIN_REF, CyberSpace.MAX_REF);
		// this.grid.decalage();
		
		this.console = new CyberConsole();

		this.localSpace.addChild(this.grid);
		this.localSpace.addChild(this.console);
		
		this.addChild(this.localSpace);
		
		this.console.addString("test 1");
		this.console.addString("test 2");
		this.console.addString("test 3");
	}
	
	public static CyberSpace getCyberSpace () {
		if (CyberSpace.instance == null)
			{ CyberSpace.instance = new CyberSpace(); }
		return CyberSpace.instance;
	}

	public CyberConsole getCyberConsole ()			{ return this.console; }
	public ElementContainer getLocalSpace () 		{ return this.localSpace; }
	public EnvironmentCyber getGrid ()				{ return this.grid; }
	
	/**
	 * Default Mouvement (0.0f, 0.0f, 1.0f). 
	 * @see CyberSpace#setMouvement(float, float, float)
	 */
	public void setMouvementDefault () 
		{ this.setMouvement(0.0f, 0.0f, 1.0f); }
	
	/**
	 * To set a Mouvement to this Scene of CyberSpace
	 * @param Xdir (float) X-direction. 
	 * @param Ydir (float) Y-direction. 
	 * @param Zdir (float) Z-direction.
	 * @see gabywald.cyberspace.behaviors.Mouvement
	 * @see gabywald.cyberspace.behaviors.Deplacement
	 * @see gabywald.cyberspace.behaviors.TextCyberConsole
	 * @see gabywald.cyberspace.behaviors.TextCyberInteraction
	 */
	public void setMouvement(float Xdir, float Ydir, float Zdir) {
		/** Interaction utilisateur */
		Mouvement mvt = new Mouvement(this.grid, Xdir, Ydir, Zdir);
		TextCyberInteraction tint = new TextCyberInteraction(this.console);
		mvt.setTextInteraction(tint);
		
		this.grid.addChild(mvt);	Thread move = new Thread(mvt);	move.start();
		this.grid.addChild(tint);	Thread tont = new Thread(tint);	tont.start();
		
		TextCyberConsole txtcon = new TextCyberConsole(this.console);
		this.grid.addChild(txtcon);
		
		Deplacement behavior = new Deplacement(this.grid, mvt);
		this.grid.addChild(behavior);
	}
	
	/** 
	 * This method provides some elements and behaviour inside CyberSpace Scene. 
	 * @see AlarmBox
	 * @see User
	 * @see Daemon
	 * @see CyberSystem#getCyberdyneSystems()
	 * @see MagicBreaker
	 * @see gabywald.cyberspace.objects.Element#setMouvement(float, float, float, int)
	 * @see gabywald.cyberspace.objects.Element#setMouvement(float, float, float, int, float, int)
	 * @see gabywald.cyberspace.objects.Element#startMove()
	 * @see gabywald.cyberspace.behaviors.Mouvement
	 */
	public void setDefaultExample () {
		/** Définition des éléments et de leur mouvement. */
		User bouboule			= new User();
		AlarmBox alarm			= new AlarmBox(0.3f, 0.3f, 0.3f);
		Daemon demon01			= new Daemon(-0.2f, -0.2f, -0.2f);
		Daemon demon02			= new Daemon(-0.2f, +0.2f, -0.2f);
		User demon03			= new User(-0.5f, +0.5f, -0.5f);
		CyberSystem cyberdyne	= CyberSystem.getCyberdyneSystems();
		MagicBreaker baguet01	= new MagicBreaker();
		MagicBreaker baguet02	= new MagicBreaker();
		MagicBreaker baguet03	= new MagicBreaker();
		ChristmasPine conic		= new ChristmasPine();
		
		baguet01.setPosition(-0.5f, 0.75f, 7.0f);
		baguet01.rotate(0.5f, 0);
		baguet01.rotate(1.0f, 1);
		baguet01.rotate((float)Math.PI/4, 2);
		baguet02.setPosition(-0.75f, 0.75f, 8.0f);
		baguet03.setPosition(+0.25f, 0.75f, 7.0f);
		conic.setPosition(-1.0f, 1.5f, -1.0f);

		alarm.setMouvement(1.0f, 0.0f, 0.0f, 1);
		bouboule.setMouvement(0.0f, 0.0f, 1.0f, 2);
		demon01.setMouvement(0.0f, 0.0f, -1.0f, 3);
		demon02.setMouvement(-0.25f, 0.25f, -1.0f, 3);
		demon03.setMouvement(0.0f, 0.05f, 0.05f, 8, 0.25f, 0);
		baguet01.setMouvement(0.05f, 0.0f, 0.05f, 2, 0.25f, 1);
		baguet02.setMouvement(0.25f, 0);
		baguet03.setMouvement(-0.25f, 2);
		conic.setMouvement(0.25f, 2);
		
		alarm.startMove();
		bouboule.startMove();
		demon01.startMove();
		demon02.startMove();
		demon03.startMove();
		baguet01.startMove();
		baguet02.startMove();
		baguet03.startMove();
		conic.startMove();

		this.grid.addChild(bouboule);
		this.grid.addChild(alarm);
		this.grid.addChild(demon01);
		this.grid.addChild(demon02);
		this.grid.addChild(demon03);
		this.grid.addChild(cyberdyne);
		
		this.grid.addChild(baguet01);
		this.grid.addChild(baguet02);
		this.grid.addChild(baguet03);
		this.grid.addChild(conic);
	}
	
	
}
