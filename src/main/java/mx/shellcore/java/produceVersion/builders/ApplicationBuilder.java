package mx.shellcore.java.produceVersion.builders;

import mx.shellcore.java.produceVersion.models.Application;

import javax.json.*;
import java.util.List;

public class ApplicationBuilder {

    public static JsonArray createJsonApplications(List<Application> applications) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Application application : applications) {
            jsonArrayBuilder.add(createJsonApplication(application));
        }

        return jsonArrayBuilder.build();
    }

    public static JsonObject createJsonApplication(Application application) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        jsonObjectBuilder.add("id", application.getId());
        jsonObjectBuilder.add("name", application.getName());
        jsonObjectBuilder.add("version", application.getVersion());
        jsonObjectBuilder.add("type", application.getType());

        return jsonObjectBuilder.build();
    }

    public static Application createApplication(JsonObject app) throws Exception {
        Application application = new Application();
        if (app.containsKey("id"))
            application.setType(app.getInt("id"));
        if (app.containsKey("name"))
            application.setName(app.getString("name"));
        if (app.containsKey("type"))
            application.setType(app.getInt("type"));
        if (app.containsKey("version"))
            application.setVersion(app.getString("version"));
        return application;
    }
}
