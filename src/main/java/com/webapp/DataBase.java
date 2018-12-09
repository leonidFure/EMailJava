package com.webapp;

import dataBase.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DataBase extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String field = req.getParameter("field");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(new DB(field).select());
        printWriter.print("<form name=\"form1\" method=\"post\" action=\"http://localhost:80/\">\n    </form>\n");
    }
}
