$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/main/java/Features/JSONToExcel.feature");
formatter.feature({
  "name": "Write JSON response to excel",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "To Parse JSON response and write in excel",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Parse JSON response",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitions.JSONToExcelStepDefinition.parse_JSON_response()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Write data in excel",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinitions.JSONToExcelStepDefinition.write_data_in_excel()"
});
formatter.result({
  "status": "passed"
});
});