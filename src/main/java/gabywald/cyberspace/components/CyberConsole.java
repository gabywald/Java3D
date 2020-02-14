package gabywald.cyberspace.components;

import java.awt.Color;

import gabywald.cyberspace.objects.AppearanceCollection;
import gabywald.cyberspace.objects.Element;
import gabywald.cyberspace.objects.ElementPlane;
import gabywald.cyberspace.objects.ElementText;

/**
 * This class defines a front textual view to put upon 3D view. 
 * @author Gabriel Chandesris (2010)
 */
public class CyberConsole extends Element {
	public static final int DEFAULT_NB_LINES	= 32;
	public static final int DEFAULT_FONT_SIZE	= 6;
	public static final Color DEFAULT_COLOR		= AppearanceCollection.getLightBlue();
	public static final float DEFAULT_XPOS		= +0.92f;
	public static final float DEFAULT_YPOS		= +0.51f;
	public static final float DEFAULT_ZPOS		= +0.00f;
	
	private int index;
	/** To show Speed. */
	private ElementText frontText;
	/** Lines of text console. */
	private ElementText[] lignes;
	/** Prompt // commands. */
	private ElementText prompt, console;
	/** Background of texts. */
	private ElementPlane frontLignes, frontPrompt, frontConsole;
	
	/** Default constructor. */
	public CyberConsole() { super();this.init(); }

	protected void init() {
		/** Un texte à l'écran pour le user, en haut à gauche. */
		this.frontText = new ElementText("Speed : ");
		this.frontText.setPosition(-CyberConsole.DEFAULT_XPOS, 
									CyberConsole.DEFAULT_YPOS, 
									CyberConsole.DEFAULT_ZPOS);
		
		/** Ajout des lignes d'affichage, sur le coté droit. */
		this.lignes = new ElementText[CyberConsole.DEFAULT_NB_LINES];
		for (int i = 0 ; i < this.lignes.length ; i++) {
			this.lignes[i] = new ElementText("Welcome to cyberspace !! ;-) "+i, 
											 CyberConsole.DEFAULT_COLOR);
			this.lignes[i].setFontSize(CyberConsole.DEFAULT_FONT_SIZE);
			this.lignes[i].setPosition(CyberConsole.DEFAULT_XPOS / 2.25f, 
									   CyberConsole.DEFAULT_YPOS
									   		-0.03f
									   		-(i*CyberConsole.DEFAULT_FONT_SIZE
									   				*ElementText.DEFAULT_SCALE), 
									   CyberConsole.DEFAULT_ZPOS);
			this.addChild(this.lignes[i]);
		}
		this.index = this.lignes.length;
		
		/** Ajout du prompt, en bas à gauche. */
		this.prompt = new ElementText("Prompt : ", CyberConsole.DEFAULT_COLOR);
		this.prompt.setFontSize(CyberConsole.DEFAULT_FONT_SIZE);
		this.prompt.setPosition(-CyberConsole.DEFAULT_XPOS, -
								CyberConsole.DEFAULT_YPOS, 
								CyberConsole.DEFAULT_ZPOS);
		
		
		/** Ajout de zone de saisie. */
		this.console = new ElementText("", CyberConsole.DEFAULT_COLOR);
		this.console.setFontSize(CyberConsole.DEFAULT_FONT_SIZE);
		this.console.setPosition(-0.6f, 
								 -CyberConsole.DEFAULT_YPOS, 
								  CyberConsole.DEFAULT_ZPOS);
		
		this.addChild(this.frontText);
		this.addChild(this.prompt);
		this.addChild(this.console);
		
		/** ********************************************** */ 
		/** ********************************************** */ 
		/** Ajout du fond derrière les lignes d'affichage. */
		this.frontLignes	= new ElementPlane(+0.670f, +0.02f, -0.01f, 0.30f, 0.50f);
		this.frontPrompt	= new ElementPlane(-0.800f, -0.50f, -0.01f, 0.15f, 0.02f);
		this.frontConsole	= new ElementPlane(+0.170f, -0.50f, -0.01f, 0.80f, 0.02f);
		
		this.addChild(this.frontLignes);
		this.addChild(this.frontPrompt);
		this.addChild(this.frontConsole);
	}
	
	/** To make lines go up. */
	public void upLines() {
		for (int i = 1 ; i < this.lignes.length ; i++) 
			{ this.lignes[i-1].setString(this.lignes[i].getString()); }
		this.lignes[this.lignes.length-1].setString("");
	}
	
	/**
	 * To add a line of text in Front. 
	 * @param text (String)
	 */
	public void addString(String text) {
		if (this.index >= this.lignes.length) 
			{ this.upLines(); } 
		else { this.index++; }
		this.lignes[this.index-1].setString(text);
	}
	
	/**
	 * To add a char in command. 
	 * @param c (char)
	 */
	public void addChar(char c) {
		String tmp = this.console.getString();
		this.console.setString(tmp+c);
	}
	
	/** Removing last char in command. */
	public void remLastChar() {
		String tmp = this.console.getString();
		if (tmp.length() > 0) 
			{ this.console.setString(tmp.substring(0, tmp.length()-1)); }
	}
	
	public void emptyConsole() { this.console.setString(""); }
	
	public String getConsole() { return this.console.getString(); }
	
	public ElementText getFrontText() { return this.frontText; }

}
