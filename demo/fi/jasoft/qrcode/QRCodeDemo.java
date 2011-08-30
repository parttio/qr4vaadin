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

import java.awt.Color;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class QRCodeDemo extends Application {

    private static final long serialVersionUID = 1L;

    private static final String COLOR_ITEM_PROPERTY = "color";

    private final QRCode code = new QRCode();

    @Override
    public void init() {
        Window mainWindow = new Window("Demo");
        ((VerticalLayout) mainWindow.getContent()).setSpacing(true);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setCaption("Text embedded in QR Code");
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
        
        HorizontalLayout layout2 = new HorizontalLayout();
        layout2.setSpacing(true);
        mainWindow.addComponent(layout2);
        
        final NativeSelect fgColor = new NativeSelect("Primary color");
        fgColor.setImmediate(true);
        fgColor.setNullSelectionAllowed(false);
        fgColor.setWidth("100px");
        fgColor.addContainerProperty(COLOR_ITEM_PROPERTY, Color.class,
                Color.BLACK);
        fgColor.addItem("Black");
        fgColor.addItem("Red").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(Color.RED);
        fgColor.addItem("Green").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(Color.GREEN);
        fgColor.addItem("Blue").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(Color.BLUE);
        fgColor.addItem("Yellow").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(Color.YELLOW);
        fgColor.select("Black");
        fgColor.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Item item = fgColor.getItem(event.getProperty().getValue());
                code.setPrimaryColor((Color) item.getItemProperty(
                        COLOR_ITEM_PROPERTY).getValue());
			}
		});
        layout2.addComponent(fgColor);
        
        final NativeSelect bgColor = new NativeSelect("Secondary color");
        bgColor.setImmediate(true);
        bgColor.setNullSelectionAllowed(false);
        bgColor.setWidth("100px");
        bgColor.addContainerProperty(COLOR_ITEM_PROPERTY, Color.class,
                Color.WHITE);
        bgColor.addItem("White");
        bgColor.addItem("Red").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(new Color(255, 0, 0, 50));
        bgColor.addItem("Green").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(new Color(0, 255, 0, 50));
        bgColor.addItem("Blue").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(new Color(0, 0, 255, 50));
        bgColor.addItem("Yellow").getItemProperty(COLOR_ITEM_PROPERTY)
                .setValue(new Color(255, 255, 0, 50));
        bgColor.select("White");
        bgColor.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Item item = bgColor.getItem(event.getProperty().getValue());
                code.setSecondaryColor((Color) item.getItemProperty(
                        COLOR_ITEM_PROPERTY).getValue());
			}
		});
        layout2.addComponent(bgColor);

        code.setCaption("QR Code");
        mainWindow.addComponent(code);

        setMainWindow(mainWindow);
    }

}
