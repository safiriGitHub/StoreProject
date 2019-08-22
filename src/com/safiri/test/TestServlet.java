package com.safiri.test;

import com.safiri.store.web.base.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet() {
        super();
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
    	System.out.println("findAll called");
    }

}
