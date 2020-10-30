package com.manywho.services.mdm;

import com.manywho.sdk.services.servers.EmbeddedServer;
import com.manywho.sdk.services.servers.Servlet3Server;
import com.manywho.sdk.services.servers.undertow.UndertowServer;
import javax.ws.rs.ApplicationPath;
//TODO EXECUTE
//ATOMMAPEXTENSION">AtomMapExtension
//ENVIRONMENTMAPEXTENSON">EnvironmentMapExtension

@ApplicationPath("/")
public class Application extends Servlet3Server  {

    public Application() {
        this.addModule(new ApplicationAtomsphereModule());
        this.setApplication(Application.class);
        this.start();
    }

    public static void main(String[] args) throws Exception {
        EmbeddedServer server = new UndertowServer();

        server.addModule(new ApplicationAtomsphereModule());
        server.setApplication(Application.class);
//        server.start("/api/mdh/1", 80);
        server.start();
    }
}
