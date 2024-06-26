package org.icann.rdapconformance.validator.workflow.rdap.dataset.model;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "registry", namespace = "http://www.iana.org/assignments")
public class RDAPJsonValues extends XmlObject {

  @XmlElementWrapper(name = "registry", namespace = "http://www.iana.org/assignments")
  @XmlElement(name = "record", namespace = "http://www.iana.org/assignments")
  private final List<RDAPJsonValuesRecord> records = new ArrayList<>();

  public Set<String> getByType(JsonValueType type) {
    return records.stream()
        .filter(r -> r.type != null)  // this may append when new value types are created
        .filter(r -> r.type.equals(type))
        .map(RDAPJsonValuesRecord::getValue)
        .collect(Collectors.toSet());
  }

  @XmlEnum
  public enum JsonValueType {
    @XmlEnumValue("notice and remark type") NOTICE_AND_REMARK_TYPE,
    @XmlEnumValue("status") STATUS,
    @XmlEnumValue("event action") EVENT_ACTION,
    @XmlEnumValue("role") ROLE,
    @XmlEnumValue("domain variant relation") DOMAIN_VARIANT_RELATION,
    @XmlEnumValue("redacted expression language") REDACTED_EXPRESSION_LANGUAGE,
    @XmlEnumValue("redacted name") REDACTED_NAME

  }

  @XmlAccessorType(XmlAccessType.FIELD)
  private static class RDAPJsonValuesRecord {

    @XmlElement(name = "type", namespace = "http://www.iana.org/assignments")
    private JsonValueType type;
    @XmlElement(name = "value", namespace = "http://www.iana.org/assignments")
    private String value;

    public String getValue() {
      return value;
    }
  }
}
