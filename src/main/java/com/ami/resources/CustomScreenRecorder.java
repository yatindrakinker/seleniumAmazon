package com.ami.resources;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

public class CustomScreenRecorder extends ScreenRecorder {

	private String fileName;
	private File currentFile;

	public CustomScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
			Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder)
			throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {
		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory.");
		}

		currentFile = getFileWithUniqueName(movieFolder.getAbsolutePath() + File.separator + fileName + "."
				+ Registry.getInstance().getExtension(fileFormat));
		return currentFile;
	}

	private File getFileWithUniqueName(String fileName) {
		String extension = "";
		String name = "";

		int idxOfDot = fileName.lastIndexOf('.'); // Get the last index of . to separate extension
		extension = fileName.substring(idxOfDot + 1);
		name = fileName.substring(0, idxOfDot);

		Path path = Paths.get(fileName);
		int counter = 1;
		while (Files.exists(path)) {
			fileName = name + "-" + counter + "." + extension;
			path = Paths.get(fileName);
			counter++;
		}
		return new File(fileName);
	}

	public void startRecording(String fileName, boolean captureMouse) throws IOException {
		this.fileName = fileName;
		start();
	}

	public void stopRecording(boolean keepFile) throws IOException {
		stop();
		if (!keepFile) {
			deleteRecording();
		}
	}

	private void deleteRecording() {
		boolean deleted = false;
		try {
			if (currentFile.exists()) {
				deleted = currentFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (deleted)
			currentFile = null;
		else
			System.out.println("Could not delete the screen-record!");
	}

}
