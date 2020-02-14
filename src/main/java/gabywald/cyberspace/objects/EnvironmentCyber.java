package gabywald.cyberspace.objects;

/**
 * This class defines a CuberSpace Environment with grid and axis. 
 * <br>Default Minimal and Maximal References to build Grid are values for Float. 
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleGrid
 * @see EnvironmentFond
 */
public class EnvironmentCyber extends Element {
	// private float nulRef;
	private float minXRef, maxXRef, minZRef, maxZRef;
	
	/** Default Constructor. */
	public EnvironmentCyber() {
		super();
		// this.nulRef = 0.0f;
		this.minXRef = Float.MIN_VALUE;
		this.maxXRef = Float.MAX_VALUE;
		this.minZRef = Float.MIN_VALUE;
		this.maxZRef = Float.MAX_VALUE;
		this.init();
	}
	
	/** 
	 * Constructor with minimal and maximal references values. 
	 * @param minRef (float) in meter, 20.0f is usual. 
	 * @param maxRef (float) in meter, 20.0f is usual. 
	 */
	public EnvironmentCyber(float minRef, float maxRef) {
		super();
		// this.nulRef = 0.0f;
		this.minXRef = minRef;
		this.maxXRef = maxRef;
		this.minZRef = minRef;
		this.maxZRef = maxRef;
		this.init();
	}
	
	/** 
	 * Constructor with minimal and maximal references values. 
	 * @param minXRef (float) in meter, 20.0f is usual. 
	 * @param maxXRef (float) in meter, 20.0f is usual.
	 * @param minZRef (float) in meter, 20.0f is usual. 
	 * @param maxZRef (float) in meter, 20.0f is usual. 
	 */
	public EnvironmentCyber(float minXRef, float maxXRef, 
							float minZRef, float maxZRef) {
		super();
		// this.nulRef = 0.0f;
		this.minXRef = minXRef;
		this.maxXRef = maxXRef;
		this.minZRef = minZRef;
		this.maxZRef = maxZRef;
		this.init();
	}
	
	protected void init() {
       	CyberSampleGrid rayons		= 
       		new CyberSampleGrid(this.minXRef, this.maxXRef, 
       							this.minZRef, this.maxZRef);
		this.addChild(rayons);
		/** On met en place la couleur du fond.  */
		EnvironmentFond fondecran	= new EnvironmentFond();
		this.addChild(fondecran);
	}
	
	/** 
	 * To set the default change position (0.2f, -0.5f, -10.0f). 
	 * @see Element#translateTo(float, float, float)
	 */
	public void decalage() 
		{ this.translateTo(0.2f, -0.5f, -10.0f); }
	
	/**
	 * To change position / visualization of Grid. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#translateTo(float, float, float)
	 */
	public void decalage(float Xpos, float Ypos, float Zpos) 
		{ this.translateTo(Xpos, Ypos, Zpos); }
	


}
