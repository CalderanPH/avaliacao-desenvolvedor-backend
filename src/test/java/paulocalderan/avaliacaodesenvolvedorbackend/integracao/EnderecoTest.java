package paulocalderan.avaliacaodesenvolvedorbackend.integracao;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import paulocalderan.avaliacaodesenvolvedorbackend.IntegrationTestConfig;
import paulocalderan.avaliacaodesenvolvedorbackend.domain.endereco.Endereco;
import paulocalderan.avaliacaodesenvolvedorbackend.repository.EnderecoRepository;
import paulocalderan.avaliacaodesenvolvedorbackend.utils.ResourceUtils;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EnderecoTest extends IntegrationTestConfig {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private String enderecoJson;
    private Endereco endereco;

    @Before
    public void setUp() {
        super.setUp();
        basePath = "/endereco";
        enderecoJson = ResourceUtils.getContentFromResource("/json/criarPessoa.json");
        //prepararDados();
    }

    private void prepararDados() {
        endereco = new Endereco("Albatroz", "8670-065", 10, "Arapongas/PR");
        this.endereco = enderecoRepository.save(endereco);
    }

    @Test
    public void cadastrar_status201() {
        String payload = enderecoJson
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
                .body("$", hasKey("cep"))
                .body("$", hasKey("logradouro"))
                .body("$", hasKey("numero"))
                .body("$", hasKey("cidade"))
                .body("id", notNullValue())
                .body("logradouro", is("Av. Arapongas"))
                .body("cep", is("8574-566"))
                .body("numero", is(400))
                .body("cidade", is("Arapongas/PR"))
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void alterar_status204() {
        String payload = enderecoJson
                .replace("{{logradouro}}", "Av. Arapongas")
                .replace("{{cep}}", "8574-566")
                .replace("{{numero}}", "400")
                .replace("{{cidade}}", "Arapongas/PR");
        given()
                .pathParam("id", endereco.getId().toString())
                .body(payload)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void deletar_status204() {
        given()
                .pathParam("id", endereco.getId())
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
                .pathParam("id", endereco.getId().toString())
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
                .pathParam("id", 100L)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}