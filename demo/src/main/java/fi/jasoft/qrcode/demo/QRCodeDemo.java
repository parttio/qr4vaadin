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
import java.util.Arrays;
import java.util.List;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import fi.jasoft.qrcode.QRCode;

public class QRCodeDemo extends UI {

    private QRCode code;

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        setContent(content);

        Label header = new Label("QR Code Generator");
        header.setStyleName(ValoTheme.LABEL_H2);
        content.addComponent(header);

        HorizontalSplitPanel root = new HorizontalSplitPanel();
        root.setSizeFull();
        root.setSplitPosition(50, Unit.PERCENTAGE);
        root.setLocked(true);

        Panel panel = new Panel(root);
        panel.setSizeFull();
        content.addComponent(panel);
        content.setExpandRatio(panel, 1);

        VerticalLayout first = new VerticalLayout();
        first.setSizeFull();
        root.setFirstComponent(first);

        first.addComponent(new HorizontalLayout(createPrimaryColorSelect(),
                createSecondaryColorSelect(), createSizeSelect()));

        code = new QRCode();
        code.setWidth("100px");
        code.setHeight("100px");     
        
        final TextArea text = new TextArea("Text embedded in QR Code");
        text.setPlaceholder("Type the message of the QR code here");
        text.setSizeFull();
        text.setValueChangeMode(ValueChangeMode.LAZY);
        text.addValueChangeListener(e -> {
        	code.setValue(e.getValue());
        });

        first.addComponent(text);
        first.setExpandRatio(text, 1);

        VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();

       
        vl.addComponent(code);
        vl.setComponentAlignment(code, Alignment.MIDDLE_CENTER);

        root.setSecondComponent(vl);
    }

    private NativeSelect<Color> createPrimaryColorSelect() {
    	List<Color> colors = Arrays.asList(Color.BLACK, Color.RED, 
    			Color.GREEN, Color.BLUE, Color.YELLOW);
    	
        NativeSelect<Color> fgColor = new NativeSelect<Color>("Primary color");
        fgColor.setWidth("100px");
        fgColor.setItemCaptionGenerator( color -> {
        	switch(colors.indexOf(color)){
        	case 0: return "Black";
        	case 1: return "Red";
        	case 2: return "Green";
        	case 3: return "Blue";
        	case 4: return "Yellow";
        	}
        	return "Black";
        });
        fgColor.setItems(colors);
        fgColor.setValue(Color.BLACK);
        fgColor.addValueChangeListener(e -> code.setPrimaryColor(e.getValue()));
        return fgColor;
    }

    private NativeSelect<Color> createSecondaryColorSelect() {
    	
    	List<Color> colors = Arrays.asList(
        		Color.WHITE, 
        		new Color(255, 0, 0, 50), 
        		new Color(0, 255, 0, 50),
        		new Color(0, 0, 255, 50),
        		new Color(255, 255, 0, 50));
    	
        final NativeSelect<Color> bgColor = new NativeSelect<Color>("Secondary color");
        bgColor.setWidth("100px");
        bgColor.setItemCaptionGenerator( color -> {
        	switch(colors.indexOf(color)){
        	case 0: return "White";
        	case 1: return "Red";
        	case 2: return "Green";
        	case 3: return "Blue";
        	case 4: return "Yellow";
        	}
        	return "White";
        });
        bgColor.setItems(colors);
        bgColor.setValue(Color.WHITE);
        bgColor.addValueChangeListener(e -> code.setSecondaryColor(e.getValue()));
        return bgColor;
    }

    private NativeSelect<Integer> createSizeSelect() {
        final NativeSelect<Integer> size = new NativeSelect<Integer>("Size");
        size.setWidth("100px");
        size.setItemCaptionGenerator(s -> s + "x" + s);
        size.setItems(50,100,150,300,500,750,1000);
        size.setValue(100);
        size.addValueChangeListener(e -> {
        	code.setWidth(e.getValue(), Unit.PIXELS);
            code.setHeight(e.getValue(), Unit.PIXELS);
        });
        return size;
    }
}
