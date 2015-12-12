package com.test.java;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkListener {
	
	private ZkClient client;
	
	private String path;
	
	private HandleData handleData;
	
	public void listenerChange(){
		client.subscribeDataChanges(path, new IZkDataListener(){

			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				handleData.dataChange(dataPath, data);
			}
			public void handleDataDeleted(String dataPath) throws Exception {
				handleDataDeleted(dataPath);
			}
		});
	}
	public ZkClient getClient() {
		return client;
	}
	public void setClient(ZkClient client) {
		this.client = client;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public HandleData getHandleData() {
		return handleData;
	}
	public void setHandleData(HandleData handleData) {
		this.handleData = handleData;
	}
}
