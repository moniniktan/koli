public class Car {
	private String VIN;
	private String regNumber;
	private int year;
	private String dateOfInsurance;
	private String ownerFirstName;
	private String ownerLastName;

	public Car(String VIN, String regNumber, int year, String dateOfInsurance, String ownerFirstName, String ownerLastName) {
		this.VIN = VIN;
		this.regNumber = regNumber;
		this.year = year;
		this.dateOfInsurance = dateOfInsurance;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
	}

	public Car() {
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String VIN) {
		this.VIN = VIN;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDateOfInsurance() {
		return dateOfInsurance;
	}

	public void setDateOfInsurance(String dateOfInsurance) {
		this.dateOfInsurance = dateOfInsurance;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
}
