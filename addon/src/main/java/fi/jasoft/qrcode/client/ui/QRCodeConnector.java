/*
 * Copyright 2013 John Ahlroos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.jasoft.qrcode.client.ui;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.communication.StateChangeEvent.StateChangeHandler;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

import fi.jasoft.qrcode.QRCode;

/**
 * Connector class for connecting the server side implementation {@link QRCode} 
 * with the client side implementatin {@link VQRCode}
 * 
 * @author John Ahlroos (www.jasoft.fi)
 */
@SuppressWarnings("serial")
@Connect(QRCode.class)
public class QRCodeConnector extends AbstractComponentConnector {

	public static final String RESOURCE_KEY = "qrcode";
	
	SizeListener sizeListener = RpcProxy.create(SizeListener.class, this);
	
	@Override
	public VQRCode getWidget() {
		return (VQRCode) super.getWidget();
	}
	
	@Override
	protected void init() {
		super.init();
		addStateChangeHandler("resources."+RESOURCE_KEY, new StateChangeHandler() {
			
			@Override
			public void onStateChanged(StateChangeEvent stateChangeEvent) {
				String url = getResourceUrl(RESOURCE_KEY);
				getWidget().setUrl(url);	
			}
		});
		getWidget().setSizeListener(sizeListener);
	}
}
