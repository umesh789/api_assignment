package base;

import utility.DataReaderManager;

public class TestBase {
    public static String g_appConfigXmlPath = System.getProperty("user.dir") + "/testData/environmentConfig.xml";
    protected static String API_environment = null;
    protected static String Application_URL = null;

    static {
        try {
            API_environment = DataReaderManager.readConfigXml("API_environment");
            Application_URL = DataReaderManager.readConfigXml("Application_URL");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
