package com.modulojames.noughtsandcrosses.GameLogic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.modulojames.noughtsandcrosses.R;

/**
 * Created by Sajuuk-Khar on 8/05/2018.
 */

public class Tile extends android.support.v7.widget.AppCompatImageButton{

    private Player owner;
    private Drawable displayImage;

    public Tile (Context context){
        this(context, null, R.attr.imageButtonStyle);
    }

    public Tile (Context context, AttributeSet attr){
        this(context, attr, R.attr.imageButtonStyle);
    }

    public Tile (Context context, AttributeSet attr,int defStyleAttr){
        super(context, attr, defStyleAttr);
        owner = Player.Unowned;
        displayImage = context.getDrawable(owner.getDrawable());

        setImageDrawable(displayImage);
        setContentDescription(owner.toString());
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
