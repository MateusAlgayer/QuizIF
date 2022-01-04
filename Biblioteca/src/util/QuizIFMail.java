/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
 */
package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class QuizIFMail {
    
  public static boolean EnviaEmail(String pEmail,String pCodigo, int id){
        try {
            // Define email e senha do emissor da mensagem
            String myAddress = "jorgejorge1702@outlook.com";
            String myPassword = "j1j2s3g4";
            // Define o email do destinatário da mensagem
            String receiver = pEmail;

            // Configura as propriedades do Outlook
            Properties prop = setProperties();
        
            // Cria uma nova seção, autenticada com email e senha
            Session session = getSession(prop, myAddress, myPassword);

            String subject = "Teste de e-mail";
            String bodyMsg = "<p>Código de teste:"+pCodigo+"</p>";
            Message message = createMessage(session, myAddress, receiver, subject, bodyMsg);
            Transport.send(message);
            Metodos.GravaLog("EML", id, "Mensagem Enviada!");
            return true;
        } catch (MessagingException e) {
            Metodos.GravaLogErro("Erro!", id, e.toString());
            return false;
        }
    }

    private static Properties setProperties(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.office365.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return prop;
    }

    private static Session getSession(Properties prop, String myAddress, String myPassword){
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAddress, myPassword);
            }
        });
        return session;
    }

    private static Message createMessage(Session session, String myAddress, String receiver, String subject, String bodyMsg) throws MessagingException {
        // Define o emissor da mensagem
        InternetAddress me = new InternetAddress(myAddress);
        // Cria uma mensagem com a seção e define o emissor
        Message message = new MimeMessage(session);
        message.setFrom(me);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        // Define o assunto da mensagem
        message.setSubject(subject);

        // Define o corpo da mensagem
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        String mimeType = "text/html;charset=UTF-8";
        mimeBodyPart.setContent(bodyMsg, mimeType);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        return message;
    }
}
