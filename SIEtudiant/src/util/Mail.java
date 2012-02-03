package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Mail {

	// Variables concernant celui qui envoie le mail ou serveur de messagerie
	private static final String SERVEUR_DE_MESSAGERIE = "smtp.gmail.com"; // Le smtp, ex : smtp.gmail.com
	private static final String COMPTE_UTILISATEUR = ""; // @mail
	private static final String MOT_DE_PASSE = ""; // MDP

	/*
	 * Envoie un mail à l'adresse mail passee en parametre
	 * @param adresse mail de l'utilisateur
	 * @param mot de passe lors de l'inscription sur le site
	 */
	public static void autoMail(String to, String mdp) {
		Properties props = System.getProperties();
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.addRecipients(Message.RecipientType.TO, to);
			message.setSubject("Compte utilisateur");
			message.setContent(
					"Votre compte sur Gestion Etudiant a été créé. Votre mot de passe est : "
							+ mdp, "text/html");
			Transport tr = session.getTransport("smtps");
			tr.connect(SERVEUR_DE_MESSAGERIE, COMPTE_UTILISATEUR, MOT_DE_PASSE);
			message.saveChanges();
			tr.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			System.out.println("Erreur :" + e);
		}
	}

	public static void main(String args[]) {
		Mail m = new Mail();
		m.autoMail("hanocq.alexandre@yahoo.fr", "test");
	}
}
