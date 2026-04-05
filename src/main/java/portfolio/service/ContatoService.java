package portfolio.service;

import org.springframework.stereotype.Service;
import portfolio.model.Contato;
import portfolio.repository.ContatoRepository;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final EmailService emailService;

    // Injeta os dois serviços via construtor
    public ContatoService(ContatoRepository contatoRepository, EmailService emailService) {
        this.contatoRepository = contatoRepository;
        this.emailService = emailService;
    }

    public Contato salvar(Contato contato) {
        // 1. Salva no banco
        Contato salvo = contatoRepository.save(contato);

        // 2. Envia notificação por e-mail
        // (em thread separada para não atrasar a resposta ao frontend)
        new Thread(() -> emailService.enviarNotificacao(salvo)).start();

        return salvo;
    }
}