package com.kongyt.civilization.utils;

import com.badlogic.gdx.Application;


public class SV {
	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 540;
	public static final boolean SCREEN_MODE = false;
	
	// 场景ID
	public static final int SCENE_LOADING = 0; // 游戏加载场景
	public static final int SCENE_LOGIN	= 1; // 登陆
	public static final int SCENE_MENU = 2; // 游戏主菜单
	public static final int SCENE_GAME = 3; // 游戏场景

	// 游戏信息
	public static final String GameName = "文明"; // 游戏名字

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
}
