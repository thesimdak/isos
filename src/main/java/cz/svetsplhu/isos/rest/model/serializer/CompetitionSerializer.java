package cz.svetsplhu.isos.rest.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cz.svetsplhu.isos.rest.model.CompetitionDto;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Serializer for a competition.
 */
public class CompetitionSerializer extends StdSerializer<CompetitionDto> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM yyyy");

    public CompetitionSerializer() {
        this(null);
    }

    public CompetitionSerializer(Class<CompetitionDto> t) {
        super(t);
    }

    @Override
    public void serialize(
            CompetitionDto value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("competitionName", value.getCompetitionName());
        jgen.writeStringField("date", value.getDate().format(formatter));
        jgen.writeStringField("place", value.getPlace());
        jgen.writeStringField("judge", value.getJudge());
        jgen.writeStringField("sensorInstallation", value.getSensorInstallation());
        jgen.writeStringField("starter", value.getStarter());
        jgen.writeStringField("type", value.getType());
        jgen.writeEndObject();
    }
}
