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
package org.qrapids.backlog.openproject.service;

import javax.inject.Inject;

import org.qrapids.backlog.openproject.openprojectproxy.IOpenProjectServiceProxy;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPRequirement;
import org.qrapids.backlog.openproject.service.data.ErrorResponse;
import org.qrapids.backlog.openproject.service.data.QualityRequirement;
import org.qrapids.backlog.openproject.service.data.SuccessResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api")
public class RequirementGeneratorService {
	
	private static final String WORKPACKAGE_URL = "/projects/modeliong/work_packages/ID_PLACEOLDER/activity";
	
	@Value("${openproject.url}")
	private String url;
	
	@Inject
	private IOpenProjectServiceProxy serviceProxy;
	
	@PostMapping("/addToBacklog")
	public ResponseEntity<Object> addToBacklog(@RequestBody QualityRequirement requirement) {	
		
		try {
			OPRequirement opRequirement = new OPRequirement();
			opRequirement.setSubject(requirement.getIssue_summary());
			opRequirement.getDescription().setRaw(requirement.getIssue_description() + " Rational:" + requirement.getDecision_rationale());
			opRequirement.getDescription().setFormat("markdown");
			opRequirement.getDescription().setHtml("<p>" + requirement.getIssue_description() +"</p> <p>" +requirement.getDecision_rationale()+"</p>");
			
			String workpackageId = serviceProxy.generateQualityRequirement(opRequirement,requirement.getProject_id());

			String workPackageUrl = url + WORKPACKAGE_URL.replaceAll("ID_PLACEOLDER", workpackageId);
			
			return new ResponseEntity<>(new SuccessResponse(workpackageId,workPackageUrl),HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

}
