package gabywald.cyberspace.objects;

/**
 * This class to define a 2D grid (Oxz) in 3D space with 3D axis. 
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleLine
 */
public class CyberSampleGrid extends Element {
	
	/** Default Constructor. */
	public CyberSampleGrid () { super();this.init(); }
	
	/**
	 * Constructor with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 */
	public CyberSampleGrid (float minRef, float maxRef) 
		{ super();this.init(minRef, maxRef); }
	
	/**
	 * Constructor with minimal and maximal positions. 
	 * @param minXRef (float) in meter. 
	 * @param maxXRef (float) in meter.
	 * @param minZRef (float) in meter. 
	 * @param maxZRef (float) in meter. 
	 */
	public CyberSampleGrid (float minXRef, float maxXRef, 
			  				float minZRef, float maxZRef) 
		{ super();this.init(minXRef, maxXRef, minZRef, maxZRef); }

	/**
	 * Initialization with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 * @see CyberSpaceAxis
	 * @see CyberSampleLine
	 */
	private void init(float minRef, float maxRef) 
		{ this.init(minRef, maxRef, minRef, maxRef); }
	
	private void init(float minXRef, float maxXRef, 
					  float minZRef, float maxZRef) {
		/** Le repère principal. */
		CyberSpaceAxis.addAxisTo(this, minXRef, maxXRef, minZRef, maxZRef);
		/** La grille horizontale : Pour construire un plan en profondeur. */
		for (float cf = minXRef  ; cf <= maxXRef ; cf++) {
			CyberSampleLine axisXtmp = new CyberSampleLine(minXRef, 0f, cf, maxXRef, 0f, cf);
			this.addChild(axisXtmp);
		}
		
		for (float cf = minZRef ; cf <= maxZRef ; cf++) {
			CyberSampleLine axisZtmp = new CyberSampleLine(cf, 0f, minZRef, cf, 0f, maxZRef);
			this.addChild(axisZtmp);
		}
		
		
	}
	
	protected void init() { this.init(Float.MIN_VALUE, Float.MAX_VALUE); }
	
}
