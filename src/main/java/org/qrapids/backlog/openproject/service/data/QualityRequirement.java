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

public class QualityRequirement {
	
	private String issue_summary;
	private String issue_description;
	private String issue_type;
	private String project_id;
	private String decision_rationale;

	public String getIssue_summary() {
		return issue_summary;
	}
	public void setIssue_summary(String issue_summary) {
		this.issue_summary = issue_summary;
	}
	public String getIssue_description() {
		return issue_description;
	}
	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getDecision_rationale() {
		return decision_rationale;
	}
	public void setDecision_rationale(String decision_rationale) {
		this.decision_rationale = decision_rationale;
	}
	
	
}
