package com.kongyt.civilization.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.kongyt.civilization.managers.GM;


public class Net{
	
    private boolean isConnected = false;
    private Socket socket = null;
	private OutputStream outputStream;
	private InputStream inputStream;
	private boolean isRunning = true;
	
	private Queue<MsgPacket> msgQueue = new LinkedBlockingQueue<MsgPacket>();
	
	public void destroy(){
		if(isRunning){
			stop();			
		}		
	}
	
	public void stop(){
		if (socket != null){
			try {
				socket.shutdownOutput();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isConnected = false;
	}
	
	public void conn(final String ip, final int port){
		Thread t = new Thread(){
			
			public void postMsg(MsgPacket msgPacket){
				Net.this.msgQueue.add(msgPacket);
			}
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();				
				
				byte[] recvBuffer = new byte[1024 * 16];
				int recvDataLen = 0;
				try {
					GM.instance().logD("尝试连接服务器");
					socket = new Socket(ip, port);
					outputStream = socket.getOutputStream();
					inputStream = socket.getInputStream();
					
					isConnected = true;
					
					GM.instance().logD("连接服务器成功("+ip +":"+ port + ")" );
					
					byte [] recv = new byte[1024];
					
					while(isRunning){						
						int len = inputStream.read(recv);
						if(len > 0){							
							if((recvBuffer.length - recvDataLen) < len) 
								throw new RuntimeException("接收服务器数据异常");
							System.arraycopy(recv, 0, recvBuffer, recvDataLen, len);
						
							recvDataLen += len;
							if( recvDataLen >= 8){	
								int dataLen = 0;
								int msgId = 0;
								
								byte[] msgIdByte = new byte[4];
								byte[] dataLenByte = new byte[4];
								System.arraycopy(recvBuffer, 0, msgIdByte, 0, 4);
								msgId = byteArray2Int(msgIdByte);
								System.arraycopy(recvBuffer, 4, dataLenByte, 0, 4);								
								dataLen = byteArray2Int(dataLenByte);								
								if(dataLen <= recvDataLen){
									byte[] packetData = new byte[dataLen-8];
									System.arraycopy(recvBuffer, 8, packetData, 0, dataLen-8);
									if(recvDataLen > dataLen){
										System.arraycopy(recvBuffer, dataLen, recvBuffer, 0, recvDataLen-dataLen);
									}
									recvDataLen -= dataLen;									
									
									 MsgPacket msgPacket = new MsgPacket();
						             msgPacket.msgId = msgId;
						             msgPacket.msgLen = dataLen;
						             msgPacket.msgData = packetData;
						             this.postMsg(msgPacket);
								}
							}
						}else{
							isRunning = false;
						}
					}
					socket.close();
					socket = null;
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		t.start();
		
	}
	
	// 发送消息
	public void sendMsg(MsgPacket msgPacket){
		try {
			outputStream.write(msgPacket.toBytes());
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// 注册消息模块
	public void registerModule(BaseMsgModule module){
		GM.instance().logD("注册消息模块句柄moduleId=" + Integer.toHexString(module.moduleId));
		this.modules.put(module.moduleId, module);
	}
	
	private Map<Integer, BaseMsgModule> modules = new HashMap<Integer, BaseMsgModule>();
	
	
	// 
	public void dispatchMsg(){
		if (!this.msgQueue.isEmpty()){
			MsgPacket msgPacket = this.msgQueue.poll();
			int moduleId = msgPacket.msgId & 0xFFFF0000;
			if(modules.containsKey(moduleId)){
				modules.get(moduleId).onReceiveMsg(msgPacket);
			}else{
				GM.instance().logD("未知消息类型");
			}
		}
	}    
	
    // 4字节数组转int
	public static int byteArray2Int(byte[] bts){
		int rs = 0;
		if(bts.length >= 4){
			int n1 = bts[0];
			int n2 = bts[1];
			int n3 = bts[2];
			int n4 = bts[3];
			n1 = n1 << 24;
			n2 = 0x00FF0000 & n2 << 16;
			n3 = 0x0000FF00 & n3 << 8;
			n4 = 0x000000FF & n4;
			rs = n1|n2|n3|n4;
		}		
		return rs;		
	}
	
	// int转4字节数组
	public static byte[] int2ByteArray(int n){
		byte[] rs = new byte[4];
		rs[0] = (byte)(n >> 24);
		rs[1] = (byte)((n >> 16)&0xFF);
		rs[2] = (byte)((n >> 8)&0xFF);
		rs[3] = (byte)(n&0xFF);		
		return rs;
		
	}
}
