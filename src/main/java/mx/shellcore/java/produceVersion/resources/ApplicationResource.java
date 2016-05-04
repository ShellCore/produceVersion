package mx.shellcore.java.produceVersion.resources;

import mx.shellcore.java.produceVersion.builders.ApplicationBuilder;
import mx.shellcore.java.produceVersion.models.Application;
import mx.shellcore.java.produceVersion.utils.JsonProcess;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("applications")
public class ApplicationResource {

    private List<Application> applications;

    @GET
    public JsonArray getAll() {
        if (applications != null)
            return ApplicationBuilder.createJsonApplications(applications);
        else
            return Json.createArrayBuilder().build();
    }

    @POST
    @Path("changeVersion")
    public String changeVersion(JsonObject object) {
        if (!(object instanceof JsonObject)) {
            return "El objeto enviado no es un Json. Lo siento.";
        }
        JsonObject jsonObject = (JsonObject) object;

        if (!jsonObject.containsKey("process")) {
            return "El Json enviado no contiene los datos requeridos.";
        }
        int process = jsonObject.getInt("process");
        if (!jsonObject.containsKey("app")) {
            return "El Json enviado no contiene los datos requeridos.";
        }
        Application application;
        try {
            application = ApplicationBuilder.createApplication((JsonObject) jsonObject.get("app"));
        } catch (Exception e) {
            return "Hubo un error en el parseo de los datos de la aplicación:\n" + e.getMessage();
        }
        if (application == null) {
            return "La aplicación no se parseó correctamente.";
        } else {
            int res = -1;
            switch (process) {
                case JsonProcess.CREATE:
                    res = create(application);
                    return "CREATE";
                case JsonProcess.READ:
                    return "READ";
                case JsonProcess.UPDATE:
                    return "UPDATE";
                case JsonProcess.DELETE:
                    return "DELETE";
                default:
                    return "EL Json no posee ninguno de los procesos válidos. Revisa la petición";
            }
        }
    }

    private int create(Application application) {
        if (applications == null) {
            applications = new ArrayList<>();
        }

        applications.add(application);
        return applications.indexOf(application);
    }
}
