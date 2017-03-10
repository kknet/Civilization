package com.kongyt.civilization.mines;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kongyt.civilization.utils.SV;

public class CopperMine extends BaseMine {

	public CopperMine(Texture tex) {
		super(tex);
		// TODO Auto-generated constructor stub
		this.setMask(SV.getMask(SV.ATTR_TYPE_WALKABLE, SV.MAP_TYPE_RES, SV.RES_TYPE_COPPER, 0));
	}
	
}
