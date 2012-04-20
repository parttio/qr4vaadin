package fi.jasoft.qrcode.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.Connector;
import com.vaadin.terminal.gwt.client.DirectionalManagedLayout;
import com.vaadin.terminal.gwt.client.LayoutManager;
import com.vaadin.terminal.gwt.client.communication.RpcProxy;
import com.vaadin.terminal.gwt.client.communication.SharedState;
import com.vaadin.terminal.gwt.client.ui.AbstractComponentConnector;
import com.vaadin.terminal.gwt.client.ui.Connect;

import fi.jasoft.qrcode.QRCode;

@Connect(QRCode.class)
public class QRCodeConnector extends AbstractComponentConnector implements DirectionalManagedLayout {

	private QRCodeServerCommunicator communicator = RpcProxy.create(QRCodeServerCommunicator.class, this);
	
	@Override
	protected Widget createWidget() {		
		return GWT.create(VQRCode.class);
	}
	
	@Override
	public VQRCode getWidget() {		
		return (VQRCode) super.getWidget();
	}

	public void layoutVertically() {
		int height = getLayoutManager().getInnerHeight(getWidget().getElement());				
		communicator.setQRHeight(height);
	}

	public void layoutHorizontally() {
		int width = getLayoutManager().getInnerWidth(getWidget().getElement());
		communicator.setQRWidth(width);
	}

}
