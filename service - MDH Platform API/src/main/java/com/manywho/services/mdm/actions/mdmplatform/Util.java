package com.manywho.services.mdm.actions.mdmplatform;

import org.dom4j.Node;

public class Util {
	public static boolean isNullOrEmpty(String str)
	{
		return str == null || str.trim().isEmpty() || str.contentEquals("null");
	}
	
	public static String getSingleNode(Node node, String path)
	{
		node = node.selectSingleNode(path);
		if (node != null)
			return node.getText();
		return null;
	}
}
