package gabywald.java3d.tutoriel.chap3;
/*

ecrit par:Roswell
email:philgauthier_@hotmail.com

Tout d'abord, de mani�re g�n�rale, lorsque 
    vous construisez chacun de vos TG vous devez sp�cifier s'il va ou non 
    bouger dans votre monde. Si c'est le cas vous devez lui en donner la capacit� 
    avec la méthode "setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE)". 
    Puis vous avez besoin d'un fonction du temps, c'est l� qu'intervient 
    la classe Alpha dont les param�tres du constructeur utilisés 
    sont le nombre de tours avant l'arr�t de la rotation, et la vitesse 
    de rotation. Ici nous voulons que le cube tourne ind�finiment, il faut 
    alors mettre -1 et nous r�glons la vitesse de rotation à 4000 
    qui est une fr�quence (augmenter ce nombre pour ralentir la rotation).<br>
    Nous allons faire connaissance avec l'animation la plus simple à mettre 
    en oeuvre: la rotation.
Pour faire varier la rotation du TG en fonction du Alpha que 
    nous avons d�fini, il convient d'utiliser la classe "RotationInterpolator" 
    qui lie ( par r�f�rence) l'Apha à notre TG (d'o&ugrave; 
    les arguments du constructeur ) et définit l'axe ainsi que les angles 
    maximun et minimum de la rotation. Par d�faut, la rotation se fait 
    autour de l'axe Y, l'angle minimal est 0, l'angle maximal est 2PI. Ensuite 
    il faut d�finir une zone d'infuence de cet &quot;interpolator&quot; 
    pour notre TG au del� de laquelle la rotation n'agira plus. Cette zone 
    d'influence correspond à la classe "BoundingSphere" 
    d�finie par d�faut par le contructeur par une sph�re 
    de rayon 1 (donc suffisant pour notre cube de 0.5). La méthode "setSchedulingBounds()" 
    permet de lier cette zone avec l'interpolator. Enfin il faut lier parentalement 
    le TG à l'interpolator comme d'habitude.
  
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
import com.sun.j3d.utils.geometry.ColorCube; 

// classes n�cessaires à la rotation
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;

public class rotAnim extends Frame implements WindowListener
{	    
	public rotAnim()
	{
        super("- une rotation d'un simple cube -");
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
	
	// crée un regroupement d'objets contenant un objet cube qui tourne 
	// grace au Transforme groupe objSpin
	public BranchGroup createSceneGraph()
	{
		//on crée le Bg principal
		BranchGroup objRoot=new BranchGroup();
		
		// on crée la rotation
			TransformGroup objSpin=new TransformGroup();
		
			// permet de modifier l'objet pendant l'execution
			objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
			// on crée un fonction de rotation au cours du temps
			Alpha rotationAlpha=new Alpha(-1,4000);
		
			// on crée un comportement qui va appliquer la rotation a l'objet voulu
			RotationInterpolator rotator=new RotationInterpolator(rotationAlpha,objSpin);
		
			// on d�finisst la zone sur laquelle va s'appliquer la rotation
			BoundingSphere bounds=new BoundingSphere();
			rotator.setSchedulingBounds(bounds);
			objSpin.addChild(rotator);
		
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
		rotAnim myApp=new rotAnim();
		myApp.setSize(300,300);
		myApp.setVisible(true);
	}
	
}