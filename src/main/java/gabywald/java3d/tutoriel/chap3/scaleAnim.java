package gabywald.java3d.tutoriel.chap3;
/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Nous allons voir maintenant un autre interpolator qui sert à retailler 
  les objets contenus dans un TG. Cet interpolator est repr�sent� 
  par la classe "ScaleInterpolator". Son emploi est tres simple, il 
  suffit de sp�cifier l'Alpha utilisé, le TG à lier, le Transform3D 
  de travail, et enfin le pourcentage de taille initiale et celui de la taille 
  finale en float ou 1.0f repr�sente 100%.
  
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
import javax.media.j3d.ScaleInterpolator;


public class scaleAnim extends Frame implements WindowListener
{	    
	public scaleAnim()
	{
        super("- d�formation d'un simple cube -");
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
			Alpha myAlpha=new Alpha(-1,6000);

			ScaleInterpolator interpol=new ScaleInterpolator(myAlpha,objSpin,trans,0.5f,1.2f);

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
		scaleAnim myApp=new scaleAnim();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}