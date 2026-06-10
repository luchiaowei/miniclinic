package tw.edu.fju.miniclinic.model;

public class Patient {
    private String chartNo;
    private String name;
    private String gender;
    private String birthDate;
    private String phone;

    public Patient() {}

    public Patient(String chartNo, String name, String gender, String birthDate, String phone) {
        this.chartNo = chartNo;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public String getChartNo() { return chartNo; }
    public void setChartNo(String chartNo) { this.chartNo = chartNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
