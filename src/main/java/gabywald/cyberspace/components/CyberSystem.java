package gabywald.cyberspace.components;

import gabywald.cyberspace.objects.ElementSubNet;

/**
 * This class contains examples of Sub-NetWorks
 * @author Gabriel Chandesris (2010)
 * TODO terminer cette classe, 
 * TODO mettre texte en attribut de subNet ?
 * TODO algorithmes de construction
 */
public class CyberSystem extends ElementSubNet {
	
	public CyberSystem ()	{ super(); }

	public CyberSystem (float Xpos, float Ypos, float Zpos) 
		{ super(Xpos, Ypos, Zpos); }
	
	public static CyberSystem getCyberdyneSystems() {
		CyberSystem cyberdyne = new CyberSystem(-1.5f, 0.5f, -0.5f);
		cyberdyne.setString("Cyberdyne Systems");
		cyberdyne.setStringPosition(-0.30f, 0.30f, -0.2f);
		cyberdyne.addSubSystem(new BlueBox(), 0.25f, 0.05f, 0.25f);
		cyberdyne.addSubSystem(new BlueBox(), -0.15f, 0.05f, 0.25f);
		BlueLink link01 = new BlueLink();
		BlueLink link02 = new BlueLink();
		cyberdyne.addLink(link01, 0.05f, 0.05f, 0.25f);
		cyberdyne.addLink(link02, -0.15f, -0.15f, 0.25f);
		link01.rotate((float)(Math.PI/2.0), 3);
		return cyberdyne;
	}
}
