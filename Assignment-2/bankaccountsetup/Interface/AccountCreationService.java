package com.bankaccountsetup.Interface;

import com.bankaccountsetup.Exception.*;

public interface AccountCreationService {
	    String openAccount() throws  InvalidAccountException;
}