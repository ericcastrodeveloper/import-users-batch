package br.com.fiap.importusersbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ImportUsersBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportUsersBatchApplication.class, args);
	}

}
