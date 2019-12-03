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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.qrapids.backlog.openproject.openprojectproxy.IOpenProjectServiceProxy;
import org.qrapids.backlog.openproject.openprojectproxy.data.OPWorkPackage;
import org.qrapids.backlog.openproject.service.data.ErrorResponse;
import org.qrapids.backlog.openproject.service.data.Milestone;
import org.qrapids.backlog.openproject.service.data.Phase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class ProjectPlanService {

	@Inject
	private IOpenProjectServiceProxy serviceProxy;

	@GetMapping("/milestones")
	public ResponseEntity<Object> getMilestones(@RequestParam String project_id,
			@RequestParam(value = "date_from", required = false) String date_from) {
		try {
			List<Milestone> milestones = new ArrayList<>();
			for (OPWorkPackage wpMilestone : serviceProxy.getMilestones(project_id, null)) {
				Milestone newMilestone = new Milestone();
				newMilestone.setName(wpMilestone.getSubject());
				newMilestone.setDate(wpMilestone.getDate());
				newMilestone.setDescription(wpMilestone.getDescription().getRaw());
				newMilestone.setType("Milestone");
				milestones.add(newMilestone);
			}

			Collections.sort(milestones, (Milestone o1, Milestone o2) -> o2.getDate().compareTo(o1.getDate()));

			return new ResponseEntity<>(milestones, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/phases")
	public ResponseEntity<Object> getPhases(@RequestParam String project_id,@RequestParam(value = "date_from", required = false) String date_from) {
		try {
			List<Phase> phases = new ArrayList<>();
			for (OPWorkPackage wpPhase : serviceProxy.getPhases(project_id, null)) {
				Phase newPhase = new Phase();
				newPhase.setName(wpPhase.getSubject());
				newPhase.setDateFrom(wpPhase.getStartDate());
				newPhase.setDescription(wpPhase.getDescription().getRaw());
				newPhase.setDateTo(wpPhase.getDueDate());
				phases.add(newPhase);
			}
			return new ResponseEntity<>(phases, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
