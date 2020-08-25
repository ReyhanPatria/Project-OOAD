package example.view;

import java.io.File;

import example.Main;

public interface ViewPanel {
	public final File src = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
	public final String srcFilePath = src.getAbsolutePath();
}
