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
 * 
 * Helper object for encoding email addresses into QR codes
 * 
 * Please note that MECARDs are not supported by all QR code readers.
 * 
 * @author John Ahlroos
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
     * @see fi.jasoft.qrcode.demo.types.QRCodeType#toQRString()
     */
    public String toQRString() {
        return "mailto:" + this.address;
    }

}
