package tracker.util;

public class TextRecognition {
    private String firstName = "";
    private String lastName = "";
    private String email = "";


    public boolean checkStatistics(String input) {
        if (input.matches("java")) return true;
        else if (input.matches("dsa")) return true;
        else if (input.matches("databases")) return true;
        else if (input.matches("spring")) return true;
        else {
            System.out.println("Unknown course.");
            return false;
        }
    }
    public boolean checkAddPoints(String input) {
        if (input.matches("[0-9A-Za-z]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+")) return true;
        else {
            System.out.println("Incorrect points format.");
            return false;
        }
    }
    public boolean checkIfNumber(String input) {
        if (input.matches("[0-9]+")) return true;
        else return false;
    }
    public boolean checkAddStudentCorrectness(String input) {
        if (!setUpNameAndEmail(input)) {
            System.out.println("Incorrect credentials.");
            return false;
        }
        if (!checkName(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }
        else if (!checkName(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }
        else if (!checkEmail(email)) {
            System.out.println("Incorrect email.");
            return false;
        }
        else {
            return true;
        }
    }

    private boolean setUpNameAndEmail(String input) {
        String[] inputArray = input.split(" ");
        if (inputArray.length < 3) return false;
        firstName = inputArray[0];
        email = inputArray[inputArray.length - 1];
        String[] lastNameArray = new String[inputArray.length - 2];
        for (int i = 0; i < inputArray.length - 2; i++) {
            lastNameArray[i] = inputArray[i + 1];
        }
        lastName = String.join(" ", lastNameArray);
        return true;
    }




    private boolean checkName(String input) {
        if (input.matches("[A-Za-z]+([-']?[A-Za-z]+)+( [A-Za-z]+([-']?[A-Za-z]+)+)*")) return true;
        //if (input.matches("([A-Z]([-'][A-Z])?[a-z]*([-'][A-Z])?[a-z]*)( [A-Z]?([-'][A-Z])?[a-z]*([-'][A-Z])?[a-z]*)*")) return true;
        else return false;
    }

    private boolean checkEmail(String input) {
        if (input.matches("[0-9A-Za-z.-]*@[0-9A-Za-z-]*\\.[0-9A-Za-z]*")) return true;
        else return false;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
