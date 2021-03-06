package cz.svetsplhu.isos;

import cz.svetsplhu.isos.rest.*;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CompetitionResource.class);
        register(NominationResource.class);
        register(ResultResource.class);
        register(RopeClimberResource.class);
        register(NominationCriteriaResource.class);
        register(CorsFilter.class);
        register(MultiPartFeature.class);
    }
}