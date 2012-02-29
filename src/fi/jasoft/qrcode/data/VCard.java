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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Embed a VCard in the QR code.
 * 
 * Please note that VCards are not supported by all QR code readers.
 * 
 * @author John Ahlroos
 */
@SuppressWarnings("serial")
public class VCard implements QRCodeType {
    public static final String NAME_PROPERTY = "N";
    public static final String TITLE_PROPERTY = "TITLE";
    public static final String FULL_NAME_PROPERTY = "FN";
    public static final String EMAIL_PROPERTY = "EMAIL";
    public static final String TELEPHONE_PROPERTY = "TEL";
    public static final String ORGANIZATION_PROPERTY = "ORG";
    public static final String PHOTO_PROPERTY = "PHOTO";

    private Map<String, Set<VCardProperty>> keyToEntry;

    public VCard() {
        keyToEntry = new HashMap<String, Set<VCardProperty>>();
    }

    /**
     * Add a vCard property to the card
     * 
     * @param property
     */
    public void addProperty(VCardProperty property) {
        Set<VCardProperty> props = keyToEntry.get(property.getKey());
        if (props == null) {
            props = new HashSet<VCardProperty>();
            keyToEntry.put(property.getKey(), props);
    }
        props.add(property);
    }

    /**
     * Is the VCard empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return keyToEntry.isEmpty();
    }


    /**
     * Returns all of the properties of the given name. Example:
     * Set<VCardProperty> phoneNumbers = getProperties("TEL");
     * 
     * @param propName
     * @return
     */
    public Set<VCardProperty> getProperties(String propName) {
        return keyToEntry.get(propName);
    }

    /**
     * Returns all of the property names present in this VCard
     * 
     * @return
     */
    public Set<String> getPropertyNames() {
        return keyToEntry.keySet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fi.jasoft.qrcode.demo.types.QRCodeType#toQRString()
     */
    public String toQRString() {
        StringBuilder sb = new StringBuilder();
        for (Set<VCardProperty> vkeys : keyToEntry.values()) {
            for (VCardProperty vkey : vkeys) {
                sb.append(vkey.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Represents a VCard property. Example: TITLE:Developer Advocate
     */
    public static class VCardProperty {
        public static final String TYPE_WORK = "WORK";
        public static final String TYPE_CELL = "CELL";
        public static final String TYPE_FAX = "FAX";

        private String key;
        private Map<String, Set<String>> keyAttributes;
        private String value;

        /**
         * The property attribute
         * 
         * @param key
         *            The attribute, for instance {@link VCard#EMAIL_PROPERTY}
         */
        public VCardProperty(String key) {
            this.key = key;
        }

        /**
         * The property attribute
         */
        public String getKey() {
            return key;
        }

        /**
         * The value of the property
         * 
         * @return
         */
        public String getValue() {
            return value;
        }

        /**
         * The value of the property
         * 
         * @param value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Determines if a vcard property has a certain named attribute.
         * Example: if (prop.hasAttribute("type", "FAX")) { ... }
         * 
         * @param attname
         * @param value
         * @return
         */
        public boolean hasAttribute(String attname, String value) {
            if (keyAttributes == null) {
                return false;
            }
            Set<String> values = keyAttributes.get(attname);
            return (values == null) ? false : values.contains(value);
        }

        /**
         * Convenience method for checking the "type" attribute
         * 
         * @param type
         * @return
         */
        public boolean isType(String type) {
            if (keyAttributes == null) {
                return false;
            }
            Set<String> values = keyAttributes.get("type");
            return (values == null) ? false : values.contains(type);
        }

        /**
         * Determines if a vcard property has a certain unnamed attribute.
         * Example: ORG:Google;Engineering
         * 
         * if (prop.hasAttribute("Engineering")) { ... }
         * 
         * @param attname
         * @return
         */
        public boolean hasAttribute(String attname) {
            return hasAttribute("", attname);
        }

        /**
         * Add an attribute to the property
         * 
         * @param attribute
         *            The attribute to add
         * @param value
         *            The value of the attribute
         */
        public void addAttribute(String attribute, String value) {
            if (keyAttributes == null) {
                keyAttributes = new HashMap<String, Set<String>>();
            }
            Set<String> values = keyAttributes.get(attribute);
            if (values == null) {
                values = new HashSet<String>();
                keyAttributes.put(attribute, values);
            }
            values.add(value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(key);
            if (keyAttributes != null) {
                for (String akey : keyAttributes.keySet()) {
                    Set<String> avalues = keyAttributes.get(akey);
                    if (akey.equals("")) {
                        for (String avalue : avalues) {
                            sb.append(';').append(avalue);
                        }
                    } else {
                        for (String avalue : avalues) {
                            sb.append(';').append(akey).append('=')
                                    .append(avalue);
                        }
                    }
                }
            }
            sb.append(':').append(value);
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            VCardProperty other = (VCardProperty) o;

            if (!key.equals(other.key)) {
                return false;
            }
            return equals(keyAttributes, other.keyAttributes);
        }

        private static boolean equals(Map<String, Set<String>> a1,
                Map<String, Set<String>> a2) {
            if (a1 == null) {
                return (a2 == null);
            }
            if (a2 == null) {
                return (a1 == null);
            }
            return a1.equals(a2);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
}
