package fi.jasoft.qrcode.demo;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.VaadinServlet;

@WebServlet(
	    asyncSupported=false,
	    urlPatterns={"/*","/VAADIN/*"},
	    initParams={
	        @WebInitParam(name="ui", value="fi.jasoft.qrcode.demo.QRCodeDemo")
	    })
public class QRCodeServlet extends VaadinServlet { }
