package fi.jasoft.qrcode.data;

/**
 * Helper object for encoding telephone numbers into QR codes
 */
@SuppressWarnings("serial")
public class PhoneNumber implements QRCodeType {

    private String countryCode;

    private String number;

    /**
     * Creates a QR encoded phone number.
     * 
     * @param countryCode
     *            The Country code prefix. You can use the
     *            {@link CountryCodeUtil} to get a countries prefix by country
     *            name.
     * @param number
     *            The phone number without the country code
     */
    public PhoneNumber(String countryCode, String number) {
        this.countryCode = getOnlyNumerics(countryCode);
        this.number = getOnlyNumerics(number);
    }

    /**
     * The country code of the phone number
     * 
     * @return A string representation of the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Set the country code of the phone number
     * 
     * @param countryCode
     *            Should start with a + and be max 3 numbers long
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = getOnlyNumerics(countryCode);
    }

    /**
     * Get the phone number without the country code
     * 
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the phone number without the country code
     * 
     * @param number
     */
    public void setNumber(String number) {
        this.number = getOnlyNumerics(number);
    }

    /*
     * (non-Javadoc)
     * 
     * @see fi.jasoft.qrcode.types.QRCodeType#toQRString()
     */
    public String toQRString() {
        StringBuilder uri = new StringBuilder("tel:");
        uri.append("+");
        uri.append(countryCode);
        uri.append(number);
        return uri.toString();
    }

    /**
     * String string from all non-numerical characters
     * 
     * @param str
     *            Input string
     * @return
     */
    private static String getOnlyNumerics(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer strBuff = new StringBuffer();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isDigit(c)) {
                strBuff.append(c);
            }
        }
        return strBuff.toString();
    }
}
