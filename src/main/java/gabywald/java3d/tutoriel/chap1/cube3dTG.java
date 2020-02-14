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

//Java standart API
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
//Java 3d API
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.BranchGroup;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.Transform3D;

public class cube3dTG extends Frame implements WindowListener
{	    
	public cube3dTG()
	{
        super("- un TG pour orienter le cube -");
        this.addWindowListener(this);
        setLayout(new BorderLayout());
        // 1ere étape création du Canvas3d qui vas afficher votre univers virtuel
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center", canvas3D);
        // 2eme étape on crée notre scene (regroupement d'objet)
        BranchGroup scene = createSceneGraph();
        // on les compile pour optimiser les calcules
        scene.compile();
        
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
		
		// on crée une matrice de tranformation pour faire tourner notre cube
		Transform3D rotate = new Transform3D();
		// (X represente la horizontale orienté vers la droite,Y represente la vericale orienté vers le haut,Z pointe sur vous)
		// les rotation s'effectues en prenant l'axe choisit comme axe de rotation dans le sens trigonometrique (inverse de celui des aiguille d'une montre)
		rotate.rotX(Math.PI/3.0d);//rotation d'angle Pi/3
		
        // on crée un groupe de transformation rotate suivant la matrice de transformation rotate
        TransformGroup objRotate = new TransformGroup(rotate);
       
		// on crée un cube qui herite de cette rotation
		objRotate.addChild(new ColorCube(0.5));// de rayon 50 cm
		
		//on ajout notre objet objRotate a notre groupe d'objet ( qui ne contiends qu'un cube dans notre cas ) 
		objRoot.addChild(objRotate);
		
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
		cube3dTG myApp=new cube3dTG();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}