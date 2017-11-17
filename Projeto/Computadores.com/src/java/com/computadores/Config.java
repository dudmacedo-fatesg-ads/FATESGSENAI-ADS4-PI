package com.computadores;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;

/**
 *
 * @author eduardo
 */
public class Config {

    private static final Config CONFIG = new Config();

    private final String banco_driver;
    private final String banco_url;
    private final String banco_user;
    private final String banco_pass;

    private Config() {
        banco_driver = "org.postgresql.Driver";
        banco_url = "jdbc:postgresql://localhost:5432/computadores";
        banco_user = "postgres";
        banco_pass = "postgres";
    }

//    static {
//        File arq = new File("configuracao.xml");
//        if (arq.exists()) {
//            CONFIG = fromXML(arq);
//            System.out.println(arq.getAbsolutePath());
//            System.out.println("Arquivo lido");
//        } else {
//            CONFIG = new Config();
//            CONFIG.toXML(arq);
//            System.out.println(arq.getAbsolutePath());
//            System.out.println("Arquivo gravado");
//        }
//    }

    public static String getBancoDriver() {
        return CONFIG.banco_driver;
    }

    public static String getBancoURL() {
        return CONFIG.banco_url;
    }

    public static String getBancoUser() {
        return CONFIG.banco_user;
    }

    public static String getBancoPass() {
        return CONFIG.banco_pass;
    }

//    private void toXML(File arq) {
//        XStream stream = getXStream();
//        stream.toXML(arq);
//    }
//
//    private static Config fromXML(File arq) {
//        XStream stream = getXStream();
//        return (Config) stream.fromXML(arq);
//    }
//
//    private static XStream getXStream() {
//        XStream stream = new XStream(new DomDriver());
//
//        stream.alias("config", Config.class);
//
//        return stream;
//    }
}
