package be.vdab.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import be.vdab.entities.Filiaal;

@Component
class MailSenderImpl implements MailSender {
	private final static Logger LOGGER = Logger.getLogger(MailSenderImpl.class.getName());
	private final JavaMailSender sender;
	private final String webmaster;
	

	public JavaMailSender getSender() {
		return sender;
	}

	public String getWebmaster() {
		return webmaster;
	}

	@Autowired
	MailSenderImpl(JavaMailSender sender, @Value("${webmaster}") String webmaster) {
		this.sender = sender;
		this.webmaster = webmaster;
	}

	@Override
	public void nieuwFiliaalMail(Filiaal filiaal) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(webmaster);
			helper.setSubject("Nieuw filiaal");
			helper.setText(String.format("Filiaal <strong>%s</strong> is toegevoegd", filiaal.getNaam()), true);
			sender.send(message);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "kan mail nieuw filiaal niet versturen", ex);
			throw new RuntimeException("Kan mail nieuw filiaal niet versturen", ex);
		}
	}
}