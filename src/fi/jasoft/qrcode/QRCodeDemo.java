/*
 * Copyright 2011 John Ahlroos
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
package fi.jasoft.qrcode;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;

public class QRCodeDemo extends Application {

	private static final long serialVersionUID = 1L;

	QRCode code = new QRCode();
	
	@Override
	public void init() {
		Window mainWindow = new Window("Demo");
		((VerticalLayout)mainWindow.getContent()).setSpacing(true);
		
		TextField text = new TextField("Type something in this field and press <ENTER> to generate a new QRCode image below");
		text.setWidth("500px");
		text.setImmediate(true);
		text.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				code.setValue(event.getProperty().getValue());
			}
		});
		
		text.setValue("The quick brown fox jumps over the lazy dog");
		mainWindow.addComponent(text);
		mainWindow.addComponent(code);
		
		setMainWindow(mainWindow);
	}

}
