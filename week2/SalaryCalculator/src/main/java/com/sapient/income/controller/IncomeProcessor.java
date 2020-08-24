package com.sapient.income.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.sapient.income.model.SalaryModel;


public class IncomeProcessor {

	private String inputFilePath;
	private String outputFilePath;
	private ArrayList<SalaryModel> salaryCollection;

	public IncomeProcessor(String path, String outputPath) {
		this.inputFilePath = path;
		this.outputFilePath = outputPath;
		salaryCollection = new ArrayList<SalaryModel>();
	}


	public void init() throws IOException {
		BufferedReader readerObj = null;
		try {
			readerObj = new BufferedReader(new FileReader(this.inputFilePath));
			String next="";
			try {
				int counter = 0;
				while ((next = readerObj.readLine()) != null) {
					if (counter != 0) {
						String[] row = next.split(",");
						SalaryModel salary = new SalaryModel();
						salary.setCity(row[0]); salary.setCountry(row[1]);
						salary.setGender(row[2]); salary.setCurrency(row[3]);
						salary.setAmount(row[4]); salaryCollection.add(salary);
					}
					counter++;
				}
				readerObj.close();
				Collections.sort(salaryCollection, new SortingCustomized());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void customizeOutput() {
        PrintWriter writer = null;
        try {
            // Print Writer class invoked
            writer = new PrintWriter(new File(this.outputFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        // Giving header names to the file
        String columnNamesList = "Country,City,Gender,Average,Income In USD";
        builder.append(columnNamesList +"\n");

        for(SalaryModel x : salaryCollection) {
            builder.append(x.getCountry()+",");
            builder.append(x.getCity()+",");
            builder.append(x.getGender()+",");
            builder.append(x.currencyConversion());
            builder.append('\n');
        }

        // Closing the writter stream
        writer.write(builder.toString());
        writer.close();
	}
	

	// custom made sorting algorithm with respect to the problem statement
	class SortingCustomized implements Comparator<SalaryModel> {
		public int compare(SalaryModel temp1, SalaryModel temp2) {
			if (temp1.getCountry().equals(temp2.getCountry()))
				if (temp1.getCity().equals(temp2.getCity()))
					if (temp1.getGender().equals(temp2.getGender())) 
						if (Double.parseDouble(temp1.currencyConversion()) > Double.parseDouble(temp2.currencyConversion()))
							return 1;
						else
							return -1;
					else
						if (temp1.getGender().equals("F"))
							return 1;
						else
							return -1;
				else
					return temp1.getCity().compareTo(temp2.getCity());
			else
				return temp1.getCountry().compareTo(temp2.getCountry());
		}
	}

}