package com.kongyt.civilization.net;

import com.kongyt.civilization.managers.GM;

public abstract class BaseMsgModule {
	public int moduleId = 0;
	public void onReceiveMsg(MsgPacket msgPacket){
		GM.instance().logD("收到消息，id=" + Integer.toHexString(msgPacket.msgId));
		GM.instance().logD("消息长度msgLen=" + msgPacket.msgLen + "字节(含消息头)");
	}
}
