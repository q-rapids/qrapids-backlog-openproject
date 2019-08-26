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
package org.qrapids.backlog.openproject.openprojectproxy.data;

import java.io.Serializable;

public class OPWorkPackage  implements Serializable{
	private String id = "";
	private String subject = "";
	private String date;
	
	private String startDate;
	private String dueDate;
	private OPDescription description;
	private OPLink _links;

	public OPWorkPackage() {
		this.description = new OPDescription();
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public OPLink get_links() {
		return _links;
	}

	public void set_links(OPLink _links) {
		this._links = _links;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public OPDescription getDescription() {
		return description;
	}

	public void setDescription(OPDescription description) {
		this.description = description;
	}

}
