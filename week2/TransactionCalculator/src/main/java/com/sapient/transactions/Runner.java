package com.sapient.transactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Runner {
	
    private ArrayList<TransactionOperation> transactionObj;
	
    public void init() throws IOException {
            // Reader data from the file
            fileReader();
            
            // Writing data to the file along with processing the transaction fee.
            processTransaction();
            
            // Program completed   
            System.out.println("Processing of the program is completed.");
            System.out.println("-------------------------------------------------------");
            
    }

    public void fileReader() throws IOException {
            String pathInputFileAbsolute = findAbsolutePath("input_transaction.csv");
            FileParser fileParserObj = new FileParser(pathInputFileAbsolute);
            // Parsing the data from the file
            transactionObj = fileParserObj.read();
    }

    public String findAbsolutePath(String pathInputFile) {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(pathInputFile).getFile());
            return file.getAbsolutePath();
    }
    
    
    public void processTransaction() {
        PrintWriter writer = null;
        try {
            // Print Writer class invoked
            writer = new PrintWriter(new File("week2\\TransactionCalculator\\src\\main\\resources\\write_data.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();

        // Giving header names to the file
        String columnNamesList = "Client Id,Transaction Type,Transaction Date,Priority,Processing Fee";
        builder.append(columnNamesList +"\n");
        
        for(TransactionOperation x : transactionObj) {
            builder.append(x.getClientId()+",");
            builder.append(x.getTransactionType()+",");
            builder.append(x.getTransactionDate()+",");
            builder.append(x.getPriority()+",");
            // processing the fee of the transaction
            builder.append(x.processTransactionFee()+",");
            builder.append('\n');
        }

        // Closing the writter stream
        writer.write(builder.toString());
        writer.close();
    }

}
