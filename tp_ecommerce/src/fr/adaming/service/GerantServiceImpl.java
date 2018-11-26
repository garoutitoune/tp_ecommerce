package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.dao.IGerantDao;
import fr.adaming.model.Gerant;
import fr.adaming.model.Produit;

@Stateful
public class GerantServiceImpl implements IGerantService{
	
	// transformation de l'association UML en JAVA
	@EJB
	private IGerantDao gDao;
	@EJB
	private IProduitService proService;

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
	         String htmlText = "<H1>Bonjour</H1><div>"+text+" <br/> Pour accedez au site <a href="+"http://localhost:8080/tp_ecommerce/"+" target="+"_blank"+"> mistigris et ses amis lien</a> </div>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "C:\\Users\\INTI0489\\Desktop\\pdftest\\pdf.pdf");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	      

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

	@Override
	public void PDF() {
		Document doc = new Document();

		try {
			PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\INTI0489\\Desktop\\pdftest\\pdf.pdf"));
			
			doc.open();

			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			Paragraph para = new Paragraph("                     ----------------------- Liste des produits -----------------------");

			doc.add(para);

			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			

			PdfPTable table = new PdfPTable(6);

			PdfPCell c1 = new PdfPCell(new Phrase("Id du produit"));
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c1);
			PdfPCell c2 = new PdfPCell(new Phrase("designation"));
			c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c2);
			PdfPCell c3 = new PdfPCell(new Phrase("description"));
			c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c3);
			PdfPCell c4 = new PdfPCell(new Phrase("image"));
			c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c4);
			PdfPCell c5 = new PdfPCell(new Phrase("prix"));
			c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c5);
			PdfPCell c6 = new PdfPCell(new Phrase("quantite"));
			c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c6);
			
			table.setHeaderRows(1);
			
			doc.add(table);
		
			List<Produit> liste=proService.getAllProduit();
			for(Produit pro:liste) {
				table.addCell(Integer.toString(pro.getId()));
				table.addCell(pro.getDesignation());
				table.addCell(pro.getDescription());
				try {
					Image img=Image.getInstance(pro.getPhoto());
					table.addCell(img);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				table.addCell(Double.toString(pro.getPrix()));
				table.addCell(Integer.toString(pro.getQuantite()));			
			}
	        
			doc.add(table);
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
			doc.add(new Paragraph(" "));
		
			doc.close();
			
			
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
