package com.test.java.listener;

public interface HandleData {
	public void dataChange(String dataPath, byte[] data);
	public void dataDelete(String dataPath);
	public void dataAdd(String dataPath, byte[] data);
}
