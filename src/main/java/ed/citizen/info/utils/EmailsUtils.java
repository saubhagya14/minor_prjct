package ed.citizen.info.utils;

import java.io.File;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailsUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(File file) {
		boolean status = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,  true);
			helper.setTo("saubhagyapatra2016@gmail.com");
			helper.setSubject("Your Report.");
			helper.setText("<h3>Please download your report </h3> ", true);
			helper.addAttachment(file.getName(), file);
			
			mailSender.send(mimeMessage);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}
}
