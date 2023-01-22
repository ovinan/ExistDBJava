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
public class AccesoExistdb {
        protected static String DRIVER = "org.exist.xmldb.DatabaseImpl"; 
        protected static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc"; 
        protected static String collectionPath = "/db/pruebas/"; 
        protected static String resourceName = "libros.xml"; 
 
        public static void main(String[] args) throws Exception { 
 
                // initialize database driver 
                Class cl = Class.forName(DRIVER); 
                Database database = (Database) cl.newInstance(); 
                DatabaseManager.registerDatabase(database); 
 
                // get the collection 
                Collection col = DatabaseManager.getCollection(URI + collectionPath); 
 
                // get the content of a document 
                System.out.println("Get the content of " + resourceName); 
                XMLResource res = (XMLResource) col.getResource(resourceName); 
                if (res == null) { 
                        System.out.println("document not found!"); 
                } else { 
                        System.out.println(res.getContent()); 
                } 
        }     
}
