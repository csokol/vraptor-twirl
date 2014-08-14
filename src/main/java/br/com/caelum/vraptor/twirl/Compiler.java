package br.com.caelum.vraptor.twirl;

import java.io.File;

import play.twirl.compiler.TwirlCompiler;
import play.twirl.parser.TwirlIO;

public class Compiler {
	private File root;
	private File outputFolder;


	public Compiler(File root, File outputFolder) {
		this.root = root;
		this.outputFolder = outputFolder;
	}


	public void compile(File viewsFolder) {
		File[] possibleViews = viewsFolder.listFiles();
		for (File view : possibleViews) {
			if(view.isDirectory()){
				compile(view);
			}else {
				TwirlCompiler.compile(view, root, outputFolder, "html", "", TwirlIO.defaultCodec(), false, false);							
			}
		}
	}
	
	
	public static void main(String[] args) {
		File root = new File("src/main/twirl");
		File outputFolder = new File("src/gen/scala");
		new Compiler(root,outputFolder).compile(root);
	}
}
