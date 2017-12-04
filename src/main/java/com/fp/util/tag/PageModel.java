package com.fp.util.tag;

/**
 * 
 * @author Higer
 *
 */
public class PageModel {
	
	/** 当前页面 */
	private int pageIndex ;
	
	/** 每页的数据数目 */
	private int pageSize = 4;
	
	/** 总的记录条数：需要分页的数据总数   */
	private  int recordCount;

	public int getPageIndex() {
		
		/** 不能超过总页码  : 如果超过总页码给最后一页*/
		pageIndex = pageIndex > this.getTotalSize() ? this.getTotalSize() : pageIndex;
		
		return pageIndex <= 0 ? 1 : pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize <= 1 ? 1 : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount < 0 ? 0 : recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalSize() {
		return (this.getRecordCount() - 1 ) / this.getPageSize() + 1;
	}
	
	/** 
	 *  4 
	 *  1 4   4   limit 0 , 4
	 *  2 4   8   limit 4 , 4
	 *  3     9   (3-1)*4  limit 8 , 4
	 *  limit ? , ?
	 *  第一个？的值 
	 * */
	 public int getLimitFirstParam(){
		 return (this.getPageIndex() -1)*this.getPageSize();
	 }
	
	
}







