package br.com.fiap.importusersbatch;

import br.com.fiap.importusersbatch.writer.ClienteItemWriter;
import br.com.fiap.importusersbatch.model.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = { ImportUsersBatchApplication.class })
@TestPropertySource(properties = {
        "file.input=lista_alunos_teste.txt",
})
@RunWith(SpringJUnit4ClassRunner.class)
public class ImportClienteJobTest {

    @Value("${file.input}")
    private Resource resource;


    @Autowired
    protected Job job;

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected ClienteItemWriter writer;

    @Test
    public void existingServiceJob() throws Exception {
        jobLauncher.run(job, new JobParameters());
        checkCliente(writer.getClientes(), new String[] {
                "309556410011",
                "861083316026",
                "149477850035",
                "120915450034",
                "371195025562",
                "278165024057",
                "337265916529",
                "337265916529",
                "399170420044"});
    }

    protected Cliente createCliente(String cpf, String nome) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        return cliente;
    }


    public void hasCliente(List<Cliente> clientes, String clienteCpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(clienteCpf))
                return;
        }
        Assert.fail("Cliente with id " + clienteCpf + " is expected.");

//        Assert.fail("Product with id " + productId + " is expected.");
    }


    public void checkCliente(List<Cliente> clientes, String[] clientesCpf) {
        Assertions.assertThat(clientes.size()).isEqualTo(8);
        for (String ClienteId : clientesCpf) {
            hasCliente(clientes, ClienteId);
        }
    }
}
