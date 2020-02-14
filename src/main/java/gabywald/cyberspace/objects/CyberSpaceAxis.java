package gabywald.cyberspace.objects;

/**
 * This class to contain the 3D axis.
 * <br><i>Design-Pattern <b>Singleton</b></i>
 * @author Gabriel Chandesris (2010)
 * @see CyberSampleLine
 * @see CyberSampleLine#getAxisX()
 * @see CyberSampleLine#getAxisY()
 * @see CyberSampleLine#getAxisZ()
 * @see CyberSampleLine#getAxisX(float, float)
 * @see CyberSampleLine#getAxisY(float, float)
 * @see CyberSampleLine#getAxisZ(float, float)
 */
public class CyberSpaceAxis extends Element {
	/** The only one instance of this class. */
	private static CyberSpaceAxis instance;
	/** X-axis instance. */
	private CyberSampleLine axisX;
	/** Y-axis instance. */
	private CyberSampleLine axisY;
	/** Z-axis instance. */
	private CyberSampleLine axisZ;
	
	/** Default constructor. */
	private CyberSpaceAxis() 
		{ super();this.init(); }
	
	/**
	 * Constructor with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 */
	private CyberSpaceAxis(float minRef, float maxRef) 
		{ super();this.init(minRef, maxRef); }
	
	/**
	 * To get the instance with "infinite" length. 
	 * @return (CyberSpaceAxis)
	 */
	public static CyberSpaceAxis getInstance() { 
		if (CyberSpaceAxis.instance == null) 
			{ CyberSpaceAxis.instance = new CyberSpaceAxis(); }
		return CyberSpaceAxis.instance;
	}
	
	/**
	 * To get the instance with "finite" length. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 * @return (CyberSpaceAxis)
	 */
	public static CyberSpaceAxis getInstance(float minRef, float maxRef) { 
		// if (CyberSpaceAxis.instance == null) 
			// { 
			CyberSpaceAxis.instance = new CyberSpaceAxis(minRef, maxRef); 
			// }
		return CyberSpaceAxis.instance;
	}
	
	/**
	 * To set the "infinite" instance to a grid. 
	 * @param gridInstance (CyberSampleGrid)
	 */
	public static void addAxisTo(CyberSampleGrid gridInstance) {
		CyberSpaceAxis instance = CyberSpaceAxis.getInstance();
		gridInstance.addChild(instance.getAxisX());
		gridInstance.addChild(instance.getAxisY());
		gridInstance.addChild(instance.getAxisZ());
	}
	
	/**
	 * To set the "finite" instance to a grid. 
	 * @param gridInstance (CyberSampleGrid)
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 */
	public static void addAxisTo(CyberSampleGrid gridInstance, float minRef, float maxRef) {
		CyberSpaceAxis instance = CyberSpaceAxis.getInstance(minRef, maxRef);
		gridInstance.addChild(instance.getAxisX());
		gridInstance.addChild(instance.getAxisY());
		gridInstance.addChild(instance.getAxisZ());
	}
	
	/**
	 * To set the "finite" instance to a grid. 
	 * @param gridInstance (CyberSampleGrid)
	 * @param minXRef (float) in meter. 
	 * @param maxXRef (float) in meter.
	 * @param minZRef (float) in meter. 
	 * @param maxZRef (float) in meter. 
	 */
	public static void addAxisTo(CyberSampleGrid gridInstance, 
								 float minXRef, float maxXRef, 
								 float minZRef, float maxZRef) {
		CyberSpaceAxis instance = 
			CyberSpaceAxis.getInstance(minXRef, maxXRef);
		gridInstance.addChild(instance.getAxisX());
		gridInstance.addChild(instance.getAxisY());
		gridInstance.addChild(instance.getAxisZ());
	}
	
	public CyberSampleLine getAxisX() { return this.axisX; }
	public CyberSampleLine getAxisY() { return this.axisY; }
	public CyberSampleLine getAxisZ() { return this.axisZ; }

	/**
	 * Initialization with minimal and maximal positions. 
	 * @param minRef (float) in meter. 
	 * @param maxRef (float) in meter. 
	 */
	private void init(float minRef, float maxRef) {
		this.axisX = CyberSampleLine.getAxisX(minRef, maxRef);
		this.axisY = CyberSampleLine.getAxisY(minRef, maxRef);
		this.axisZ = CyberSampleLine.getAxisZ(minRef, maxRef);
	}
	
	protected void init() { this.init(Float.MIN_VALUE, Float.MAX_VALUE); }

}
