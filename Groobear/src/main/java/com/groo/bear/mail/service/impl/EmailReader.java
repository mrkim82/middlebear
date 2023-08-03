package com.groo.bear.mail.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.groo.bear.mail.service.MailService;
import com.groo.bear.mail.service.MailVO;

public class EmailReader {
    private String saveDirectory;

    /**
     * 첨부파일이 저장될 위치 설정
     * 
     * @param dir
     */
    public void setSaveDirectory(String dir) {
        this.saveDirectory = dir;
    }

    @Autowired
    MailService mailService;
    
    /**
     * 개인 메일 서버에 접근하여 지정한 기간 내에 모든 메일 가져오기
     * 
     * @param userName
     * @param password
     * @param startDate
     * @param endDate
     * @throws MessagingException
     */
    public List<MailVO> receiveMailAttachedFile(String userName, String password, Date startDate, Date endDate) throws MessagingException {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "pop3");
        List<MailVO> mailList = new ArrayList<MailVO>(); 
        
        try {
            String host = "3.34.167.122"; // 메일 서버 호스트 주소 ec2 명시적IP
            int port = 110; // pop3 서버의 기본 포트

            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("pop3");
            store.connect(host, port, userName, password);
            // 받은편지함을 INBOX 라고 한다.
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, startDate);
            SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, endDate);
            SearchTerm andTerm = new AndTerm(olderThan, newerThan);
            String receiver = "";
            List<String> referrerList = new ArrayList<String>();
            String referrer = "";
            String referrer2 = "";
            String referrer3 = "";
            
            // 검색한 기간에 해당하는 메일 읽어오기
            Message[] arrayMessages = inbox.getMessages();//.search(andTerm);
            System.out.println("arrayMessages = "+arrayMessages.length);
            for (int i = arrayMessages.length-1; i >= 0; i--) {
            	MailVO mailVO = new MailVO();
                Message msg = arrayMessages[i];
                System.out.println("msg = "+msg.getContent());
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                // 메일 내용 변수에 담기
                receiver = printRecipientsAndTo(msg);
                referrerList = printRecipientsAndCc(msg);
                String subject = msg.getSubject();
                String sentDate = msg.getSentDate().toString();
                //String receivedDate = msg.getReceivedDate().toString();
                String contentType = msg.getContentType();
                String messageContent = msg.getContent().toString();
                String attachFiles = "";
                // 첨부파일
                if (contentType.contains("multipart")) {
                    Multipart multiPart = (Multipart) msg.getContent();
                    int numberOfParts = multiPart.getCount();
                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            // 첨부파일 있을 경우 지정 폴더로 저장
                            String fileName = part.getFileName();
                            attachFiles += fileName + ", ";
                            part.saveFile(saveDirectory + File.separator + fileName);
                        } else {
                            // 메일 내용 저장
                            messageContent = part.getContent().toString();
                        }
                    }
                    if (attachFiles.length() > 1) {
                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                    }
                } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
                    Object content = msg.getContent();
                    if (content != null) {
                        messageContent = content.toString();
                    }
                    if(from.indexOf("@") >=0) {
                    	from =from.substring(0,from.indexOf("@"));
        				}
    				if(receiver.indexOf("@")>=0) {
    					receiver = receiver.substring(0,receiver.indexOf("@"));
    				}
                    
                    mailVO.setTitle(subject);
                    mailVO.setContent(messageContent);
                    mailVO.setSender(from);
                    mailVO.setReceiver(receiver);
                    //mailVO.setSendDate();
                    //mailVO.setReceiveDate((Date)(sentDate));
                    if(referrerList.size()>0 ) {
                    	System.out.println("referrerList.size() = "+referrerList.size());
                    	for(int j=0; j< referrerList.size();j++) {
                    		if(j == 0) {
                    			referrer = referrerList.get(j);
                    			System.out.println("referrer = "+referrer);
                    			System.out.println("referrerList.get(j) = "+referrerList.get(j));
                    			mailVO.setReferrer(referrer);
                    		}else if(j == 1) {
                    			referrer2 = referrerList.get(j);
                    			System.out.println("referrer2 = "+referrer2);
                    			mailVO.setReferrer2(referrer2);
                    		}else {
                    			referrer3 = referrerList.get(i);
                    			System.out.println("referrer3 = "+referrer3);
                    			mailVO.setReferrer3(referrer3);
                    		}
                    		
                    	}
                    }
                    mailList.add(mailVO);
                    System.out.println("MailVO = "+mailVO);
                }
                System.out.println("mailList = "+mailList);
             // getContent()로 메일의 내용을 가져옴
                // 읽어온 메일 콘솔창 출력
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Received: " + sentDate);
                System.out.println("\t Message: " + messageContent);
                System.out.println("\t Attachments: " + attachFiles);
                
            }

            // disconnect
            inbox.close(false);
            store.close();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return mailList;
    }
    public String printRecipientsAndTo(Message msg) throws MessagingException {
        Address[] toRecipients = msg.getRecipients(Message.RecipientType.TO);
        
        InternetAddress internetAddress = new InternetAddress();
        System.out.println("To Recipients:");
        if (toRecipients != null) {
            for (Address toRecipient : toRecipients) {
                internetAddress = (InternetAddress) toRecipient;
                System.out.println("수신자"+internetAddress.getAddress());
            }
        }
        return internetAddress.getAddress();

    }
    public List<String> printRecipientsAndCc(Message msg) throws MessagingException {
    	Address[] ccRecipients = msg.getRecipients(Message.RecipientType.CC);
    	String referrer = "";
    	InternetAddress internetAddress = new InternetAddress();
    	List<String> list = new ArrayList<String>();
    	System.out.println("Cc Recipients:");
    	if (ccRecipients != null) {
    		for (Address ccRecipient : ccRecipients) {
    			internetAddress = (InternetAddress) ccRecipient;
    			
    			System.out.println("참조자"+internetAddress.getAddress());
    			if(internetAddress.getAddress() != null) {
					if( internetAddress.getAddress().indexOf("@")>=0 ) {
						referrer = internetAddress.getAddress().substring(0,internetAddress.getAddress().indexOf("@"));
						System.out.println(referrer);
					}
					list.add(referrer);
				}
    		}
    		System.out.println("참조자 리스트 = "+list);
    	}
    	
    	return list;
    }
}