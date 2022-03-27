package cz.svetsplhu.isos.rest.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cz.svetsplhu.isos.rest.model.ParticipationDto;

import java.io.IOException;

/**
 * Serializer for a participation.
 */
public class ParticipationSerializer extends StdSerializer<ParticipationDto> {

    public ParticipationSerializer() {
        this(null);
    }

    public ParticipationSerializer(Class<ParticipationDto> t) {
        super(t);
    }

    @Override
    public void serialize(
            ParticipationDto value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        Double topTime = null;
        Double tempTime;

        jgen.writeStartObject();
        jgen.writeStringField("firstName", value.getRopeClimber().getFirstName());
        jgen.writeStringField("lastName", value.getRopeClimber().getLastName());
        jgen.writeNumberField("yearOfBirth", value.getRopeClimber().getYearOfBirth());
        jgen.writeStringField("organization", value.getOrganization());
        jgen.writeNumberField("year", value.getCompetition().getDate().getYear());
        jgen.writeStringField("competitionName", value.getCompetition().getName());
        if (value.getTimeList().size() >= 1) {
            topTime = value.getTimeList().get(0).getTime();
            jgen.writeNumberField("time1", value.getTimeList().get(0).getTime());
        }
        if (value.getTimeList().size() >= 2) {
            tempTime = value.getTimeList().get(1).getTime();
            if (topTime != null && tempTime < topTime) {
                topTime = tempTime;
            }
            jgen.writeNumberField("time2", value.getTimeList().get(1).getTime());
        }
        if (value.getTimeList().size() >= 3) {
            tempTime = value.getTimeList().get(2).getTime();
            if (topTime != null && tempTime < topTime) {
                topTime = tempTime;
            }
            jgen.writeNumberField("time3", value.getTimeList().get(2).getTime());
        }
        if (value.getTimeList().size() >= 4) {
            tempTime = value.getTimeList().get(3).getTime();
            if (topTime != null && tempTime < topTime) {
                topTime = tempTime;
            }
            jgen.writeNumberField("time4", value.getTimeList().get(3).getTime());
        }
        if (topTime != null) {
            jgen.writeNumberField("topTime", topTime);
        }
        jgen.writeNumberField("resultRank", value.getResultRank());
        int compYear = value.getCompetition().getDate().getYear();
        int ropeClimperAge = compYear - value.getRopeClimber().getYearOfBirth();
        jgen.writeBooleanField("isSenior", value.getCompetition().getDate().getYear() >= 2018 && (ropeClimperAge >= 41 && ropeClimperAge <= 50));
        jgen.writeEndObject();
    }
}