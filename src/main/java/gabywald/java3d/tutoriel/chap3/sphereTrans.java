package gabywald.java3d.tutoriel.chap3;
/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Nous allons maintenant, pour terminer ce chapitre voir comment rendre nos 
primitives transparente. Il suffit de sp�cifier une "TransparencyAttributes" 
contenant le taux de transparence ( 0=pas de transparence et 1=invisible ) 
� notre apparence.
*/
// classes Java standart
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.Font;
// classes Java 3D
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.Appearance;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Color3f;
import javax.vecmath.Point2f;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;

public class sphereTrans extends Frame implements WindowListener
{	    	    
	public sphereTrans()
	{
        super("- intro à la transparence -");
        this.addWindowListener(this);
        setLayout(new BorderLayout());
        // 1ere étape création du Canvas3d qui vas afficher votre univers virtuel avec une config prédéfinie
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center", canvas3D);
        // 2eme étape on crée notre scene (regroupement d'objet)
        BranchGroup scene = createSceneGraph();
        // on les compile pour optimiser les calcules
        scene.compile();
        
        // 3eme étape on creer l'univer qui va contenir notre scene 3d
        // utilise simpleUniverse qui simplifie le code (il crée un environemment minimal simple)
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        // on met le plan de projection en arriere par rapport a l'origine mais plus que d'habitude
        // donc on n'utilise pas :simpleU.getViewingPlatform().setNominalViewingTransform();
        Transform3D tmp=new Transform3D();
        tmp.set(new Vector3f(0.0f, 0.0f, 5.0f));
        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(tmp);
        // on place la scene dans l'univers simpleU
        simpleU.addBranchGraph(scene);
    }	
	
	//crée un regroupement d'objet contenant un objet box
	private BranchGroup createSceneGraph()
	{
		BranchGroup scene=new BranchGroup();	
				
		// on crée une apparence avec une couleur et une couche alpha 
			Appearance app0=new Appearance();
			app0.setColoringAttributes(new ColoringAttributes(new Color3f(0.3f,0.2f,1.0f),ColoringAttributes.SHADE_GOURAUD));
			app0.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.NICEST,0.5f));//0.5f represente 50% de transparence
		
		// on crée un TG pour faire tourner notre cube
			Transform3D rotate = new Transform3D();
			rotate.rotX(Math.PI/3.0d);
			Transform3D rotate2 = new Transform3D();
			rotate2.rotY(Math.PI/3.0d);
			rotate.mul(rotate2);
		    // on crée un groupe de transformation rotate suivant la matrice de transformation rotate
        	TransformGroup objRotate = new TransformGroup(rotate);
       
		// on crée un cube qui herite de cette rotation
		objRotate.addChild(new ColorCube(0.5));// de rayon 50 cm
		
		scene.addChild(objRotate);
		
		// on crée une sphere bleu de transparence 10% devant le cube
		Transform3D trans=new Transform3D();
		trans.set(new Vector3f(0.3f, -0.3f, 1.0f));
		TransformGroup TG=new TransformGroup(trans);
		scene.addChild(TG);
		TG.addChild( new Sphere(0.3f,app0) );
		
		return scene;
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
		sphereTrans myApp=new sphereTrans();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}