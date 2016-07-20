/**
 * Created by admin on 20.07.2016.
 */

import static spark.Spark.*;

public class KonServer {
    public static void main(String[] args) {


        if (true) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
        }

        get("/hello", (req, res) -> "Hello World");

        System.out.println("http://localhost:4567/hello");
    }
}