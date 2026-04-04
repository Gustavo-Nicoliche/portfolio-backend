package portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfolio.model.Contato;

// @Repository → marca esta interface como componente de acesso a dados
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    // JpaRepository<Contato, Long> significa:
    // → Contato: a entidade que este repository gerencia
    // → Long: o tipo do ID da entidade
    //
    // Não precisamos escrever nada aqui!
    // O Spring Data JPA implementa tudo automaticamente.
}
