package gabywald.java3d.tutoriel.finale;

/*

écrit par:Roswell
email:philgauthier_@hotmail.com

Maintenant que nous savons construire un comportement assez évolué, nous pouvons construire
un comportement nous permettant de déplacer la caméra dans notre monde virtuel. 
L'essentiel du travail est ici de récupérer le TG de la caméra. Pour cela, 
il convient de modifier l�gerement la méthode qui construit la scene pour lui ajouter 
en argument l'univers dans lequel on travaille car c'est lui qui contient la camera et le TG associé.
Jusqu'à présent nous éludions la classe "simpleUniverse" du schéma d'organisation 
car il ne servait peu dans la construction de notre monde et nous le configurions pas 
( nous ommettons encore certains éléments de SimpleUniverse). 
On obtient la caméra grâce à la méthode "getViewingPlatform()" 
de l'univers utilisé, puis on r�cup�re le TG de la caméra 
grâce à la méthode "getViewPlatformTransform()". 
Pour que vous puissiez aller partout dans votre monde, il faut également 
que vous définissiez une zone d'influence le couvrant complètement, 
c'est pour cela que nous n'utilisons plus le constructeur par d�faut. 
La classe de comportement ne nécessite que peu de transformation 
il faut juste remplacer certaines des rotations en translations.

 */

// classes Java standard
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;

// classes Java 3D
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Appearance;

import javax.vecmath.Vector3f;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.geometry.Box;

// classe cr��e pour l'interaction
import javax.media.j3d.BoundingSphere;

public class World extends Frame implements WindowListener
{	    
	public World()
	{
		super("- navigation : contr�le de la caméra -");
		this.addWindowListener(this);
		setLayout(new BorderLayout());
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		add("Center", canvas3D);

		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		simpleU.getViewingPlatform().setNominalViewingTransform();

		// on a modifié le constructeur pour inclure l'univers et pouvoir ainsi accéder
		// à la caméra et interagir avec elle
		BranchGroup scene = createSceneGraph(simpleU);
		scene.compile();
		simpleU.addBranchGraph(scene);        
	}	

	public BranchGroup createSceneGraph(SimpleUniverse simpleU)
	{
		//on crée le Bg principal
		BranchGroup objRoot=new BranchGroup();

		//------------ debut creation des apparences ---------------
		// on crée une apparence de couleur orange
		Appearance app_orang		= new Appearance();
		ColoringAttributes orang	= new ColoringAttributes();
		orang.setColor(0.8f, 0.4f, 0.2f);
		orang.setShadeModel(ColoringAttributes.NICEST);
		app_orang.setColoringAttributes(orang);		
		//------------ fin creation des apparences -----------------

		//------------ début de creation d'une box -----------------			
		TransformGroup TG		= new TransformGroup();

		Transform3D rayon3D		= new Transform3D();
		rayon3D.setTranslation(new Vector3f(0.0f, 0.0f, -10.0f));
		TransformGroup rayon	= new TransformGroup(rayon3D);
		TG.addChild(rayon);

		rayon.addChild(new Box(3.2f, 2.4f, 5.05f, app_orang));
		// rayon.addChild(new Box(3.2f, 2.4f, 5.05f, app_orang));

		objRoot.addChild(TG);
		//------------ fin de creation d'une box -------------------

		//------- début de ajout de l'interaction ------------------
		// On récupère le TG de la caméra
		TransformGroup view		= simpleU.getViewingPlatform().getViewPlatformTransform();

		// l'interaction aura lieu avec le TG de la caméra (lien par référence) 
		mouvbehav behav			= new mouvbehav(view);

		// On définit une zone d'influence très grande pour que l'interaction soit active sur une très grande zone
		BoundingSphere influPartout	= new BoundingSphere(new Point3d(), 1000.0);
		behav.setSchedulingBounds(influPartout);

		// lien d'héritage
		objRoot.addChild(behav);
		//------- fin de ajout de l'interaction --------------------

		return objRoot;
	}

	public void windowActivated(WindowEvent e)		{}
	public void windowClosed(WindowEvent e)			{}
	public void windowDeactivated(WindowEvent e)	{}
	public void windowDeiconified(WindowEvent e)	{}
	public void windowIconified(WindowEvent e)		{}
	public void windowOpened(WindowEvent e)			{}

	public void windowClosing(WindowEvent e)
	{
		System.exit(1);
	}

	public static void main(String args[])
	{
		World myApp	= new World();
		myApp.setSize(1024, 768);
		myApp.setVisible(true);
	}

}