package gabywald.cyberspace.view;

import gabywald.cyberspace.behaviors.CyberAdder;

/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Maintenant que vous connaissez les bases de l'interection, nous pouvons passer 
à un exemple plus conséquent: le contrôle complet de la rotation 
de notre porte grâce au pavé numérique. 
Pour cela, il y a très peu de modification à faire au fichier 
      box.java. Il suffit juste de spécifier que le TG que l'on va manipuler
      sera disponible non seulement en écriture, mais également 
      en lecture pour à tout instant pouvoir récuperer l'orientation 
      de la porte et la modifier.
 */

// classes Java standard
import gabywald.cyberspace.behaviors.Deplacement;
import gabywald.cyberspace.behaviors.Mouvement;
import gabywald.cyberspace.behaviors.TextCyberConsole;
import gabywald.cyberspace.behaviors.TextCyberInteraction;
import gabywald.cyberspace.components.CyberSpace;
import gabywald.cyberspace.objects.Element;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class InterWeb extends JFrame implements WindowListener {	    
	/** To avoid a Warning. */
	// private static final long serialVersionUID = 1L;
	
	private CyberSpace cyberspace;

	public InterWeb() {
		super("- CyberSpace View -");
		this.addWindowListener(this);
		this.setLayout(new BorderLayout());
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		// canvas3D.setBackground(Color.GRAY);
		this.add(BorderLayout.CENTER, canvas3D);

		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		/** Pour obtenir la caméra : 
		 * simpleU.getViewingPlatform().setNominalViewingTransform();
		 */
		
		// BranchGroup scene = this.createSceneGraphGlobal();
		BranchGroup scene = this.createSceneGraph();
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		this.cyberspace.getGrid().setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		// scene.compile();
		simpleU.addBranchGraph(scene);
		
		BranchGroup users = this.createUserGraph(simpleU);
		// users.compile();
		simpleU.addBranchGraph(users);
		
//		for (int i = 0 ; i < 20 ; i++) {
//			User bolo			= new User(0,0,0);
//			bolo.setMouvement(i, i, 1.0f, 1);
//			bolo.startMove();
//			// this.cyberspace.getGrid().setCapability(Node.ALLOW_CHILDREN_EXTENDS);
//			// this.cyberspace.getGrid().setCapability(Node.ALLOW_PICKABLE_WRITE);
//			BranchGroup more = new BranchGroup();
//			more.addChild(bolo);
//			this.cyberspace.addChild(more);
//		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public BranchGroup createSceneGraph() {
		/** On crée le fond principal (racine de la représentation). */
		this.cyberspace = CyberSpace.getCyberSpace();
		this.cyberspace.setDefaultExample();
		// this.cyberspace.setMouvementDefault();
		return this.cyberspace;
	}

	public BranchGroup createUserGraph(SimpleUniverse universe) {
		/** ******************************************* */
		/** On récupere le TransformGroup de la caméra. */
		TransformGroup view = universe.getViewingPlatform().getViewPlatformTransform();
		/** On déplace la vue. */
		float Xmov = -0.2f;
		float Ymov = +1.0f;
		float Zmov = 10.0f;
		float Zdif = 2.40f;
		Element.translateTo(view, Xmov, Ymov, Zmov);
		this.cyberspace.getCyberConsole().translateTo(Xmov, Ymov, Zmov-Zdif);
		
		/** ******************************************* */
		BranchGroup testbranche = new BranchGroup();
		
		/** Interaction utilisateur */
		TextCyberInteraction tint	= new TextCyberInteraction(this.cyberspace.getCyberConsole());
		TextCyberConsole txtcon		= new TextCyberConsole(this.cyberspace.getCyberConsole());
		Mouvement mvt				= new Mouvement(view, 0.0f, 0.0f, 5.0f);
		
		CyberAdder ca	=  new CyberAdder();
		
		mvt.setTextInteraction(tint);
		
		/** DONE associer la console dans le mouvement. */
		mvt.addAssociateGroup(this.cyberspace.getCyberConsole(), Zdif);
		
		Deplacement behavior	= new Deplacement(view, mvt);
		
		testbranche.addChild( ca );
		
		testbranche.addChild(txtcon);
		testbranche.addChild(behavior);

		testbranche.addChild(mvt);	Thread move = new Thread(mvt);	move.start();
		testbranche.addChild(tint);	Thread tont = new Thread(tint);	tont.start();
		
		return testbranche;
	}
	
	public void windowActivated(WindowEvent e)		{ ; }
	public void windowClosed(WindowEvent e)			{ ; }
	public void windowDeactivated(WindowEvent e)	{ ; }
	public void windowDeiconified(WindowEvent e)	{ ; }
	public void windowIconified(WindowEvent e)		{ ; }
	public void windowOpened(WindowEvent e)			{ ; }

	public void windowClosing(WindowEvent e) 		{ System.exit(1); }

}
