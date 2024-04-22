package bashMe.utils;

public enum ScriptPaths {

    TEST("test.sh"),
    INSTALL_MONGO("install/mongo.sh"),
    INSTALL_POSTGRES("install/postgres.sh"),
    UNINSTALL_MONGO("uninstall/mongo.sh"),
    UNINSTALL_POSTGRES("uninstall/postgres.sh");


    private final static String BASE_PATH = "/home/amol/Downloads/demo/src/main/resources/scripts/";
    private final String path;

    // Constructor
    ScriptPaths(String path) {
        this.path = path;
    }

    // Method to get the full path
    public String getPath() {
        return BASE_PATH + path;
    }
}
