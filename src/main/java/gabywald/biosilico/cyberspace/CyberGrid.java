package gabywald.biosilico.cyberspace;

import gabywald.cyberspace.components.User;
import gabywald.cyberspace.objects.ElementContainer;
import gabywald.cyberspace.objects.EnvironmentCyber;
import gabywald.cyberspace.objects.Scene;

public class CyberGrid extends Scene {
	private ElementContainer localSpace;
	private EnvironmentCyber grid;
	
	public CyberGrid(float minX,float maxX,float minZ,float maxZ) {
		this.localSpace = new ElementContainer();
		
		/** L'environnement : grille et couleur de fond. */
		this.grid = new EnvironmentCyber(minX,maxX,minZ,maxZ);
		this.grid.decalage(1.0f, 1.0f, -0.0f);
		
		User test = new User(0.0f,0.0f,0.0f);
		
		this.localSpace.addChild(this.grid);
		this.localSpace.addChild(test);
		
		
		this.addChild(this.localSpace);
	}
	
	public ElementContainer getLocalSpace () 		{ return this.localSpace; }
	public EnvironmentCyber getGrid ()				{ return this.grid; }

}
