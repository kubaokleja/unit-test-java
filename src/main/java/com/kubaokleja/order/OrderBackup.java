package com.kubaokleja.order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class OrderBackup {
	private Writer writer;
	
	public Writer getWriter() {
		return writer;
	}
	
	void createFile() throws FileNotFoundException {
		File file = new File("ordetBackup.txt");
		FileOutputStream  fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		writer = new BufferedWriter(osw);
	}
	
	void backupOrder(Order order) throws IOException {
		writer.append(order.toString());
	}
	
	void closeFile() throws IOException {
		writer.close();
	}
}
