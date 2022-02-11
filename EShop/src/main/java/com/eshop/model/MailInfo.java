package com.eshop.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private String[] cc;
	private String[] bcc;
	private String subject;
	private String body;
	private MultipartFile[] attachments;

	public MailInfo(String to, String subject, String body) {
		this.from = "Minh Nguyễn <testemailservlet@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
