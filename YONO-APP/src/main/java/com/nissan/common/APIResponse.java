package com.nissan.common;

import org.springframework.stereotype.Component;

@Component
public class APIResponse {
	//instance varibales
		private Integer status; 
		private Object data;
		private Object error;
		public APIResponse() {
				
		}
		public APIResponse(Integer status, Object data, Object eror) {
			super();
			this.status = status;
			this.data = data;
			this.error = eror;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public Object getEror() {
			return error;
		}
		public void setEror(Object eror) {
			this.error = eror;
		}
}
