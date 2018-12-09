package com.webapp;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form name=\"form1\" method=\"post\" action=\"http://localhost:80/MailTest_war/hello\">\n" +
                "        Email: <input type=\"text\" name=\"email\" placeholder=\"Enter EMail...\"><br><br>\n" +
                "        Theme: <input type=\"text\" name=\"theme\" placeholder=\"Enter theme...\"><br><br>\n" +
                "        Text:  <input type=\"text\" name=\"text\"placeholder=\"Enter text...\"><br><br>\n" +
                "        <input type=\"submit\" value=\"Send\">\n" +
                "    </form>\n" +
                "    <form name=\"form1\" method=\"post\" action=\"http://localhost:80/MailTest_war/db\">\n" +
                "        Поле: <input type=\"text\" name=\"field\" placeholder=\"Enter Field...\"><br><br>\n" +
                "        <input type=\"submit\" value=\"Select\">\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>");
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String eMail = req.getParameter("email");
        String theme = req.getParameter("theme");
        String text = req.getParameter("text");
        final String from = "nastyafilyushkina98@gmail.com";
        final String password = "ujujkm2010";
        String host = "smtp.gmail.com";
        int port = 465;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(eMail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(theme);
            msg.setText(text);
            Transport.send(msg);
        }
        catch (Exception e){
            PrintWriter out = resp.getWriter();
            out.println(e);
            out.close();
        }
    }
}
