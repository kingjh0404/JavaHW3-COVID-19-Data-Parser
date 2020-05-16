package edu.handong.csee.java.hw3;

public class Analyzer {

	private String[] analyzerData;
	private String[] columnnsOfData;
	private String[] taskData;
	private String[] countries;
	private int numberCountries;
	private int numberAllPatient;
	private int numberCountyPatient;
	private int numberSpecifiedDate;
	private int numberBeforeDate;
	private int numberBetweenDate;

	// constructor
	public Analyzer(String[] inputData) {
		analyzerData = inputData; // original data sets
		numberCountries = 0;
		numberAllPatient = 0;
		numberCountyPatient = 0;
		numberSpecifiedDate = 0;
		numberBeforeDate = 0;
		numberBetweenDate = 0;
		setColumnnsOfData(); // get first row data
	}

	public void setColumnnsOfData() {
		columnnsOfData = analyzerData[0].split(",");
	}

	public String[] getColumnnsOfData() {
		return columnnsOfData;
	}

	public void setNumberofCountries() {
		countries = new String[analyzerData.length]; // initialize, Country/Region column
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
		numberCountries = numberCountries - 1; // first string (Country/Region) is not country name, information of
												// column.
	}

	public int getNumberOfCountries() {
		setNumberofCountries();
		return numberCountries;
	}

	public void setNumberOfAllPatients() {
		for (String task : analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)"); // data split
			if (taskData[0].equals(getColumnnsOfData()[0])) // data information row ignore
				continue;
			else // last index is total number of confirmed patients on each county.
				numberAllPatient = numberAllPatient + Util.convertStringToInt(taskData[taskData.length - 1]);
		}
	}

	public int getNumberOfAllPatients() {
		setNumberOfAllPatients();
		return numberAllPatient;
	}

	public void setNumberOfPatientsOfACountry(String country) {
		int row = 0; // country index
		for (String task : countries) {
			task = task.replace("\"", ""); // example \"Korea, South\" -> Korea, South
			if (task.equals(country)) {
				taskData = analyzerData[row].split(",(?=([^\"]|\"[^\"]*\")*$)");
				numberCountyPatient = numberCountyPatient + Util.convertStringToInt(taskData[taskData.length - 1]);
			}
			row++;
		}
	}

	public int getNumberOfPatientsOfACountry(String country) {
		setNumberOfPatientsOfACountry(country);
		return numberCountyPatient;
	}

	public void setNumberOfPatientsFromASpecifiedDate(String fromDate) {
		int index = 0;
		for (String task : columnnsOfData) {
			if (task.equals(fromDate))
				break;
			index++;
		} // index is from date columns
		for (String task : analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)");
			if (taskData[0].equals(getColumnnsOfData()[0])) // data information row ignore
				continue;
			if (index < 5) { // index 4 is first date
				numberSpecifiedDate = numberSpecifiedDate + Util.convertStringToInt(taskData[taskData.length - 1]);
			} else {
				numberSpecifiedDate = numberSpecifiedDate + Util.convertStringToInt(taskData[taskData.length - 1])
						- Util.convertStringToInt(taskData[index - 1]);
			}
		}
	}

	public int getNumberOfPatientsFromASpecifiedDate(String fromDate) {
		setNumberOfPatientsFromASpecifiedDate(fromDate);
		return numberSpecifiedDate;
	}

	public void setNumberOfPatientsBeforeASpecifiedDate(String specifiedDate) {
		int index = 0;
		for (String task : columnnsOfData) {
			if (task.equals(specifiedDate))
				break;
			index++;
		}
		if (index < 5) { // index 4 is first date
			numberBeforeDate = 0;
			return;
		}
		for (String task : analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)");
			if (taskData[0].equals(getColumnnsOfData()[0])) // data information row ignore
				continue;
			if (Util.convertStringToInt(taskData[index - 1]) > Util.convertStringToInt(taskData[index])) { // decrease
																											// cases
				numberBeforeDate = numberBeforeDate + Util.convertStringToInt(taskData[index]);
			} else
				numberBeforeDate = numberBeforeDate + Util.convertStringToInt(taskData[index - 1]);
		}
	}

	public int getNumberOfPatientsBeforeASpecifiedDate(String specifiedDate) {
		setNumberOfPatientsBeforeASpecifiedDate(specifiedDate);
		return numberBeforeDate;
	}

	public void setNumberOfPatientsBetweenTwoDates(String date1, String date2) {
		int index1 = 0;
		int index2 = 0;
		for (String task : columnnsOfData) {
			if (task.equals(date1))
				break;
			index1++;
		}
		for (String task : columnnsOfData) {
			if (task.equals(date2))
				break;
			index2++;
		}
		for (String task : analyzerData) {
			taskData = task.split(",(?=([^\"]|\"[^\"]*\")*$)");
			if (taskData[0].equals(getColumnnsOfData()[0])) // data information row ignore
				continue;
			if (index1 > 4) {
				numberBetweenDate = numberBetweenDate
						+ (Util.convertStringToInt(taskData[index1]) - Util.convertStringToInt(taskData[index1 - 1]));
				numberBetweenDate = numberBetweenDate
						+ (Util.convertStringToInt(taskData[index2]) - Util.convertStringToInt(taskData[index1]));
			} else // index 4 is first date
				numberBetweenDate = numberBetweenDate + Util.convertStringToInt(taskData[index2]);
		}
	}

	public int getNumberOfPatientsBetweenTwoDates(String date1, String date2) {
		setNumberOfPatientsBetweenTwoDates(date1, date2);
		return numberBetweenDate;
	}

}