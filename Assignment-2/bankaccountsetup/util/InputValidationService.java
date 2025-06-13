package com.bankaccountsetup.util;

public class InputValidationService {

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    public static boolean isValidAadhaar(String aadhaar) {
        return aadhaar != null && aadhaar.matches("\\d{12}");
    }

    public static boolean isValidPan(String pan) {
        return pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]");
    }

    public static boolean isValidAccountType(String accountType) {
        return accountType != null && 
               (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Current"));
    }
    
    public static boolean isValidCustomerForAccountType(String accountType, String customerType) {
        if (accountType.equalsIgnoreCase("Savings")) {
            return customerType.equalsIgnoreCase("Individual");
        } else if (accountType.equalsIgnoreCase("Current")) {
            return customerType.equalsIgnoreCase("Individual") || customerType.equalsIgnoreCase("Business");
        }
        return false;
    }

    public static boolean isBusinessProofValid(String accountType, String customerType, String businessProof) {
        if (accountType.equalsIgnoreCase("Current") && customerType.equalsIgnoreCase("Business")) {
            return businessProof != null && !businessProof.trim().isEmpty();
        }
        return true;
    }

    
    public static boolean isValidDeposit(int deposit, String accountType) {
        int minDeposit = accountType.equalsIgnoreCase("Savings") ? 1000 :
                         accountType.equalsIgnoreCase("Current") ? 5000 : -1;

        if (minDeposit == -1) return false; // optional: assume valid input only

        if (deposit >= minDeposit) return true;

        System.out.println("Minimum deposit for " + accountType + " Account is ₹" + minDeposit + ".");
        return false;
    }
    
    
    public static boolean areRequiredFieldsFilled(String field1, String field2, String field3,
                                                  String field4, String field5, String field6) {
        return !(field1 == null || field1.trim().isEmpty() ||
                 field2 == null || field2.trim().isEmpty() ||
                 field3 == null || field3.trim().isEmpty() ||
                 field4 == null || field4.trim().isEmpty() ||
                 field5 == null || field5.trim().isEmpty() ||
                 field6 == null || field6.trim().isEmpty());
    }

  
}

