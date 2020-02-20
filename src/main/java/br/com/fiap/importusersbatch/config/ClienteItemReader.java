package br.com.fiap.importusersbatch.config;

import br.com.fiap.importusersbatch.model.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ClienteItemReader {

    @Value("${file.input}")
    private Resource resource;


    public FlatFileItemReader<Cliente> itemReader(){
        return new FlatFileItemReaderBuilder<Cliente>()
                .delimited().delimiter(";").names("nome", "cpf", "email", "numero", "rua", "cidade", "estado")
                .resource(resource).targetType(Cliente.class).name("Flat Item Reader Clientes").build();
    }
}
