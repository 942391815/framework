package com.test.java.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

public abstract class AbstractListener implements HandleData{
	private String path;
	private CuratorFramework client;
	public void listener() throws InterruptedException{
		client.start();
		PathChildrenCache listener = new PathChildrenCache(client, path, true);
		listener.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework context, PathChildrenCacheEvent event) throws Exception {
				String path = event.getData().getPath();
				switch(event.getType()){
				case CHILD_ADDED:
					dataAdd(path, event.getData().getData());
					break;
				case CHILD_REMOVED:
					dataDelete(path);
					break;
				case CHILD_UPDATED: 
					dataChange(path, event.getData().getData());
					break;
				default:break;
				}
			}
		});
//		Thread.sleep(Integer.MAX_VALUE);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public CuratorFramework getClient() {
		return client;
	}
	public void setClient(CuratorFramework client) {
		this.client = client;
	}
}
