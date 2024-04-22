package com.backend.ebankbackend.services;

import com.backend.ebankbackend.entities.BankAccount;
import com.backend.ebankbackend.entities.CurrentAccount;
import com.backend.ebankbackend.entities.SavingAccount;
import com.backend.ebankbackend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("52107a70-5592-448d-8152-0412d0db9de5").orElse(null);
        if (bankAccount != null) {
            System.out.println("******************************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("OverDraft=>" + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }

}
