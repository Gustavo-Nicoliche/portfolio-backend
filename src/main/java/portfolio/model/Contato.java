package portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

// @Entity → diz ao JPA que esta classe é uma tabela no banco
@Entity
// @Table → define o nome da tabela no PostgreSQL
@Table(name = "contatos")
public class Contato {

    // @Id → esta é a chave primária da tabela
    // @GeneratedValue → o valor é gerado automaticamente (auto incremento)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank → não pode ser vazio (validação)
    // @Size → define tamanho mínimo e máximo
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(nullable = false, length = 150)
    private String email;

    @NotBlank(message = "Assunto é obrigatório")
    @Column(nullable = false, length = 50)
    private String assunto;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(min = 10, max = 1000, message = "Mensagem deve ter entre 10 e 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String mensagem;

    // @Column → configurações da coluna
    // updatable = false → esta coluna nunca é atualizada após a inserção
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // @PrePersist → executa este método automaticamente
    // ANTES de salvar o registro no banco pela primeira vez
    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    // ── Getters e Setters ──────────────────────────
    // O JPA exige getters e setters para acessar os atributos

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
}