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


	public void compileDir(File viewsFolder) {
		File[] possibleViews = viewsFolder.listFiles();
		for (File view : possibleViews) {
			if(view.isDirectory()){
				compileDir(view);
			}else {
				compile(view);			
			}
		}
	}
	
	public void compile(File view){
		System.out.println("Generating scala file "+view.getAbsolutePath());
		TwirlCompiler.compile(view, root, outputFolder, "play.twirl.api.HtmlFormat", "", TwirlIO.defaultCodec(), false, false);		
	}
	
	
	public static void main(String[] args) {
		final String viewsFolder = "src/main/twirl";
		DefaultFileChangeWatcher watcher = new DefaultFileChangeWatcher(viewsFolder,new Consumer() {
			
			@Override
			public void execute(File changed){
				File root = new File(viewsFolder);
				File outputFolder = new File("src/gen/scala");
				new Compiler(root,outputFolder).compile(changed);				
			}

		});
		watcher.work();
	}
}
