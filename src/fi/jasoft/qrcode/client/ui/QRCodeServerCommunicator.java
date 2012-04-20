package fi.jasoft.qrcode.client.ui;

import com.vaadin.terminal.gwt.client.communication.ServerRpc;

public interface QRCodeServerCommunicator extends ServerRpc {

	void init(int width, int height);
	
	void setQRWidth(int width);
	
	void setQRHeight(int height);
	
}
