package gabywald.java3d.tutoriel.chap3;

/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Nous allons maintenant d�couvrir un autre interpolator très utile 
    le "PositionPathInterpolator" qui permet à un 
    TG de se déplacer au cours du temps en passant par diff�rents 
    points.
    Pour cela on associe des positions ( un tableau de Point3f ) à l'�chelle 
    du temps represent�e par un tableau de float. Le constructeur du "PositionPathInterpolator</code>&quot; 
    a besoin de ces 2 tableaux, d'une fontion du temps ( toujours la classe Alpha), 
    du TG auquel l'interpolator sera reli� (par r�f�rence), et 
    enfin un Transform3D qui sera manipul� par la classe au cours du temps.
  
*/

//Java standard API
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
//Java 3d API
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.ColorCube;

// classes n�cessaires à la rotation
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.PositionPathInterpolator;

public class pathAnim extends Frame implements WindowListener
{	    
	public pathAnim()
	{
        super("- une translation d'un simple cube -");
        this.addWindowListener(this);
        setLayout(new BorderLayout());
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center", canvas3D);
        BranchGroup scene = createSceneGraph();
        scene.compile();
        
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }
	
	// crée un regroupement d'objets contenant un objet cube qui va et vient entre 2 points 
	// grace au Transforme groupe objSpin
	public BranchGroup createSceneGraph()
	{
		//on crée le Bg principal
		BranchGroup objRoot=new BranchGroup();
		
		// on crée le TG contenant le cheminement
			Transform3D trans=new Transform3D();
			TransformGroup objSpin=new TransformGroup();

			// permet de modifier l'objet pendant l'execution
			objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

			// on crée un fonction de rotation au cours du temps
			Alpha transAlpha=new Alpha(-1,6000);

			// on crée notre chemin: une matrice de 3 Point3f: point de d�part, point d'arriv�e, 
			// puis retour au point de d�part pour permettre de boucler
			Point3f[] chemin=new Point3f[3];
			chemin[0]=new Point3f(0.8f,0.0f,0.0f);
			chemin[1]=new Point3f(-0.8f,0.0f,0.0f);
			chemin[2]=new Point3f(0.8f,0.0f,0.0f);

			// on crée une matrice de float qui sert à faire correspondre 
			// à chaque point dans l'espace un point dans l'�chelle du temps qui s'�tend de 0 à 1
			float[] timePosition={0.0f,0.50f,1.0f};

			PositionPathInterpolator interpol=new PositionPathInterpolator(transAlpha,
                                											objSpin,
																			trans,
																			timePosition,
																			chemin);

			// on définit la zone sur laquelle va s'appliquer le chemin
			BoundingSphere bounds=new BoundingSphere();
			interpol.setSchedulingBounds(bounds);
			objSpin.addChild( interpol);
		
		objRoot.addChild(objSpin);
		
		// on crée un cube
		objSpin.addChild(new ColorCube(0.5));// de rayon 50 cm

		return objRoot;
	}
    
    public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(1);
	}
    	
	public static void main(String args[])
	{
		pathAnim myApp=new pathAnim();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}