/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.fraud;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;

public class YmlFraudBase {

	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new FraudDetectionController(),
				new FraudStatsController(stubbedStatsProvider()));
	}

	private StatsProvider stubbedStatsProvider() {
		return fraudType -> {
			switch (fraudType) {
			case DRUNKS:
				return 100;
			case ALL:
				return 200;
			}
			return 0;
		};
	}

	public void assertThatRejectionReasonIsNull(Object rejectionReason) {
		assert rejectionReason == null;
	}

}
