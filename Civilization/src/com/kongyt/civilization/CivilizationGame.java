package com.kongyt.civilization;

import com.badlogic.gdx.Game;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.utils.SV;

public class CivilizationGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		// 设置输出日志等级
		GM.instance().setLogLevel(SV.LOG_DEBUG);
		
		GM.instance().logD("游戏开始");
		
		// 向游戏管理器中注册游戏实例
		GM.instance().registerGame(this);
	
		// 切换场景到主菜单
		GM.instance().changeScene(SV.SCENE_GAME);
	}

}
