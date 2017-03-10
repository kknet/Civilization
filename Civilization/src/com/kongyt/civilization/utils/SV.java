package com.kongyt.civilization.utils;

import com.badlogic.gdx.Application;


public class SV {
	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 540;
	public static final boolean SCREEN_MODE = false;
	
	// 场景ID
	public static final int SCENE_LOADING = 0; // 游戏加载场景
	public static final int SCENE_MENU = 1; // 游戏主菜单
	public static final int SCENE_GAME = 2; // 游戏场景

	// 游戏信息
	public static final String GameName = "Civilization"; // 游戏名字

	// DEBUG信息
	public static boolean DEBUG = true; // 是否是DEBUG模式
	public static boolean GAME_MAP_DEBUG = false; // 场景地图是否开启DEBUG模式

	// 日志输出等级
	public static final int LOG_NONE = Application.LOG_NONE;
	public static final int LOG_DEBUG = Application.LOG_DEBUG;
	public static final int LOG_INFO = Application.LOG_INFO;
	public static final int LOG_ERROR = Application.LOG_ERROR;
	
	// 逻辑帧间隔
	public static final float PHYSICS_TIME_SPAN = 1 / 50f; // 物理帧时间
	
	// Handler消息值
	public static final int RECV_SERVER_DATA = 1;
	
	// 地图块掩码说明
	// 高8位，属性
	// 次高8位地图块类别，包括资源，建筑
	// 次低8位地图块，具体物体
	// 低8位，同一物体的不同表现
	
	// 地图块属性
	public static final int ATTR_TYPE_NO_WALKABLE = 0;	// 不可行走
	public static final int ATTR_TYPE_WALKABLE = 1;		// 可行走
	
	// 第一类型为空
	public static final int FIRST_TYPE_NONE   = 0;
	
	// 地图块类型
	public static final int MAP_TYPE_RES	= 1;
	public static final int MAP_TYPE_BUILDING = 2;
	
	
	// 第二类型为空
	public static final int SECOND_TYPE_NONE = 0;
	
	// 资源类型
	public static final int RES_TYPE_COAL	= 1;		// 煤炭
	public static final int RES_TYPE_COPPER = 2;		// 铜
	public static final int RES_TYPE_IRON   = 3;		// 铁
	public static final int RES_TYPE_OIL    = 4;		// 石油
	public static final int RES_TYPE_STONE  = 5;		// 石头
	public static final int RES_TYPE_WOOD   = 6;		// 木材
	
	// 纹理为空
	public static final int TEX_ID_NONE	= 0;
	
	public static int getMask(int attr, int firstType, int secondType, int texId){
		int res = 0;
		res |= (attr&0xFF) << 24;
		res |= (firstType&0xFF) << 16;
		res |= (secondType&0xFF) << 8;
		res |= (texId&0xFF);		
		return res;
	}
	
	public static boolean isWalkable(int mask){
		if(((mask>>24) & ATTR_TYPE_WALKABLE) == ATTR_TYPE_WALKABLE){
			return true;
		}else{
			return false;
		}
	}
	
	public static int getFirstType(int mask){
		return ((mask >> 16)&0xFF);
	}
	
	public static int getSecondType(int mask){
		return ((mask >> 8)&0xFF);
	}
	
	public static int getTexID(int mask){
		return (mask&0xFF);
	}
	
}
