package br.com.caelum.vraptor.twirl;

import play.twirl.compiler.TemplateCompilationError;
import play.twirl.compiler.TwirlCompiler;
import play.twirl.parser.TwirlIO;

import java.io.File;

public class Compiler {
	public void compile(File source) {
		File sourceDirectory = source.getParentFile();
		File gen = new File("src/gen/scala/");
		TwirlCompiler.compile(source, sourceDirectory, gen, "html", "", TwirlIO.defaultCodec(), false, false);
	}
}
