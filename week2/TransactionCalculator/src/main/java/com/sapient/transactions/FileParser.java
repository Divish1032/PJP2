package com.sapient.transactions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {

	private String pathInputFile;

	public FileParser(String path) throws IOException {
		this.pathInputFile = path;
	}

	public ArrayList<TransactionOperation> read() throws IOException{
		ArrayList<TransactionOperation> result = new ArrayList<TransactionOperation>();
		BufferedReader readerObj = null;
		try {
			readerObj = new BufferedReader(new FileReader(pathInputFile));
			String next="";
			try {
				int counter = 0;
				while ((next = readerObj.readLine()) != null) {
					if (counter != 0) {
						TransactionOperation to = new TransactionOperation();
						String[] data = next.split(",");
						to.setClientId(data[2]);
						to.setSecurityId(data[3]);
						to.setTransactionDate(data[5]);
						to.setTransactionType(data[4]);
						to.setPriority(data[7]);
						result.add(to);
					}
					counter++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
