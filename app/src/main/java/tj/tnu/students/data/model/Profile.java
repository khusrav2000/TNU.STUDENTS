package tj.tnu.students.data.model;

public class Profile {
    String recordbookNumber;
    String fullName;
    String faculty;
    String specialty;
    String codeSpecialty;
    String trainingForm;
    String trainingLevel;
    int course;
    String group;
    String yearUniversityEntrance;
    int trainingPeriod;

    public Profile(String recordbookNumber, String fullName, String faculty, String specialty, String codeSpecialty, String trainingForm, String trainingLevel, int course, String group, String yearUniversityEntrance, int trainingPeriod) {
        this.recordbookNumber = recordbookNumber;
        this.fullName = fullName;
        this.faculty = faculty;
        this.specialty = specialty;
        this.codeSpecialty = codeSpecialty;
        this.trainingForm = trainingForm;
        this.trainingLevel = trainingLevel;
        this.course = course;
        this.group = group;
        this.yearUniversityEntrance = yearUniversityEntrance;
        this.trainingPeriod = trainingPeriod;
    }



    public String getRecordbookNumber() {
        return recordbookNumber;
    }

    public void setRecordbookNumber(String recordbookNumber) {
        this.recordbookNumber = recordbookNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCodeSpecialty() {
        return codeSpecialty;
    }

    public void setCodeSpecialty(String codeSpecialty) {
        this.codeSpecialty = codeSpecialty;
    }

    public String getTrainingForm() {
        return trainingForm;
    }

    public void setTrainingForm(String trainingForm) {
        this.trainingForm = trainingForm;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getYearUniversityEntrance() {
        return yearUniversityEntrance;
    }

    public void setYearUniversityEntrance(String yearUniversityEntrance) {
        this.yearUniversityEntrance = yearUniversityEntrance;
    }

    public int getTrainingPeriod() {
        return trainingPeriod;
    }

    public void setTrainingPeriod(int trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "recordbookNumber='" + recordbookNumber + '\'' +
                ", fullName='" + fullName + '\'' +
                ", faculty='" + faculty + '\'' +
                ", specialty='" + specialty + '\'' +
                ", codeSpecialty='" + codeSpecialty + '\'' +
                ", trainingForm='" + trainingForm + '\'' +
                ", trainingLevel='" + trainingLevel + '\'' +
                ", course=" + course +
                ", group='" + group + '\'' +
                ", yearUniversityEntrance='" + yearUniversityEntrance + '\'' +
                ", trainingPeriod=" + trainingPeriod +
                '}';
    }

}
