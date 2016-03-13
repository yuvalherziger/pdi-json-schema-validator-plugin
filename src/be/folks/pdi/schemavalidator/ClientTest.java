package be.folks.pdi.schemavalidator;

import com.github.fge.jsonschema.core.report.ProcessingReport;
import junit.framework.TestCase;

public class ClientTest extends TestCase {

    public Client client;
    @org.junit.Before
    public void setUp() throws Exception {
        this.client = null;
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testGetReport() throws Exception {

    }

    @org.junit.Test
    public void testValidateWithRemoteSchema() throws Exception {
        String object = "{\n" +
                "  \"address\": {\n" +
                "    \"streetAddress\": \"21 2nd Street\",\n" +
                "    \"city\": \"New York\"\n" +
                "  },\n" +
                "  \"phoneNumber\": [\n" +
                "    {\n" +
                "      \"location\": \"home\",\n" +
                "      \"code\": 44\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String schema = "https://api.myjson.com/bins/2ia87";
        this.client = new Client(object, schema, true);
        assertTrue(this.client.validate());
    }

    @org.junit.Test
    public void testValidateWithSchemaFromString() throws Exception {
        String object = "{\n" +
                "  \"address\": {\n" +
                "    \"streetAddress\": \"21 2nd Street\",\n" +
                "    \"city\": \"New York\"\n" +
                "  },\n" +
                "  \"phoneNumber\": [\n" +
                "    {\n" +
                "      \"location\": \"home\",\n" +
                "      \"code\": 44\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String schema = "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"id\": \"http://jsonschema.net\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"address\": {\n" +
                "      \"id\": \"http://jsonschema.net/address\",\n" +
                "      \"type\": \"object\",\n" +
                "      \"properties\": {\n" +
                "        \"streetAddress\": {\n" +
                "          \"id\": \"http://jsonschema.net/address/streetAddress\",\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"city\": {\n" +
                "          \"id\": \"http://jsonschema.net/address/city\",\n" +
                "          \"type\": \"string\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"required\": [\n" +
                "        \"streetAddress\",\n" +
                "        \"city\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"phoneNumber\": {\n" +
                "      \"id\": \"http://jsonschema.net/phoneNumber\",\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"id\": \"http://jsonschema.net/phoneNumber/0\",\n" +
                "        \"type\": \"object\",\n" +
                "        \"properties\": {\n" +
                "          \"location\": {\n" +
                "            \"id\": \"http://jsonschema.net/phoneNumber/0/location\",\n" +
                "            \"type\": \"string\"\n" +
                "          },\n" +
                "          \"code\": {\n" +
                "            \"id\": \"http://jsonschema.net/phoneNumber/0/code\",\n" +
                "            \"type\": \"integer\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\n" +
                "    \"address\",\n" +
                "    \"phoneNumber\"\n" +
                "  ]\n" +
                "}";
        this.client = new Client(object, schema, false);
        assertTrue(this.client.validate());
    }

    @org.junit.Test
    public void testGetReportWithSchemaFromString()  throws Exception {
        String object = "{\n" +
                "  \"address\": {\n" +
                "    \"streetAddress\": \"21 2nd Street\",\n" +
                "    \"city\": \"New York\"\n" +
                "  },\n" +
                "  \"phoneNumber\": [\n" +
                "    {\n" +
                "      \"location\": \"home\",\n" +
                "      \"code\": 44\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        String schema = "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"id\": \"http://jsonschema.net\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"address\": {\n" +
                "      \"id\": \"http://jsonschema.net/address\",\n" +
                "      \"type\": \"object\",\n" +
                "      \"properties\": {\n" +
                "        \"streetAddress\": {\n" +
                "          \"id\": \"http://jsonschema.net/address/streetAddress\",\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"city\": {\n" +
                "          \"id\": \"http://jsonschema.net/address/city\",\n" +
                "          \"type\": \"string\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"required\": [\n" +
                "        \"streetAddress\",\n" +
                "        \"city\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"phoneNumber\": {\n" +
                "      \"id\": \"http://jsonschema.net/phoneNumber\",\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": {\n" +
                "        \"id\": \"http://jsonschema.net/phoneNumber/0\",\n" +
                "        \"type\": \"object\",\n" +
                "        \"properties\": {\n" +
                "          \"location\": {\n" +
                "            \"id\": \"http://jsonschema.net/phoneNumber/0/location\",\n" +
                "            \"type\": \"string\"\n" +
                "          },\n" +
                "          \"code\": {\n" +
                "            \"id\": \"http://jsonschema.net/phoneNumber/0/code\",\n" +
                "            \"type\": \"integer\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\n" +
                "    \"address\",\n" +
                "    \"phoneNumber\"\n" +
                "  ]\n" +
                "}";
        this.client = new Client(object, schema, false);
        ProcessingReport report = this.client.getReport();
        assertTrue(report.isSuccess());
    }
}