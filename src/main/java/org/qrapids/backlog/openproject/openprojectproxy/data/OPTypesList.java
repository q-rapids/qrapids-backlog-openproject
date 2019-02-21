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

public class OPTypesList {

	private TEmbeded _embedded;

	public TEmbeded get_embedded() {
		return _embedded;
	}

	public void set_embedded(TEmbeded _embedded) {
		this._embedded = _embedded;
	}

	public OPType getOPType(String typeName) {
		if (_embedded != null) {
			for (TElement element : _embedded.getElements()) {
				if (element.getName().equals(typeName)) {
					OPType type = new OPType();
					type.setHref("/api/v3/types/" + element.getId());
					type.setTitle(typeName);
					return type;
				}
			}
		}
		return null;
	}

}
