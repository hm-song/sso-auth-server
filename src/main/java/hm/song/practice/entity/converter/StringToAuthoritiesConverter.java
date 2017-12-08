package hm.song.practice.entity.converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Converter
public class StringToAuthoritiesConverter implements AttributeConverter<List<GrantedAuthority>, String> {

    @Override
    public String convertToDatabaseColumn(List<GrantedAuthority> attribute) {
        return attribute.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<GrantedAuthority> convertToEntityAttribute(String dbData) {
        if (dbData.indexOf(",") >= 0) {
            return Arrays.stream(dbData.split(","))
                    .map(String::trim)
                    .map(SimpleGrantedAuthority::new)
                    .collect(toList());
        } else {
            return Arrays.asList(new SimpleGrantedAuthority(dbData));
        }
    }
}
