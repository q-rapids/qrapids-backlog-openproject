 /*

Copyright 2018 Softeam

 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/
package org.qrapids.backlog.openproject.service.data;

public class SuccessResponse {
	
	private String issue_id;
	private String issue_url;
	
	public SuccessResponse(String issue_id,String issue_url) {
		this.issue_id = issue_id;
		this.issue_url = issue_url;
	}
	
	public String getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}
	public String getIssue_url() {
		return issue_url;
	}
	public void setIssue_url(String issue_url) {
		this.issue_url = issue_url;
	}
}
