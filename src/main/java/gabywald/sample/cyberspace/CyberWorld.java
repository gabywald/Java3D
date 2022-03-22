package gabywald.sample.cyberspace;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.j3d.utils.universe.SimpleUniverse;

import gabywald.cyberspace.components.User;
import gabywald.cyberspace.objects.Element;

@SuppressWarnings("serial")
public class CyberWorld extends JFrame 
						implements ActionListener {
	private static CyberWorld instance;
	private JPanel southPanel;
	private JButton addOrganism;
	private CyberMove mvt;
	/** 3D view in center Panel. */
	private CyberGrid basicGrid;
	/** Relative position of the grid to the screen / view. */
	private static float Xmov = -0.0f, Ymov = -5.0f, Zmov = -35.0f;
	/** All views of WorldCases's. */
	private BranchGroup table[][];
	
	private CyberWorld() {
		this.setLocation(50, 50);
		this.setSize(1024, 760); 
		this.getContentPane().setLayout(new BorderLayout());
		/** this.pack(); */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.initSouthPanel();

		this.table = new BranchGroup[20][20];
		for (int i = 0 ; i < this.table.length ; i++) {
			for (int j = 0 ; j < this.table[i].length ; j++) {
				this.table[i][j] = new BranchGroup();
				this.table[i][j].setCapability(BranchGroup.ALLOW_DETACH);
				this.table[i][j].setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
				this.table[i][j].setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
			}
		}
		
		/** Building element's in main scene (grid and axis). */
		this.basicGrid		= 
					new CyberGrid(	-20/2, 20/2,
									-20/2, 20/2);
		this.basicGrid.setCapability(BranchGroup.ALLOW_DETACH);
		this.basicGrid.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		this.basicGrid.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

		/** Defines 3D Scene. */
		Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		
		/** Behaviour definition for the 3D view, and adding it to the scene element. */
		this.mvt = new CyberMove(simpleU);
		this.basicGrid.addChild(this.mvt.getBehaviour());
		
		/** Adding the grid to the view. */
		simpleU.addBranchGraph(this.basicGrid);
		Element.translateTo(this.basicGrid.getGrid(), CyberWorld.Xmov, 
							CyberWorld.Ymov, CyberWorld.Zmov);
		
		/** Finishes the JFrame. */
		// this.add(this.southPanel, BorderLayout.SOUTH);
		
		this.add(canvas3D, BorderLayout.CENTER);
		this.setTitle("BioSilico Modelization View");
		this.setVisible(true);
	}
	
	public static CyberWorld getInstance() {
		if (CyberWorld.instance == null) { CyberWorld.instance = new CyberWorld(); }
		return CyberWorld.instance;
	}
	
	private void initSouthPanel() {
		this.southPanel		= new JPanel();
		this.addOrganism	= new JButton("Add Organism");

		List<String> tmpX = new ArrayList<String>();
		List<String> tmpY = new ArrayList<String>();
		for (int i = 0 ; i < 20 ; i++)
			{ tmpX.add(i+""); }
		for (int i = 0 ; i < 20 ; i++)
			{ tmpY.add(i+""); }
		
		this.addOrganism.addActionListener(this);
		
		this.southPanel.add(this.addOrganism);
	}
	
	private void reloadTable() {
		// this.basicGrid.removeAllChildren();
		for (int i = 0 ; i < this.table.length ; i++) {
			for (int j = 0 ; j < this.table[i].length ; j++) {
				if (this.basicGrid.indexOfChild(this.table[i][j]) >= 0) 
					{ this.basicGrid.removeChild(this.table[i][j]); }
				this.basicGrid.addChild(this.table[i][j]);
			}
		}
	}
	
	public void addAtPosition(int posX,int posZ,Element child) {
		if ( (posX >= 0) && (posZ >= 0) ) {
			if ( (posX < this.table.length) 
					&& (posZ < this.table[posX].length) ) {
				int numElts = this.table[posX][posZ].numChildren();
				float modX = 0.0f,modY = 0.0f,modZ = 0.0f;
				float defDistance = Element.EDGE_SIZE*2;
				// modZ += numElts*0.1f + 0.1f;
				modX += numElts*defDistance-numElts/10;
				modZ += numElts/10*defDistance*5;
				child.setPosition(posX+CyberWorld.Xmov+modX,
								  0.0f+CyberWorld.Ymov+modY,
								  posZ+CyberWorld.Zmov+modZ);
				BranchGroup tmp = new BranchGroup();
				tmp.addChild(child);
				this.table[posX][posZ].addChild(tmp);
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if (source.equals(this.addOrganism)) {
			int posX = 0;
			int posZ = 0;
			User bolo = new User(posX + Xmov, 0.0f + Ymov, posZ + Zmov);
			this.addAtPosition(posX, posZ, bolo);
			this.reloadTable();
		}
		// this.mvt.initialize();
	}
	
}
