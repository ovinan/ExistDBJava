package ejemploexistdb;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

/**
 * Obtencion del contenido del documento de una coleccion de la BD en eXist DB
 * PREREQUISITOS: 
 * (1) Importar en el proyecto las librerias:
 * XMLDB-API, XMLRPC-CLIENT, XMLRPC-COMMON,LOG4J-API, LOG4J-CORE, WS-COMMONS, COMMONS-IO
 * y EXIST-CORE.
 * (2) Crear una coleccion llamada PRUEBAS, con un documento libros.xml
 * 
 * @author ovinan
 */
public class EjemploExistdb {
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";

    /**
     * args[0] Should be the name of the collection to access args[1] Should be
     * the XPath expression to execute
     */
    public static void main(String args[]) throws Exception {

        final String driver = "org.exist.xmldb.DatabaseImpl";

        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;
        try {
            col = DatabaseManager.getCollection(URI + "pruebas");
            XPathQueryService xpqs = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");

            //ResourceSet result = xpqs.query("for $titulo in /bookstore/book/title/text()\n" +"return <titulo>{$titulo}</titulo>");
            ResourceSet result = xpqs.query("for $titulo in /bookstore/book/title/text() return <titulo>{$titulo}</titulo>");
            ResourceIterator i = result.getIterator();
            Resource res = null;
            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    System.out.println(res.getContent());
                } finally {

                }
            }
        } finally {
            //dont forget to cleanup
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }
   
}
