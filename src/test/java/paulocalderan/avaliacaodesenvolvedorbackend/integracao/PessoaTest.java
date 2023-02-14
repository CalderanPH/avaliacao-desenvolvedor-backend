package paulocalderan.avaliacaodesenvolvedorbackend.integracao;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import paulocalderan.avaliacaodesenvolvedorbackend.IntegrationTestConfig;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.pessoa.Pessoa;
import paulocalderan.avaliacaodesenvolvedorbackend.repository.PessoaRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.utils.ResourceUtils;

import java.time.LocalDate;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PessoaTest extends IntegrationTestConfig {

    @Autowired
    private PessoaRepository pessoaRepository;

    private String pessoaJson;
    private Pessoa pessoa;
    private Endereco endereco;

    @Before
    public void setUp() {
        super.setUp();
        basePath = "/pessoa";
        pessoaJson = ResourceUtils.getContentFromResource("/json/criarPessoa.json");
        prepararDados();
    }

    private void prepararDados() {
        Endereco endereco = new Endereco("Albatroz", "8670-065", 10, "Arapongas/PR");
        Pessoa pessoa = new Pessoa("Paulo Henrique", LocalDate.of(1995, 8, 20), endereco);
        this.pessoa = pessoaRepository.save(pessoa);
    }

    @Test
    public void cadastrar_status201() {
        String payload = pessoaJson
                .replace("{{nome}}", "Paulo Henrique Calderan")
                .replace("{{dataDeNascimento}}", "1995-08-20")
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");

        Response response = given()
                .body(payload)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post();
        response.then()
                .statusCode(HttpStatus.CREATED.value());

        String id = getIdHeaderLocation(response);
        given()
                .pathParam("id", id)
                .when()
                .get("{/id}")
                .then()
                .body("size()", is(8))
                .body("$", hasKey("id"))
                .body("$", hasKey("nome"))
                .body("$", hasKey("dataDeNascimento"))
                .body("endereco", hasKey("logradouro"))
                .body("endereco", hasKey("cep"))
                .body("endereco", hasKey("numero"))
                .body("endereco", hasKey("cidade"))
                .body("id", notNullValue())
                .body("nome", is("Paulo Henrique Calderan"))
                .body("dataDeNascimento", is("1995-09-21"))
                .body("endereco.logradouro", is("Av. Arapongas"))
                .body("endereco.cep", is("8574-566"))
                .body("endereco.numero", is(400))
                .body("endereco.cidade", is("Arapongas/PR"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void alterar_status204() {
        String payload = pessoaJson
                .replace("{{nome}}", "Paulo Henrique Calderan")
                .replace("{{dataDeNascimento}}", "1995-08-20")
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");
        given()
                .pathParam("/pessoa/{id}", pessoa.getId().toString())
                .body(payload)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deletar_status204() {
        given()
                .pathParam("id", pessoa.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void findById_status200() {
        given()
                .pathParam("id", pessoa.getId().toString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void findById_naoEncontrado() {
        given()
                .pathParam("id", -1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}