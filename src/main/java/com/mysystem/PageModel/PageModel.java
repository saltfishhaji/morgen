package com.mysystem.PageModel;
import java.util.List;
public class PageModel<T> {
	private Integer pagestart = 0;
	private Integer pagesize = 5;
	private Integer pageNo = 1;
	private Integer totalpage = 0;
	private Integer totalrecode = 0;
	private Integer uid=-1;
	private Integer priority=-1;
	private Integer point=-1;
	private List<T> datas;
	private T data;
	private String username="";
	private String stime="";
	private String param;
	private Integer thre;
	private String conf;
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPagestart() {
		return pagestart = (pageNo - 1) * pagesize;
	}

	public void setPagestart(Integer pagestart) {
		this.pagestart = pagestart;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalpage() {
		return totalpage = (totalrecode + pagesize - 1) / pagesize;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}

	public Integer getTotalrecode() {
		return totalrecode;
	}

	public void setTotalrecode(Integer totalrecode) {
		this.totalrecode = totalrecode;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getThre() {
		return thre;
	}

	public void setThre(Integer thre) {
		this.thre = thre;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}
}
