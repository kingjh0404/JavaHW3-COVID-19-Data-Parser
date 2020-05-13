package edu.handong.csee.java.hw3;

public class Analyzer {

	private String[] analyzerData;
	private String[] columnnsOfData;
	private String[] taskData;
	private String[] countries;
	private int numberCountries;
	private int numberAllPatient;
	private int numberCountyPatient;

	// constructor
	Analyzer(String[] inputData) {
		analyzerData = inputData;
		setColumnnsOfData();
	}

	public void setColumnnsOfData() {
		columnnsOfData = analyzerData[0].split(",");
	}

	public String[] getColumnnsOfData() {
		return columnnsOfData;
	}

	public void setNumberofCountries() {
		countries = new String[analyzerData.length]; // initialize, country for each row
		numberCountries = 0; // initialize
		int i = 0; // index
		// countries split
		for (String task : analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)");
			countries[i] = taskData[1];
			i++;
		}
		// duplication countries check
		duplication: for (int j = 0; j < countries.length; j++) {
			for (int k = 0; k < j; k++) {
				if (countries[j].equals(countries[k])) {
					continue duplication; // duplicate
				}
			}
			// System.out.println(countries[j]);
			numberCountries++; // not duplicate
		}
		numberCountries = numberCountries - 1; // first string (Country/Region) is not country name, information of column.
	}

	public int getNumberOfCountries() {
		setNumberofCountries();
		return numberCountries;
	}

	public void setNumberOfAllPatients() {
		for (String task : analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)");
			if (taskData[0].equals(getColumnnsOfData()[0])) // data information row ignore
				continue;
			else
				numberAllPatient = numberAllPatient + Util.convertStringToInt(taskData[taskData.length-1]);
		}
	}

	public int getNumberOfAllPatients() {
		setNumberOfAllPatients();
		return numberAllPatient;
	}

	public void setNumberOfPatientsOfACountry(String country) {
		int row = 0; // country index
		for(String task : countries) {
			task = task.replace("\"", ""); // example "Korea, South"
			if(task.equals(country)) break;
			row++;
		}
		taskData = analyzerData[row].split(",(?=([^\"]|\"[^\"]*\")*$)");
		numberCountyPatient = Util.convertStringToInt(taskData[taskData.length-1]);
	}
	
	public int getNumberOfPatientsOfACountry(String country) {
		setNumberOfPatientsOfACountry(country);
		return numberCountyPatient;
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