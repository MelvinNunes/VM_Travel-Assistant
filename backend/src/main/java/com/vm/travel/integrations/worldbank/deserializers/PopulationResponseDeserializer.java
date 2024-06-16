package com.vm.travel.integrations.worldbank.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vm.travel.integrations.worldbank.dto.PopulationData;
import com.vm.travel.integrations.worldbank.dto.PopulationResponse;

import java.io.IOException;
import java.util.List;

public class PopulationResponseDeserializer extends StdDeserializer<PopulationResponse> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public PopulationResponseDeserializer() {
        this(null);
    }

    public PopulationResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public PopulationResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode gdpDataNode = node.get(1);

        List<PopulationData> populationData = mapper.readValue(
                gdpDataNode.traverse(jsonParser.getCodec()),
                mapper.getTypeFactory().constructCollectionType(List.class, PopulationData.class)
        );

        return new PopulationResponse(
                populationData
        );
    }
}
