package gabywald.java3d.tutoriel.chap1;
/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Nous allons maintenant voir comment, à l'aide des TransformGroup, 
on peut positionner deux objets l'un par rapport à l'autre. Nous d�taillons 
ici les 3 utilisations principale de la classe Transform3D qui permet de faire 
des translations, des rotations ( voir le schéma rep�re ), et 
de retailler vos objet. Faites attention, une instance d'un objet ( Shape3D ) 
ne peut pas �tre ref�renc�e par deux TransformGroup 
ou BranchGroup. Par contre plusieurs Shape3D peuvent tres bien avoir le même 
BG ou TG. Il faut créer une instance de la Shape3D pour chaque TransformGroup 
ou BranchGroup.
*/

// classes Java standart
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
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.ColorCube;

// attention vous ne pouvez pas utiliser un même objet (shape3d), comme notre cube, dans plusieur Group de transformation
// il faut créer 2 entité distinct comme nous l'avons fait

public class cube3d2 extends Frame implements WindowListener
{	    
	public cube3d2()
	{
        super("- 2 TG pour placer 2 cubes -");
        this.addWindowListener(this);
        setLayout(new BorderLayout());
        // 1ere étape création du Canvas3d qui vas afficher votre univers virtuel avec une config pr� etablie
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center", canvas3D);
        // 2eme étape on crée notre scene (regroupement d'objet)
        BranchGroup scene = createSceneGraph();
        // on les compile pour optimiser les calcules
        //scene.compile();
        
        // 3eme étape on creer l'univer qui va contenir notre scene 3d
        // utilise simpleUniverse qui simplifie le code (il crée un environemment minimal simple)
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        // on met le plan de projection en arriere par rapport a l'origine
        simpleU.getViewingPlatform().setNominalViewingTransform();
        // on place la scene dans l'univers simpleU
        simpleU.addBranchGraph(scene);
    }	
	
	//crée un regroupement d'objet contenant un objet cube
	public BranchGroup createSceneGraph()
	{
		//on crée le Bg principal
		BranchGroup objRoot=new BranchGroup();
		
		//------------ début de creation du premier cube  ------------
			// on crée un vecteur de translation 30 cm suivant les Y
			Transform3D translate1 = new Transform3D();
	    	translate1.set(new Vector3f(0.4f, 0.4f, 0.0f));
	    	
			// on crée une matrice de tranformation pour faire tourner notre cube
			Transform3D rotate = new Transform3D();
			//(X represente la horizontal orienté vers le droite, Y represente la vertical orienté vers le haut, Z pointe sur vous)
			rotate.rotX(Math.PI/3.0d);//rotation d'angle Pi/3
			
			// on combine les deux transformations: translation puis rotation
			//translate1.mul(rotate);
			translate1.mul(rotate);
        	// on crée un groupe de transformation rotate suivant la matrice de transformation translate1
        	TransformGroup TG1 = new TransformGroup(translate1);
       
			// on crée un cube qui herite de cette rotation
			TG1.addChild(new ColorCube(0.3));// de rayon 30 cm
			
			objRoot.addChild(TG1);
		//------------ fin de creation du premier cube  ------------
       
		
		//------------ début de creation du deuxieme cube  ------------
			// on crée un vecteur de translation 30 cm suivant les Y (dans l'autre sens)
			Transform3D translate2 = new Transform3D();
	    	translate2.set(new Vector3f(-0.4f, -0.4f, 0.0f));
	    	
			// on crée une matrice de tranformation pour faire tourner notre cube
			Transform3D rotate2 = new Transform3D();
			rotate2.rotZ(Math.PI/3.0d);//rotation d'angle Pi/3
			
			// on combine les deux transformations: translation puis rotation
			translate2.mul(rotate2);
			
			// on réduit la taille du cube par 2 (on la multiplie par 0.5)
			translate2.setScale(0.5f);
			
        	// on crée un groupe de transformation rotate suivant la matrice de transformation translate1
        	TransformGroup TG2 = new TransformGroup(translate2);
       
			// on crée un cube qui herite de cette rotation
			TG2.addChild(new ColorCube(0.3));// de rayon 20 cm
			
			objRoot.addChild(TG2);
		//------------ fin de creation du deuxieme cube  ------------		
		
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
		cube3d2 myApp=new cube3d2();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}