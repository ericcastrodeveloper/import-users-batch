package br.com.fiap.importusersbatch.config;

import br.com.fiap.importusersbatch.model.Cliente;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ClienteImportJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Autowired
    private ClienteItemReader clienteItemReader;

    @Bean
    public Job job(Step step){
        return jobBuilders.get("customerImportJob").start(step).build();
    }

    @Bean
    public Step step(ItemWriter<Cliente> itemWriter){
        return stepBuilders.get("customerImportStep")
                .<Cliente, Cliente>chunk(2)
                .reader(reader())
                .writer(itemWriter)
                .build();
    }

    FlatFileItemReader reader(){
        return clienteItemReader.itemReader();
    }

    @Bean
    public ItemWriter<Cliente> itemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Cliente>()
                .beanMapped()
                .dataSource(dataSource)
                .sql("INSERT INTO TB_CLIENTE(" +
                        "NOME, CPF, EMAIL, NUMERO, RUA, CIDADE, ESTADO" +
                        ") VALUES (" +
                        ":nome, :cpf, :email, :numero, :rua, :cidade, :estado" +
                        ")")
                .build();
    }

}
