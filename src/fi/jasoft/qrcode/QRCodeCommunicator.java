package fi.jasoft.qrcode;

import fi.jasoft.qrcode.client.ui.QRCodeServerCommunicator;

public class QRCodeCommunicator implements QRCodeServerCommunicator {

	private final QRCode code;
	
	public QRCodeCommunicator(QRCode code) {
		this.code = code;
	}
		
	public void init(int width, int height) {
		setQRWidth(width);
		setQRHeight(height);		
	}

	public void setQRWidth(int width) {
		code.setInternalWidth(width);
	}

	public void setQRHeight(int height) {
		code.setInternalHeight(height);				
	}

}
