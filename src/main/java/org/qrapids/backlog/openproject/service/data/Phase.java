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

public class Phase {
	
	private String date_from;
	private String date_to;
	private String phase_name;
	private String phase_description;
	
	public String getDate_from() {
		return date_from;
	}
	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}
	public String getDate_to() {
		return date_to;
	}
	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}
	public String getPhase_name() {
		return phase_name;
	}
	public void setPhase_name(String phase_name) {
		this.phase_name = phase_name;
	}
	public String getPhase_description() {
		return phase_description;
	}
	public void setPhase_description(String phase_description) {
		this.phase_description = phase_description;
	}
}
