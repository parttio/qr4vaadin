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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;

import com.vaadin.data.Property;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.AbstractField;

import fi.jasoft.qrcode.zxing.ByteMatrix;
import fi.jasoft.qrcode.zxing.Encoder;
import fi.jasoft.qrcode.zxing.ErrorCorrectionLevel;
import fi.jasoft.qrcode.zxing.WriterException;
import fi.jasoft.qrcode.zxing.ZXingQRCode;

/**
 * A component for encoding values into QR coded images and embedding
 * them into Vaadin applications.
 * 
 * @author John Ahlroos (www.jasoft.fi)
 */
@SuppressWarnings("serial")
@com.vaadin.ui.ClientWidget(fi.jasoft.qrcode.client.ui.VQRCode.class)
public class QRCode extends AbstractField {

	private final ZXingQRCode qrcode = new ZXingQRCode();

	private int pixelWidth = 100;
	private int pixelHeight = 100;

	protected ErrorCorrectionLevel ecl = ErrorCorrectionLevel.L;

	/**
	 * Constructs an empty <code>QRCode</code> with no caption.
	 */
	public QRCode() {
		setValue("");
	}

	/**
	 * Constructs an empty <code>QRCode</code> with given caption.
	 * 
	 * @param caption
	 *            the caption <code>String</code> for the editor.
	 */
	public QRCode(String caption) {
		this();
		setCaption(caption);
	}

	/**
	 * Constructs a new <code>QRCode</code> that's bound to the specified
	 * <code>Property</code> and has no caption.
	 * 
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public QRCode(Property dataSource) {
		setPropertyDataSource(dataSource);
	}

	/**
	 * Constructs a new <code>QRCode</code> that's bound to the specified
	 * <code>Property</code> and has the given caption <code>String</code>.
	 * 
	 * @param caption
	 *            the caption <code>String</code> for the editor.
	 * @param dataSource
	 *            the Property to be edited with this editor.
	 */
	public QRCode(String caption, Property dataSource) {
		this(dataSource);
		setCaption(caption);
	}

	/**
	 * Constructs a new <code>QRCode</code> with the given caption and initial
	 * text contents. The editor constructed this way will not be bound to a
	 * Property unless
	 * {@link com.vaadin.data.Property.Viewer#setPropertyDataSource(Property)}
	 * is called to bind it.
	 * 
	 * @param caption
	 *            the caption <code>String</code> for the editor.
	 * @param text
	 *            the initial text content of the editor.
	 */
	public QRCode(String caption, String value) {
		setValue(value);
		setCaption(caption);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
		super.changeVariables(source, variables);
		if (variables.containsKey("pixelWidth")) {
			pixelWidth = Integer.parseInt(variables.get("pixelWidth")
					.toString());
			if(pixelWidth == -1){
				pixelWidth = 100;
			}
		}
		if(variables.containsKey("pixelHeight")) {
			pixelHeight = Integer.parseInt(variables.get("pixelHeight").toString());
			if(pixelHeight == -1){
				pixelHeight = 100;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);

		String value = getValue() == null ? "" : getValue().toString();

		if (pixelWidth > 0 || pixelWidth == -1) {
			/*
			 * Generate a unique filename for this qrcode relative to the value
			 * of the qrcode and the width
			 */
			int hash = (value + pixelWidth).hashCode();
			String filename = "qcode-" + Integer.toHexString(hash) + ".png";

			// Create a image resource
			StreamResource resource = new StreamResource(
					new StreamResource.StreamSource() {
						public InputStream getStream() {
							BufferedImage image = toBufferedImage(renderResult(qrcode, pixelWidth, pixelWidth));
							ByteArrayOutputStream imagebuffer = new ByteArrayOutputStream();

							try {
								ImageIO.write(image, "png", imagebuffer);
								return new ByteArrayInputStream(imagebuffer
										.toByteArray());
							} catch (IOException e) {
								e.printStackTrace();
							}
							return null;
						}
					}, filename, getApplication());

			target.addAttribute("qrcode", resource);
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setInternalValue(Object newValue) {
		super.setInternalValue(newValue);

		String value = newValue == null ? "" : newValue.toString();

		try {
			Encoder.chooseMode(value);
			Encoder.encode(value, ecl, qrcode);
			requestRepaint();
		} catch (WriterException e) {
			throw new IllegalArgumentException(
					"Could not encode the string into QRCode");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getType() {
		return String.class;
	}

	/**
	 * Copy&Paste from com.google.zxing.qrcode.QRCodeWriter.java
	 * 
	 * http://zxing.googlecode.com/svn-history/r800/trunk/core/src/com/google/zxing/qrcode/QRCodeWriter.java
	 * 
	 */
	private static final int QUIET_ZONE_SIZE = 4;
	private static ByteMatrix renderResult(ZXingQRCode code, int width,
			int height) {
		ByteMatrix input = code.getMatrix();
		int inputWidth = input.getWidth();
		int inputHeight = input.getHeight();
		int qrWidth = inputWidth + (QUIET_ZONE_SIZE << 1);
		int qrHeight = inputHeight + (QUIET_ZONE_SIZE << 1);
		int outputWidth = Math.max(width, qrWidth);
		int outputHeight = Math.max(height, qrHeight);

		int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
		int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
		int topPadding = (outputHeight - (inputHeight * multiple)) / 2;

		ByteMatrix output = new ByteMatrix(outputHeight, outputWidth);
		byte[][] outputArray = output.getArray();
		byte[] row = new byte[outputWidth];

		// 1. Write the white lines at the top
		for (int y = 0; y < topPadding; y++) {
			setRowColor(outputArray[y], (byte) 255);
		}

		// 2. Expand the QR image to the multiple
		byte[][] inputArray = input.getArray();
		for (int y = 0; y < inputHeight; y++) {
			// a. Write the white pixels at the left of each row
			for (int x = 0; x < leftPadding; x++) {
				row[x] = (byte) 255;
			}

			// b. Write the contents of this row of the barcode
			int offset = leftPadding;
			for (int x = 0; x < inputWidth; x++) {
				byte value = (inputArray[y][x] == 1) ? 0 : (byte) 255;
				for (int z = 0; z < multiple; z++) {
					row[offset + z] = value;
				}
				offset += multiple;
			}

			// c. Write the white pixels at the right of each row
			offset = leftPadding + (inputWidth * multiple);
			for (int x = offset; x < outputWidth; x++) {
				row[x] = (byte) 255;
			}

			// d. Write the completed row multiple times
			offset = topPadding + (y * multiple);
			for (int z = 0; z < multiple; z++) {
				System.arraycopy(row, 0, outputArray[offset + z], 0,
						outputWidth);
			}
		}

		// 3. Write the white lines at the bottom
		int offset = topPadding + (inputHeight * multiple);
		for (int y = offset; y < outputHeight; y++) {
			setRowColor(outputArray[y], (byte) 255);
		}

		return output;
	}

	/**
	 * Copy & Paste from com.google.zxing.qrcode.QRCodeWriter.java
	 * 
	 * http://zxing.googlecode.com/svn-history/r800/trunk/core/src/com/google/zxing/qrcode/QRCodeWriter.java
	 * 
	 */
	private static void setRowColor(byte[] row, byte value) {
		for (int x = 0; x < row.length; x++) {
			row[x] = value;
		}
	}
	
	/**
	 * Copy & Paste from com.google.zxing.client.j2se.MatrixToImageWriter.java
	 * 
	 * http://zxing.googlecode.com/svn-history/r1028/trunk/javase/src/com/google/zxing/client/j2se/MatrixToImageWriter.java
	 */
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static BufferedImage toBufferedImage(ByteMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == 0 ? BLACK : WHITE);
			}
		}
		return image;
	}
}
