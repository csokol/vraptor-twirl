package br.com.caelum.vraptor.twirl;


import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;

public class DefaultFileChangeWatcher {
	
	private Runnable runnable;
	private WatchService watcher;

	public DefaultFileChangeWatcher(String dir, Runnable runnable) {
//		dir = dir + ".git/refs/remotes";
		this.runnable = runnable;
		try {
			Path path = Paths.get(dir);
			watcher = FileSystems.getDefault().newWatchService();
			path.register(watcher, ENTRY_MODIFY);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void work() {
		while(true) {
			try {
				watcher.take();
				runnable.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}
