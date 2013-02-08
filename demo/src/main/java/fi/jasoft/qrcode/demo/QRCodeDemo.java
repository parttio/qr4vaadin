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
package fi.jasoft.qrcode.demo;

import java.awt.Color;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import fi.jasoft.qrcode.QRCode;

public class QRCodeDemo extends UI {

    private static final String COLOR_ITEM_PROPERTY = "color";

    private static final String SIZE_ITEM_PROPERTY = "size";

    private QRCode code;

    private ObjectProperty<String> message = new ObjectProperty<String>("");

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        setContent(content);

        Label header = new Label("QR Code Generator");
        header.setStyleName(Reindeer.LABEL_H2);
        content.addComponent(header);

        HorizontalSplitPanel root = new HorizontalSplitPanel();
        root.setSizeFull();
        root.setSplitPosition(50, Unit.PERCENTAGE);
        root.setLocked(true);
        root.setStyleName(Reindeer.SPLITPANEL_SMALL);

        Panel panel = new Panel(root);
        panel.setSizeFull();
        content.addComponent(panel);
        content.setExpandRatio(panel, 1);

        VerticalLayout first = new VerticalLayout();
        first.setSizeFull();
        root.setFirstComponent(first);

        first.addComponent(new HorizontalLayout(createPrimaryColorSelect(),
                createSecondaryColorSelect(), createSizeSelect()));

        final TextArea text = new TextArea("Text embedded in QR Code");
        text.setInputPrompt("Type the message of the QR code here");
        text.setSizeFull();
        text.setTextChangeEventMode(TextChangeEventMode.LAZY);
        text.addTextChangeListener(new TextChangeListener() {

            @Override
            public void textChange(TextChangeEvent event) {
                text.setValue(event.getText());

            }
        });
        text.setImmediate(true);
        text.setPropertyDataSource(message);
        first.addComponent(text);
        first.setExpandRatio(text, 1);

        VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();

        code = new QRCode();
        code.setWidth("100px");
        code.setHeight("100px");
        code.setPropertyDataSource(message);
        vl.addComponent(code);
        vl.setComponentAlignment(code, Alignment.MIDDLE_CENTER);

        root.setSecondComponent(vl);
    }

    private NativeSelect createPrimaryColorSelect() {
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
        return fgColor;
    }

    private NativeSelect createSecondaryColorSelect() {
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
        return bgColor;
    }

    private NativeSelect createSizeSelect() {
        final NativeSelect size = new NativeSelect("Size");
        size.setImmediate(true);
        size.setNullSelectionAllowed(false);
        size.setWidth("100px");
        size.addContainerProperty(SIZE_ITEM_PROPERTY, Integer.class, 50);

        size.addItem("50x50");
        size.addItem("100x100").getItemProperty(SIZE_ITEM_PROPERTY)
                .setValue(100);
        size.addItem("150x150").getItemProperty(SIZE_ITEM_PROPERTY)
                .setValue(150);
        size.addItem("300x300").getItemProperty(SIZE_ITEM_PROPERTY)
                .setValue(300);
        size.addItem("500x500").getItemProperty(SIZE_ITEM_PROPERTY)
                .setValue(500);
        size.addItem("750x750").getItemProperty(SIZE_ITEM_PROPERTY)
                .setValue(750);
        size.addItem("1000x1000").getItemProperty(SIZE_ITEM_PROPERTY)
                .setValue(1000);

        size.select("100x100");
        size.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Item item = size.getItem(event.getProperty().getValue());
                Integer size = (Integer) item.getItemProperty(
                        SIZE_ITEM_PROPERTY).getValue();
                code.setWidth(size, Unit.PIXELS);
                code.setHeight(size, Unit.PIXELS);
            }
        });
        return size;
    }
}
