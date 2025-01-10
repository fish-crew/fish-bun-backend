package fish.common.detail.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.common.detail.dto.DetailFlavor;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Converter
public class DetailFlavorConverter implements AttributeConverter<List<DetailFlavor>, String> {
    private static final Logger log = LoggerFactory.getLogger(DetailFlavorConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<DetailFlavor> detailFlavor) {
        // Transfer DetailFlavor to Json
        try {
            return objectMapper.writeValueAsString(detailFlavor);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize List<DetailFlavor> to Json. Input: {}", detailFlavor, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DetailFlavor> convertToEntityAttribute(String json) {
        // Transfer Json To DetailFlavor
        try {
            return Arrays.asList(objectMapper.readValue(json, DetailFlavor[].class));
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize JSON to DetailFlavor. Input: {}", json, e);
            throw new RuntimeException(e);
        }
    }
}
