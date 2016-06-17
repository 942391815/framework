package com.test.java.listener;

import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ZkListener extends AbstractListener{
	@Value("${host}")
	private String path;
	public ZkListener() throws InterruptedException{
		setPath(path);
		setClient(CuratorFrameworkFactory.newClient(path, new RetryNTimes(10, 5000)));
		listener();
	}
	@Override
	public void dataChange(String dataPath, byte[] data) {
		PropertiesHolder.map.put(dataPath, new String(data));
	}
	@Override
	public void dataDelete(String dataPath) {
		PropertiesHolder.map.remove(dataPath);
	}
	@Override
	public void dataAdd(String dataPath, byte[] data) {
		dataChange(dataPath, data);
	}
}
