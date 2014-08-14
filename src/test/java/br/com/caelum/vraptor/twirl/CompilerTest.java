package br.com.caelum.vraptor.twirl;

import java.io.File;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CompilerTest {
	
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void shouldCompileSimpleTemplate() throws Exception {		
		File root = new File("src/test/twirl");
		File outputFolder = temporaryFolder.newFolder();
		Compiler compiler = new Compiler(root,outputFolder);
		compiler.compile(root);
		Assert.assertTrue(new File(outputFolder, "html/simple.template.scala").exists());
	}


} 
