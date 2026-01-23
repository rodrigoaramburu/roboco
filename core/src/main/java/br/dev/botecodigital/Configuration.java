package br.dev.botecodigital;

import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Configuration {

    private static boolean levelBasicLoad = true; 
    private static String levelExternalPath = "levels"; 

    public static void load(){
        Properties prop = new Properties();
        try {
            prop.load( Gdx.files.internal("../config.properties").read() );

            levelBasicLoad = ((String) prop.getOrDefault("level.basic.load", "true")).equals("true");
            levelExternalPath = (String) prop.getOrDefault("level.external.path", "levels");
            
        } catch (IOException | GdxRuntimeException e) {
            Gdx.app.log("CONFIGURATION", "config.properties não encontrado");
        }
    }

    public static boolean getLevelBasicLoad(){
        return levelBasicLoad;
    }
    public static String getLevelExternalPath(){
        return levelExternalPath;
    }

}
