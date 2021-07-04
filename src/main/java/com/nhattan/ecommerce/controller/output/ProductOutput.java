package com.nhattan.ecommerce.controller.output;

import java.util.ArrayList;
import java.util.List;

import com.nhattan.ecommerce.response.ProductResponse;

public class ProductOutput {
	private int page;
	private int totalPage;
	private List<ProductResponse> listResult = new ArrayList<>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<ProductResponse> getListResult() {
		return listResult;
	}

	public void setListResult(List<ProductResponse> listResult) {
		this.listResult = listResult;
	}

}
