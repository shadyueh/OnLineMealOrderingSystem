package com.wangyanci.pojo;

import java.util.List;

public class PageDish {
	private int page; // 页码
	private int rows; // 每页条数
	private int totalPage; // 总页数
	private int totalCount; // 总条数
	private List<Dish> dishs; // 每页数据

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Dish> getDishs() {
		return dishs;
	}

	public void setDishs(List<Dish> dishs) {
		this.dishs = dishs;
	}

	@Override
	public String toString() {
		return "PageDish [page=" + page + ", rows=" + rows + ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ ", dishs=" + dishs + "]";
	}

}
