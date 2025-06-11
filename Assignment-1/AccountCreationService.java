package com.bankaccountsetup;

interface AccountCreationService {
    boolean validateAccountDetails();
    String  createAccount();
    void displayUserDetails();
}
