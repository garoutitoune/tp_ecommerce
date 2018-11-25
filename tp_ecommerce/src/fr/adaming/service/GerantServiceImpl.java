package fr.adaming.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import fr.adaming.dao.IGerantDao;
import fr.adaming.model.Gerant;

@Stateful
public class GerantServiceImpl implements IGerantService{
	
	// transformation de l'association UML en JAVA
	@EJB
	private IGerantDao gDao;

	@Override
	public Gerant isExist(Gerant gIn) {
		return gDao.isEsist(gIn);
	}

	@Override
	public void mailAjoutCl(String toMail, String sujet, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("proxibanque17@gmail.com","proxibanque17+20");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("aaaa7@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toMail));
			message.setSubject(sujet);
			message.setText(text);
			// test
			  // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H1>Bonjour</H1><div>"+text+" <a href="+"http://localhost:8080/tp_ecommerce/"+" target="+"_blank"+">mistigris et ses amis lien</a> </div><img src=\"cid:image\">";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "C:\\Users\\INTI0489\\Desktop\\formation\\WorkSpaces\\03_WorkSpace_JSF\\Projet_proxibanque\\WebContent\\resources\\images\\logo.png");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
