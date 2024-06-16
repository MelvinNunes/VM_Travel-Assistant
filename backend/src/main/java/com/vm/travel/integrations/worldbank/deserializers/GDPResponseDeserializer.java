package com.vm.travel.integrations.worldbank.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vm.travel.integrations.worldbank.dto.GDPData;
import com.vm.travel.integrations.worldbank.dto.GDPResponse;

import java.io.IOException;
import java.util.List;

public class GDPResponseDeserializer extends StdDeserializer<GDPResponse> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public GDPResponseDeserializer() {
        this(null);
    }

    public GDPResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GDPResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode gdpDataNode = node.get(1);

        List<GDPData> gdpData = mapper.readValue(
                gdpDataNode.traverse(jsonParser.getCodec()),
                mapper.getTypeFactory().constructCollectionType(List.class, GDPData.class)
        );

        return new GDPResponse(
                gdpData
        );
    }
}
