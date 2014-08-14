package br.com.caelum.vraptor.twirl;

import java.io.File;

public interface Consumer {

	public void execute(File changed);
}
