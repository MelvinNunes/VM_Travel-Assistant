package com.vm.travel.integrations.geodb.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vm.travel.integrations.geodb.dto.GeoDbCity;
import com.vm.travel.integrations.geodb.dto.GeoDbRes;
import com.vm.travel.integrations.worldbank.dto.GDPData;
import com.vm.travel.integrations.worldbank.dto.GDPResponse;

import java.io.IOException;
import java.util.List;

public class GeoDbDeserializer  extends StdDeserializer<GeoDbRes> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public GeoDbDeserializer() {
        this(null);
    }

    public GeoDbDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GeoDbRes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        JsonNode gdpDataNode = node.get("data");

        List<GeoDbCity> data = mapper.readValue(
                gdpDataNode.traverse(jsonParser.getCodec()),
                mapper.getTypeFactory().constructCollectionType(List.class, GeoDbCity.class)
        );

        return new GeoDbRes(
                data
        );
    }
}
