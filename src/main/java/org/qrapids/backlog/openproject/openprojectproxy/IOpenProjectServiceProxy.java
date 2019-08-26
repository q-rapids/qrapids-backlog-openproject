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
package org.qrapids.backlog.openproject.openprojectproxy;

import java.util.List;

import org.qrapids.backlog.openproject.openprojectproxy.data.OPRequirement;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPWorkPackage;
import org.springframework.stereotype.Service;

@Service
public interface IOpenProjectServiceProxy {

	public String generateQualityRequirement(OPRequirement requirement,String project_id) throws Exception;

	public List<OPWorkPackage> getMilestones(String project_id, String date);

	public List<OPWorkPackage>  getPhases(String project_id);
 
}
