package br.com.fiap.importusersbatch.config;

import br.com.fiap.importusersbatch.mapper.ClienteFieldSetMapper;
import br.com.fiap.importusersbatch.model.Cliente;
import br.com.fiap.importusersbatch.policy.CustomRecordSeparatorPolicy;
import br.com.fiap.importusersbatch.policy.LineSeparatorPolicy;
import br.com.fiap.importusersbatch.writer.ClienteItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Class that settings a Cliente Job
 */
@Configuration
public class ClienteImportJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;

    @Value("${file.input}")
    private Resource resource;

    @Bean
    public Job job(Step step){
        return jobBuilders.get("clienteImportJob").start(step).build();
    }

    @Bean
    public Step step(ItemWriter<Cliente> itemWriter) throws Exception {
        return stepBuilders.get("clienteImportStep")
                .<Cliente, Cliente>chunk(2)
                .reader(clienteItemReader()).faultTolerant().skipPolicy(lineSeparatorPolicy())
                .writer(itemWriter)
                .allowStartIfComplete(true)
                .build();
    }


    @Bean
    public ItemWriter<Cliente> itemWriter(){
        return new ClienteItemWriter();
    }

    @Bean
    public FlatFileItemReader<Cliente> clienteItemReader() {
        FlatFileItemReader<Cliente> reader = new FlatFileItemReader<Cliente>();
        reader.setResource(new ClassPathResource(resource.getFilename()));
        reader.setRecordSeparatorPolicy(recordSeparatorPolicy());
        reader.setLineMapper(clientetLineMapper());
        reader.setLinesToSkip(1);

        return reader;
    }


    @Bean
    public DefaultLineMapper<Cliente> clientetLineMapper() {
        DefaultLineMapper<Cliente> mapper = new DefaultLineMapper<Cliente>();
        mapper.setLineTokenizer(clienteLineTokenizer());
        mapper.setFieldSetMapper(clienteFieldSetMapper());
        return mapper;
    }

    @Bean
    public FixedLengthTokenizer clienteLineTokenizer() {
        FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
        tokenizer.setColumns(new Range[] { new Range(1, 41), new Range(42, 55)});
        tokenizer.setNames(new String[] { "nome", "cpf"});
        return tokenizer;
    }

    @Bean
    public ClienteFieldSetMapper clienteFieldSetMapper() {
        return new ClienteFieldSetMapper();
    }

    @Bean
    public LineSeparatorPolicy lineSeparatorPolicy() {
        return new LineSeparatorPolicy();
    }

    @Bean
    public RecordSeparatorPolicy recordSeparatorPolicy() {
        return new CustomRecordSeparatorPolicy();
    }

}
