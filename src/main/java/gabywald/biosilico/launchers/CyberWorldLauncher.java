package gabywald.biosilico.launchers;

import gabywald.biosilico.cyberspace.CyberWorld;

public class CyberWorldLauncher {
	/** Unique instance of this launcher. */
	private static CyberWorldLauncher instance = null;
	/** View of the controller. */
	private CyberWorld localView;
	
	/**
	 * To get the unique instance of the launcher. 
	 * @return (GenKit)
	 */
	public static CyberWorldLauncher getInstance() {
		if (CyberWorldLauncher.instance == null) 
			{ CyberWorldLauncher.instance = new CyberWorldLauncher(); }
		return CyberWorldLauncher.instance;
	}
	
	public void setView(CyberWorld view) { this.localView = view; }
	public CyberWorld getView() { return this.localView; }
	
	/** 
	 * MAIN launch for this view. 
	 * @param args (String[]) not used.
	 */
	public static void main(String[] args) {
		CyberWorldLauncher controller = CyberWorldLauncher.getInstance();
		controller.setView(CyberWorld.getInstance());
	}
}
