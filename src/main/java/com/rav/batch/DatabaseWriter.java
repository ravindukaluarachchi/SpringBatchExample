package com.rav.batch;

import com.rav.entity.CustomerTransaction;
import com.rav.repository.CustomerTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseWriter implements ItemWriter<CustomerTransaction> {

    Logger logger = LoggerFactory.getLogger(DatabaseWriter.class);
    @Autowired
    private CustomerTransactionRepository repository;

    @Override
    public void write(List<? extends CustomerTransaction> list) throws Exception {
        list.forEach(ct -> {
            repository.save(ct);
            logger.info(String.format("saved %s" ,ct));
        });
    }
}
