package models;

public class Student extends Model {
    public String firstName;
    public String lastName;
    public double midExam;
    public double finalExam;
    public double total;
    public String estimation;


    public Student(int id, String firstName, String lastName, double midExam, double finalExam) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.total = midExam + finalExam;
        this.estimation = totalToEstimation(total);
    }


    public static String totalToEstimation(double total) {
        if (total >= 90) {
            return "Excellent";
        } else if (total >= 80) {
            return "Very Good";
        } else if (total >= 70) {
            return "Good";
        } else if (total >= 60) {
            return "Pass";
        } else {
            return "Failed";
        }
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public double getMidExam() {
        return midExam;
    }


    public void setMidExam(double midExam) {
        this.midExam = midExam;
    }


    public double getFinalExam() {
        return finalExam;
    }


    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }


    public double getTotal() {
        return total;
    }


    public void setTotal(double total) {
        this.total = total;
    }


    public String getEstimation() {
        return estimation;
    }


    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    
    
}
