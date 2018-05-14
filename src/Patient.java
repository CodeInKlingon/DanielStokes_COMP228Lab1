import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Patient {
    static int patientIDCount = 1;
    private int patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    public Patient(){
        patientId = patientIDCount++;
        firstName = "John";
        lastName = "Smith";
        address = "941 Progress Ave.";
        city = "Scarborough";
        province = "Ontario";
        postalCode = "M1G 3T8";
    }

    //Constructor that takes in the arguments for all patient details
    public Patient( String fName, String lName, String address, String city,String province, String postalCode){
        patientId = patientIDCount++;
        firstName = fName;
        lastName = lName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String toString() {
        return "Name: '" + this.firstName + " " + this.lastName + "| Patient Number: " + patientId + "| Address: " + address + ", " + city + ", " + province + ", " + postalCode;
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

    public int getPatientId() {
        return patientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }



}

class Driver{

    public static void main( String[] args){

        String fname = PromptInput("Whats your first name fam");
        String lname = PromptInput("Whats your last name fam");
        String address = PromptInput("Please enter your street address:");
        String city = PromptInput("Please enter the city:");
        String province = PromptInput("Please enter the province:");
        String postal = PromptInput("Please enter the postal code:");

        Patient newPatient = new Patient(fname, lname, address, city, province, postal);

        JOptionPane.showMessageDialog (null,"New patient Created:    " + newPatient);


    }

    //method to prompt for input and repeat if input was empty
    static String PromptInput(String message){
        String s;
        do{
            System.out.println("check input.");
            s = JOptionPane.showInputDialog (null,message);
            System.out.println("Input was " + s);
        }while (s.isEmpty());

        return s;

    }

}

