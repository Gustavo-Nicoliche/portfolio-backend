package portfolio.service;

import org.springframework.stereotype.Service;
import portfolio.model.Contato;
import portfolio.repository.ContatoRepository;

// @Service → marca esta classe como componente de serviço
// O Spring a gerencia automaticamente (injeção de dependência)
@Service
public class ContatoService {

    // Injeção de dependência:
    // O Spring injeta automaticamente uma instância do Repository
    // Não precisamos fazer "new ContatoRepository()" manualmente
    private final ContatoRepository contatoRepository;

    // Injeção via construtor (forma recomendada)
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    /**
     * Salva um novo contato no banco de dados.
     * @param contato → objeto recebido do Controller
     * @return o contato salvo (com ID e criadoEm preenchidos)
     */
    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }
}