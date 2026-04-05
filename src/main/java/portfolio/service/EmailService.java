package portfolio.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portfolio.model.Contato;

@Service
public class EmailService {

    // Injeta o valor da propriedade resend.api.key do application.properties
    @Value("${resend.api.key}")
    private String resendApiKey;

    // Injeta o e-mail de destino
    @Value("${resend.to.email}")
    private String toEmail;

    /**
     * Envia um e-mail de notificação quando um novo contato é recebido.
     * @param contato → dados do formulário preenchido pelo visitante
     */
    public void enviarNotificacao(Contato contato) {
        try {
            Resend resend = new Resend(resendApiKey);

            // Monta o corpo do e-mail em HTML
            String htmlBody = """
                <div style="font-family: sans-serif; max-width: 600px; margin: 0 auto;">
                    <h2 style="color: #1a6b4a;">📬 Novo contato no portfólio!</h2>
                    <hr style="border-color: #e4e2db;" />
                    <p><strong>Nome:</strong> %s</p>
                    <p><strong>E-mail:</strong> %s</p>
                    <p><strong>Assunto:</strong> %s</p>
                    <p><strong>Mensagem:</strong></p>
                    <blockquote style="background: #f8f7f4; padding: 16px; border-left: 4px solid #1a6b4a; border-radius: 4px;">
                        %s
                    </blockquote>
                    <hr style="border-color: #e4e2db;" />
                    <p style="color: #6b6b62; font-size: 12px;">
                        Recebido em: %s
                    </p>
                </div>
                """.formatted(
                    contato.getNome(),
                    contato.getEmail(),
                    contato.getAssunto(),
                    contato.getMensagem(),
                    contato.getCriadoEm()
            );

            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("Portfólio <onboarding@resend.dev>")
                    .to(toEmail)
                    .subject("📬 Novo contato: " + contato.getAssunto() + " - " + contato.getNome())
                    .html(htmlBody)
                    .build();

            CreateEmailResponse response = resend.emails().send(params);
            System.out.println("E-mail enviado! ID: " + response.getId());

        } catch (ResendException e) {
            // Loga o erro mas não interrompe o fluxo principal
            // O contato já foi salvo no banco mesmo se o e-mail falhar
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}