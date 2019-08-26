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

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPRequirement;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPType;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPTypesList;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPWorkPackage;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPWorkPackageList;
import org.qrapids.backlog.openproject.service.data.Milestone;
import org.qrapids.backlog.openproject.service.data.Phase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class OpenProjectServiceProxy implements IOpenProjectServiceProxy {

	@Value("${openproject.url}")
	private String url;

	@Value("${openproject.apikey}")
	private String apikey;

	@Value("${openproject.requirement.type}")
	private String requirementType;

	@Value("${openproject.project.name}")
	private String projectName;

	@Override
	public String generateQualityRequirement(OPRequirement requirement,String projectId) throws Exception {
		OPType type = getTypeId(requirementType);
		if (type != null) {
			requirement.setType(type);
		}
		
		if(projectId == null || "".equals(projectId)) {
			projectId = projectName;
		}

		String plainCreds = "apikey:" + apikey;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();

		ObjectMapper mapper = new ObjectMapper();

		String val = mapper.writeValueAsString(requirement);
		HttpEntity<String> request = new HttpEntity<String>(val, headers);
		OPRequirement response = restTemplate.postForObject(url + "/api/v3/projects/" + projectId + "/work_packages", request,OPRequirement.class);
		if(response.getId() != null) {
			return response.getId();
		}	
		throw new Exception("The quality requerement has not bean generated");
	}

	private OPType getTypeId(String type) {
		String plainCreds = "apikey:" + apikey;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<OPTypesList> response = restTemplate.exchange(url + "/api/v3/types", HttpMethod.GET, request,
				OPTypesList.class);
		OPTypesList typeListe = response.getBody();

		return typeListe.getOPType(type);
	}

	@Override
	public List<OPWorkPackage> getMilestones(String project_id, String from_date) {
		
		if(project_id == null || "".equals(project_id)) {
			project_id = projectName;
		}

		String plainCreds = "apikey:" + apikey;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<OPWorkPackageList> response = restTemplate.exchange(url + "/api/v3/projects/" + project_id + "/work_packages",HttpMethod.GET, request,OPWorkPackageList.class);

		OPWorkPackageList wpListe = response.getBody();
		
		if(response != null && wpListe.get_embedded() != null) {
			List<OPWorkPackage> result = new ArrayList<>();
			for(OPWorkPackage element :  wpListe.get_embedded().getElements()) {
				if(element.get_links() != null && "Milestone".equals(element.get_links().getType().getTitle())){
					if(from_date != null) {
						if(element.getDate().compareTo("from_date") > 0 ) {
							result.add(element);
						}
						
					}else {
						result.add(element);
					}
				}
			}
			return result;
		}
		return null;
	}

	@Override
	public List<OPWorkPackage> getPhases(String project_id) {
		if(project_id == null || "".equals(project_id)) {
			project_id = projectName;
		}

		String plainCreds = "apikey:" + apikey;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<OPWorkPackageList> response = restTemplate.exchange(url + "/api/v3/projects/" + project_id + "/work_packages",HttpMethod.GET, request,OPWorkPackageList.class);

		OPWorkPackageList wpListe = response.getBody();
		if(response != null && wpListe.get_embedded() != null) {
			List<OPWorkPackage> result = new ArrayList<>();
			for(OPWorkPackage element :  wpListe.get_embedded().getElements()) {
				if(element.get_links() != null && "Phase".equals(element.get_links().getType().getTitle())){
					result.add(element);
				}
			}
			return result;
		}
		return null;
	}
}
