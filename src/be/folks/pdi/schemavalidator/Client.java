package be.folks.pdi.schemavalidator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import java.io.IOException;
import java.net.URL;

public class Client {
    private String jsonInput;
    private String schemaInput;
    private boolean schemaIsRemote = false;
    private JsonNode jsonNodeInput;
    private JsonNode jsonNodeSchema;
    private ProcessingReport report;

    public Client(String jsonInput, String schemaInput, boolean schemaIsRemote) throws IOException {
        this.jsonInput = jsonInput;
        this.schemaInput = schemaInput;
        this.schemaIsRemote = schemaIsRemote;
        ObjectMapper mapper = new ObjectMapper();
        this.jsonNodeInput = mapper.readTree(this.jsonInput);

        if (!this.schemaIsRemote) {
            this.jsonNodeSchema = mapper.readTree(this.schemaInput);
        } else {
            this.jsonNodeSchema = this.getJsonNodeFromUrl(this.schemaInput);
        }
    }

    public ProcessingReport getReport() throws ProcessingException {
        if (this.report == null) {
            this.validate();
        }
        return this.report;
    }

    public boolean validate() throws ProcessingException {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(this.jsonNodeSchema);
        this.report = schema.validate(this.jsonNodeInput);
        return report.isSuccess();
    }

    private static JsonNode getJsonNodeFromUrl(String url) throws IOException {
        return JsonLoader.fromURL(new URL(url));
    }
}
