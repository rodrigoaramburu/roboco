package br.dev.botecodigital.level.tiles;

import br.dev.botecodigital.level.tiles.tilegroup.FabricaTiles;

public class TileManager {
    
    public static void load(){

        FabricaTiles.load();
   
    }

    public static void dispose(){
        FabricaTiles.dispose();
    }

}
