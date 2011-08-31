package fi.jasoft.qrcode.data;

import java.io.Serializable;

public interface QRCodeType extends Serializable {

    /**
     * Serializes the QR Code object to a string
     * 
     * @return
     */
    String toQRString();
}
