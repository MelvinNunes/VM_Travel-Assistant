package com.vm.travel.integrations.restcountries.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vm.travel.integrations.restcountries.dto.RestCountriesData;
import com.vm.travel.integrations.restcountries.dto.RestCountriesResponse;
import com.vm.travel.integrations.worldbank.dto.GDPData;
import com.vm.travel.integrations.worldbank.dto.GDPResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestCountriesDeserializer extends StdDeserializer<RestCountriesResponse> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public RestCountriesDeserializer() {
        this(null);
    }

    public RestCountriesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RestCountriesResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<RestCountriesData> restCountriesData = new ArrayList<>();

        if (node.isArray()) {
            for (JsonNode item : node) {
                RestCountriesData data = mapper.treeToValue(item, RestCountriesData.class);
                restCountriesData.add(data);
            }
        } else {
            throw new JsonParseException(jsonParser, "Expected an array of RestCountriesData objects");
        }

        return new RestCountriesResponse(restCountriesData);
    }
}
