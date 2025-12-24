package br.dev.botecodigital.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileManager {

    private static Texture texture;
    
    public static Tile __BLANK__;

    public static Tile FAC_F_01_;
    public static Tile FAC_F_02_;
    public static Tile FAC_F_03_;
    public static Tile FAC_F_04_;
    public static Tile FAC_F_05_;
    public static Tile FAC_F_06_;
    public static Tile FAC_F_01X;
    public static Tile FAC_F_02X;
    public static Tile FAC_F_03X;
    public static Tile FAC_F_04X;
    public static Tile FAC_F_05X;
    public static Tile FAC_F_06X;

    public static Tile FAC_W_01_;
    public static Tile FAC_W_02_;
    public static Tile FAC_W_03_;
    public static Tile FAC_W_04_;
    public static Tile FAC_W_05_;
    public static Tile FAC_W_06_;
    public static Tile FAC_W_07_;
    public static Tile FAC_W_08_;
    public static Tile FAC_W_09_;
    public static Tile FAC_W_10_;
    public static Tile FAC_W_11_;
    public static Tile FAC_W_12_;


    public static void load(){
        TileManager.texture = new Texture("tiles/tile-factory.png");
        TextureRegion[][] frames = TextureRegion.split(TileManager.texture, 50, 50);

        __BLANK__ = null;

        FAC_F_01X = new Tile(frames[0][0]);
        FAC_F_02X = new Tile(frames[0][1]);
        FAC_F_03X = new Tile(frames[0][2]);
        FAC_F_04X = new Tile(frames[0][3]);
        FAC_F_05X = new Tile(frames[0][4]);
        FAC_F_06X = new Tile(frames[0][5]);
        FAC_F_01_ = new Tile(frames[1][0]);
        FAC_F_02_ = new Tile(frames[1][1]);
        FAC_F_03_ = new Tile(frames[1][2]);
        FAC_F_04_ = new Tile(frames[1][3]);
        FAC_F_05_ = new Tile(frames[1][4]);
        FAC_F_06_ = new Tile(frames[1][5]);

        FAC_W_01_ = new Tile(frames[2][0]);
        FAC_W_02_ = new Tile(frames[2][1]);
        FAC_W_03_ = new Tile(frames[2][2]);
        FAC_W_04_ = new Tile(frames[2][3]);
        FAC_W_05_ = new Tile(frames[2][4]);
        FAC_W_06_ = new Tile(frames[2][5]);
        FAC_W_07_ = new Tile(frames[3][0]);
        FAC_W_08_ = new Tile(frames[3][1]);
        FAC_W_09_ = new Tile(frames[3][2]);
        FAC_W_10_ = new Tile(frames[3][3]);
        FAC_W_11_ = new Tile(frames[3][4]);
        FAC_W_12_ = new Tile(frames[3][5]);
    }



    public static void dispose(){
        TileManager.texture.dispose();
    }
}
