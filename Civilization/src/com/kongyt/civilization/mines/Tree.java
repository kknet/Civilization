package com.kongyt.civilization.mines;

import com.badlogic.gdx.graphics.Texture;
import com.kongyt.civilization.utils.SV;

public class Tree extends BaseMine {

	public Tree(Texture tex) {
		super(tex);
		// TODO Auto-generated constructor stub
		this.setMask(SV.getMask(SV.ATTR_TYPE_NO_WALKABLE, SV.MAP_TYPE_RES, SV.RES_TYPE_WOOD, 0));
		this.img.setPosition(-(this.img.getWidth()-16)/2, 0);
	}
}
