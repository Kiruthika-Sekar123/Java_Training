package com.bankaccountsetup.Interface;

import com.bankaccountsetup.Exception.*;

public interface AmountWithdrawService {
	 String withdraw(double amount) throws InsufficientFundsException;
}
