package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;
import javax.media.j3d.TransparencyAttributes;

/**
 * This class to define a cyberspace sub-network, i.e.  a big transparent cube and containing sub-cubes linked. 
 * <br>Default Appearance of main cube is {@linkplain AppearanceCollection#getCyanAppearance()} with a 75% transparency. 
 * @author Gabriel Chandesris (2010)
 */
public class ElementSubNet extends Element {
	/** The big Cube */
	private ElementCubic cube;
	/** The name of the SubNet.  */
	private ElementText subNetTitle;
	/** The subElement's. */
	private ElementCubic subsystems[];
	/** The links. */
	private ElementLine links[];
	
	/** Default constructor. */
	public ElementSubNet() { super();this.init(); }
	
	/** 
	 * Constructor with Appearance. 
	 * @param current (Appearance)
	 * @see AppearanceCollection
	 */
	public ElementSubNet(Appearance current) 
		{ super();this.init(current); }
	
	/**
	 * Constructor with positions. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @see Element#Element(float, float, float)
	 */
	public ElementSubNet(float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos);this.init(); }

	/**
	 * Constructor with positions and Appearance. 
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 * @param current (Appearance)
	 * @see Element#Element(float, float, float)
	 */
	public ElementSubNet(float Xpos, float Ypos, float Zpos, Appearance current) 
		{ super(Xpos, Ypos, Zpos);this.init(current); }
	
	/**
	 * Initialization with Appearance. 
	 * @param current (Appearance)
	 */
	protected void init(Appearance current) {
		this.subNetTitle	= new ElementText("");
		this.subsystems		= new ElementCubic[0];
		this.links			= new ElementLine[0];
		this.cube 			= new ElementCubic(Element.EDGE_SIZE * 5, current);
		this.addChild(this.cube);
		this.addChild(this.subNetTitle);
	}
	
	protected void init() {
		TransparencyAttributes trans = 
			new TransparencyAttributes(TransparencyAttributes.NICEST, 0.75f);
		Appearance global = AppearanceCollection.getCyanAppearance();
		global.setTransparencyAttributes(trans);
		this.init(global);
	}
	
	public void setString(String subNetTitle) 
		{ this.subNetTitle.setString(subNetTitle); }
	public void setStringPosition(float Xpos, float Ypos, float Zpos)
		{ this.subNetTitle.setPosition(Xpos, Ypos, Zpos); }
	
	/**
	 * To add a SubSystem representation in local SubNet. 
	 * @param subsys (ElementCubic)
	 */
	public void addSubSystem(ElementCubic subsys) {
		ElementCubic nextSubSystems[] = 
				new ElementCubic[this.subsystems.length+1];
		for (int i = 0 ; i < this.subsystems.length ; i++) 
			{ nextSubSystems[i] = this.subsystems[i]; }
		nextSubSystems[this.subsystems.length] = subsys;
		this.subsystems = nextSubSystems;
		this.addChild(subsys);
	}
	
	/**
	 * To add a Link representation in local SubNet. 
	 * @param link (ElementLine)
	 */
	public void addLink(ElementLine link) {
		ElementLine nextLinks[] = new ElementLine[this.links.length+1];
		for (int i = 0 ; i < this.links.length ; i++) 
			{ nextLinks[i] = this.links[i]; }
		nextLinks[this.links.length] = link;
		this.links		= nextLinks;
		this.addChild(link);
	}
	
	/**
	 * To add a SubSystem representation in local SubNet, with given positions. 
	 * @param subsys (ElementCubic)
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	public void addSubSystem(ElementCubic subsys, 
							 float Xpos, float Ypos, float Zpos) {
		this.addSubSystem(subsys);
		this.setSubSystemTo(this.subsystems.length-1, Xpos, Ypos, Zpos);
	}
	
	/**
	 * To add a Link representation in local SubNet, with given positions. 
	 * @param link (ElementLine)
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	public void addLink(ElementLine link, 
						float Xpos, float Ypos, float Zpos) {
		this.addLink(link);
		this.setLinkTo(this.links.length-1, Xpos, Ypos, Zpos);
	}
	
	/**
	 * For a given SubSystem, set the position. 
	 * @param i (int)
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	private void setSubSystemTo(int i, float Xpos, float Ypos, float Zpos) {
		if ( (i >= 0) && (i < this.subsystems.length) ) 
			{ this.subsystems[i].setPosition(Xpos, Ypos, Zpos); }
	}
	
	/**
	 * For a given Link, set the position. 
	 * @param i (int)
	 * @param Xpos (float) X-position. 
	 * @param Ypos (float) Y-position. 
	 * @param Zpos (float) Z-position. 
	 */
	private void setLinkTo(int i, float Xpos, float Ypos, float Zpos) {
		if ( (i >= 0) && (i < this.links.length) ) 
			{ this.links[i].setPosition(Xpos, Ypos, Zpos); }
	}

}
