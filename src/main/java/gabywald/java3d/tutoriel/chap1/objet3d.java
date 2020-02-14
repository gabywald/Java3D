package gabywald.java3d.tutoriel.chap1;
/*
ecrit par:Roswell
email:philgauthier_@hotmail.com

Maintenant que l'on sait manipuler des objets, nous allons d�couvrir 
les diff�rentes primitives que nous offre le java3D ainsi que le contr�le 
basic de leurs apparences. Les apparences correspondent a la classe Appareance, 
pour d�finir une couleur nous devons utilser la classe ColoringAttributes, 
sp�cifier sa couleur et son mode de rendu (FASTEST, NICEST, SHADE_FLAT,ou SHADE_GOURAUD ).
Dans les primitives la liaison (par r�f�rence ) entre g�ometrie et apparence 
est faite dans le constructeur, vous n'aurez donc pas encore �; vous en soucier. 
En effet, jusqu'a pr�sent nous utilisions la classe ColorCube() qui avait d�ja une apparence 
pr�configur�e.
*/

// classes Java standard
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
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;

public class objet3d extends Frame implements WindowListener
{	    
	public objet3d()
	{
        super("- 4 primitives aux apparences simples -");
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
		
		//------------ debut creation des apparences -----------------
			// on crée une apparence de couleur bleue
			Appearance app_bleu = new Appearance();
			ColoringAttributes bleu=new ColoringAttributes();
			bleu.setColor(0.1f,0.1f,1.0f);
			// on precise le rendu
			bleu.setShadeModel(ColoringAttributes.NICEST);
			app_bleu.setColoringAttributes(bleu);
			
			// on crée une apparence de couleur roug
			Appearance app_rouge = new Appearance();
			ColoringAttributes rouge=new ColoringAttributes();
			rouge.setColor(1.0f,0.1f,0.1f);
			// on precise le rendu
			rouge.setShadeModel(ColoringAttributes.NICEST);
			app_rouge.setColoringAttributes(rouge);	
			
			// on crée une apparence de couleur vert
			Appearance app_vert = new Appearance();
			ColoringAttributes vert=new ColoringAttributes();
			vert.setColor(0.1f,1.0f,0.2f);
			// on precise le rendu
			vert.setShadeModel(ColoringAttributes.NICEST);
			app_vert.setColoringAttributes(vert);	
			
			// on crée une apparence de couleur orange
			Appearance app_orang = new Appearance();
			ColoringAttributes orang=new ColoringAttributes();
			orang.setColor(0.8f,0.4f,0.2f);
			// on precise le rendu
			orang.setShadeModel(ColoringAttributes.NICEST);
			app_orang.setColoringAttributes(orang);		
			
		//------------ fin creation des apparences -----------------
		
		//------------ début de creation de la sphere ------------
			// on crée un vecteur de translation 30 cm suivant les Y (dans l'autre sens)
			Transform3D translate1 = new Transform3D();
	    	translate1.set(new Vector3f(0.4f, 0.4f, 0.0f));
			
        	// on crée un groupe de transformation TG1 suivant la matrice de transformation translate1
        	TransformGroup TG1 = new TransformGroup(translate1);

			// on crée un cube qui herite de cette rotation
			TG1.addChild(new Sphere(0.3f,app_bleu));// de rayon 20 cm peinte en bleu
			
			objRoot.addChild(TG1);
		//------------ fin de creation de la sphere --------------	
		
		//------------ début de creation du cone ------------
			// on crée un vecteur de translation 30 cm suivant les Y (dans l'autre sens)
			Transform3D translate2 = new Transform3D();
	    	translate2.set(new Vector3f(-0.4f, -0.4f, 0.0f));
			
        	// on crée un groupe de transformation TG2 suivant la matrice de transformation translate2
        	TransformGroup TG2 = new TransformGroup(translate2);			
			
			// on crée un cone qui herite de cette translation
			TG2.addChild(new Cone(0.2f, 0.4f,Cone.ENABLE_APPEARANCE_MODIFY,app_rouge));// de rayon 20 cm de hauteur 40cm peint en rouge
			
			objRoot.addChild(TG2);
		//------------ fin de creation du cone --------------	
		
		//------------ début de creation d'une boite ------------
			// on crée un vecteur de translation 30 cm suivant les Y (dans l'autre sens)
			Transform3D translate3 = new Transform3D();
	    	translate3.set(new Vector3f(-0.4f, 0.4f, 0.0f));
	    	
			// on crée une matrice de tranformation pour faire tourner notre cube
			Transform3D rotate = new Transform3D();
			rotate.rotZ(2*Math.PI/3.0d);//rotation d'angle 2Pi/3
			
			// on combine les deux transformations: translation puis rotation
			translate3.mul(rotate);
			//rotate.mul(translate3);
			
        	// on crée un groupe de transformation TG3 suivant la matrice de transformation translate3
        	TransformGroup TG3 = new TransformGroup(translate3);			
			
			// on crée un cube qui herite de cette rotation
			TG3.addChild(new Box(0.2f, 0.4f,0.1f,app_orang));// de largeur 40 cm, de hauteur 80cm, de profondeur 20 cm peint en vert
			
			objRoot.addChild(TG3);
		//------------ fin de creation d'une boite --------------	
		
		//------------ début de creation d'un texte en 3d------------
			
			// on definit la font 3d dont on va se servir, attention les font sont surdimentionn� 
			Font3D my_font=new Font3D(new Font("Helvetica",Font.PLAIN,1),new FontExtrusion());
			// on utilise cette font3D pour créer un texte en 3d
			Text3D textGeom=new Text3D(my_font,new String("Coucou"),new Point3f(3.0f,-3.0f,0.0f));
			textGeom.setAlignment(Text3D.ALIGN_CENTER);
			// finalement on transforme ce txt en shape3d avec l'apparence app_vert affichable par java3d
			//Shape3D text=new Shape3D(textGeom,app_vert); ou bien
			Shape3D text=new Shape3D();
			text.setGeometry(textGeom);
        	text.setAppearance(app_vert);
        	
        	// on réduit la taille du texte car beaucoup trop grande par d�faut
        	Transform3D scale3D = new Transform3D();
        	scale3D.setScale(0.2f);
        	TransformGroup scale = new TransformGroup(scale3D);
	    	
			objRoot.addChild(scale);
			scale.addChild(text);
		//------------ fin de creation d'un texte en 3d--------------	
		
		// met le fond en blanc
        Background background = new Background(0.2f, 0.2f, 0.2f);
        background.setApplicationBounds(new BoundingSphere());
        objRoot.addChild(background);
		
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
		objet3d myApp=new objet3d();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}