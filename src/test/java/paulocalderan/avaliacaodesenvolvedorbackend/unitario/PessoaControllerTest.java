package paulocalderan.avaliacaodesenvolvedorbackend.unitario;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import paulocalderan.avaliacaodesenvolvedorbackend.controller.PessoaController;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;
import paulocalderan.avaliacaodesenvolvedorbackend.request.PessoaRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.PessoaResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.service.PessoaService;
import paulocalderan.avaliacaodesenvolvedorbackend.utils.ResourceUtils;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PessoaControllerTest extends AbstractControllerTest {

    private static final String URI = "/pessoa";

    @Mock(name = "pessoaService")
    private PessoaService pessoaServiceMock;

    @InjectMocks
    private PessoaController pessoaController;
    private Pessoa pessoa;
    private Endereco endereco;
    private String pessoaJson;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        this.pessoaController = new PessoaController(pessoaServiceMock);
        this.registerController(this.pessoaController);


        endereco = new Endereco(1L, "Albatroz", "8670-065", 10, "Arapongas/PR");
        pessoa = new Pessoa(2L, "Paulo Henrique", LocalDate.of(1995, 9, 21), endereco);
        pessoaJson = ResourceUtils.getContentFromResource("/json/criarPessoa.json");
    }

    @Test
    public void findById() throws Exception {
        String uri = String.format("%s/%s", URI, 2L);
        PessoaResponse pessoaResponse = new PessoaResponse(pessoa);

        when(pessoaServiceMock.getPerson(any(Long.class))).thenReturn(pessoaResponse);

        mockMvc.perform(get(uri))
                .andExpect(jsonPath("nome", is("Paulo Henrique")))
                .andExpect(jsonPath("dataDeNascimento", is("1995-09-21")))
                .andExpect(status().isOk());

        verify(pessoaServiceMock, times(1)).getPerson(any(Long.class));
        verifyNoMoreInteractions(pessoaServiceMock);
    }

    @Test
    public void salvarComSucesso_retornando201Created() throws Exception {
        PessoaResponse response = new PessoaResponse();

        String payload = pessoaJson
                .replace("{{nome}}", "Paulo Henrique Calderan")
                .replace("{{dataDeNascimento}}", "1995-08-20")
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");

        when(pessoaServiceMock.save(any(PessoaRequest.class))).thenReturn(response);

        mockMvc.perform(post(URI)
                        .contentType(contentType)
                        .content(payload))
                .andExpect(status().isCreated());

        verify(pessoaServiceMock, times(1)).save(any(PessoaRequest.class));
        verifyNoMoreInteractions(pessoaServiceMock);
    }

    @Test
    public void atualizarComSucesso_retornando204() throws Exception {
        String uri = String.format("%s/%s", URI, 2L);

        String payload = pessoaJson
                .replace("{{nome}}", "Paulo Henrique Calderan")
                .replace("{{dataDeNascimento}}", "1995-08-20")
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");

        mockMvc.perform(put(uri)
                        .contentType(contentType)
                        .content(payload))
                .andExpect(status().isNoContent());

        verify(pessoaServiceMock, times(1)).update(any(Long.class), any(PessoaRequest.class));
        verifyNoMoreInteractions(pessoaServiceMock);
    }

    @Test
    public void deletarComSucesso_retornando204NoContent() throws Exception {
        String uri = String.format("%s/%s", URI, 2L);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

        verify(pessoaServiceMock, times(1)).delete(any(Long.class));
        verifyNoMoreInteractions(pessoaServiceMock);
    }

}
