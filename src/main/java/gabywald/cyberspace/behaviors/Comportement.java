package gabywald.cyberspace.behaviors;

/**
écrit initalement par:Roswell // email:philgauthier_@hotmail.com

Les évenements détectées sont toujours "touche pressée" représenté par la constante "KeyEvent.KEY_PRESSED" 
	et la méthode "initialize()" reste inchangée. La méthode "processStimulus" détermine les actions 
	en reponse aux différents stimulis. La méthode "getAWTEvent()" permet de déterminer quel type de 
	"KeyEvent.KEY_PRESSED" quelle touche à été enfoncé et nous stoquons les évenements dans un tableau 
	d'évenements "AWTEvent events[]". On initialise une matrice à la matrice d'identité "rot" 
	(la matrice identité est un facteur neutre pour la multiplication). Puis on affecte une rotation 
	de 0.1 selon l'axe voulu a cette matrice. La matrice "rot" est maintenant prete à être multipliée 
	avec la matrice contenant l'orientation du TG. On récupere cette dernière à l'aide de la méthode 
	"getTransform(rotation)" et la stoque dans la matrice "rotation", puis on les multiplie et 
	réaffecte la matrice résultant: "rotation" à l'aide de la méthode "setTransform(rotation)" au TG.
Enfin il ne faut pas oublier de réactivé le mode d'attente d'évenements grâce à la méthode "wakeupOn(keyEvent)". 
*/

import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.AWTEvent;
import java.util.Enumeration;
import java.awt.event.KeyEvent;

/**
 * This class defines a specific and configurable Behavior. 
 * @author Gabriel Chandesris (2010-2012)
 */
public class Comportement extends Behaviour {
	
	private WakeupOnAWTEvent keyEvent	= new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
	
	public Comportement(TransformGroup currentGroup) 
		{ super(currentGroup); }
	
	public void initialize()
		{ this.wakeupOn(this.keyEvent); }
		
	@SuppressWarnings({ "rawtypes" })
	public void processStimulus(Enumeration criteria) {
		/** On récupere le tableau d'evenements enregistrés. */
		AWTEvent events[] = this.keyEvent.getAWTEvent();
		
		switch (((KeyEvent)events[0]).getKeyCode()) {
		case (KeyEvent.VK_NUMPAD1):
			/** Rotation autour de l'axe Z sens positif assignée à la touche 1 du pavé numérique */
			this.rotZ(0.1f);break;
		case (KeyEvent.VK_NUMPAD3):
			/** Rotation autour de l'axe Z sens négatif assignée à la touche 3 du pavé numérique */
			this.rotZ(-0.1f);break;
		case (KeyEvent.VK_DOWN):
		case (KeyEvent.VK_KP_DOWN):
		case (KeyEvent.VK_NUMPAD2):
			/** Rotation autour de l'axe X sens positif assignée à la touche 2 du pavé numérique */
			this.rotX(0.1f);break;
		case (KeyEvent.VK_UP):
		case (KeyEvent.VK_KP_UP):
		case (KeyEvent.VK_NUMPAD8):
			/** Rotation autour de l'axe X sens négatif assignée à la touche 8 du pavé numérique */
			this.rotX(-0.1f);break;
		case (KeyEvent.VK_RIGHT):
		case (KeyEvent.VK_KP_RIGHT):
		case (KeyEvent.VK_NUMPAD6):
			/** Rotation autour de l'axe Y sens positif assignée à la touche 6 du pavé numérique */
			this.rotY(0.1f);break;
		case (KeyEvent.VK_LEFT):
		case (KeyEvent.VK_KP_LEFT):
		case (KeyEvent.VK_NUMPAD4):
			/** Rotation autour de l'axe Y sens négatif assignée à la touche 4 du pavé numérique */
			this.rotY(-0.1f);break;
		case (KeyEvent.VK_NUMPAD5):break;
		case (KeyEvent.VK_NUMPAD7):break;
		case (KeyEvent.VK_NUMPAD9):break;
		}
		
		/** On se remet en mode d'attente des évenements de keyEvent : touche pressée ? */
		this.wakeupOn(keyEvent);
	}
}

