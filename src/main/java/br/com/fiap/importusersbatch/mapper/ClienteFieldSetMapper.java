package br.com.fiap.importusersbatch.mapper;

import br.com.fiap.importusersbatch.model.Cliente;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

/**
 * Class that map Cliente fields
 */
public class ClienteFieldSetMapper implements FieldSetMapper<Cliente> {
    @Override
    public Cliente mapFieldSet(FieldSet fieldSet) {
        Cliente cliente = new Cliente();
        cliente.setNome(fieldSet.readString("nome"));
        cliente.setCpf(fieldSet.readString("cpf"));
        cliente.setCpf(tratarCPF(cliente.getCpf()));

        return cliente;
    }

    private String tratarCPF(String cpf) {
        cpf = cpf.replaceAll(" ", "").replaceAll("-", "");
        return cpf;

    }
}
