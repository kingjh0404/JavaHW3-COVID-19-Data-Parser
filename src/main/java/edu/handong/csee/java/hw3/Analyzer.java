package edu.handong.csee.java.hw3;

public class Analyzer {
	
	private String[] analyzerData;
	private String[] columnnsOfData;
	private String[] taskData;
	private String[] countries;
	private int numberCountries;
	
	Analyzer(String[] inputData) {
		analyzerData = inputData;
		countries = new String[analyzerData.length];
		numberCountries = 0;
		setNumberofCountries();
	}
	
	public void setNumberofCountries() {
		int i = 0; // index
		// countries split
		for(String task:analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)");
			countries[i] = taskData[1];
			i++;
		}
		// duplication countries check
		duplication:
		for(int j=0; j<countries.length; j++) {
			for(int k=0; k<j; k++) {
				if(countries[j].equals(countries[k])) {
					 continue duplication;
				}
			}
			// System.out.println(countries[j]);
			numberCountries++;
		}
		numberCountries = numberCountries - 1; // first string (Country/Region) is not country name, information of column.
	}
	
	public int getNumberOfCountries() {
		return numberCountries;
	}
	
	public int getNumberOfAllPatients() {
		return 1;
	}
	
	public int getNumberOfPatientsOfACountry(String country) {
		return 1;
	}
	
	public int getNumberOfPatientsFromASpecifiedDate(String fromDate) {
		return 1;
	}
	
	public int getNumberOfPatientsBeforeASpecifiedDate(String specifiedDate) {
		return 1;
	}
	
	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2) {
		return 1;
	}
	
}