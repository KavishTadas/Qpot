package com.demo.TaskMannagment.Utilities;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Send_Mail_with_Report {

    public static void Mail() {
        final String username = "kavish.tadas@omfysgroup.com"; // your organization email
        final String password = "TadasK@123"; // your organization email password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "hosted-ex01.go4hosting.in"); // your organization's SMTP server
        props.put("mail.smtp.port", "587"); // the SMTP port used by your organization

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password); 
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kavish.tadas@omfysgroup.com")); // your organization email
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("kavish.tadas@omfysgroup.com"));
                    message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("priyanka.shukla@omfysgroup.com, sampada.pokale@omfysgroup.com, anuja.jadhav@omfysgroup.com"));
       
            message.setSubject("Test");

            // Create a Multipart object to handle multiple parts (text, attachments, etc.)
            Multipart multipart = new MimeMultipart();

            // Add text part
            BodyPart textPart = new MimeBodyPart();
            textPart.setText("PFA." + "\nSending you the Bug Report." + "\nPlease Review");
            multipart.addBodyPart(textPart);

//            // Add HTML file
//            BodyPart htmlPart = new MimeBodyPart();
//            String htmlContent = readHtmlFile("E:\\ORANGE\\Report\\Report-07.12.2023_21.24.06\\ORANGE_TestCases.html");
//            htmlPart.setContent(htmlContent, "text/html");
//            multipart.addBodyPart(htmlPart);
//
            // Add Excel file
//            BodyPart excelPart = new MimeBodyPart();
//            File excelFile = new File("E:\\Data_Driven_Demo\\Data.xlsx");
//            ((MimeBodyPart) excelPart).attachFile(excelFile);
//            multipart.addBodyPart(excelPart);

            // Add entire folder (assuming it contains files you want to attach)
            BodyPart folderPart = new MimeBodyPart();
            File folder = new File("E:\\Qpot\\Report_zipped.zip");
            FileDataSource folderDataSource = new FileDataSource(folder);
            folderPart.setDataHandler(new DataHandler(folderDataSource));
            folderPart.setFileName(folder.getName());
            multipart.addBodyPart(folderPart);

            // Set the content of the message to the Multipart object
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String readHtmlFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] htmlBytes = Files.readAllBytes(path);
        return new String(htmlBytes);
    }
}

