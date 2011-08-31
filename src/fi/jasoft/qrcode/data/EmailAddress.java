package fi.jasoft.qrcode.data;

/**
 * 
 * Helper object for encoding email addresses into QR codes
 * 
 */
@SuppressWarnings("serial")
public class EmailAddress implements QRCodeType {

    private String address;

    /**
     * An email address
     * 
     * @param address
     *            An email address in the form john@doe.com
     */
    public EmailAddress(String address) {
        this.address = address;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fi.jasoft.qrcode.types.QRCodeType#toQRString()
     */
    public String toQRString() {
        return "mailto:" + this.address;
    }

}
