package com.sapient.income;

import java.io.IOException;

import com.sapient.income.controller.IncomeProcessor;

public class App {

	public static void main(String[] args) throws IOException {
		System.out.print("Process is going to start.");
		System.out.print("-------------------------------------------");

		String input = "week2\\SalaryCalculator\\src\\main\\resources\\sample_input.csv";
		String output = "week2\\SalaryCalculator\\src\\main\\resources\\generated_output.csv";
		IncomeProcessor query = new IncomeProcessor(input, output);
		query.init();
		query.customizeOutput();
	}
}