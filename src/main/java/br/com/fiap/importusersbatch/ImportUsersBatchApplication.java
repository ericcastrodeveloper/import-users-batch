package br.com.fiap.importusersbatch;

import org.h2.tools.Server;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
@EnableBatchProcessing
public class ImportUsersBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportUsersBatchApplication.class, args);
	}

}
