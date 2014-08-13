package br.com.caelum.vraptor.twirl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;

public class CompilerTest {

	@Test
	public void shouldCompileSimpleTemplate() throws Exception {
		Compiler compiler = new Compiler();
		File template = new File("src/test/resources/simple.scala.html");
		compiler.compile(template);

		Assert.assertTrue(new File("src/gen/scala/html/simple.template.scala").exists());
	}


} 
