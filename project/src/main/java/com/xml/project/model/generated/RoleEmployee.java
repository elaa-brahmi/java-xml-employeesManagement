//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.12.16 at 05:39:04 PM WAT 
//


package com.xml.project.model.generated;
import com.xml.project.model.generated.*;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleEmployee.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="roleEmployee"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="employee"/&gt;
 *     &lt;enumeration value="maintenance technician"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "roleEmployee")
@XmlEnum
public enum RoleEmployee {

    @XmlEnumValue("employee")
    employee("employee"),
    @XmlEnumValue("maintenance technician")
    maintenance_technician("maintenance_technician");
    private final String value;

    RoleEmployee(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoleEmployee fromValue(String v) {
        for (RoleEmployee c: RoleEmployee.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
