package com.rav.batch;

import com.rav.entity.CustomerTransaction;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<CustomerTransaction, CustomerTransaction> {

    @Override
    public CustomerTransaction process(CustomerTransaction transaction) throws Exception {
        transaction.setTransactionId(String.format("%s-%s", transaction.getCustomerId(), transaction.getOrderNo()));
        return transaction;
    }
}
