package apiStaticData;

import base.TestBase;
import lombok.Data;

@Data
public class RestEndPoint extends TestBase {

    private String PET_STORE_HOST;
    //private String Application_URL;
    public static final String  Application_URL = "https://www.demoblaze.com/index.html";

        public RestEndPoint(String env) {
        PET_STORE_HOST = "https://petstore.swagger.io/" + env + "/";

    }
}
