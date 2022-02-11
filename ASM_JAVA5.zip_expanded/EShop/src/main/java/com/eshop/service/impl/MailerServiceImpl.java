package com.eshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.model.MailInfo;
import com.eshop.service.MailerService;

@Service
public class MailerServiceImpl implements MailerService {
	private List<MailInfo> list = new ArrayList<>();
	
	@Autowired
	JavaMailSender sender;

	@Autowired
	ServletContext app;

	@Override
	public void send(MailInfo mail) throws MessagingException {	
		// Tạo message
		MimeMessage message = sender.createMimeMessage();
		// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(), true);
		
		String from = mail.getFrom() + "<name@example.com>";
		if(from != null && !from.isBlank()) {
			helper.setFrom(from);
			helper.setReplyTo(from);
		}
		String to = mail.getTo();
		if (to != null && !to.isBlank()) {
			helper.setTo(to);
		}
		String[] cc = mail.getCc();
		if (cc != null && cc.length > 0) {
			helper.setCc(cc);
		}
		String[] bcc = mail.getBcc();
		if (bcc != null && bcc.length > 0) {
			helper.setBcc(bcc);
		}
		
		// Nếu có file đính kèm thì thêm vào message
		MultipartFile[] attachments = mail.getAttachments();
		if (attachments != null && attachments.length > 0) {
			for (MultipartFile attachment : attachments) {
				if (attachment.isEmpty()) {
					continue;
				}
				FileSystemResource file = new FileSystemResource(app.getRealPath("/uploads/" + attachment.getOriginalFilename()));
				helper.addAttachment(attachment.getOriginalFilename(), file);
			}
		}
		
		// Gửi message đến SMTP server
		sender.send(message);
	}

	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		this.send(new MailInfo(to, subject, body));
	}

	@Override
	public void queue(MailInfo mail) {
		list.add(mail);
	}

	@Override
	public void queue(String to, String subject, String body) {
		queue(new MailInfo(to, subject, body));
	}

	@Scheduled(fixedDelay = 3000)
	public void run() throws Exception {
		while (!list.isEmpty()) {
			MailInfo mail = list.remove(0);
			this.send(mail);
		}
	}

}