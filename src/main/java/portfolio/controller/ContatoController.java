package portfolio.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.model.Contato;
import portfolio.service.ContatoService;

// @RestController → combina @Controller + @ResponseBody
// Significa que esta classe responde requisições HTTP
// e retorna dados JSON automaticamente
@RestController

// @RequestMapping → define o prefixo de todas as rotas desta classe
// Todas as rotas aqui começam com /api/contato
@RequestMapping("/api/contato")

// @CrossOrigin → permite que o frontend (outra origem) acesse a API
// origins = "*" libera qualquer origem (ajustaremos em produção)
@CrossOrigin(origins = "*")
public class ContatoController {

    private final ContatoService contatoService;

    // Injeção de dependência via construtor
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * POST /api/contato
     * Recebe os dados do formulário e salva no banco.
     *
     * @RequestBody → pega o JSON do corpo da requisição
     *               e converte automaticamente para objeto Contato
     * @Valid → ativa as validações que definimos no Model
     *         (@NotBlank, @Email, @Size...)
     */
    @PostMapping
    public ResponseEntity<Contato> receberContato(@Valid @RequestBody Contato contato) {
        Contato salvo = contatoService.salvar(contato);

        // ResponseEntity → permite controlar o status HTTP da resposta
        // HttpStatus.CREATED = código 201 (recurso criado com sucesso)
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}