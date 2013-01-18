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
 * A contact card definition by NTT DoCoMo.
 * 
 * Visit http://www.nttdocomo.co.jp/english/service/imode/make/content/barcode/
 * function/application/addressbook/index.html for more information
 * 
 * Please note that MECARDs are not supported by all QR code readers.
 * 
 * @author John Ahlroos
 */
@SuppressWarnings("serial")
public class MECARD implements QRCodeType {

    private String name;
    private String reading;
    private String tel;
    private String telAV;
    private String email;
    private String memo;
    private String bday;
    private String address;
    private String url;
    private String nickname;

    /*
     * (non-Javadoc)
     * 
     * @see fi.jasoft.qrcode.demo.types.QRCodeType#toQRString()
     */
    public String toQRString() {
        StringBuilder str = new StringBuilder();
        str.append("MECARD:");
        str.append("N:" + name + ";");
        str.append("SOUND:" + reading + ";");
        str.append("TEL:" + tel + ";");
        str.append("TEL-AV" + telAV + ";");
        str.append("EMAIL:" + email + ";");
        str.append("NOTE:" + memo + ";");
        str.append("BDAY:" + bday + ";");
        str.append("ADR:" + address + ";");
        str.append("URL:" + url + ";");
        str.append("NICKNAME:" + nickname + ";");
        str.append(";");
        return str.toString();
    }

    /**
     * Designates a text string to be set as the name in the phonebook. (0 or
     * more characters)
     * 
     * When a field is divided by a comma (,), the first half is treated as the
     * last name and the second half is treated as the first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Designates a text string to be set as the name in the phonebook. (0 or
     * more characters)
     * 
     * When a field is divided by a comma (,), the first half is treated as the
     * last name and the second half is treated as the first name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Designates a text string to be set as the kana name in the phonebook. (0
     * or more characters)
     * 
     * When a field is divided by a comma (,), the first half is treated as the
     * last name and the second half is treated as the first name.
     * 
     * @return
     */
    public String getReading() {
        return reading;
    }

    /**
     * Designates a text string to be set as the kana name in the phonebook. (0
     * or more characters)
     * 
     * When a field is divided by a comma (,), the first half is treated as the
     * last name and the second half is treated as the first name.
     * 
     */
    public void setReading(String reading) {
        this.reading = reading;
    }

    /**
     * Designates a text string to be set as the telephone number in the
     * phonebook. (1 to 24 digits)
     * 
     * @return
     */
    public String getTel() {
        return tel;
    }

    /**
     * Designates a text string to be set as the telephone number in the
     * phonebook. (1 to 24 digits)
     * 
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Designates a text string to be set as the videophone number in the
     * phonebook. (1 to 24 digits)
     * 
     * @return
     */
    public String getTelAV() {
        return telAV;
    }

    /**
     * Designates a text string to be set as the videophone number in the
     * phonebook. (1 to 24 digits)
     * 
     */
    public void setTelAV(String telAV) {
        this.telAV = telAV;
    }

    /**
     * Designates a text string to be set as the e-mail address in the
     * phonebook. (0 or more characters)
     * 
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Designates a text string to be set as the e-mail address in the
     * phonebook. (0 or more characters)
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Designates a text string to be set as the memo in the phonebook. (0 or
     * more characters)
     * 
     * @return
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Designates a text string to be set as the memo in the phonebook. (0 or
     * more characters)
     * 
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * Designates a text string to be set as the birthday in the phonebook. (8
     * digits) The 8 digits consist of the year (4 digits), month (2 digits) and
     * day (2 digits), in order.
     * 
     * @return
     */
    public String getBirthday() {
        return bday;
    }

    /**
     * Designates a text string to be set as the birthday in the phonebook. (8
     * digits) The 8 digits consist of the year (4 digits), month (2 digits) and
     * day (2 digits), in order.
     * 
     */
    public void setBirthday(String birthday) {
        this.bday = birthday;
    }

    /**
     * Designates a text string to be set as the address in the phonebook. (0 or
     * more characters) The fields divided by commas (,) denote PO box, room
     * number, house number, city, prefecture, zip code and country, in order.
     * 
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Designates a text string to be set as the address in the phonebook. (0 or
     * more characters) The fields divided by commas (,) denote PO box, room
     * number, house number, city, prefecture, zip code and country, in order.
     * 
     * @return
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Designates a text string to be set as the homepage URL in the phonebook.
     * (0 or more characters)
     * 
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Designates a text string to be set as the homepage URL in the phonebook.
     * (0 or more characters)
     * 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Designates a text string to be set as the nickname in the phonebook. (0
     * or more characters)
     * 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Designates a text string to be set as the nickname in the phonebook. (0
     * or more characters)
     * 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
