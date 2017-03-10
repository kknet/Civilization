package com.kongyt.civilization.mines;

import com.badlogic.gdx.graphics.Texture;
import com.kongyt.civilization.utils.SV;

public class IronMine extends BaseMine {

	public IronMine(Texture tex) {
		super(tex);
		// TODO Auto-generated constructor stub
		this.setMask(SV.getMask(SV.ATTR_TYPE_WALKABLE, SV.MAP_TYPE_RES, SV.RES_TYPE_IRON, 0));
	}

}
