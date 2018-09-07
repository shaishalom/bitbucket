package com.example.demo.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.repo.AccountRepository;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final AccountRepository accountRepository;

    public AppRunner(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(".... Fetching books");
        logger.info("account-1 -->" + accountRepository.getAccountById(1L));
        logger.info("account-2 -->" + accountRepository.getAccountById(2L));
        logger.info("account-2 -->" + accountRepository.getAccountById(2L));
        logger.info("account-1 -->" + accountRepository.getAccountById(1L));
        logger.info("account-1 -->" + accountRepository.getAccountById(1L));

    }

}