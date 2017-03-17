package com.kongyt.civilization.net;


import com.google.protobuf.InvalidProtocolBufferException;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.messages.Message.LoginReq;
import com.kongyt.civilization.messages.Message.Msg;
import com.kongyt.civilization.messages.Message.ReconnectReq;
import com.kongyt.civilization.messages.Message.RegisterReq;
import com.kongyt.civilization.messages.Message.Request;
import com.kongyt.civilization.messages.Message.Response;



public class BaseModule extends BaseMsgModule {

	public BaseModule(){
		this.moduleId = 0x00010000;
	}

	public boolean isLogin = false;
	
	// 消息分发处理函数
	@Override
	public void onReceiveMsg(MsgPacket msgPacket) {
		// TODO Auto-generated method stub
		super.onReceiveMsg(msgPacket);	
		switch(msgPacket.msgId){
		
		// 处理注册响应消息
		case Msg.Register_Res_VALUE:
			onRegisterRes(msgPacket);
			break;
			
		// 处理登陆响应消息
		case Msg.Login_Res_VALUE:
			onLoginRes(msgPacket);
			break;
			
		// 处理重新连接响应消息
		case Msg.Reconnect_Res_VALUE:
			onReconnectRes(msgPacket);
			break;
			
		default:
			GM.instance().logD("未知消息类型");
			break;			
		}
	}
	
	// 收到对手消息
	public void onReceivePeerMsg(MsgPacket msgPacket){
		
		switch (msgPacket.msgId) {

		// 处理注册响应消息
		case Msg.Register_Res_VALUE:
			onRegisterRes(msgPacket);
			break;
			
		default:
			GM.instance().logD("未知消息类型(From Peer Client)");
		}
	}



	// 发送注册消息
	public void sendRegisterReq(String userName, String password){
		Request req = Request.newBuilder()
							.setRegisterReq(RegisterReq.newBuilder()
													.setUserName(userName)
													.setPassword(password)
													.build())
							.build();
		byte[] reqBytePacket = req.toByteArray();
		
		MsgPacket msgPacket = new MsgPacket();
		msgPacket.msgId = Msg.Register_Req_VALUE;
		msgPacket.msgLen = reqBytePacket.length + 8;
		msgPacket.msgData = reqBytePacket;
		GM.instance().getNet().sendMsg(msgPacket);
	}
	
	
	// 响应注册消息
	private void onRegisterRes(MsgPacket msgPacket) {
		// TODO Auto-generated method stub
		GM.instance().logD("收到注册响应消息");
		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			if (res.getResult() == true) {
				String uuid = res.getRegisterRes().getUuid();
				GM.instance().setUuid(uuid);
				GM.instance().logD("注册成功(uuid="+uuid + ")");
			} else {
				GM.instance().logD("注册失败");
				GM.instance().logD("错误描述信息: " + res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// 发送登陆请求消息
	public void sendLoginReq(String uuid) {
		// TODO Auto-generated method stub
		Request req = Request.newBuilder()
							.setLoginReq(LoginReq.newBuilder()
												.setUuid(uuid)
												.build())
							.build();
		byte[] reqBytePacket = req.toByteArray();
		
		MsgPacket msgPacket = new MsgPacket();
		msgPacket.msgId = Msg.Login_Req_VALUE;
		msgPacket.msgLen = reqBytePacket.length + 8;
		msgPacket.msgData = reqBytePacket;
		GM.instance().getNet().sendMsg(msgPacket);
	}
	
	
	// 收到登陆响应消息
	private void onLoginRes(MsgPacket msgPacket) {
		// TODO Auto-generated method stub
		GM.instance().logD("收到登陆响应消息");
		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			if (res.getResult() == true) {
				GM.instance().logD("登陆成功");
			} else {
				GM.instance().logD("登陆失败");
				GM.instance().logD("错误描述信息: " + res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 发送断线重连消息
	public void sendReconnectReq(String uuid) {
		// TODO Auto-generated method stub
		Request req = Request.newBuilder()
							.setReconnectReq(ReconnectReq.newBuilder()
												.setUuid(uuid)
												.build())
							.build();
		byte[] reqBytePacket = req.toByteArray();
		
		MsgPacket msgPacket = new MsgPacket();
		msgPacket.msgId = Msg.Reconnect_Req_VALUE;
		msgPacket.msgLen = reqBytePacket.length + 8;
		msgPacket.msgData = reqBytePacket;
		GM.instance().getNet().sendMsg(msgPacket);
	}

	
	// 收到端线重连响应消息
	private void onReconnectRes(MsgPacket msgPacket) {
		// TODO Auto-generated method stub
		GM.instance().logD("收到断线重连响应消息");
		try {
			Response res = Response.parseFrom(msgPacket.msgData);
			if (res.getResult() == true) {
				GM.instance().logD("重新连接成功");
			} else {
				GM.instance().logD("重新连接失败");
				GM.instance().logD("错误描述信息: " + res.getErrorDescribe());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// 发送离开当前游戏请求
	public void sendLeaveReq(){
		
	}
	
	// 处理离开当前游戏响应详细
	private void onLeaveRes(){
		
	}
	
	// 处理对手离开游戏通知，当前游戏直接胜利
	private void onPeerLeaveNoti() {
		// TODO Auto-generated method stub
		GM.instance().logD("对手离开游戏，You Win!!!");
	}
	
	
	// 处理开始游戏通知
	private void onStartGameNoti() {
		// TODO Auto-generated method stub
		GM.instance().logD("开始游戏");
	}
	
	// 发送转发消息包，将请求包包装一次再发送
	public void sendRouteMsg(){
		
	}
	
	// 处理转发消息包，解一次包，再处理具体消息
	private void onRouteMsg() {
		// TODO Auto-generated method stub
		GM.instance().logD("转发消息");
	}
	
	




}
