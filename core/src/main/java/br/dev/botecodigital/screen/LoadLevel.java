package br.dev.botecodigital.screen;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.badlogic.gdx.Gdx;

import br.dev.botecodigital.level.Level;

public class LoadLevel {
    
    public List<Level> load(String levelFolder){
        List<Level> levels = new ArrayList<Level>();
        try {
            ClassLoader levelClassLoader = this.loadJars(levelFolder);
            
            ServiceLoader<Level> loader = ServiceLoader.load(Level.class, levelClassLoader);
            
            for (Level level : loader) {
                levels.add(level);
            }
            return levels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return levels;
    }

    private ClassLoader loadJars(String levelFolder) throws MalformedURLException {
        File levelFolderFile = new File(levelFolder);
         
        File folder = levelFolderFile.isAbsolute()
            ? Gdx.files.absolute(levelFolder).file()
            : Gdx.files.internal("../"+levelFolder).file();
        

        List<URL> urls = new ArrayList<>();

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".jar")) {
                urls.add(file.toURI().toURL());
            }
        }

        return new URLClassLoader(
            urls.toArray(new URL[0]),
            ClassLoader.getSystemClassLoader()
        );
    }


}
