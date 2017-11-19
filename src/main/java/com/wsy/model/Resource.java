package com.wsy.model;

import com.wsy.model.base.BaseResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Resource extends BaseResource<Resource> {
	public static final Resource dao = new Resource().dao();

	private int level = 1;

	private List<Resource> children = new ArrayList<>();

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}
}