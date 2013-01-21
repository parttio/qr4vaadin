// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package fi.jasoft.qrcode.data;

/**
 * Embed a SMS message in a QR code
 * 
 * Please note that SMSs are not supported by all QR code readers.
 * 
 * @author John Ahlroos
 */
public class SMS implements QRCodeType {

    private String number;

    private String message;

    /**
     * Constructor
     * 
     * @param number
     *            The number to send an SMS to
     * 
     * @param message
     *            The SMS message
     */
    public SMS(String number, String message) {
        this.number = number;
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see fi.jasoft.qrcode.demo.types.QRCodeType#toQRString()
     */
    public String toQRString() {
        return "sms:" + number + ":" + message;
    }

    /**
     * Get the phone number
     * 
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the phone number
     * 
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Get the message
     * 
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
