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
        if (input.matches("[\\dA-Za-z]+ \\d+ \\d+ \\d+ \\d+")) return true;
        else {
            System.out.println("Incorrect points format.");
            return false;
        }
    }
    public boolean checkIfNumber(String input) {
        return input.matches("\\d+");
    }
    public boolean checkAddStudentCorrectness(String input) {
        if (!setUpNameAndEmail(input)) {
            System.out.println("Incorrect credentials.");
            return false;
        }
        if (isInvalidName(firstName)) {
            System.out.println("Incorrect first name.");
            return false;
        }
        else if (isInvalidName(lastName)) {
            System.out.println("Incorrect last name.");
            return false;
        }
        else if (!isValidEmail(email)) {
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
        System.arraycopy(inputArray, 1, lastNameArray, 0, inputArray.length - 2);
        lastName = String.join(" ", lastNameArray);
        return true;
    }




    private boolean isInvalidName(String input) {
        return !input.matches("[A-Za-z]+([-']?[A-Za-z]+)+( [A-Za-z]+([-']?[A-Za-z]+)+)*");
    }

    private boolean isValidEmail(String input) {
        return input.matches("[\\dA-Za-z.-]*@[\\dA-Za-z-]*\\.[\\dA-Za-z]*");
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
