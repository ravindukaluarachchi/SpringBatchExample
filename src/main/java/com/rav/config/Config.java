package com.rav.config;

import com.rav.entity.CustomerTransaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
@EnableBatchProcessing
public class Config {
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<CustomerTransaction> itemReader,
                   ItemProcessor<CustomerTransaction, CustomerTransaction> itemProcessor,
                   ItemWriter<CustomerTransaction> itemWriter) {

        Step step = stepBuilderFactory.get("CusTnx-file-load")
                .<CustomerTransaction, CustomerTransaction>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("CusTnx-load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();


    }

    @Bean
    public FlatFileItemReader<CustomerTransaction> flatFileItemReader(@Value("${inputFile}") Resource resource) {
        FlatFileItemReader<CustomerTransaction> reader = new FlatFileItemReader<>();
        reader.setResource(resource);
        reader.setName("CSV-Reader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }

    @Bean
    public LineMapper<CustomerTransaction> lineMapper() {
        DefaultLineMapper<CustomerTransaction> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        tokenizer.setNames(new String[]{"customerId",
                "orderNo",
                "item",
                "quantity"});

        BeanWrapperFieldSetMapper<CustomerTransaction> fieldSetMapper
                = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CustomerTransaction.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(tokenizer);
        return lineMapper;
    }
}
