package br.com.caelum.vraptor.twirl;


import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.util.List;

public class DefaultFileChangeWatcher {
	
	private Consumer runnable;
	private WatchService watcher;

	public DefaultFileChangeWatcher(String dir, Consumer runnable) {
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
				WatchKey key = watcher.take();
				Path dir = (Path) key.watchable();
				
				List<WatchEvent<?>> events = key.pollEvents();
				for (WatchEvent<?> watchEvent : events) {
					Path path = (Path) watchEvent.context();
					System.out.println(dir.resolve(path).toFile().getAbsolutePath());
					runnable.execute(dir.resolve(path).toFile());
				}
				key.reset();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}
