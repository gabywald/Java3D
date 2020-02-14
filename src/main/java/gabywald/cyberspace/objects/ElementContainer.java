package gabywald.cyberspace.objects;

import javax.media.j3d.Appearance;
import javax.media.j3d.Node;

/**
 * A default container of Elements and SubElements to be transform in the same group. 
 * @author Gabriel Chandesris (2010)
 */
public class ElementContainer extends Element {
	protected void init() { ; }
	protected void init(Appearance current) { ; }
	public void addChild (Node elt) { super.addChild(elt); }
}
