package br.com.fiap.importusersbatch.writer;

import br.com.fiap.importusersbatch.model.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that write itens to the database
 */
public class ClienteItemWriter implements ItemWriter<Cliente> {

    private static final String INSERT_CLIENTE = "INSERT INTO TB_CLIENTE(CPF, NOME) VALUES(?,?)";

    private List<Cliente> clientes = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(List<? extends Cliente> list) throws Exception {

        for(Cliente cliente : list){

            jdbcTemplate.update(INSERT_CLIENTE, cliente.getCpf(), cliente.getNome());
        }

        clientes.addAll(list);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

}
