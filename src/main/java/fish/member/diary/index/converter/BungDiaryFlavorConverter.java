package fish.member.diary.index.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fish.member.diary.index.dto.BungDiaryFlavor;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@Converter
public class BungDiaryFlavorConverter implements AttributeConverter<List<BungDiaryFlavor>, String> {
    private static final Logger log = LoggerFactory.getLogger(BungDiaryFlavorConverter.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<BungDiaryFlavor> bungDiaryFlavor) {
        // Transfer DetailFlavor to Json
        try {
            return objectMapper.writeValueAsString(bungDiaryFlavor);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize List<DetailFlavor> to Json. Input: {}", bungDiaryFlavor, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BungDiaryFlavor> convertToEntityAttribute(String json) {
        // Transfer Json To DetailFlavor
        try {
            return Arrays.asList(objectMapper.readValue(json, BungDiaryFlavor[].class));
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize JSON to DetailFlavor. Input: {}", json, e);
            throw new RuntimeException(e);
        }
    }
}
