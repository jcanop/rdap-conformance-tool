package org.icann.rdapconformance.validator.exception.parser;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.icann.rdapconformance.validator.workflow.rdap.RDAPValidationResult;
import org.icann.rdapconformance.validator.workflow.rdap.RDAPValidatorResults;
import org.json.JSONObject;

public class UniqueItemsExceptionParser extends ExceptionParser {

  protected UniqueItemsExceptionParser(ValidationException e, Schema schema,
      JSONObject jsonObject,
      RDAPValidatorResults results) {
    super(e, schema, jsonObject, results);
  }

  @Override
  public boolean matches(ValidationException e) {
    return e.getKeyword() != null && e.getKeyword().equals("uniqueItems");
  }

  @Override
  protected void doParse() {
    results.add(RDAPValidationResult.builder()
        .code(parseErrorCode(() -> (int) getPropertyFromViolatedSchema(e,
            "duplicateItemsErrorCode")))
        .value(jsonObject.toString())
        .message("A " + e.getPointerToViolation() + " value appeared more than once.")
        .build());
  }
}
