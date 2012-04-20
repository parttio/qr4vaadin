package fi.jasoft.qrcode;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.gwt.client.AbstractFieldState;
import com.vaadin.terminal.gwt.client.ComponentState;

public class QRCodeState extends AbstractFieldState {

	private Resource qrcode;

	public Resource getQRCode() {
		return qrcode;
	}

	public void setQRCode(Resource qrcode) {
		this.qrcode = qrcode;
	}	
}
