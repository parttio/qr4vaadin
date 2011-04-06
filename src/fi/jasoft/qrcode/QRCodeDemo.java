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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class QRCodeDemo extends Application {

    private static final long serialVersionUID = 1L;

    QRCode code = new QRCode();

    @Override
    public void init() {
        Window mainWindow = new Window("Demo");
        ((VerticalLayout) mainWindow.getContent()).setSpacing(true);

        HorizontalLayout layout = new HorizontalLayout();
        mainWindow.addComponent(layout);

        final TextField text = new TextField();
        text.setWidth("500px");
        text.setImmediate(true);
        text.setValue("The quick brown fox jumps over the lazy dog");
        layout.addComponent(text);

        code.setValue("The quick brown fox jumps over the lazy dog");

        Button gen = new Button("Generate!", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                code.setValue(text.getValue());
            }
        });
        layout.addComponent(gen);

        mainWindow.addComponent(code);

        setMainWindow(mainWindow);
    }

}
