package com.training.cap5;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FiltruFisiere implements FilenameFilter{

	String extensie;
	public FiltruFisiere(String extensie){
		this.extensie = extensie;
	}
	@Override
	public boolean accept (File dir, String name) {
		return name.endsWith("." + extensie);
	}
}
