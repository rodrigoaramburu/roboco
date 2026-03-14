package br.dev.botecodigital.level.tiles.tilegroup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.dev.botecodigital.level.tiles.Tile;
import br.dev.botecodigital.level.tiles.TileAnimate;
import br.dev.botecodigital.level.tiles.TileImage;
import br.dev.botecodigital.level.tiles.TileText;
import br.dev.botecodigital.level.tiles.TileImage.TileDirection;

public class FabricaTiles{

    private static Texture texture;
    private static Texture textureMarkers;

    public static Tile BLANK__;

    public static Tile BACK010;
    public static Tile BACK020;
    public static Tile BACK030;
    public static Tile BACK031;
    public static Tile BACK040;
    public static Tile BACK041;
    public static Tile BACK042;
    public static Tile BACK043;
    public static Tile BACK050;
    public static Tile BACK060;

    public static Tile BACK070;
    public static Tile BACK080;
    public static Tile BACK090;
    public static Tile BACK091;
    public static Tile BACK100;
    public static Tile BACK101;
    public static Tile BACK102;
    public static Tile BACK103;
    public static Tile BACK110;
    public static Tile BACK120;

    public static Tile OBST010;
    public static Tile OBST020;
    public static Tile OBST030;
    public static Tile OBST040;
    public static Tile OBST050;
    public static Tile OBST060;
    public static Tile OBST070;
    public static Tile OBST080;
    public static Tile OBST090;
    public static Tile OBST100;
    public static Tile OBST110;
    public static Tile OBST120;
    public static Tile OBST130;
    public static Tile OBST131;
    public static Tile OBST140;
    public static Tile OBST150;
    public static Tile OBST160;
    public static Tile OBST170;
    public static Tile OBST180;
    public static Tile OBST190;
    public static Tile OBST200;
    public static Tile OBST210;
    public static Tile OBST220;
    public static Tile OBST230;
    public static Tile OBST240;
    public static Tile OBST25A;
    
    public static Tile MARK010;
    public static Tile MARK020;
    public static Tile MARK02A;
    public static Tile MARK02B;
    public static Tile MARK02C;
    public static Tile MARK030;

    private static Texture fireAnimateTexture;


    public static void load(){
        texture = new Texture("tiles/tile-factory.png");
        TextureRegion[][] frames = TextureRegion.split(
            texture, 
            50, 
            50
        );

        BLANK__ = null;
        
        BACK010 = new TileImage(frames[0][0]);
        BACK020 = new TileImage(frames[0][1]);
        BACK030 = new TileImage(frames[0][2]);
        BACK031 = new TileImage(frames[0][2], TileDirection.RIGHT);
        BACK040 = new TileImage(frames[0][3], TileDirection.UP);
        BACK041 = new TileImage(frames[0][3], TileDirection.RIGHT);
        BACK042 = new TileImage(frames[0][3], TileDirection.DOWN);
        BACK043 = new TileImage(frames[0][3], TileDirection.LEFT);
        BACK050 = new TileImage(frames[0][4]);
        BACK060 = new TileImage(frames[0][5]);

        BACK070 = new TileImage(frames[1][0]);
        BACK080 = new TileImage(frames[1][1]);
        BACK090 = new TileImage(frames[1][2]);
        BACK091 = new TileImage(frames[1][2], TileDirection.RIGHT);
        BACK100 = new TileImage(frames[1][3], TileDirection.UP);
        BACK101 = new TileImage(frames[1][3], TileDirection.RIGHT);
        BACK102 = new TileImage(frames[1][3], TileDirection.DOWN);
        BACK103 = new TileImage(frames[1][3], TileDirection.LEFT);
        BACK110 = new TileImage(frames[1][4]);
        BACK120 = new TileImage(frames[1][5]);



        OBST010 = new TileImage(frames[2][0]);
        OBST020 = new TileImage(frames[2][1]);
        OBST030 = new TileImage(frames[2][2]);
        OBST040 = new TileImage(frames[2][3]);
        OBST050 = new TileImage(frames[2][4]);
        OBST060 = new TileImage(frames[2][5]);

        OBST070 = new TileImage(frames[3][0]);
        OBST080 = new TileImage(frames[3][1]);
        OBST090 = new TileImage(frames[3][2]);
        OBST100 = new TileImage(frames[3][3]);
        OBST110 = new TileImage(frames[3][4]);
        OBST120 = new TileImage(frames[3][5]);

        OBST130 = new TileImage(frames[4][0]);
        OBST140 = new TileImage(frames[4][1]);
        OBST150 = new TileImage(frames[4][2]);
        OBST160 = new TileImage(frames[4][3]);
        OBST170 = new TileImage(frames[4][4]);
        OBST180 = new TileImage(frames[4][5]);

        OBST190 = new TileImage(frames[5][0]);
        OBST200 = new TileImage(frames[5][1]);
        OBST210 = new TileImage(frames[5][2]);
        OBST220 = new TileImage(frames[5][3]);
        OBST230 = new TileImage(frames[5][4]);
        OBST240 = new TileImage(frames[5][5]);


        fireAnimateTexture = new Texture(Gdx.files.internal("tiles/animate-fire.png"));
        TextureRegion[][] fireAnimateTextureRegion = TextureRegion.split(
            fireAnimateTexture,
				50,
				50
        );

       OBST25A = new TileAnimate(fireAnimateTextureRegion[0], 0.15f);
        
        
        textureMarkers = new Texture("tiles/markers.png");
        TextureRegion[][] frameMarkers = TextureRegion.split(
            textureMarkers, 
            50, 
            50
        );
        
        MARK010 = new TileImage(frameMarkers[0][0]);
        MARK020 = new TileImage(frameMarkers[0][1]);
        MARK02A = new TileText(frameMarkers[0][1], "A");
        MARK02B = new TileText(frameMarkers[0][1], "B");
        MARK02C = new TileText(frameMarkers[0][1], "C");
        MARK030 = new TileImage(frameMarkers[0][2]);
        
    }

    public static void dispose() {
        texture.dispose();
        textureMarkers.dispose();
        fireAnimateTexture.dispose();
    }


}
