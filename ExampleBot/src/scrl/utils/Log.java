package scrl.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	private static Log instance = null;

	public static final boolean DEBUG = false;
	private static final boolean printer = false;
	private File outFile;
	private BufferedWriter writer;

	protected Log() {
		outFile = new File("teste1.txt");

		if (!outFile.exists()) {
			try {
				writer = new BufferedWriter(new FileWriter(outFile, true));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static Log getInstance() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}

	public static void log(String msg) {
		System.out.println(msg);
		//		getInstance().write(msg);
	}

	public void write(String msg) {
		if (DEBUG) {
			if (!outFile.isFile()) {
				try {
					outFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				writer.append(msg);
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void endGame() {
		if (printer) {
			FileUtils.policyToFile();
			FileUtils.qTableToFile();
		}
	}
}
