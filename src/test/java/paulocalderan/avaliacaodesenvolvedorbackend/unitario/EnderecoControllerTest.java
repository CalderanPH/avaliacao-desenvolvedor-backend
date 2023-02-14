package paulocalderan.avaliacaodesenvolvedorbackend.unitario;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import paulocalderan.avaliacaodesenvolvedorbackend.controller.EnderecoController;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;
import paulocalderan.avaliacaodesenvolvedorbackend.request.EnderecoRequest;
import paulocalderan.avaliacaodesenvolvedorbackend.response.EnderecoResponse;
import paulocalderan.avaliacaodesenvolvedorbackend.service.EnderecoService;
import paulocalderan.avaliacaodesenvolvedorbackend.utils.ResourceUtils;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EnderecoControllerTest extends AbstractControllerTest {

    private static final String URI = "/endereco";

    @Mock(name = "enderecoService")
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;
    private Endereco endereco;
    private String enderecoJson;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        this.enderecoController = new EnderecoController(enderecoService);
        this.registerController(this.enderecoController);

        endereco = new Endereco(1L, "Albatroz", "8670-065", 10, "Arapongas/PR");
        enderecoJson = ResourceUtils.getContentFromResource("/json/criarEndereco.json");
    }

    @Test
    public void findById() throws Exception {
        String uri = String.format("%s/%s", URI, 1L);
        EnderecoResponse enderecoResponse = new EnderecoResponse(endereco);

        when(enderecoService.getEndereco(any(Long.class))).thenReturn(enderecoResponse);

        mockMvc.perform(get(uri))
                .andExpect(jsonPath("logradouro", is("Albatroz")))
                .andExpect(jsonPath("cep", is("8670-065")))
                .andExpect(jsonPath("numero", is(10)))
                .andExpect(jsonPath("cidade", is("Arapongas/PR")))
                .andExpect(status().isOk());

        verify(enderecoService, times(1)).getEndereco(any(Long.class));
        verifyNoMoreInteractions(enderecoService);
    }

    @Test
    public void salvarComSucesso_retornando201Created() throws Exception {
        EnderecoResponse response = new EnderecoResponse(endereco);

        String payload = enderecoJson
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");

        when(enderecoService.save(any(EnderecoRequest.class))).thenReturn(response);

        mockMvc.perform(post(URI)
                        .contentType(contentType)
                        .content(payload))
                .andExpect(status().isCreated());

        verify(enderecoService, times(1)).save(any(EnderecoRequest.class));
        verifyNoMoreInteractions(enderecoService);
    }

    @Test
    public void atualizarComSucesso_retornando204() throws Exception {
        String uri = String.format("%s/%s", URI, 1L);

        String payload = enderecoJson
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");

        mockMvc.perform(put(uri)
                        .contentType(contentType)
                        .content(payload))
                .andExpect(status().isNoContent());

        verify(enderecoService, times(1)).update(any(Long.class), any(EnderecoRequest.class));
        verifyNoMoreInteractions(enderecoService);
    }

    @Test
    public void deletarComSucesso_retornando204NoContent() throws Exception {
        String uri = String.format("%s/%s", URI, 1L);

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

        verify(enderecoService, times(1)).delete(any(Long.class));
        verifyNoMoreInteractions(enderecoService);
    }

}
